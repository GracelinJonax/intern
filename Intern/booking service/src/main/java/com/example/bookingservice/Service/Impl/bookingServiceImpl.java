package com.example.bookingservice.Service.Impl;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.*;
import com.example.bookingservice.Repository.*;
import com.example.bookingservice.Repository.Service.*;
import com.example.bookingservice.Service.bookingService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class bookingServiceImpl implements bookingService {
    private final JourneyRepository journeyRepository;
    private final JourneyRepoService journeyRepoService;
    private final BusDetailsRepository busDetailsRepository;
    private final BusDetailsRepoService busDetailsRepoService;
    private final BookingDetailsRepoService bookingDetailsRepoService;
    private final BookingDetailsRepository bookingDetailsRepository;
    private final  PassengersRepository passengersRepository;
    private final PassengersRepoService passengersRepoService;
    private final PaymentRepository paymentRepository;
    private final PaymentRepoService paymentRepoService;
    private final UserDetailsRepository userDetailsRepository;
    private final ModelMapper modelMapper;
    private final LayoutRepository layoutRepository;
    private final RefundRepository refundRepository;
    private final RewardInformationRepository rewardInformationRepository;
    private final RewardsRepository rewardsRepository;
    private final RewardsRepoService rewardsRepoService;

    public bookingServiceImpl(JourneyRepository journeyRepository, BusDetailsRepository busDetailsRepository,LayoutRepository layoutRepository,
        ModelMapper modelMapper, BusDetailsRepoService busDetailsRepoService, JourneyRepoService journeyRepoService,
         BookingDetailsRepoService bookingDetailsRepoService,PassengersRepository passengersRepository, PassengersRepoService passengersRepoService,
         UserDetailsRepository userDetailsRepository, BookingDetailsRepository bookingDetailsRepository,PaymentRepository paymentRepository,
         PaymentRepoService paymentRepoService,RefundRepository refundRepository,RewardInformationRepository rewardInformationRepository,
          RewardsRepository rewardsRepository,RewardsRepoService rewardsRepoService) {
        this.journeyRepository = journeyRepository;
        this.journeyRepoService = journeyRepoService;
        this.busDetailsRepository = busDetailsRepository;
        this.busDetailsRepoService = busDetailsRepoService;
        this.bookingDetailsRepository = bookingDetailsRepository;
        this.bookingDetailsRepoService = bookingDetailsRepoService;
        this.passengersRepoService = passengersRepoService;
        this.passengersRepository=passengersRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.layoutRepository = layoutRepository;
        this.paymentRepository=paymentRepository;
        this.paymentRepoService=paymentRepoService;
        this.refundRepository=refundRepository;
        this.modelMapper = modelMapper;
        this.rewardInformationRepository=rewardInformationRepository;
        this.rewardsRepository=rewardsRepository;
        this.rewardsRepoService=rewardsRepoService;
    }

    @Override
    public void saveBusService(BusDto busDto) {
        List<Journey> journeys = new ArrayList<>();
        busDto.getBusDetails().getJourneys().forEach(a-> {
            Journey journey=journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(a.getBoardingPoint(),a.getEndPoint(),a.getStartTime(),a.getEndTime());
            if (journey == null)
                journeys.add(journeyRepository.save(a));
            else
                journeys.add(journey);
        });
        BusDetails busDetails = busDto.getBusDetails();
        busDetails.setJourneys(journeys);
        int[][][] busLayout = busDto.getLayout();
        int totalSpace = busLayout[busLayout.length - 1][busLayout[1].length - 1][busLayout[1][0].length - 1];
        System.out.println(busLayout.length+"  hai");
        System.out.println(busLayout[1].length+"  lll");
        System.out.println(busLayout[1][0].length+" kkk");
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
    public LayoutDto getBusLayoutService(getLayoutDto getLayoutDto) {
        int[][][] layout = layoutRepository.findById(getLayoutDto.getBusId()).get().getLayout();
        Journey journey=journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(getLayoutDto.getJourney().getBoardingPoint(),getLayoutDto.getJourney().getEndPoint(),getLayoutDto.getJourney().getStartTime(),getLayoutDto.getJourney().getEndTime());
        System.out.println(journey+" hjkl");
        List<BookingDetails> bookingDetailsList = bookingDetailsRepoService.findAllByTravelDateAndJourney(getLayoutDto.getJourney().getTravelDate(),journey);
        for (BookingDetails bookings : bookingDetailsList) {
            List<Passengers> passengersList = passengersRepoService.findByBookingDetailsAndSeatStatus(bookings,"blocked");
            System.out.println(passengersList+" blocked");
            passengersList.addAll(passengersRepoService.findByBookingDetailsAndSeatStatus(bookings,"booked"));
            System.out.println(passengersList+" booked");
            for (Passengers passengers : passengersList) {
                String[] seat = passengers.getSeatNo().split(" ")[0].split("-");
                System.out.println(Arrays.stream(seat).toList()+"  seats");
                layout[Integer.parseInt(seat[0])][Integer.parseInt(seat[1])][Integer.parseInt(seat[2])] = 0;
            }
        }
        System.out.println(Arrays.deepToString(layout) +"  layout");
        LayoutDto layoutDto=new LayoutDto();
        layoutDto.setLayout(layout);
        layoutDto.setSeat(busDetailsRepository.findById(getLayoutDto.getBusId()).get().getSeat());
        return layoutDto;
    }

    @Override
    public String blockSeatService(BlockDto blockDto) {

//        Journey journey=busDetailsRepoService.findByIdAndJourneysBoardingPointAndJourneysEndPoint(blockDto.getBusId(),blockDto.getJourney().getBoardingPoint(),blockDto.getJourney().getEndPoint());
        Journey journey=journeyRepoService.findByBoardingPointAndEndPointAndStartTimeAndEndTime(blockDto.getJourney().getBoardingPoint(),blockDto.getJourney().getEndPoint(),blockDto.getJourney().getStartTime(),blockDto.getJourney().getEndTime());
        System.out.println(journey+" jou");
        BookingDetails bookingDetails = new BookingDetails();
//        modelMapper.map(blockDto, bookingDetails);
        bookingDetails.setEmail(blockDto.getEmail());
        bookingDetails.setMobileNo(blockDto.getMobileNo());
        bookingDetails.setBusId(blockDto.getBusId());
        bookingDetails.setTravelDate(blockDto.getJourney().getTravelDate());
        bookingDetails.setJourney(journey);
        bookingDetails.setUser(userDetailsRepository.findById(blockDto.getUserId()).get());
        bookingDetails=bookingDetailsRepository.save(bookingDetails);
        for(Passengers passengers:blockDto.getPassengersList()){
            Passengers passenger=new Passengers();
            modelMapper.map(passengers,passenger);
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
        Payment payment=new Payment();
        modelMapper.map(paymentDto,payment);
        BookingDetails bookingDetails=bookingDetailsRepository.findById(paymentDto.getBookId()).get();
        payment.setBookingDetails(bookingDetails);
        String pnr=UUID.randomUUID().toString().substring(0,6);
        payment.setPNR(pnr);
        payment=paymentRepository.save(payment);
        TicketDto ticket=new TicketDto();
        ticket.setPayment(payment);
        List<Passengers> passengers=passengersRepoService.findByBookingDetailsAndSeatStatus(bookingDetails,"blocked");
        passengers.forEach(a->a.setSeatStatus("booked"));
        passengersRepository.saveAll(passengers);

        List<Integer> seats=new ArrayList<>();
        passengers.forEach(a->seats.add(Integer.parseInt(a.getSeatNo().split(" ")[1])));
        System.out.println(seats);
        ticket.setSeats(seats);
        ticket.setBusName(busDetailsRepository.findById(bookingDetails.getBusId()).get().getName());
        RewardDto rewardDto=new RewardDto();
        rewardDto.setRewards(findReward(bookingDetails.getUser()));
        rewardDto.setTicket(ticket);
        return rewardDto;
    }
    Rewards findReward(UserDetails user){
        Rewards rewards=new Rewards();
        List<RewardInformation> totalRewards=rewardInformationRepository.findAll();
        int index=new Random().nextInt(totalRewards.size());
        RewardInformation selectedReward=totalRewards.get(index);
        rewards.setInformation(selectedReward);
        rewards.setStatus("assigned");
        rewards.setUserDetails(user);
        LocalDate date=LocalDate.now();
        rewards.setValidDate(date.plusDays(selectedReward.getValidity()));
        rewards=rewardsRepository.save(rewards);
        Base64.Encoder encoder=Base64.getEncoder();
        String coupon=encoder.encodeToString((selectedReward.getInformation()+" expiry date: "+rewards.getValidDate()).getBytes());
        return rewards;
    }
    @Override
    public String cancelBookingSerice(CancelDto cancelDto) {
        BookingDetails booking=paymentRepoService.findByPNR(cancelDto.getPnr()).getBookingDetails();
        List< BusDetails.Seat> seats =busDetailsRepository.findById(booking.getBusId()).get().getSeat();
        List<Passengers> passengers=passengersRepoService.findByBookingDetails(booking);
        double amount=0;
        for (Passengers passenger:passengers){
            int seat=Integer.parseInt(passenger.getSeatNo().split(" ")[1]);
            if(cancelDto.getSeats().contains(seat)){
                if(seat<=Integer.parseInt(seats.get(0).getSeatRange().split("-")[1])){
                    if(passenger.getAge()>12)
                        amount+=seats.get(0).getPrice().getAdultPrice();
                    else
                        amount+=seats.get(0).getPrice().getChildPrice();
                }
                else {
                    if(passenger.getAge()>12)
                        amount+=seats.get(1).getPrice().getAdultPrice();
                    else
                        amount+=seats.get(1).getPrice().getChildPrice();
                }
                passenger.setSeatStatus("cancelled");
                passengersRepository.save(passenger);
                cancelDto.getSeats().remove(cancelDto.getSeats().indexOf(seat));
                System.out.println(cancelDto.getSeats()+"  seats");
            }
        }
        Refund refund=new Refund();
        refund.setAmount(amount/2);
        refund.setPayment(paymentRepository.findByPNR(cancelDto.getPnr()));
        refundRepository.save(refund);
        return "seats cancelled and amount refund";
    }

    @Override
    public List<RewardInformation> saveAllRewards(RewardInformation rewardInformation) {
        if(rewardInformation.getType().equals("code"))
            rewardInformation.setInformation(rewardInformation.getInformation()+" "+UUID.randomUUID());
        rewardInformationRepository.save(rewardInformation);
        return rewardInformationRepository.findAll();
    }

    @Override
    public Rewards getRewardService(Long id) {
        Rewards rewards=rewardsRepository.findById(id).get();
        if (rewards.getStatus().equals("assigned"))
        rewards.setStatus("opened");
        else if (rewards.getStatus().equals("opened"))
            rewards.setStatus("claimed");
        return rewardsRepository.save(rewards);
    }

    @Override
    public List<Rewards> getUserRewardService(String userId) {
        UserDetails userDetails=userDetailsRepository.findById(userId).get();
        return rewardsRepoService.findByUserDetails(userDetails);
    }

    @Scheduled(fixedDelay = 1000)
    public void unblock(){
       List<Passengers> passengers= passengersRepoService.findBySeatStatus("blocked");
        System.out.println(passengers);
       for(Passengers passenger:passengers){
           long l = new Date().getTime() - passenger.getCreatedDate().getTime();
           System.out.println(l+"  block");
           if(l>=1200000){
               passenger.setSeatStatus("unblocked");
               passengersRepository.save(passenger);
           }
       }
       List<Rewards> rewards=rewardsRepoService.findByStatusIsNot("expired");
       for(Rewards reward:rewards){
//           ZonedDateTime z=ZonedDateTime.of(reward.getValidDate().plusDays(1), ZoneId.systemDefault());
//           long date = z.toInstant().toEpochMilli();
           java.sql.Date date= java.sql.Date.valueOf(reward.getValidDate().plusDays(1));
           long l=date.getTime()-new Date().getTime();
           System.out.println(l+" reward");
           if(l<=0){
               reward.setStatus("expired");
               rewardsRepository.save(reward);
           }
       }
    }

}
