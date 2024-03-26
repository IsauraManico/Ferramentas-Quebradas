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
@Table(catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPkPerson", query = "SELECT p FROM Person p WHERE p.pkPerson = :pkPerson"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByPhoneNumber", query = "SELECT p FROM Person p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Person.findByBirthDate", query = "SELECT p FROM Person p WHERE p.birthDate = :birthDate"),
    @NamedQuery(name = "Person.findByLatitude", query = "SELECT p FROM Person p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Person.findByLongitude", query = "SELECT p FROM Person p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Person.findByLocationDescription", query = "SELECT p FROM Person p WHERE p.locationDescription = :locationDescription")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_person", nullable = false)
    private Integer pkPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "phone_number", nullable = false, length = 2147483647)
    private String phoneNumber;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double latitude;
    @Column(precision = 17, scale = 17)
    private Double longitude;
    @Size(max = 2147483647)
    @Column(name = "location_description", length = 2147483647)
    private String locationDescription;
    @OneToMany(mappedBy = "fkPerson")
    private List<Technician> technicianList;
    @OneToMany(mappedBy = "fkPerson")
    private List<NormalUser> normalUserList;
    @JoinColumn(name = "fk_gender", referencedColumnName = "pk_gender")
    @ManyToOne
    private Gender fkGender;
    @OneToMany(mappedBy = "fkPerson")
    private List<Account> accountList;

    public Person() {
    }

    public Person(Integer pkPerson) {
        this.pkPerson = pkPerson;
    }

    public Person(Integer pkPerson, String name, String phoneNumber) {
        this.pkPerson = pkPerson;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Integer getPkPerson() {
        return pkPerson;
    }

    public void setPkPerson(Integer pkPerson) {
        this.pkPerson = pkPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    @XmlTransient
    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public void setTechnicianList(List<Technician> technicianList) {
        this.technicianList = technicianList;
    }

    @XmlTransient
    public List<NormalUser> getNormalUserList() {
        return normalUserList;
    }

    public void setNormalUserList(List<NormalUser> normalUserList) {
        this.normalUserList = normalUserList;
    }

    public Gender getFkGender() {
        return fkGender;
    }

    public void setFkGender(Gender fkGender) {
        this.fkGender = fkGender;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPerson != null ? pkPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.pkPerson == null && other.pkPerson != null) || (this.pkPerson != null && !this.pkPerson.equals(other.pkPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Person[ pkPerson=" + pkPerson + " ]";
    }
    
}
