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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author isaura
 */
@Entity
@Table(catalog = "xtragou", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByPkAccount", query = "SELECT a FROM Account a WHERE a.pkAccount = :pkAccount"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findByProfilePicture", query = "SELECT a FROM Account a WHERE a.profilePicture = :profilePicture"),
    @NamedQuery(name = "Account.findByProfileCoverPicture", query = "SELECT a FROM Account a WHERE a.profileCoverPicture = :profileCoverPicture"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_account", nullable = false)
    private Integer pkAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String password;
    @Size(max = 2147483647)
    @Column(name = "profile_picture", length = 2147483647)
    private String profilePicture;
    @Size(max = 2147483647)
    @Column(name = "profile_cover_picture", length = 2147483647)
    private String profileCoverPicture;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccount")
    private List<UserSubServiceClassification> userSubServiceClassificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccountSender")
    private List<ChatMessage> chatMessageList;
    @OneToMany(mappedBy = "fkAccountReciver")
    private List<Notification> notificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccount1")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccount2")
    private List<Chat> chatList1;
    @JoinColumn(name = "fk_person", referencedColumnName = "pk_person")
    @ManyToOne
    private Person fkPerson;
    @JoinColumn(name = "fk_type_of_account", referencedColumnName = "pk_type_of_account")
    @ManyToOne
    private TypeOfAccount fkTypeOfAccount;

    public Account() {
    }

    public Account(Integer pkAccount) {
        this.pkAccount = pkAccount;
    }

    public Account(Integer pkAccount, String username, String password, String email) {
        this.pkAccount = pkAccount;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getPkAccount() {
        return pkAccount;
    }

    public void setPkAccount(Integer pkAccount) {
        this.pkAccount = pkAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfileCoverPicture() {
        return profileCoverPicture;
    }

    public void setProfileCoverPicture(String profileCoverPicture) {
        this.profileCoverPicture = profileCoverPicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<UserSubServiceClassification> getUserSubServiceClassificationList() {
        return userSubServiceClassificationList;
    }

    public void setUserSubServiceClassificationList(List<UserSubServiceClassification> userSubServiceClassificationList) {
        this.userSubServiceClassificationList = userSubServiceClassificationList;
    }

    @XmlTransient
    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @XmlTransient
    public List<Chat> getChatList1() {
        return chatList1;
    }

    public void setChatList1(List<Chat> chatList1) {
        this.chatList1 = chatList1;
    }

    public Person getFkPerson() {
        return fkPerson;
    }

    public void setFkPerson(Person fkPerson) {
        this.fkPerson = fkPerson;
    }

    public TypeOfAccount getFkTypeOfAccount() {
        return fkTypeOfAccount;
    }

    public void setFkTypeOfAccount(TypeOfAccount fkTypeOfAccount) {
        this.fkTypeOfAccount = fkTypeOfAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAccount != null ? pkAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.pkAccount == null && other.pkAccount != null) || (this.pkAccount != null && !this.pkAccount.equals(other.pkAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xtragou.xtragoubackend.entities.Account[ pkAccount=" + pkAccount + " ]";
    }
    
}
