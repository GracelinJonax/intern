package com.example.qrcode.Repository.Service.Impl;

import com.example.qrcode.Model.UserDetails;
import com.example.qrcode.Repository.Service.UserDetailsRepoService;
import com.example.qrcode.Repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRepoServiceImpl implements UserDetailsRepoService {
    private final UserDetailsRepository userDetailsRepository;
    public UserDetailsRepoServiceImpl(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository=userDetailsRepository;
    }
    @Override
    public UserDetails findByMobileNumber(String mobileNumber) {
        return userDetailsRepository.findByMobileNumber(mobileNumber);
    }
}
