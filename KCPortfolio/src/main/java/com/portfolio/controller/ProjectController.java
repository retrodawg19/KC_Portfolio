package com.portfolio.controller;

import com.portfolio.dto.ApiResponseDTO;
import com.portfolio.dto.ProjectRequestDTO;
import com.portfolio.dto.ProjectResponseDTO;
import com.portfolio.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<ApiResponseDTO<List<ProjectResponseDTO>>> getAllProjects() {
        ApiResponseDTO<List<ProjectResponseDTO>> response = ApiResponseDTO.success(projectService.getAllProjects());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/featured")
    public ResponseEntity<ApiResponseDTO<List<ProjectResponseDTO>>> getFeaturedProjects() {
        ApiResponseDTO<List<ProjectResponseDTO>> response = ApiResponseDTO.success(projectService.getFeaturedProjects());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> getProjectById(@PathVariable Long id) {
        ApiResponseDTO<ProjectResponseDTO> response = ApiResponseDTO.success(projectService.getProjectById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admin/projects")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> createProject(@Valid @RequestBody ProjectRequestDTO dto) {
        ApiResponseDTO<ProjectResponseDTO> response = ApiResponseDTO.success("Project created successfully", projectService.createProject(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/admin/projects/{id}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> updateProject(
            @PathVariable Long id, @Valid @RequestBody ProjectRequestDTO dto) {
        ApiResponseDTO<ProjectResponseDTO> response = ApiResponseDTO.success("Project updated successfully", projectService.updateProject(id, dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/admin/projects/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        ApiResponseDTO<Void> response = ApiResponseDTO.success("Project deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}