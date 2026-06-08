package com.portfolio.mapper;

import com.portfolio.dto.AboutDTO;
import com.portfolio.entity.About;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AboutMapper {

    // DTO → Entity (for updates, maps into existing entity)
    void updateEntityFromDTO(AboutDTO dto, @MappingTarget About about);

    // Entity → DTO
    AboutDTO toDTO(About about);
}