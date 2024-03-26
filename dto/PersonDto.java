package com.ferramentas.ferramentasbackend.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonDto implements Serializable {

    private String name;
    private String phoneNumber;
    private Date birthDate;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private Integer genderId;

    public PersonDto(String name, String phoneNumber, Date birthDate, Double latitude, Double longitude, String locationDescription, Integer genderId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationDescription = locationDescription;
        this.genderId = genderId;
    }

    public PersonDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }
}
