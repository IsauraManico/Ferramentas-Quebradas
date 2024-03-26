package com.ferramentas.ferramentasbackend.dto.output;

import java.io.Serializable;
import java.util.Date;

public class ChatPresentationDto implements Serializable {
    private Integer id;
    private AccountPresentationDto user1;
    private AccountPresentationDto user2;
    private Date lastActivity;
    private Boolean newMessages;
    private Integer quantNewMessages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountPresentationDto getUser1() {
        return user1;
    }

    public void setUser1(AccountPresentationDto user1) {
        this.user1 = user1;
    }

    public AccountPresentationDto getUser2() {
        return user2;
    }

    public void setUser2(AccountPresentationDto user2) {
        this.user2 = user2;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Boolean getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(Boolean newMessages) {
        this.newMessages = newMessages;
    }

    public Integer getQuantNewMessages() {
        return quantNewMessages;
    }

    public void setQuantNewMessages(Integer quantNewMessages) {
        this.quantNewMessages = quantNewMessages;
    }
}
