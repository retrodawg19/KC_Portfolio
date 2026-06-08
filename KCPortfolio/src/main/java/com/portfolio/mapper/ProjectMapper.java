package com.portfolio.mapper;

import com.portfolio.dto.ProjectRequestDTO;
import com.portfolio.dto.ProjectResponseDTO;
import com.portfolio.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    // RequestDTO → Entity
    Project toEntity(ProjectRequestDTO dto);

    // Entity → ResponseDTO
    ProjectResponseDTO toResponseDTO(Project project);

    // Update existing entity from DTO (preserves ID)
    void updateEntityFromDTO(ProjectRequestDTO dto, @MappingTarget Project project);
}