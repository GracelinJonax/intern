package com.example.geocoding.Service.Impl;

import com.example.geocoding.Dto.*;
import com.example.geocoding.Exception.BadRequestException;
import com.example.geocoding.Model.*;
import com.example.geocoding.Repository.*;
import com.example.geocoding.Repository.Service.StoreCompanyViewRepoService;
import com.example.geocoding.Service.Services;
import com.example.geocoding.Repository.Service.StoreRepoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements Services {
    private final StoreRepository storeRepository;
    private final StoreRepoService storeRepoService;
    private final CompanyRepository companyRepository;
    private final StoreCompanyViewRepoService storeCompanyViewRepoService;
    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionViewRepository subscriptionViewRepository;
    private final ModelMapper modelMapper;
    public ServiceImpl(StoreRepository storeRepository, StoreRepoService storeRepoService, CompanyRepository companyRepository,StoreCompanyViewRepoService storeCompanyViewRepoService,
                       PlanRepository planRepository,SubscriptionRepository subscriptionRepository,SubscriptionViewRepository subscriptionViewRepository,ModelMapper modelMapper){
        this.storeRepository=storeRepository;
        this.storeRepoService=storeRepoService;
        this.companyRepository=companyRepository;
        this.storeCompanyViewRepoService=storeCompanyViewRepoService;
        this.planRepository=planRepository;
        this.subscriptionRepository=subscriptionRepository;
        this.subscriptionViewRepository=subscriptionViewRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public List<Store> saveStoreService(SaveStoreDto storeDto) {
        Optional<Company>company=companyRepository.findById(storeDto.getCompanyId());
        if(company.isEmpty())
            throw new BadRequestException("no such company present");
        Store store=new Store();
        modelMapper.map(storeDto,store);
        modelMapper.map(storeDto.getAddress(),store);

        storeRepository.save(store);
        return storeRepository.findAll();
    }

    @Override
    public List<Company> saveCompanyService(SaveCompanyDto companyDto) {
        Company company=new Company();
        modelMapper.map(companyDto.getAddress(),company);
        modelMapper.map(companyDto,company);

        companyRepository.save(company);
        return companyRepository.findAll();
    }

    @Override
    public List<StoreCompanyView> findNearStoreService(DistanceDto distanceDto) {
        Optional<Company>company=companyRepository.findById(distanceDto.getCompanyId());
        if(company.isEmpty())
            throw new BadRequestException("no such company present");
        return storeCompanyViewRepoService.findNearByStores(distanceDto.getLatitude(),distanceDto.getLongitude(),distanceDto.getDistance(), distanceDto.getCompanyId());
//        return storeRepoService.findStore(distanceDto.getLatitude(),distanceDto.getLongitude(),distanceDto.getDistance(), distanceDto.getCompanyId());
    }

    @Override
    public List<Plan> savePlanServices(SavePlanDto planDto) {
        if(!planDto.getPlanType().equalsIgnoreCase("unlimited")) {
            if (planDto.getTotalRequest() == null)
                throw new BadRequestException("totalRequest must not be null");
            else if (planDto.getTotalRequest()<1)
                throw new BadRequestException("totalRequest must be greater than 0");
        }
        Plan plan=new Plan();
        modelMapper.map(planDto,plan);
        planRepository.save(plan);
        return planRepository.findAll();
    }

    @Override
    public List<SubscriptionView> saveSubscriptionService(SubscriptionDto subscriptionDto) {
        Optional<Company>company=companyRepository.findById(subscriptionDto.getCompanyId());
        if(company.isEmpty())
            throw new BadRequestException("no such company present");
        Optional<Plan>plan=planRepository.findById(subscriptionDto.getPlanId());
        if (plan.isEmpty())
            throw new BadRequestException("no such plan present");
        Subscription subscription=new Subscription();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(subscriptionDto,subscription);
        subscription.setExpiryDate(LocalDate.now().plusDays(plan.get().getValidDays()));
        subscriptionRepository.save(subscription);
        return subscriptionViewRepository.findAll();
    }
}
