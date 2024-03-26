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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "technician_service", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TechnicianService.findAll", query = "SELECT t FROM TechnicianService t"),
    @NamedQuery(name = "TechnicianService.findByPkTechnicianService", query = "SELECT t FROM TechnicianService t WHERE t.pkTechnicianService = :pkTechnicianService"),
    @NamedQuery(name = "TechnicianService.findByCreatedAt", query = "SELECT t FROM TechnicianService t WHERE t.createdAt = :createdAt")})
public class TechnicianService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_technician_service", nullable = false)
    private Integer pkTechnicianService;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fk_service", referencedColumnName = "pk_service")
    @ManyToOne
    private Service fkService;
    @JoinColumn(name = "fk_technician", referencedColumnName = "pk_technician")
    @ManyToOne
    private Technician fkTechnician;

    public TechnicianService() {
    }

    public TechnicianService(Integer pkTechnicianService) {
        this.pkTechnicianService = pkTechnicianService;
    }

    public Integer getPkTechnicianService() {
        return pkTechnicianService;
    }

    public void setPkTechnicianService(Integer pkTechnicianService) {
        this.pkTechnicianService = pkTechnicianService;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTechnicianService != null ? pkTechnicianService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TechnicianService)) {
            return false;
        }
        TechnicianService other = (TechnicianService) object;
        if ((this.pkTechnicianService == null && other.pkTechnicianService != null) || (this.pkTechnicianService != null && !this.pkTechnicianService.equals(other.pkTechnicianService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.TechnicianService[ pkTechnicianService=" + pkTechnicianService + " ]";
    }
    
}
