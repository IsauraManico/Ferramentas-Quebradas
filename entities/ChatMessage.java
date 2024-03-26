/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "chat_message", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatMessage.findAll", query = "SELECT c FROM ChatMessage c"),
    @NamedQuery(name = "ChatMessage.findByPkChatMessage", query = "SELECT c FROM ChatMessage c WHERE c.pkChatMessage = :pkChatMessage"),
    @NamedQuery(name = "ChatMessage.findBySendedTime", query = "SELECT c FROM ChatMessage c WHERE c.sendedTime = :sendedTime"),
    @NamedQuery(name = "ChatMessage.findByViewedTime", query = "SELECT c FROM ChatMessage c WHERE c.viewedTime = :viewedTime")})
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_chat_message", nullable = false)
    private Integer pkChatMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sended_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendedTime;
    @Column(name = "viewed_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewedTime;
    @JoinColumn(name = "fk_account_sender", referencedColumnName = "pk_account", nullable = false)
    @ManyToOne(optional = false)
    private Account fkAccountSender;
    @JoinColumn(name = "fk_chat", referencedColumnName = "pk_chat")
    @ManyToOne
    private Chat fkChat;
    @JoinColumn(name = "fk_chat_message_type", referencedColumnName = "pk_chat_message_type", nullable = false)
    @ManyToOne(optional = false)
    private ChatMessageType fkChatMessageType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkChatMessage")
    private List<ChatSubServiceMessage> chatSubServiceMessageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkChatMessage")
    private List<ChatNormalMessage> chatNormalMessageList;

    public ChatMessage() {
    }

    public ChatMessage(Integer pkChatMessage) {
        this.pkChatMessage = pkChatMessage;
    }

    public ChatMessage(Integer pkChatMessage, Date sendedTime) {
        this.pkChatMessage = pkChatMessage;
        this.sendedTime = sendedTime;
    }

    public Integer getPkChatMessage() {
        return pkChatMessage;
    }

    public void setPkChatMessage(Integer pkChatMessage) {
        this.pkChatMessage = pkChatMessage;
    }

    public Date getSendedTime() {
        return sendedTime;
    }

    public void setSendedTime(Date sendedTime) {
        this.sendedTime = sendedTime;
    }

    public Date getViewedTime() {
        return viewedTime;
    }

    public void setViewedTime(Date viewedTime) {
        this.viewedTime = viewedTime;
    }

    public Account getFkAccountSender() {
        return fkAccountSender;
    }

    public void setFkAccountSender(Account fkAccountSender) {
        this.fkAccountSender = fkAccountSender;
    }

    public Chat getFkChat() {
        return fkChat;
    }

    public void setFkChat(Chat fkChat) {
        this.fkChat = fkChat;
    }

    public ChatMessageType getFkChatMessageType() {
        return fkChatMessageType;
    }

    public void setFkChatMessageType(ChatMessageType fkChatMessageType) {
        this.fkChatMessageType = fkChatMessageType;
    }

    @XmlTransient
    public List<ChatSubServiceMessage> getChatSubServiceMessageList() {
        return chatSubServiceMessageList;
    }

    public void setChatSubServiceMessageList(List<ChatSubServiceMessage> chatSubServiceMessageList) {
        this.chatSubServiceMessageList = chatSubServiceMessageList;
    }

    @XmlTransient
    public List<ChatNormalMessage> getChatNormalMessageList() {
        return chatNormalMessageList;
    }

    public void setChatNormalMessageList(List<ChatNormalMessage> chatNormalMessageList) {
        this.chatNormalMessageList = chatNormalMessageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkChatMessage != null ? pkChatMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatMessage)) {
            return false;
        }
        ChatMessage other = (ChatMessage) object;
        if ((this.pkChatMessage == null && other.pkChatMessage != null) || (this.pkChatMessage != null && !this.pkChatMessage.equals(other.pkChatMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.ChatMessage[ pkChatMessage=" + pkChatMessage + " ]";
    }
    
}
