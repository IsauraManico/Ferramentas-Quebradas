/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "chat_normal_message", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatNormalMessage.findAll", query = "SELECT c FROM ChatNormalMessage c"),
    @NamedQuery(name = "ChatNormalMessage.findByChatNormalMessage", query = "SELECT c FROM ChatNormalMessage c WHERE c.chatNormalMessage = :chatNormalMessage"),
    @NamedQuery(name = "ChatNormalMessage.findByContent", query = "SELECT c FROM ChatNormalMessage c WHERE c.content = :content")})
public class ChatNormalMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chat_normal_message", nullable = false)
    private Integer chatNormalMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String content;
    @JoinColumn(name = "fk_chat_message", referencedColumnName = "pk_chat_message", nullable = false)
    @ManyToOne(optional = false)
    private ChatMessage fkChatMessage;

    public ChatNormalMessage() {
    }

    public ChatNormalMessage(Integer chatNormalMessage) {
        this.chatNormalMessage = chatNormalMessage;
    }

    public ChatNormalMessage(Integer chatNormalMessage, String content) {
        this.chatNormalMessage = chatNormalMessage;
        this.content = content;
    }

    public Integer getChatNormalMessage() {
        return chatNormalMessage;
    }

    public void setChatNormalMessage(Integer chatNormalMessage) {
        this.chatNormalMessage = chatNormalMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatMessage getFkChatMessage() {
        return fkChatMessage;
    }

    public void setFkChatMessage(ChatMessage fkChatMessage) {
        this.fkChatMessage = fkChatMessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatNormalMessage != null ? chatNormalMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatNormalMessage)) {
            return false;
        }
        ChatNormalMessage other = (ChatNormalMessage) object;
        if ((this.chatNormalMessage == null && other.chatNormalMessage != null) || (this.chatNormalMessage != null && !this.chatNormalMessage.equals(other.chatNormalMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.ChatNormalMessage[ chatNormalMessage=" + chatNormalMessage + " ]";
    }
    
}
