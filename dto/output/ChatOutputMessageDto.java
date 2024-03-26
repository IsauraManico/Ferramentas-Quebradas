package com.ferramentas.ferramentasbackend.dto.output;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;



public class ChatOutputMessageDto implements Serializable {
    @NotNull
    private final Integer chatId;
    @NotNull
    private final Integer senderId;
    @NotNull
    private final Integer messageType;
    @NotNull
    private final Object content;
    @NotNull
    private final Date sentTime ;
    @NotNull
    private final Date viewedTime ;

    public ChatOutputMessageDto(Integer chatId, Integer senderId, Integer messageType, Object content, Date sentTime, Date viewedTime) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.messageType = messageType;
        this.content = content;
        this.sentTime = sentTime;
        this.viewedTime = viewedTime;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public Object getContent() {
        return content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public Date getViewedTime() {
        return viewedTime;
    }
}
