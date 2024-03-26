package com.ferramentas.ferramentasbackend.dto;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SmallPresentationUserDto implements Serializable {
    @Size(max = 2147483647)
    private final String image;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String name;

    public SmallPresentationUserDto(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image  == null || image.equals("") || image.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : image;
    }

    public String getName() {
        return name;
    }
}
