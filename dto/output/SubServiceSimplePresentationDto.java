package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class SubServiceSimplePresentationDto implements Serializable {
    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String title;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String image;
    @NotNull
    private double rating;
    private TechnicianSimplePresentationDto technician;

    public SubServiceSimplePresentationDto(Integer id, String title, String image, double rating, TechnicianSimplePresentationDto technician) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.technician = technician;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image  == null || image.equals("") || image.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public TechnicianSimplePresentationDto getTechnician() {
        return technician;
    }

    public void setTechnician(TechnicianSimplePresentationDto technician) {
        this.technician = technician;
    }
}
