package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.Links;
import com.example.bookingservice.Repository.LinksRepository;
import com.example.bookingservice.Repository.Service.LinksRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksRepoServiceImpl implements LinksRepoService {
    private final LinksRepository linksRepository;
    public LinksRepoServiceImpl(LinksRepository linksRepository){
        this.linksRepository=linksRepository;
    }

    @Override
    public Links getFirstByPublishIsNot(String publish) {
        return linksRepository.getFirstByPublishIsNot(publish);
    }

}
