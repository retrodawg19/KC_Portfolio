package com.portfolio.controller;

import com.portfolio.dto.AboutDTO;
import com.portfolio.dto.ApiResponseDTO;
import com.portfolio.entity.About;
import com.portfolio.service.AboutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("/about")
    public ResponseEntity<ApiResponseDTO<About>> getAbout() {
        ApiResponseDTO<About> response = ApiResponseDTO.success(aboutService.getAbout());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/admin/about")
    public ResponseEntity<ApiResponseDTO<About>> updateAbout(@Valid @RequestBody AboutDTO dto) {
        ApiResponseDTO<About> response = ApiResponseDTO.success("About updated successfully", aboutService.saveAbout(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}