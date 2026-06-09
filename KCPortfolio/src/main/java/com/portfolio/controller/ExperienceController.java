package com.portfolio.controller;

import com.portfolio.dto.ApiResponseDTO;
import com.portfolio.dto.ExperienceDTO;
import com.portfolio.service.ExperienceService;
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
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping("/experience")
    public ResponseEntity<ApiResponseDTO<List<ExperienceDTO>>> getAllExperience() {
        ApiResponseDTO<List<ExperienceDTO>> response = ApiResponseDTO.success(experienceService.getAllExperience());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/experience/{id}")
    public ResponseEntity<ApiResponseDTO<ExperienceDTO>> getExperienceById(@PathVariable Long id) {
        ApiResponseDTO<ExperienceDTO> response = ApiResponseDTO.success(experienceService.getExperienceById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admin/experience")
    public ResponseEntity<ApiResponseDTO<ExperienceDTO>> createExperience(@Valid @RequestBody ExperienceDTO dto) {
        ApiResponseDTO<ExperienceDTO> response = ApiResponseDTO.success("Experience created successfully", experienceService.createExperience(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/admin/experience/{id}")
    public ResponseEntity<ApiResponseDTO<ExperienceDTO>> updateExperience(
            @PathVariable Long id, @Valid @RequestBody ExperienceDTO dto) {
        ApiResponseDTO<ExperienceDTO> response = ApiResponseDTO.success("Experience updated successfully", experienceService.updateExperience(id, dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/admin/experience/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        ApiResponseDTO<Void> response = ApiResponseDTO.success("Experience deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}