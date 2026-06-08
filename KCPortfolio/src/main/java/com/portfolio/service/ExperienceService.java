package com.portfolio.service;

import java.util.List;

import com.portfolio.dto.ExperienceDTO;

public interface ExperienceService {

	List<ExperienceDTO> getAllExperience();

	ExperienceDTO getExperienceById(Long id);

	ExperienceDTO createExperience(ExperienceDTO dto);

	ExperienceDTO updateExperience(Long id, ExperienceDTO dto);

	void deleteExperience(Long id);

}
