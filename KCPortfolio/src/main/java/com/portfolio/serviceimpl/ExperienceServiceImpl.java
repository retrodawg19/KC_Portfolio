package com.portfolio.serviceimpl;

import com.portfolio.entity.Experience;
import com.portfolio.repository.ExperienceRepository;
import com.portfolio.service.ExperieneService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements  ExperieneService {

    private ExperienceRepository experienceRepository;

    public List<Experience> getAllExperience() {
        return experienceRepository.findAllByOrderByStartDateDesc();
    }

    public Experience getExperienceById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long id, Experience updatedExperience) {
        Experience existing = getExperienceById(id);
        updatedExperience.setId(existing.getId());
        return experienceRepository.save(updatedExperience);
    }

    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new RuntimeException("Experience not found with id: " + id);
        }
        experienceRepository.deleteById(id);
    }
}