package com.example.geocoding.Service.Impl;

import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Dto.SaveStoreDto;
import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import com.example.geocoding.Service.Services;
import com.example.geocoding.repository.CompanyRepository;
import com.example.geocoding.repository.Service.StoreRepoService;
import com.example.geocoding.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class serviceImpl implements Services {
    private final StoreRepository storeRepository;
    private final StoreRepoService storeRepoService;
    private final CompanyRepository companyRepository;
    public serviceImpl(StoreRepository storeRepository,StoreRepoService storeRepoService,CompanyRepository companyRepository){
        this.storeRepository=storeRepository;
        this.storeRepoService=storeRepoService;
        this.companyRepository=companyRepository;
    }
    @Override
    public List<Store> saveStoreService(SaveStoreDto storeDto) {
        if(storeDto.getStore()==null)
            throw new RuntimeException("no store present");
        else if(storeDto.getStore().getName().length()>50)
            throw new RuntimeException("store length is too long");
        else if(!(storeDto.getStore().getLatitude()>=-90&&storeDto.getStore().getLatitude()<=90)||!(storeDto.getStore().getLongitude()>=-180&&storeDto.getStore().getLongitude()<=180))
            throw new RuntimeException("check your store latitude and longitude");
        else if (storeDto.getCompanyId()==null)
            throw new RuntimeException("companyId is null");
        Optional<Company>company=companyRepository.findById(storeDto.getCompanyId());
        if(company.isEmpty())
            throw new RuntimeException("no such company present");
        storeDto.getStore().setCompany(company.get());
        storeRepository.save(storeDto.getStore());
        return storeRepository.findAll();
    }

    @Override
    public List<Store> findNearStoreService(DistanceDto distanceDto) {
        if (distanceDto==null)
            throw new RuntimeException("no values present");
        return storeRepoService.findStore(distanceDto.getLatitude(),distanceDto.getLongitude(),distanceDto.getDistance(),companyRepository.findById(distanceDto.getCompanyId()).get());
    }

    @Override
    public List<Company> saveCompanyService(Company company) {
        if(company==null)
            throw new RuntimeException("no company present");
        else if(company.getName().length()>50)
            throw new RuntimeException("company length is too long");
        companyRepository.save(company);
        return companyRepository.findAll();
    }
}
