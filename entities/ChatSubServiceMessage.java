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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "chat_sub_service_message", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatSubServiceMessage.findAll", query = "SELECT c FROM ChatSubServiceMessage c"),
    @NamedQuery(name = "ChatSubServiceMessage.findByPkChatSubServiceMessage", query = "SELECT c FROM ChatSubServiceMessage c WHERE c.pkChatSubServiceMessage = :pkChatSubServiceMessage")})
public class ChatSubServiceMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_chat_sub_service_message", nullable = false)
    private Integer pkChatSubServiceMessage;
    @JoinColumn(name = "fk_chat_message", referencedColumnName = "pk_chat_message", nullable = false)
    @ManyToOne(optional = false)
    private ChatMessage fkChatMessage;
    @JoinColumn(name = "fk_sub_service", referencedColumnName = "pk_sub_service", nullable = false)
    @ManyToOne(optional = false)
    private SubService fkSubService;

    public ChatSubServiceMessage() {
    }

    public ChatSubServiceMessage(Integer pkChatSubServiceMessage) {
        this.pkChatSubServiceMessage = pkChatSubServiceMessage;
    }

    public Integer getPkChatSubServiceMessage() {
        return pkChatSubServiceMessage;
    }

    public void setPkChatSubServiceMessage(Integer pkChatSubServiceMessage) {
        this.pkChatSubServiceMessage = pkChatSubServiceMessage;
    }

    public ChatMessage getFkChatMessage() {
        return fkChatMessage;
    }

    public void setFkChatMessage(ChatMessage fkChatMessage) {
        this.fkChatMessage = fkChatMessage;
    }

    public SubService getFkSubService() {
        return fkSubService;
    }

    public void setFkSubService(SubService fkSubService) {
        this.fkSubService = fkSubService;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkChatSubServiceMessage != null ? pkChatSubServiceMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatSubServiceMessage)) {
            return false;
        }
        ChatSubServiceMessage other = (ChatSubServiceMessage) object;
        if ((this.pkChatSubServiceMessage == null && other.pkChatSubServiceMessage != null) || (this.pkChatSubServiceMessage != null && !this.pkChatSubServiceMessage.equals(other.pkChatSubServiceMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.ChatSubServiceMessage[ pkChatSubServiceMessage=" + pkChatSubServiceMessage + " ]";
    }
    
}
