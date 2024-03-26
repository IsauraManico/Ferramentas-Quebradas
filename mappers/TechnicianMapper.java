package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Technician;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianSimplePresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TechnicianMapper {
    @Mapping(source = "pkTechnician", target = "id")
    @Mapping(source = "fkPerson.name", target = "name")
    @Mapping(source = "fkPerson.locationDescription", target = "location")
    TechnicianSimplePresentationDto technicianToTechnicianSimplePresentationDto(Technician technician);
}
