package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Service;
import com.ferramentas.ferramentasbackend.dto.output.ServicePresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mapping(source = "pkService", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "image", target = "image")
    ServicePresentationDto serviceToServicePresentationDto(Service service);
}
