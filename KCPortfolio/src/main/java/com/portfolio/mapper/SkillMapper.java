package com.portfolio.mapper;

import com.portfolio.dto.SkillDTO;
import com.portfolio.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    // DTO → Entity
    Skill toEntity(SkillDTO dto);

    // Entity → DTO
    SkillDTO toDTO(Skill skill);

    // Update existing entity from DTO (preserves ID)
    void updateEntityFromDTO(SkillDTO dto, @MappingTarget Skill skill);
}