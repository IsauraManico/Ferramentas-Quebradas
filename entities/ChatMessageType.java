/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "chat_message_type", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatMessageType.findAll", query = "SELECT c FROM ChatMessageType c"),
    @NamedQuery(name = "ChatMessageType.findByPkChatMessageType", query = "SELECT c FROM ChatMessageType c WHERE c.pkChatMessageType = :pkChatMessageType"),
    @NamedQuery(name = "ChatMessageType.findByDesignation", query = "SELECT c FROM ChatMessageType c WHERE c.designation = :designation")})
public class ChatMessageType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_chat_message_type", nullable = false)
    private Integer pkChatMessageType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkChatMessageType")
    private List<ChatMessage> chatMessageList;

    public ChatMessageType() {
    }

    public ChatMessageType(Integer pkChatMessageType) {
        this.pkChatMessageType = pkChatMessageType;
    }

    public ChatMessageType(Integer pkChatMessageType, String designation) {
        this.pkChatMessageType = pkChatMessageType;
        this.designation = designation;
    }

    public Integer getPkChatMessageType() {
        return pkChatMessageType;
    }

    public void setPkChatMessageType(Integer pkChatMessageType) {
        this.pkChatMessageType = pkChatMessageType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @XmlTransient
    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkChatMessageType != null ? pkChatMessageType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatMessageType)) {
            return false;
        }
        ChatMessageType other = (ChatMessageType) object;
        if ((this.pkChatMessageType == null && other.pkChatMessageType != null) || (this.pkChatMessageType != null && !this.pkChatMessageType.equals(other.pkChatMessageType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.ChatMessageType[ pkChatMessageType=" + pkChatMessageType + " ]";
    }
    
}
