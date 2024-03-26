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
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByPkReview", query = "SELECT r FROM Review r WHERE r.pkReview = :pkReview"),
    @NamedQuery(name = "Review.findByDescription", query = "SELECT r FROM Review r WHERE r.description = :description"),
    @NamedQuery(name = "Review.findByRating", query = "SELECT r FROM Review r WHERE r.rating = :rating"),
    @NamedQuery(name = "Review.findByPublishedTime", query = "SELECT r FROM Review r WHERE r.publishedTime = :publishedTime")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_review", nullable = false)
    private Integer pkReview;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double rating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "published_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedTime;
    @OneToMany(mappedBy = "fkReview")
    private List<Notification> notificationList;
    @JoinColumn(name = "fk_normal_user", referencedColumnName = "pk_normal_user")
    @ManyToOne
    private NormalUser fkNormalUser;
    @JoinColumn(name = "fk_sub_service", referencedColumnName = "pk_sub_service")
    @ManyToOne
    private SubService fkSubService;

    public Review() {
    }

    public Review(Integer pkReview) {
        this.pkReview = pkReview;
    }

    public Review(Integer pkReview, String description, double rating, Date publishedTime) {
        this.pkReview = pkReview;
        this.description = description;
        this.rating = rating;
        this.publishedTime = publishedTime;
    }

    public Integer getPkReview() {
        return pkReview;
    }

    public void setPkReview(Integer pkReview) {
        this.pkReview = pkReview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public NormalUser getFkNormalUser() {
        return fkNormalUser;
    }

    public void setFkNormalUser(NormalUser fkNormalUser) {
        this.fkNormalUser = fkNormalUser;
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
        hash += (pkReview != null ? pkReview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.pkReview == null && other.pkReview != null) || (this.pkReview != null && !this.pkReview.equals(other.pkReview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Review[ pkReview=" + pkReview + " ]";
    }
    
}
