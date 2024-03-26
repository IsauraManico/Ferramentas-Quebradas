package com.ferramentas.ferramentasbackend.dto.input;

import javax.validation.constraints.*;

public class NewReviewInputDto {
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    @NotNull
    private int publisherId;
    @NotNull
    private int subServiceId;

    public NewReviewInputDto(String description, int rating, int publisherId, int subServiceId) {
        this.description = description;
        this.rating = rating;
        this.publisherId = publisherId;
        this.subServiceId = subServiceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(int subServiceId) {
        this.subServiceId = subServiceId;
    }
}
