package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class ServicePresentationDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String title;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String image;

    public ServicePresentationDto(Integer id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
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
}
