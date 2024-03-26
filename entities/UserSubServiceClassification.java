/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtragou.xtragoubackend.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isaura
 */
@Entity
@Table(name = "user_sub_service_classification", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSubServiceClassification.findAll", query = "SELECT u FROM UserSubServiceClassification u"),
    @NamedQuery(name = "UserSubServiceClassification.findByPkUserSubServiceClassification", query = "SELECT u FROM UserSubServiceClassification u WHERE u.pkUserSubServiceClassification = :pkUserSubServiceClassification"),
    @NamedQuery(name = "UserSubServiceClassification.findByIsAllowedClassification", query = "SELECT u FROM UserSubServiceClassification u WHERE u.isAllowedClassification = :isAllowedClassification")})
public class UserSubServiceClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_user_sub_service_classification", nullable = false)
    private Integer pkUserSubServiceClassification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_allowed_classification", nullable = false)
    private boolean isAllowedClassification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "technician_perform_action", nullable = false)
    private boolean technicianPerformAction;
    @JoinColumn(name = "fk_account", referencedColumnName = "pk_account", nullable = false)
    @ManyToOne(optional = false)
    private Account fkAccount;
    @JoinColumn(name = "fk_sub_service", referencedColumnName = "pk_sub_service", nullable = false)
    @ManyToOne(optional = false)
    private SubService fkSubService;

    public UserSubServiceClassification() {
    }

    public UserSubServiceClassification(Integer pkUserSubServiceClassification) {
        this.pkUserSubServiceClassification = pkUserSubServiceClassification;
    }

    public UserSubServiceClassification(Integer pkUserSubServiceClassification, boolean isAllowedClassification) {
        this.pkUserSubServiceClassification = pkUserSubServiceClassification;
        this.isAllowedClassification = isAllowedClassification;
    }

    public Integer getPkUserSubServiceClassification() {
        return pkUserSubServiceClassification;
    }

    public void setPkUserSubServiceClassification(Integer pkUserSubServiceClassification) {
        this.pkUserSubServiceClassification = pkUserSubServiceClassification;
    }

    public boolean getIsAllowedClassification() {
        return isAllowedClassification;
    }

    public void setIsAllowedClassification(boolean isAllowedClassification) {
        this.isAllowedClassification = isAllowedClassification;
    }

    public Account getFkAccount() {
        return fkAccount;
    }

    public void setFkAccount(Account fkAccount) {
        this.fkAccount = fkAccount;
    }

    public SubService getFkSubService() {
        return fkSubService;
    }

    public void setFkSubService(SubService fkSubService) {
        this.fkSubService = fkSubService;
    }

    public boolean isTechnicianPerformAction() {
        return technicianPerformAction;
    }

    public void setTechnicianPerformAction(boolean technicianPerformAction) {
        this.technicianPerformAction = technicianPerformAction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUserSubServiceClassification != null ? pkUserSubServiceClassification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSubServiceClassification)) {
            return false;
        }
        UserSubServiceClassification other = (UserSubServiceClassification) object;
        if ((this.pkUserSubServiceClassification == null && other.pkUserSubServiceClassification != null) || (this.pkUserSubServiceClassification != null && !this.pkUserSubServiceClassification.equals(other.pkUserSubServiceClassification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.UserSubServiceClassification[ pkUserSubServiceClassification=" + pkUserSubServiceClassification + " ]";
    }
    
}
