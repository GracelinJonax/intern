package com.example.bookingservice.service.impl;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.model.*;
import com.example.bookingservice.repository.*;
import com.example.bookingservice.repository.service.*;
import com.example.bookingservice.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    private static final String BLOCKED = "blocked";
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
    Random random = new Random();

    public BookingServiceImpl(JourneyRepository journeyRepository, BusDetailsRepository busDetailsRepository, LayoutRepository layoutRepository,
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
            journeys.add(Objects.requireNonNullElseGet(journey, () -> journeyRepository.save(a)));
        });
        BusDetails busDetails = busDto.getBusDetails();
        busDetails.setJourneys(journeys);
        int[][][] busLayout = busDto.getLayout();
        int totalSpace = busLayout[busLayout.length - 1][busLayout[1].length - 1][busLayout[1][0].length - 1];
        busDetails.setTotalSeats(totalSpace);
        BusDetails bus = busDetailsRepository.save(busDetails);
        Layout layout = new Layout();
        layout.setId(bus.getId());
        layout.setDefaultLayout(busLayout);
        layoutRepository.save(layout);
    }

    @Override
    public List<BusDetails> getAllBusService(TravelDto travelDto) {
        if (journeyRepoService.findByBoardingPointAndEndPoint(travelDto.getBoardingPoint(), travelDto.getEndPoint()).isEmpty())
            return Collections.emptyList();
        return busDetailsRepoService.findByJourneysBoardingPointAndJourneysEndPoint(travelDto.getBoardingPoint(), travelDto.getEndPoint());
    }

    @Override
    public LayoutDto getBusLayoutService(GetLayoutDto getLayoutDto) {
        int[][][] layout = layoutRepository.findById(getLayoutDto.getBusId()).get().getDefaultLayout();
        Journey journey = journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(getLayoutDto.getJourney().getBoardingPoint(), getLayoutDto.getJourney().getEndPoint(), getLayoutDto.getJourney().getStartTime(), getLayoutDto.getJourney().getEndTime());
        List<BookingDetails> bookingDetailsList = bookingDetailsRepoService.findAllByTravelDateAndJourney(getLayoutDto.getJourney().getTravelDate(), journey);
        for (BookingDetails bookings : bookingDetailsList) {
            List<Passengers> passengersList = passengersRepoService.findByBookingDetailsAndSeatStatus(bookings, BLOCKED);
            passengersList.addAll(passengersRepoService.findByBookingDetailsAndSeatStatus(bookings, "booked"));
            for (Passengers passengers : passengersList) {
                String[] seat = passengers.getSeatNo().split(" ")[0].split("-");
                layout[Integer.parseInt(seat[0])][Integer.parseInt(seat[1])][Integer.parseInt(seat[2])] = 0;
            }
        }
        LayoutDto layoutDto = new LayoutDto();
        layoutDto.setLayout(layout);
        layoutDto.setSeat(busDetailsRepository.findById(getLayoutDto.getBusId()).get().getSeat());
        return layoutDto;
    }

    @Override
    public String blockSeatService(BlockDto blockDto) {
        Journey journey = journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(blockDto.getJourney().getBoardingPoint(), blockDto.getJourney().getEndPoint(), blockDto.getJourney().getStartTime(), blockDto.getJourney().getEndTime());
        BookingDetails bookingDetails = new BookingDetails();
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
            passenger.setSeatStatus(BLOCKED);
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
        Payment payment = new Payment();
        modelMapper.map(paymentDto, payment);
        BookingDetails bookingDetails = bookingDetailsRepository.findById(paymentDto.getBookId()).get();
        payment.setBookingDetails(bookingDetails);
        String pnr = UUID.randomUUID().toString().substring(0, 6);
        payment.setPnr(pnr);
        payment.setPaymentType(paymentDto.getPaymentType());

        payment = paymentRepository.save(payment);
        TicketDto ticket = new TicketDto();
        ticket.setPayment(payment);
        List<Passengers> passengers = passengersRepoService.findByBookingDetailsAndSeatStatus(bookingDetails, BLOCKED);
        passengers.forEach(a -> a.setSeatStatus("booked"));
        passengersRepository.saveAll(passengers);

        List<Integer> seats = new ArrayList<>();
        passengers.forEach(a -> seats.add(Integer.parseInt(a.getSeatNo().split(" ")[1])));
        ticket.setSeats(seats);
        ticket.setBusName(busDetailsRepository.findById(bookingDetails.getBusId()).get().getName());
        RewardDto rewardDto = new RewardDto();
        rewardDto.setReward(findReward(bookingDetails.getUser()));
        rewardDto.setTicket(ticket);
        return rewardDto;
    }

    Reward findReward(UserDetails user) {
        int type = this.random.nextInt(3);
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
        return reward;
    }

    @Override
    public String cancelBookingService(CancelDto cancelDto) {
        BookingDetails booking = paymentRepoService.findByPnr(cancelDto.getPnr()).getBookingDetails();
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
                cancelDto.getSeats().remove((Integer) seat);
            }
        }
        Refund refund = new Refund();
        refund.setAmount(amount / 2);
        refund.setPayment(paymentRepository.findByPnr(cancelDto.getPnr()));
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
//rewardRepository.findById(id).ifPresent(reward -> rewardRepository.findById(id).get());
    @Override
    public OfferDto getRewardService(Long id) {
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
        return rewardRepoService.findByUserDetails(userDetails).stream()
                .map(a -> {
                    OfferDto offer = new OfferDto();
                    modelMapper.map(a, offer);
                    offer.setInformation(a.getOffers().getInformation());
                    if (a.getLinkId() != null)
                        offer.setInformation(offer.getInformation() + linksRepository.findById(a.getLinkId()).get().getLink());
                    return offer;
                }).toList();
    }

    @Scheduled(fixedDelay = 1000)
    public void unblock() {
        List<Passengers> passengers = passengersRepoService.findBySeatStatus(BLOCKED);
        for (Passengers passenger : passengers) {
            long l = new Date().getTime() - passenger.getCreatedDate().getTime();
            if (l >= 1200000) {
                passenger.setSeatStatus("unblocked");
                passengersRepository.save(passenger);
            }
        }
        List<Reward> rewards = rewardRepoService.findByStatusIsNot("expired");
        for (Reward reward : rewards) {
            java.sql.Date date = java.sql.Date.valueOf(reward.getValidDate().plusDays(1));
            long l = date.getTime() - new Date().getTime();
            if (l <= 0) {
                reward.setStatus("expired");
                rewardRepository.save(reward);
            }
        }
    }

}
