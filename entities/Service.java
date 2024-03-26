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
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByPkService", query = "SELECT s FROM Service s WHERE s.pkService = :pkService"),
    @NamedQuery(name = "Service.findByTitle", query = "SELECT s FROM Service s WHERE s.title = :title"),
    @NamedQuery(name = "Service.findByImage", query = "SELECT s FROM Service s WHERE s.image = :image"),
    @NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description = :description")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_service", nullable = false)
    private Integer pkService;
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
    @OneToMany(mappedBy = "fkService")
    private List<SubService> subServiceList;
    @OneToMany(mappedBy = "fkService")
    private List<TechnicianService> technicianServiceList;

    public Service() {
    }

    public Service(Integer pkService) {
        this.pkService = pkService;
    }

    public Service(Integer pkService, String title, String image, String description) {
        this.pkService = pkService;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Integer getPkService() {
        return pkService;
    }

    public void setPkService(Integer pkService) {
        this.pkService = pkService;
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

    @XmlTransient
    public List<SubService> getSubServiceList() {
        return subServiceList;
    }

    public void setSubServiceList(List<SubService> subServiceList) {
        this.subServiceList = subServiceList;
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
        hash += (pkService != null ? pkService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.pkService == null && other.pkService != null) || (this.pkService != null && !this.pkService.equals(other.pkService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Service[ pkService=" + pkService + " ]";
    }
    
}
