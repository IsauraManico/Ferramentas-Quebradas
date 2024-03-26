package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.SubService;
import com.ferramentas.ferramentasbackend.dto.output.SubServiceSimplePresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ServiceMapper.class, TechnicianMapper.class})
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    @Mapping(target = "id", source = "pkSubService")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "technician", source = "fkTechnician")
    SubServiceSimplePresentationDto subServiceToSubServicePresentationDto(SubService subService);
}
