package com.ferramentas.ferramentasbackend.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupDto {
    @Size(min = 2)
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    private Integer accountType;
    private Set<Integer> serviceList;

    public SignupDto(String name, String email, String password, String phoneNumber, Integer accountType, Set<Integer> serviceList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
        this.serviceList = serviceList;
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Set<Integer> getServiceList() {
        return serviceList;
    }

    public void setServiceList(Set<Integer> serviceList) {
        this.serviceList = serviceList;
    }
}
