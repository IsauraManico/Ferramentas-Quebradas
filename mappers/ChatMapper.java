package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Chat;
import com.ferramentas.ferramentasbackend.dto.output.ChatPresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AccountMapper.class})
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(source = "pkChat", target = "id")
    @Mapping(source = "lastChatActivity", target = "lastActivity")
    @Mapping(source = "newMessages", target = "newMessages")
    @Mapping(source = "quantOfNewMessages", target = "quantNewMessages")
    @Mapping(source = "fkAccount1", target = "user1")
    @Mapping(source = "fkAccount2", target = "user2")
    ChatPresentationDto chatToChatPresentationDto(Chat chat);
}
