package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


public class TechnicianPresentationDto implements Serializable {
    private final Integer id;
    private final Integer account;
    private final String image;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String name;
    @Size(max = 2147483647)
    private final String location;
    private final List<String> services;
    private final Float ranking = 0.0f;

    public TechnicianPresentationDto(Integer id, Integer account, String image, String name, String location, List<String> services) {
        this.id = id;
        this.account = account;
        this.image = image;
        this.name = name;
        this.location = location;
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccount() {
        return account;
    }

    public String getImage() {
        return image  == null || image.equals("") || image.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getServices() {
        return services;
    }

    public Float getRanking() {
        return ranking;
    }
}
