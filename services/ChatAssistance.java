package com.ferramentas.ferramentasbackend.services;

import com.ferramentas.ferramentasbackend.entities.*;
import com.ferramentas.ferramentasbackend.repository.*;
import com.ferramentas.ferramentasbackend.dto.input.ChatMessageDto;
import com.ferramentas.ferramentasbackend.dto.output.ChatPresentationDto;
import com.xtragou.xtragoubackend.entities.*;
import com.ferramentas.ferramentasbackend.mappers.ChatMapper;
import com.xtragou.xtragoubackend.repository.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Component
public class ChatAssistance {
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatNormalMessageRepository chatNormalMessageRepository;
    private final ChatSubServiceMessageRepository chatSubServiceMessageRepository;
    private final ChatMessageTypeRepository chatMessageTypeRepository;
    private final SubServiceRepository subServiceRepository;
    private final TechnicianServiceAssistance technicianServiceAssistance;
    private final ServiceAssistance serviceAssistance;
    private final AccountRepository accountRepository;

    public ChatAssistance(
            ChatRepository chatRepository,
            ChatMessageRepository chatMessageRepository,
            ChatNormalMessageRepository chatNormalMessageRepository,
            ChatSubServiceMessageRepository chatSubServiceMessageRepository,
            ChatMessageTypeRepository chatMessageTypeRepository,
            SubServiceRepository subServiceRepository,
            TechnicianServiceAssistance technicianServiceAssistance,
            ServiceAssistance serviceAssistance,
            AccountRepository accountRepository
    ) {
        this.chatRepository = chatRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatNormalMessageRepository = chatNormalMessageRepository;
        this.chatSubServiceMessageRepository = chatSubServiceMessageRepository;
        this.chatMessageTypeRepository = chatMessageTypeRepository;
        this.subServiceRepository = subServiceRepository;
        this.technicianServiceAssistance = technicianServiceAssistance;
        this.serviceAssistance = serviceAssistance;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public ChatPresentationDto createMessage(ChatMessageDto chatMessageDto) {
        Chat chat;
        Optional<Account> senderOptional = accountRepository.findById(chatMessageDto.getSenderId());
        Optional<Account> receiverOptional;

        receiverOptional = accountRepository.findById(chatMessageDto.getReceiverId());

        if (receiverOptional.isEmpty()) {
            receiverOptional = accountRepository.findTechnicianAccountByTechnicianPk(chatMessageDto.getReceiverId());
        }


        if (senderOptional.isEmpty()) {
            throw new Error("Not valid sender");
        }

        if (receiverOptional.isEmpty()) {
            throw new Error("Not valid receiver");
        }


        Account senderAccount = senderOptional.get();
        Account receiverAccount = receiverOptional.get();

        Optional<Chat> chatByUsers = chatRepository.findChatByUsers(
                senderAccount.getPkAccount(),
                receiverAccount.getPkAccount()
        );

        if (chatByUsers.isEmpty()) {
            chat = new Chat();

            chat.setFkAccount1(senderAccount);
            chat.setFkAccount2(receiverAccount);

            chat.setLastChatActivity(new Date(System.currentTimeMillis()));
            chat.setNewMessages(true);
            chat.setQuantOfNewMessages(1);

            chatRepository.save(chat);
        } else {
            Optional<Chat> chatOptional = chatRepository.findById(chatMessageDto.getChatId());

            /*chatOptional.orElseThrow(() -> {
                throw new Error("Not valid chat");
            });

            if (chatByUsers.get().getPkChat() != chatOptional.get().getPkChat()) {
                throw new Error("Not valid chat");
            }*/

            chat = chatByUsers.get();
            chat.setLastChatActivity(new Date(System.currentTimeMillis()));
            chat.setNewMessages(true);
            chat.setQuantOfNewMessages(chat.getQuantOfNewMessages() + 1);

            chatRepository.save(chat);
        }


        Optional<ChatMessageType> chatMessageTypeOptional = chatMessageTypeRepository.findById(chatMessageDto.getMessageType());

        chatMessageTypeOptional.orElseThrow(() -> {
            throw new Error("Message type not founded");
        });

        ChatMessageType chatMessageType = chatMessageTypeOptional.get();

        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setFkAccountSender(senderAccount);
        chatMessage.setFkChat(chat);
        chatMessage.setFkChatMessageType(chatMessageType);
        chatMessage.setSendedTime(chatMessageDto.getSentTime());

        chatMessageRepository.save(chatMessage);

        if (chatMessageType.getPkChatMessageType() == 1) {
            ChatNormalMessage chatNormalMessage = new ChatNormalMessage();

            chatNormalMessage.setContent(chatMessageDto.getContent().toString());
            chatNormalMessage.setFkChatMessage(chatMessage);

            chatNormalMessageRepository.save(chatNormalMessage);

            return ChatMapper.INSTANCE.chatToChatPresentationDto(chat);

        } else if (chatMessageType.getPkChatMessageType() == 2) {
            ChatSubServiceMessage chatSubServiceMessage = new ChatSubServiceMessage();

            Integer subServiceId = Integer.parseInt(chatMessageDto.getContent().toString());

            Optional<SubService> subServiceOptional = subServiceRepository.findById(subServiceId);

            subServiceOptional.orElseThrow(() -> {
                throw new Error("Sub-Service not founded");
            });

            SubService subService = subServiceOptional.get();

            chatSubServiceMessage.setFkSubService(subService);
            chatSubServiceMessage.setFkChatMessage(chatMessage);

            chatSubServiceMessageRepository.save(chatSubServiceMessage);

            return ChatMapper.INSTANCE.chatToChatPresentationDto(chat);

        } else {
            throw new Error("Not valid message content");
        }
    }
}
