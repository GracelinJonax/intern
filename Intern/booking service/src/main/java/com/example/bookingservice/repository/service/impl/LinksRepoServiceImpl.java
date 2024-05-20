package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.Links;
import com.example.bookingservice.repository.LinksRepository;
import com.example.bookingservice.repository.service.LinksRepoService;
import org.springframework.stereotype.Service;

@Service
public class LinksRepoServiceImpl implements LinksRepoService {
    private final LinksRepository linksRepository;

    public LinksRepoServiceImpl(LinksRepository linksRepository) {
        this.linksRepository = linksRepository;
    }

    @Override
    public Links getFirstByPublishIsNot(String publish) {
        return linksRepository.getFirstByPublishIsNot(publish);
    }

}
