package com.ferramentas.ferramentasbackend.dto.input;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class ChatMessageDto implements Serializable {
    private final int chatId;
    @NotNull
    private final int senderId;
    @NotNull
    private final int receiverId;
    @NotNull
    private final int messageType;
    @NotNull
    private final Object content;
    @NotNull
    private final Date sentTime;

    public ChatMessageDto(int chatId, int senderId, int receiverId, int messageType, Object content, Date sentTime) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageType = messageType;
        this.content = content;
        this.sentTime = sentTime;
    }

    public int getChatId() {
        return chatId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getMessageType() {
        return messageType;
    }

    public Object getContent() {
        return content;
    }

    public Date getSentTime() {
        return sentTime;
    }
}
