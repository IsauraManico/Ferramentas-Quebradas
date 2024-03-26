/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isaura
 */
@Entity
@Table(catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByPkNotification", query = "SELECT n FROM Notification n WHERE n.pkNotification = :pkNotification"),
    @NamedQuery(name = "Notification.findByTitle", query = "SELECT n FROM Notification n WHERE n.title = :title"),
    @NamedQuery(name = "Notification.findByGeneratedTime", query = "SELECT n FROM Notification n WHERE n.generatedTime = :generatedTime"),
    @NamedQuery(name = "Notification.findByDescription", query = "SELECT n FROM Notification n WHERE n.description = :description")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_notification", nullable = false)
    private Integer pkNotification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "generated_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String description;
    @JoinColumn(name = "fk_account_reciver", referencedColumnName = "pk_account")
    @ManyToOne
    private Account fkAccountReciver;
    @JoinColumn(name = "fk_notification_type", referencedColumnName = "pk_notification_type", nullable = false)
    @ManyToOne(optional = false)
    private NotificationType fkNotificationType;
    @JoinColumn(name = "fk_review", referencedColumnName = "pk_review")
    @ManyToOne
    private Review fkReview;

    public Notification() {
    }

    public Notification(Integer pkNotification) {
        this.pkNotification = pkNotification;
    }

    public Notification(Integer pkNotification, String title, Date generatedTime, String description) {
        this.pkNotification = pkNotification;
        this.title = title;
        this.generatedTime = generatedTime;
        this.description = description;
    }

    public Integer getPkNotification() {
        return pkNotification;
    }

    public void setPkNotification(Integer pkNotification) {
        this.pkNotification = pkNotification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(Date generatedTime) {
        this.generatedTime = generatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getFkAccountReciver() {
        return fkAccountReciver;
    }

    public void setFkAccountReciver(Account fkAccountReciver) {
        this.fkAccountReciver = fkAccountReciver;
    }

    public NotificationType getFkNotificationType() {
        return fkNotificationType;
    }

    public void setFkNotificationType(NotificationType fkNotificationType) {
        this.fkNotificationType = fkNotificationType;
    }

    public Review getFkReview() {
        return fkReview;
    }

    public void setFkReview(Review fkReview) {
        this.fkReview = fkReview;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkNotification != null ? pkNotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.pkNotification == null && other.pkNotification != null) || (this.pkNotification != null && !this.pkNotification.equals(other.pkNotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Notification[ pkNotification=" + pkNotification + " ]";
    }
    
}
