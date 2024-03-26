package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class SubServicePresentationDto implements Serializable {
    @NotNull
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String title;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String image;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String description;
    @NotNull
    private final double minPrice;
    @NotNull
    private final double maxPrice;
    @NotNull
    private final double rating;
    private final TechnicianPresentationDto technician;
    private final ServicePresentationDto service;

    public SubServicePresentationDto(Integer id, String title, String image, String description, double minPrice, double maxPrice, double rating, TechnicianPresentationDto technician, ServicePresentationDto service) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.rating = rating;
        this.technician = technician;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image  == null || image.equals("") || image.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : image;
    }

    public String getDescription() {
        return description;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getRating() {
        return rating;
    }

    public TechnicianPresentationDto getTechnician() {
        return technician;
    }

    public ServicePresentationDto getService() {
        return service;
    }
}
