package com.ferramentas.ferramentasbackend.dto.input;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class ServiceCreationDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String title;
    @NotNull
    private final MultipartFile image;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String description;

    public ServiceCreationDto(String title, MultipartFile image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
