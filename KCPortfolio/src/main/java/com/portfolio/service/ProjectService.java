package com.portfolio.service;

import java.util.List;

import com.portfolio.dto.ProjectRequestDTO;
import com.portfolio.dto.ProjectResponseDTO;

public interface ProjectService {

	List<ProjectResponseDTO> getAllProjects();

	List<ProjectResponseDTO> getFeaturedProjects();

	ProjectResponseDTO getProjectById(Long id);

	ProjectResponseDTO createProject(ProjectRequestDTO dto);

	ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto);

	void deleteProject(Long id);

}
