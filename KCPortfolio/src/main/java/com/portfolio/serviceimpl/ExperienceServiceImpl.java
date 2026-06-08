package com.portfolio.serviceimpl;

import com.portfolio.dto.ExperienceDTO;
import com.portfolio.entity.Experience;
import com.portfolio.mapper.ExperienceMapper;
import com.portfolio.repository.ExperienceRepository;
import com.portfolio.service.ExperienceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public List<ExperienceDTO> getAllExperience() {
        return experienceRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(experienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
        return experienceMapper.toDTO(experience);
    }

    @Override
    public ExperienceDTO createExperience(ExperienceDTO dto) {
        Experience saved = experienceRepository.save(experienceMapper.toEntity(dto));
        log.info("Experience created: {} at {}", saved.getJobTitle(), saved.getCompany());
        return experienceMapper.toDTO(saved);
    }

    @Override
    public ExperienceDTO updateExperience(Long id, ExperienceDTO dto) {
        Experience existing = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
        experienceMapper.updateEntityFromDTO(dto, existing);  // maps into existing, preserves ID
        log.info("Experience updated: {}", existing.getJobTitle());
        return experienceMapper.toDTO(experienceRepository.save(existing));
    }

    @Override
    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new RuntimeException("Experience not found with id: " + id);
        }
        experienceRepository.deleteById(id);
        log.info("Experience deleted with id: {}", id);
    }
}