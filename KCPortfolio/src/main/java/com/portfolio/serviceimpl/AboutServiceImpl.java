package com.portfolio.serviceimpl;

import com.portfolio.dto.AboutDTO;
import com.portfolio.entity.About;
import com.portfolio.mapper.AboutMapper;
import com.portfolio.repository.AboutRepository;
import com.portfolio.service.AboutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutRepository aboutRepository;
    private final AboutMapper aboutMapper;

    @Override
    public About getAbout() {
        return aboutRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("About info not found"));
    }

    @Override
    public About saveAbout(AboutDTO dto) {
        About about = aboutRepository.findTopByOrderByIdAsc().orElse(new About());
        aboutMapper.updateEntityFromDTO(dto, about);  // maps all fields, preserves ID
        log.info("About section updated");
        return aboutRepository.save(about);
    }
}		