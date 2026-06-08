package com.portfolio.mapper;

import com.portfolio.dto.ExperienceDTO;
import com.portfolio.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    // DTO → Entity
    Experience toEntity(ExperienceDTO dto);

    // Entity → DTO
    ExperienceDTO toDTO(Experience experience);

    // Update existing entity from DTO (preserves ID)
    void updateEntityFromDTO(ExperienceDTO dto, @MappingTarget Experience experience);
}