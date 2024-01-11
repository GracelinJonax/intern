package com.example.qrcode.Repository.Service;

import com.example.qrcode.Model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsRepoService {
    UserDetails findByMobileNumber(String mobileNumber);
}
