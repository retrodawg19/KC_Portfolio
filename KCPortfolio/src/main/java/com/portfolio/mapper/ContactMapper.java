package com.portfolio.mapper;

import com.portfolio.dto.ContactRequestDTO;
import com.portfolio.dto.ContactResponseDTO;
import com.portfolio.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    // RequestDTO → Entity (ignore auto-set fields)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "submittedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Contact toEntity(ContactRequestDTO dto);

    // Entity → ResponseDTO
    ContactResponseDTO toResponseDTO(Contact contact);
}