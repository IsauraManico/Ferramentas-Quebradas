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
@Table(catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByPkChat", query = "SELECT c FROM Chat c WHERE c.pkChat = :pkChat"),
    @NamedQuery(name = "Chat.findByLastChatActivity", query = "SELECT c FROM Chat c WHERE c.lastChatActivity = :lastChatActivity"),
    @NamedQuery(name = "Chat.findByNewMessages", query = "SELECT c FROM Chat c WHERE c.newMessages = :newMessages"),
    @NamedQuery(name = "Chat.findByQuantOfNewMessages", query = "SELECT c FROM Chat c WHERE c.quantOfNewMessages = :quantOfNewMessages")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_chat", nullable = false)
    private Integer pkChat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_chat_activity", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChatActivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "new_messages", nullable = false)
    private boolean newMessages;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant_of_new_messages", nullable = false)
    private int quantOfNewMessages;
    @OneToMany(mappedBy = "fkChat")
    private List<ChatMessage> chatMessageList;
    @JoinColumn(name = "fk_account_1", referencedColumnName = "pk_account", nullable = false)
    @ManyToOne(optional = false)
    private Account fkAccount1;
    @JoinColumn(name = "fk_account_2", referencedColumnName = "pk_account", nullable = false)
    @ManyToOne(optional = false)
    private Account fkAccount2;

    public Chat() {
    }

    public Chat(Integer pkChat) {
        this.pkChat = pkChat;
    }

    public Chat(Integer pkChat, Date lastChatActivity, boolean newMessages, int quantOfNewMessages) {
        this.pkChat = pkChat;
        this.lastChatActivity = lastChatActivity;
        this.newMessages = newMessages;
        this.quantOfNewMessages = quantOfNewMessages;
    }

    public Integer getPkChat() {
        return pkChat;
    }

    public void setPkChat(Integer pkChat) {
        this.pkChat = pkChat;
    }

    public Date getLastChatActivity() {
        return lastChatActivity;
    }

    public void setLastChatActivity(Date lastChatActivity) {
        this.lastChatActivity = lastChatActivity;
    }

    public boolean getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(boolean newMessages) {
        this.newMessages = newMessages;
    }

    public int getQuantOfNewMessages() {
        return quantOfNewMessages;
    }

    public void setQuantOfNewMessages(int quantOfNewMessages) {
        this.quantOfNewMessages = quantOfNewMessages;
    }

    @XmlTransient
    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public Account getFkAccount1() {
        return fkAccount1;
    }

    public void setFkAccount1(Account fkAccount1) {
        this.fkAccount1 = fkAccount1;
    }

    public Account getFkAccount2() {
        return fkAccount2;
    }

    public void setFkAccount2(Account fkAccount2) {
        this.fkAccount2 = fkAccount2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkChat != null ? pkChat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.pkChat == null && other.pkChat != null) || (this.pkChat != null && !this.pkChat.equals(other.pkChat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Chat[ pkChat=" + pkChat + " ]";
    }
    
}
