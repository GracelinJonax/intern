package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.Links;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LinksRepoService{
    Links getFirstByPublishIsNot(String publish);
}
