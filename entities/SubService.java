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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sub_service", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubService.findAll", query = "SELECT s FROM SubService s"),
    @NamedQuery(name = "SubService.findByPkSubService", query = "SELECT s FROM SubService s WHERE s.pkSubService = :pkSubService"),
    @NamedQuery(name = "SubService.findByTitle", query = "SELECT s FROM SubService s WHERE s.title = :title"),
    @NamedQuery(name = "SubService.findByImage", query = "SELECT s FROM SubService s WHERE s.image = :image"),
    @NamedQuery(name = "SubService.findByDescription", query = "SELECT s FROM SubService s WHERE s.description = :description"),
    @NamedQuery(name = "SubService.findByMinPrice", query = "SELECT s FROM SubService s WHERE s.minPrice = :minPrice"),
    @NamedQuery(name = "SubService.findByMaxPrice", query = "SELECT s FROM SubService s WHERE s.maxPrice = :maxPrice"),
    @NamedQuery(name = "SubService.findByRating", query = "SELECT s FROM SubService s WHERE s.rating = :rating")})
public class SubService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_sub_service", nullable = false)
    private Integer pkSubService;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_price", nullable = false)
    private double minPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_price", nullable = false)
    private double maxPrice;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double rating;
    @JoinColumn(name = "fk_service", referencedColumnName = "pk_service")
    @ManyToOne
    private Service fkService;
    @JoinColumn(name = "fk_technician", referencedColumnName = "pk_technician")
    @ManyToOne
    private Technician fkTechnician;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSubService")
    private List<UserSubServiceClassification> userSubServiceClassificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSubService")
    private List<ChatSubServiceMessage> chatSubServiceMessageList;
    @OneToMany(mappedBy = "fkSubService")
    private List<Review> reviewList;

    public SubService() {
    }

    public SubService(Integer pkSubService) {
        this.pkSubService = pkSubService;
    }

    public SubService(Integer pkSubService, String title, String image, String description, double minPrice, double maxPrice, double rating) {
        this.pkSubService = pkSubService;
        this.title = title;
        this.image = image;
        this.description = description;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.rating = rating;
    }

    public Integer getPkSubService() {
        return pkSubService;
    }

    public void setPkSubService(Integer pkSubService) {
        this.pkSubService = pkSubService;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Service getFkService() {
        return fkService;
    }

    public void setFkService(Service fkService) {
        this.fkService = fkService;
    }

    public Technician getFkTechnician() {
        return fkTechnician;
    }

    public void setFkTechnician(Technician fkTechnician) {
        this.fkTechnician = fkTechnician;
    }

    @XmlTransient
    public List<UserSubServiceClassification> getUserSubServiceClassificationList() {
        return userSubServiceClassificationList;
    }

    public void setUserSubServiceClassificationList(List<UserSubServiceClassification> userSubServiceClassificationList) {
        this.userSubServiceClassificationList = userSubServiceClassificationList;
    }

    @XmlTransient
    public List<ChatSubServiceMessage> getChatSubServiceMessageList() {
        return chatSubServiceMessageList;
    }

    public void setChatSubServiceMessageList(List<ChatSubServiceMessage> chatSubServiceMessageList) {
        this.chatSubServiceMessageList = chatSubServiceMessageList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSubService != null ? pkSubService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubService)) {
            return false;
        }
        SubService other = (SubService) object;
        if ((this.pkSubService == null && other.pkSubService != null) || (this.pkSubService != null && !this.pkSubService.equals(other.pkSubService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.SubService[ pkSubService=" + pkSubService + " ]";
    }
    
}
