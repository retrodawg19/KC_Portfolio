package com.portfolio.serviceimpl;

import com.portfolio.entity.About;
import com.portfolio.repository.AboutRepository;
import com.portfolio.service.AboutService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AboutServiceImpl implements AboutService {

    private AboutRepository aboutRepository;
    public About getAbout() {
        return aboutRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("About info not found"));
    }

    public About saveAbout(About about) {
        // Always keep only one About record
        aboutRepository.findTopByOrderByIdAsc()
                .ifPresent(existing -> about.setId(existing.getId()));
        return aboutRepository.save(about);
}}