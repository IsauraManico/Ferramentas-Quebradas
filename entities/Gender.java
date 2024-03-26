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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isaura
 */
@Entity
@Table(catalog = "xtragou", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"designation"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gender.findAll", query = "SELECT g FROM Gender g"),
    @NamedQuery(name = "Gender.findByPkGender", query = "SELECT g FROM Gender g WHERE g.pkGender = :pkGender"),
    @NamedQuery(name = "Gender.findByDesignation", query = "SELECT g FROM Gender g WHERE g.designation = :designation")})
public class Gender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gender", nullable = false)
    private Integer pkGender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designation;
    @OneToMany(mappedBy = "fkGender")
    private List<Person> personList;

    public Gender() {
    }

    public Gender(Integer pkGender) {
        this.pkGender = pkGender;
    }

    public Gender(Integer pkGender, String designation) {
        this.pkGender = pkGender;
        this.designation = designation;
    }

    public Integer getPkGender() {
        return pkGender;
    }

    public void setPkGender(Integer pkGender) {
        this.pkGender = pkGender;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGender != null ? pkGender.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gender)) {
            return false;
        }
        Gender other = (Gender) object;
        if ((this.pkGender == null && other.pkGender != null) || (this.pkGender != null && !this.pkGender.equals(other.pkGender))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Gender[ pkGender=" + pkGender + " ]";
    }
    
}
