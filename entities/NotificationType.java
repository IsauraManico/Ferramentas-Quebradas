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
@Table(name = "notification_type", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationType.findAll", query = "SELECT n FROM NotificationType n"),
    @NamedQuery(name = "NotificationType.findByPkNotificationType", query = "SELECT n FROM NotificationType n WHERE n.pkNotificationType = :pkNotificationType"),
    @NamedQuery(name = "NotificationType.findByDesignation", query = "SELECT n FROM NotificationType n WHERE n.designation = :designation")})
public class NotificationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_notification_type", nullable = false)
    private Integer pkNotificationType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkNotificationType")
    private List<Notification> notificationList;

    public NotificationType() {
    }

    public NotificationType(Integer pkNotificationType) {
        this.pkNotificationType = pkNotificationType;
    }

    public NotificationType(Integer pkNotificationType, String designation) {
        this.pkNotificationType = pkNotificationType;
        this.designation = designation;
    }

    public Integer getPkNotificationType() {
        return pkNotificationType;
    }

    public void setPkNotificationType(Integer pkNotificationType) {
        this.pkNotificationType = pkNotificationType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkNotificationType != null ? pkNotificationType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationType)) {
            return false;
        }
        NotificationType other = (NotificationType) object;
        if ((this.pkNotificationType == null && other.pkNotificationType != null) || (this.pkNotificationType != null && !this.pkNotificationType.equals(other.pkNotificationType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.NotificationType[ pkNotificationType=" + pkNotificationType + " ]";
    }
    
}
