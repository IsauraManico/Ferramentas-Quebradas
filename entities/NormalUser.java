/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "normal_user", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NormalUser.findAll", query = "SELECT n FROM NormalUser n"),
    @NamedQuery(name = "NormalUser.findByPkNormalUser", query = "SELECT n FROM NormalUser n WHERE n.pkNormalUser = :pkNormalUser")})
public class NormalUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_normal_user", nullable = false)
    private Integer pkNormalUser;
    @JoinColumn(name = "fk_person", referencedColumnName = "pk_person")
    @ManyToOne
    private Person fkPerson;
    @OneToMany(mappedBy = "fkNormalUser")
    private List<Review> reviewList;

    public NormalUser() {
    }

    public NormalUser(Integer pkNormalUser) {
        this.pkNormalUser = pkNormalUser;
    }

    public Integer getPkNormalUser() {
        return pkNormalUser;
    }

    public void setPkNormalUser(Integer pkNormalUser) {
        this.pkNormalUser = pkNormalUser;
    }

    public Person getFkPerson() {
        return fkPerson;
    }

    public void setFkPerson(Person fkPerson) {
        this.fkPerson = fkPerson;
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
        hash += (pkNormalUser != null ? pkNormalUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NormalUser)) {
            return false;
        }
        NormalUser other = (NormalUser) object;
        if ((this.pkNormalUser == null && other.pkNormalUser != null) || (this.pkNormalUser != null && !this.pkNormalUser.equals(other.pkNormalUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.NormalUser[ pkNormalUser=" + pkNormalUser + " ]";
    }
    
}
