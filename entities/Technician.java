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
@Table(catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technician.findAll", query = "SELECT t FROM Technician t"),
    @NamedQuery(name = "Technician.findByPkTechnician", query = "SELECT t FROM Technician t WHERE t.pkTechnician = :pkTechnician")})
public class Technician implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_technician", nullable = false)
    private Integer pkTechnician;
    @OneToMany(mappedBy = "fkTechnician")
    private List<SubService> subServiceList;
    @JoinColumn(name = "fk_person", referencedColumnName = "pk_person")
    @ManyToOne
    private Person fkPerson;
    @OneToMany(mappedBy = "fkTechnician")
    private List<TechnicianService> technicianServiceList;

    public Technician() {
    }

    public Technician(Integer pkTechnician) {
        this.pkTechnician = pkTechnician;
    }

    public Integer getPkTechnician() {
        return pkTechnician;
    }

    public void setPkTechnician(Integer pkTechnician) {
        this.pkTechnician = pkTechnician;
    }

    @XmlTransient
    public List<SubService> getSubServiceList() {
        return subServiceList;
    }

    public void setSubServiceList(List<SubService> subServiceList) {
        this.subServiceList = subServiceList;
    }

    public Person getFkPerson() {
        return fkPerson;
    }

    public void setFkPerson(Person fkPerson) {
        this.fkPerson = fkPerson;
    }

    @XmlTransient
    public List<TechnicianService> getTechnicianServiceList() {
        return technicianServiceList;
    }

    public void setTechnicianServiceList(List<TechnicianService> technicianServiceList) {
        this.technicianServiceList = technicianServiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTechnician != null ? pkTechnician.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Technician)) {
            return false;
        }
        Technician other = (Technician) object;
        if ((this.pkTechnician == null && other.pkTechnician != null) || (this.pkTechnician != null && !this.pkTechnician.equals(other.pkTechnician))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Technician[ pkTechnician=" + pkTechnician + " ]";
    }
    
}
