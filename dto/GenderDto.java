package com.ferramentas.ferramentasbackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class GenderDto implements Serializable {
    @NotBlank
    @Size(min = 2)
    private String designation;

    public GenderDto() {
    }

    public GenderDto(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenderDto entity = (GenderDto) o;
        return Objects.equals(this.designation, entity.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "designation = " + designation + ")";
    }
}
