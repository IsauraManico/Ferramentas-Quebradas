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
@Table(name = "type_of_account", catalog = "xtragou", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOfAccount.findAll", query = "SELECT t FROM TypeOfAccount t"),
    @NamedQuery(name = "TypeOfAccount.findByPkTypeOfAccount", query = "SELECT t FROM TypeOfAccount t WHERE t.pkTypeOfAccount = :pkTypeOfAccount"),
    @NamedQuery(name = "TypeOfAccount.findByDesignation", query = "SELECT t FROM TypeOfAccount t WHERE t.designation = :designation")})
public class TypeOfAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_type_of_account", nullable = false)
    private Integer pkTypeOfAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designation;
    @OneToMany(mappedBy = "fkTypeOfAccount")
    private List<Account> accountList;

    public TypeOfAccount() {
    }

    public TypeOfAccount(Integer pkTypeOfAccount) {
        this.pkTypeOfAccount = pkTypeOfAccount;
    }

    public TypeOfAccount(Integer pkTypeOfAccount, String designation) {
        this.pkTypeOfAccount = pkTypeOfAccount;
        this.designation = designation;
    }

    public Integer getPkTypeOfAccount() {
        return pkTypeOfAccount;
    }

    public void setPkTypeOfAccount(Integer pkTypeOfAccount) {
        this.pkTypeOfAccount = pkTypeOfAccount;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
        hash += (pkTypeOfAccount != null ? pkTypeOfAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeOfAccount)) {
            return false;
        }
        TypeOfAccount other = (TypeOfAccount) object;
        if ((this.pkTypeOfAccount == null && other.pkTypeOfAccount != null) || (this.pkTypeOfAccount != null && !this.pkTypeOfAccount.equals(other.pkTypeOfAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.TypeOfAccount[ pkTypeOfAccount=" + pkTypeOfAccount + " ]";
    }
    
}
