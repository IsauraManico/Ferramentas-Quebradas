package com.ferramentas.ferramentasbackend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class ReviewPresentationDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String description;
    @NotNull
    private final double rating;
    @NotNull
    private final Date publishedTime;
    private final SmallPresentationUserDto user;

    public ReviewPresentationDto(Integer id, String description, double rating, Date publishedTime, SmallPresentationUserDto user) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.publishedTime = publishedTime;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public SmallPresentationUserDto getUser() {
        return user;
    }
}
