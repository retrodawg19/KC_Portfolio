package com.portfolio.serviceimpl;

import com.portfolio.dto.ProjectRequestDTO;
import com.portfolio.dto.ProjectResponseDTO;
import com.portfolio.entity.Project;
import com.portfolio.mapper.ProjectMapper;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(projectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponseDTO> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrueOrderByDisplayOrderAsc()
                .stream()
                .map(projectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return projectMapper.toResponseDTO(project);
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO dto) {
        Project saved = projectRepository.save(projectMapper.toEntity(dto));
        log.info("Project created: {}", saved.getTitle());
        return projectMapper.toResponseDTO(saved);
    }

    @Override
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        projectMapper.updateEntityFromDTO(dto, existing);  // maps into existing, preserves ID
        log.info("Project updated: {}", existing.getTitle());
        return projectMapper.toResponseDTO(projectRepository.save(existing));
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
        log.info("Project deleted with id: {}", id);
    }
}