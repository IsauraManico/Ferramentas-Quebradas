package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import java.io.Serializable;

public class AccountPresentationDto implements Serializable {
    private Integer id;
    private String username;
    private String image;
    private String backgroundImage;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image == null || image.equals("") || image.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackgroundImage() {
        return backgroundImage == null || backgroundImage.equals("") || backgroundImage.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
