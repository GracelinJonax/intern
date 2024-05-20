package com.example.bookingservice.Service.Impl;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.*;
import com.example.bookingservice.Repository.*;
import com.example.bookingservice.Repository.Service.*;
import com.example.bookingservice.Service.bookingService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class bookingServiceImpl implements bookingService {
    @Value("${keyId}")
    private String keyid;
    @Value("${secret_Key}")
    private String secretkey;
    private final JourneyRepository journeyRepository;
    private final JourneyRepoService journeyRepoService;
    private final BusDetailsRepository busDetailsRepository;
    private final BusDetailsRepoService busDetailsRepoService;
    private final BookingDetailsRepoService bookingDetailsRepoService;
    private final BookingDetailsRepository bookingDetailsRepository;
    private final PassengersRepository passengersRepository;
    private final PassengersRepoService passengersRepoService;
    private final PaymentRepository paymentRepository;
    private final PaymentRepoService paymentRepoService;
    private final UserDetailsRepository userDetailsRepository;
    private final ModelMapper modelMapper;
    private final LayoutRepository layoutRepository;
    private final RefundRepository refundRepository;
    private final OffersRepository offersRepository;
    private final RewardRepository rewardRepository;
    private final RewardRepoService rewardRepoService;
    private final LinksRepository linksRepository;

    public bookingServiceImpl(JourneyRepository journeyRepository, BusDetailsRepository busDetailsRepository, LayoutRepository layoutRepository,
                              ModelMapper modelMapper, BusDetailsRepoService busDetailsRepoService, JourneyRepoService journeyRepoService,
                              BookingDetailsRepoService bookingDetailsRepoService, PassengersRepository passengersRepository, PassengersRepoService passengersRepoService,
                              UserDetailsRepository userDetailsRepository, BookingDetailsRepository bookingDetailsRepository, PaymentRepository paymentRepository,
                              PaymentRepoService paymentRepoService, RefundRepository refundRepository, OffersRepository offersRepository,
                              RewardRepository rewardRepository, RewardRepoService rewardRepoService, LinksRepository linksRepository) {
        this.journeyRepository = journeyRepository;
        this.journeyRepoService = journeyRepoService;
        this.busDetailsRepository = busDetailsRepository;
        this.busDetailsRepoService = busDetailsRepoService;
        this.bookingDetailsRepository = bookingDetailsRepository;
        this.bookingDetailsRepoService = bookingDetailsRepoService;
        this.passengersRepoService = passengersRepoService;
        this.passengersRepository = passengersRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.layoutRepository = layoutRepository;
        this.paymentRepository = paymentRepository;
        this.paymentRepoService = paymentRepoService;
        this.refundRepository = refundRepository;
        this.modelMapper = modelMapper;
        this.offersRepository = offersRepository;
        this.rewardRepository = rewardRepository;
        this.rewardRepoService = rewardRepoService;
        this.linksRepository = linksRepository;
    }

    @Override
    public void saveBusService(BusDto busDto) {
        List<Journey> journeys = new ArrayList<>();
        busDto.getBusDetails().getJourneys().forEach(a -> {
            Journey journey = journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(a.getBoardingPoint(), a.getEndPoint(), a.getStartTime(), a.getEndTime());
            if (journey == null)
                journeys.add(journeyRepository.save(a));
            else
                journeys.add(journey);
        });
        BusDetails busDetails = busDto.getBusDetails();
        busDetails.setJourneys(journeys);
        int[][][] busLayout = busDto.getLayout();
        int totalSpace = busLayout[busLayout.length - 1][busLayout[1].length - 1][busLayout[1][0].length - 1];
        System.out.println(busLayout.length + "  hai");
        System.out.println(busLayout[1].length + "  lll");
        System.out.println(busLayout[1][0].length + " kkk");
        busDetails.setTotalSeats(totalSpace);
        BusDetails bus = busDetailsRepository.save(busDetails);
        Layout layout = new Layout();
        layout.setId(bus.getId());
        layout.setLayout(busLayout);
        layoutRepository.save(layout);
    }

    @Override
    public List<BusDetails> getAllBusService(TravelDto travelDto) {
        if (journeyRepoService.findByBoardingPointAndEndPoint(travelDto.getBoardingPoint(), travelDto.getEndPoint()).isEmpty())
            return null;
        return busDetailsRepoService.findByJourneysBoardingPointAndJourneysEndPoint(travelDto.getBoardingPoint(), travelDto.getEndPoint());
    }

    @Override
    public LayoutDto getBusLayoutService(GetLayoutDto getLayoutDto) {
        int[][][] layout = layoutRepository.findById(getLayoutDto.getBusId()).get().getLayout();
        Journey journey = journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(getLayoutDto.getJourney().getBoardingPoint(), getLayoutDto.getJourney().getEndPoint(), getLayoutDto.getJourney().getStartTime(), getLayoutDto.getJourney().getEndTime());
        System.out.println(journey + " hjkl");
        List<BookingDetails> bookingDetailsList = bookingDetailsRepoService.findAllByTravelDateAndJourney(getLayoutDto.getJourney().getTravelDate(), journey);
        for (BookingDetails bookings : bookingDetailsList) {
            List<Passengers> passengersList = passengersRepoService.findByBookingDetailsAndSeatStatus(bookings, "blocked");
            System.out.println(passengersList + " blocked");
            passengersList.addAll(passengersRepoService.findByBookingDetailsAndSeatStatus(bookings, "booked"));
            System.out.println(passengersList + " booked");
            for (Passengers passengers : passengersList) {
                String[] seat = passengers.getSeatNo().split(" ")[0].split("-");
                System.out.println(Arrays.stream(seat).toList() + "  seats");
                layout[Integer.parseInt(seat[0])][Integer.parseInt(seat[1])][Integer.parseInt(seat[2])] = 0;
            }
        }
        System.out.println(Arrays.deepToString(layout) + "  layout");
        LayoutDto layoutDto = new LayoutDto();
        layoutDto.setLayout(layout);
        layoutDto.setSeat(busDetailsRepository.findById(getLayoutDto.getBusId()).get().getSeat());
        return layoutDto;
    }

    @Override
    public String blockSeatService(BlockDto blockDto) {
//        Journey journey=busDetailsRepoService.findByIdAndJourneysBoardingPointAndJourneysEndPoint(blockDto.getBusId(),blockDto.getJourney().getBoardingPoint(),blockDto.getJourney().getEndPoint());
        Journey journey = journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(blockDto.getJourney().getBoardingPoint(), blockDto.getJourney().getEndPoint(), blockDto.getJourney().getStartTime(), blockDto.getJourney().getEndTime());
        System.out.println(journey + " jou");
        BookingDetails bookingDetails = new BookingDetails();
//        modelMapper.map(blockDto, bookingDetails);
        bookingDetails.setEmail(blockDto.getEmail());
        bookingDetails.setMobileNo(blockDto.getMobileNo());
        bookingDetails.setBusId(blockDto.getBusId());
        bookingDetails.setTravelDate(blockDto.getJourney().getTravelDate());
        bookingDetails.setJourney(journey);
        bookingDetails.setUser(userDetailsRepository.findById(blockDto.getUserId()).get());
        bookingDetails = bookingDetailsRepository.save(bookingDetails);
        for (Passengers passengers : blockDto.getPassengersList()) {
            Passengers passenger = new Passengers();
            modelMapper.map(passengers, passenger);
            passenger.setSeatStatus("blocked");
            passenger.setBookingDetails(bookingDetails);
            passengersRepository.save(passenger);
        }
        return bookingDetails.getId();
    }

    @Override
    public String saveUserService(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "user details are saved";
    }

    @Override
    public RewardDto makePayment(PaymentDto paymentDto) {
        System.out.println(keyid+"   key");
        System.out.println(secretkey+"  secret");
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyid, secretkey);
            Payment payment = new Payment();
            modelMapper.map(paymentDto, payment);
            BookingDetails bookingDetails = bookingDetailsRepository.findById(paymentDto.getBookId()).get();
            payment.setBookingDetails(bookingDetails);
            String pnr = UUID.randomUUID().toString().substring(0, 6);
            payment.setPNR(pnr);
            JSONObject payOrder = new JSONObject();
            payOrder.put("amount", paymentDto.getAmount() * 100);
            payOrder.put("currency", "INR");
            payOrder.put("receipt", paymentDto.getBookId());
            Order order = razorpayClient.orders.create(payOrder);
            payment.setPaymentId(order.get("id"));

            payment = paymentRepository.save(payment);
            TicketDto ticket = new TicketDto();
            ticket.setPayment(payment);
            List<Passengers> passengers = passengersRepoService.findByBookingDetailsAndSeatStatus(bookingDetails, "blocked");
            passengers.forEach(a -> a.setSeatStatus("booked"));
            passengersRepository.saveAll(passengers);

            List<Integer> seats = new ArrayList<>();
            passengers.forEach(a -> seats.add(Integer.parseInt(a.getSeatNo().split(" ")[1])));
            System.out.println(seats);
            ticket.setSeats(seats);
            ticket.setBusName(busDetailsRepository.findById(bookingDetails.getBusId()).get().getName());
            RewardDto rewardDto = new RewardDto();
            rewardDto.setReward(findReward(bookingDetails.getUser()));
            rewardDto.setTicket(ticket);
            return rewardDto;
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

    Reward findReward(UserDetails user) {
        int type = new Random().nextInt(3);
        Offers selectedReward = new Offers();
        if (type == 1)
            selectedReward = offersRepository.findRandom("code");
        if (type == 2)
            selectedReward = offersRepository.findRandom("cash");
        if (type == 0)
            selectedReward = offersRepository.findRandom("link");
        Reward reward = new Reward();
        reward.setOffers(selectedReward);
        if (type == 0) {
            Links links = linksRepository.getFirstByPublishIsNot("published");
            reward.setLinkId(links.getId());
            links.setPublish("published");
            linksRepository.save(links);
        }
        reward.setStatus("assigned");
        reward.setScratch(false);
        reward.setUserDetails(user);
        LocalDate date = LocalDate.now();
        reward.setValidDate(date.plusDays(selectedReward.getValidity()));
        reward = rewardRepository.save(reward);
//        Base64.Encoder encoder = Base64.getEncoder();
//        String coupon = encoder.encodeToString((selectedReward.getInformation() + " expiry date: " + reward.getValidDate()).getBytes());
        return reward;
    }

    @Override
    public String cancelBookingSerice(CancelDto cancelDto) {
        BookingDetails booking = paymentRepoService.findByPNR(cancelDto.getPnr()).getBookingDetails();
        List<BusDetails.Seat> seats = busDetailsRepository.findById(booking.getBusId()).get().getSeat();
        List<Passengers> passengers = passengersRepoService.findByBookingDetails(booking);
        double amount = 0;
        for (Passengers passenger : passengers) {
            int seat = Integer.parseInt(passenger.getSeatNo().split(" ")[1]);
            if (cancelDto.getSeats().contains(seat)) {
                if (seat <= Integer.parseInt(seats.get(0).getSeatRange().split("-")[1])) {
                    if (passenger.getAge() > 12)
                        amount += seats.get(0).getPrice().getAdultPrice();
                    else
                        amount += seats.get(0).getPrice().getChildPrice();
                } else {
                    if (passenger.getAge() > 12)
                        amount += seats.get(1).getPrice().getAdultPrice();
                    else
                        amount += seats.get(1).getPrice().getChildPrice();
                }
                passenger.setSeatStatus("cancelled");
                passengersRepository.save(passenger);
                cancelDto.getSeats().remove(cancelDto.getSeats().indexOf(seat));
                System.out.println(cancelDto.getSeats() + "  seats");
            }
        }
        Refund refund = new Refund();
        refund.setAmount(amount / 2);
        refund.setPayment(paymentRepository.findByPNR(cancelDto.getPnr()));
        refundRepository.save(refund);
        return "seats cancelled and amount refund";
    }

    @Override
    public List<Offers> saveAllRewards(Offers offers) {
        if (offers.getType().equals("code"))
            offers.setInformation(offers.getInformation() + " " + UUID.randomUUID());
        offersRepository.save(offers);
        return offersRepository.findAll();
    }

    @Override
    public List<Links> saveLinksService(Links links) {
        links.setPublish("new");
        linksRepository.save(links);
        return linksRepository.findAll();
    }

    @Override
    public OfferDto getRewardService(Long id) {
//        if(rewardRepository.existsById(id)
        Reward reward = rewardRepository.findById(id).get();
        if (reward.getStatus().equals("assigned")) {
            reward.setStatus("opened");
            reward.setScratch(true);
        } else if (reward.getStatus().equals("opened"))
            reward.setStatus("claimed");
        OfferDto offerDto = new OfferDto();
        rewardRepository.save(reward);
        modelMapper.map(reward, offerDto);
        offerDto.setInformation(reward.getOffers().getInformation());
        if (reward.getLinkId() != null)
            offerDto.setInformation(offerDto.getInformation() + linksRepository.findById(reward.getLinkId()).get().getLink());
        return offerDto;
    }

    @Override
    public List<OfferDto> getUserRewardService(String userId) {
        UserDetails userDetails = userDetailsRepository.findById(userId).get();
        List<OfferDto> offerList = rewardRepoService.findByUserDetails(userDetails).stream()
                .map(a -> {
                    OfferDto offer = new OfferDto();
                    modelMapper.map(a, offer);
                    offer.setInformation(a.getOffers().getInformation());
                    if (a.getLinkId() != null)
                        offer.setInformation(offer.getInformation() + linksRepository.findById(a.getLinkId()).get().getLink());
                    return offer;
                }).toList();
//        modelMapper.map(rewardRepoService.findByUserDetails(userDetails),offerList);
        return offerList;
    }

    @Scheduled(fixedDelay = 1000)
    public void unblock() {
        List<Passengers> passengers = passengersRepoService.findBySeatStatus("blocked");
        System.out.println(passengers);
        for (Passengers passenger : passengers) {
            long l = new Date().getTime() - passenger.getCreatedDate().getTime();
            System.out.println(l + "  block");
            if (l >= 1200000) {
                passenger.setSeatStatus("unblocked");
                passengersRepository.save(passenger);
            }
        }
        List<Reward> rewards = rewardRepoService.findByStatusIsNot("expired");
        for (Reward reward : rewards) {
//           ZonedDateTime z=ZonedDateTime.of(reward.getValidDate().plusDays(1), ZoneId.systemDefault());
//           long date = z.toInstant().toEpochMilli();
            java.sql.Date date = java.sql.Date.valueOf(reward.getValidDate().plusDays(1));
            long l = date.getTime() - new Date().getTime();
            System.out.println(l + " reward");
            if (l <= 0) {
                reward.setStatus("expired");
                rewardRepository.save(reward);
            }
        }
    }

}
