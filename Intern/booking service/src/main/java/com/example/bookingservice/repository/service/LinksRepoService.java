package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.Links;
import org.springframework.stereotype.Service;

@Service
public interface LinksRepoService{
    Links getFirstByPublishIsNot(String publish);
}
