package com.ferramentas.ferramentasbackend.dto.input;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class SubServiceCreationDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String title;
    @NotNull
    private MultipartFile image;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String description;
    @NotNull
    @Min(value = 0)
    private double minPrice;
    @NotNull
    @Min(value = 0)
    private double maxPrice;
    @NotNull
    private Integer service;
    @NotNull
    private Integer technician;

    public SubServiceCreationDto(String title, MultipartFile image, String description, double minPrice, double maxPrice, Integer service, Integer technician) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.service = service;
        this.technician = technician;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }
}
