package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.UserSubServiceClassification;
import com.ferramentas.ferramentasbackend.dto.output.ClassificationSubServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AccountMapper.class, SubServiceMapper.class})
public interface ClassificationMapper {
    //UserSubServiceClassification
    ClassificationMapper INSTANCE = Mappers.getMapper(ClassificationMapper.class);

    @Mapping(source = "fkAccount", target = "account")
    @Mapping(source = "fkSubService", target = "subService")
    @Mapping(source = "isAllowedClassification", target = "canClassify", defaultValue = "false")
    @Mapping(source = "technicianPerformAction", target = "technicianAction")
    ClassificationSubServiceDto userSubServiceClassificationToClassificationSubService(UserSubServiceClassification userSubServiceClassification);
}
