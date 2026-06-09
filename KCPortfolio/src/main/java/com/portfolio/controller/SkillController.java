package com.portfolio.controller;

import com.portfolio.dto.ApiResponseDTO;
import com.portfolio.dto.SkillDTO;
import com.portfolio.entity.Skill;
import com.portfolio.service.SkillService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SkillController {

    private final SkillService skillService;

    @GetMapping("/skills")
    public ResponseEntity<ApiResponseDTO<List<Skill>>> getAllSkills() {
        ApiResponseDTO<List<Skill>> response = ApiResponseDTO.success(skillService.getAllSkills());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/skills/grouped")
    public ResponseEntity<ApiResponseDTO<Map<String, List<Skill>>>> getSkillsGrouped() {
        ApiResponseDTO<Map<String, List<Skill>>> response = ApiResponseDTO.success(skillService.getSkillsGroupedByCategory());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admin/skills")
    public ResponseEntity<ApiResponseDTO<SkillDTO>> createSkill(@Valid @RequestBody SkillDTO dto) {
        ApiResponseDTO<SkillDTO> response = ApiResponseDTO.success("Skill created successfully", skillService.createSkill(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/admin/skills/{id}")
    public ResponseEntity<ApiResponseDTO<SkillDTO>> updateSkill(
            @PathVariable Long id, @Valid @RequestBody SkillDTO dto) {
        ApiResponseDTO<SkillDTO> response = ApiResponseDTO.success("Skill updated successfully", skillService.updateSkill(id, dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/admin/skills/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        ApiResponseDTO<Void> response = ApiResponseDTO.success("Skill deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}