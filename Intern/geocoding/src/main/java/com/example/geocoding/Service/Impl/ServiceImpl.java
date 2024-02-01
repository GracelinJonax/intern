package com.example.geocoding.Service.Impl;

import com.example.geocoding.Config.JwtService;
import com.example.geocoding.Dto.*;
import com.example.geocoding.Exception.BadRequestException;
import com.example.geocoding.Model.*;
import com.example.geocoding.Repository.*;
import com.example.geocoding.Repository.Service.CompanyRepoService;
import com.example.geocoding.Repository.Service.RequestResponseLogRepoService;
import com.example.geocoding.Repository.Service.StoreCompanyViewRepoService;
import com.example.geocoding.Repository.Service.StoreRepoService;
import com.example.geocoding.Service.Services;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements Services {
    private final StoreRepository storeRepository;
    private final StoreRepoService storeRepoService;
    private final CompanyRepository companyRepository;
    private final CompanyRepoService companyRepoService;
    private final StoreCompanyViewRepoService storeCompanyViewRepoService;
    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionViewRepository subscriptionViewRepository;
    private final RequestResponseLogRepoService requestResponseLogRepoService;
    private final RequestResponseLogRepository requestResponseLogRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public ServiceImpl(StoreRepository storeRepository, StoreRepoService storeRepoService, CompanyRepository companyRepository, CompanyRepoService companyRepoService, StoreCompanyViewRepoService storeCompanyViewRepoService,
                       PlanRepository planRepository, SubscriptionRepository subscriptionRepository, SubscriptionViewRepository subscriptionViewRepository, RequestResponseLogRepoService requestResponseLogRepoService, RequestResponseLogRepository requestResponseLogRepository, JwtService jwtService, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.storeRepoService = storeRepoService;
        this.companyRepository = companyRepository;
        this.companyRepoService = companyRepoService;
        this.storeCompanyViewRepoService = storeCompanyViewRepoService;
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionViewRepository = subscriptionViewRepository;
        this.requestResponseLogRepoService = requestResponseLogRepoService;
        this.requestResponseLogRepository = requestResponseLogRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Store> saveStoreService(SaveStoreDto storeDto) {
        Optional<Company> company = companyRepository.findById(storeDto.getCompanyId());
        if (company.isEmpty())
            throw new BadRequestException("no such company present");
        Store store = new Store();
        modelMapper.map(storeDto, store);
        modelMapper.map(storeDto.getAddress(), store);

        storeRepository.save(store);
        return storeRepository.findAll();
    }

    @Override
    public List<Company> saveCompanyService(SaveCompanyDto companyDto) {
        Company company = new Company();
        modelMapper.map(companyDto.getAddress(), company);
        modelMapper.map(companyDto, company);

        companyRepository.save(company);
        return companyRepository.findAll();
    }

    @Override
    public List<StoreCompanyView> findNearStoreService(DistanceDto distanceDto) {
        Optional<Company> company = companyRepository.findById(distanceDto.getCompanyId());
        if (company.isEmpty())
            throw new BadRequestException("no such company present");
        return storeCompanyViewRepoService.findNearByStores(distanceDto.getLatitude(), distanceDto.getLongitude(), distanceDto.getDistance(), distanceDto.getCompanyId());
//        return storeRepoService.findStore(distanceDto.getLatitude(),distanceDto.getLongitude(),distanceDto.getDistance(), distanceDto.getCompanyId());
    }

    @Override
    public List<Plan> savePlanServices(SavePlanDto planDto) {
        if (!planDto.getPlanType().equalsIgnoreCase("unlimited")) {
            if (planDto.getTotalRequest() == null)
                throw new BadRequestException("totalRequest must not be null");
            else if (planDto.getTotalRequest() < 1)
                throw new BadRequestException("totalRequest must be greater than 0");
        }
        Plan plan = new Plan();
        modelMapper.map(planDto, plan);
        planRepository.save(plan);
        return planRepository.findAll();
    }

    @Override
    public List<SubscriptionView> saveSubscriptionService(SubscriptionDto subscriptionDto) {
        Optional<Company> company = companyRepository.findById(subscriptionDto.getCompanyId());
        if (company.isEmpty())
            throw new BadRequestException("no such company present");
        Optional<Plan> plan = planRepository.findById(subscriptionDto.getPlanId());
        if (plan.isEmpty())
            throw new BadRequestException("no such plan present");
        Subscription subscription = new Subscription();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(subscriptionDto, subscription);
        subscription.setExpiryDate(LocalDate.now().plusDays(plan.get().getValidDays()));
        subscriptionRepository.save(subscription);
        return subscriptionViewRepository.findAll();
    }

    @Override
    public String loginService(LoginDto loginDto) {
        String token;
        HashMap<String, Object> claims = new HashMap<>();
        Optional<Company> company = companyRepoService.findByCompanyName(loginDto.getCompanyName());
        if (company.isEmpty())
            throw new BadRequestException("no such company present");
        if (company.get().getPassword().equals(loginDto.getPassword())) {
            SubscriptionView subscriptionView = subscriptionViewRepository.findByCompanyIdAndExpiryDateAfter(company.get().getCompanyId(), LocalDate.now());
            if (subscriptionView == null)
                claims.put("subscription", null);
            else
                claims.put("subscription", subscriptionView.getSubscriptionId());

            token = jwtService.generateToken(claims, company.get());
            System.out.println(jwtService.extractAllClaims(token).get("subscription") + "    claims");
            return token;
        } else
            throw new BadRequestException("password incorrect");
    }

    @Override
    public boolean isSubscribed(String jwt) {
        Object subscriptionId = jwtService.extractAllClaims(jwt).get("subscription");
        if (subscriptionId == null)
            return false;
        Optional<SubscriptionView> subscriptionView = subscriptionViewRepository.findBySubscriptionIdAndExpiryDateAfter(subscriptionId.toString(), LocalDate.now());
        if (subscriptionView.isEmpty())
            throw new BadRequestException("subscriptionId is not valid");
        Long totalRequest=subscriptionView.get().getTotalRequest();
        if (requestResponseLogRepoService.findTopBySubscriptionId(subscriptionId.toString()).isEmpty() ||totalRequest==null
                ||(subscriptionView.get().getPlanType().equalsIgnoreCase("limited")
                &&requestResponseLogRepoService.countBySubscriptionId(subscriptionId.toString())<totalRequest)
                ||(subscriptionView.get().getPlanType().equalsIgnoreCase("limited request per day")
                && requestResponseLogRepoService.countBySubscriptionIdAndDateLike(subscriptionId.toString(),LocalDate.now())<totalRequest))
        return true;
        return false;
    }

    @Override
    public void saveRequestResponse(ContentCachingRequestWrapper requestWrapper, HttpServletRequest request, ContentCachingResponseWrapper responseWrapper, HttpServletResponse response, String jwt) {
        String token = jwtService.extractAllClaims(jwt).get("subscription").toString();
        RequestResponseLog requestResponseLog = new RequestResponseLog();
        try {
            String requestBody = new String(requestWrapper.getContentAsByteArray(),
                    request.getCharacterEncoding());
            String responseBody = new String(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

            requestResponseLog.setSubscriptionId(token);
            requestResponseLog.setRequestMethod(request.getMethod());
            requestResponseLog.setRequestUrl(request.getRequestURL().toString());
            requestResponseLog.setRequestBody(requestBody);
            requestResponseLog.setResponseStatusCode(response.getStatus());
            requestResponseLog.setResponseBody(responseBody);
            requestResponseLogRepository.save(requestResponseLog);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
