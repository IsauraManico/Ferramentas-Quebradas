package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.dto.output.AccountPresentationDto;
import com.ferramentas.ferramentasbackend.dto.output.AccountSimplePresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "pkAccount", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "profilePicture", target = "image")
    @Mapping(source = "profileCoverPicture", target = "backgroundImage")
    @Mapping(source = "fkPerson.name", target = "name")
    AccountPresentationDto accountToAccountPresentationDto(Account account);

    @Mapping(source = "pkAccount", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "profilePicture", target = "image")
    @Mapping(source = "fkPerson.name", target = "name")
    AccountSimplePresentationDto accountToAccountSimplePresentationDto(Account account);


}
