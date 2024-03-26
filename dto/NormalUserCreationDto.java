package com.ferramentas.ferramentasbackend.dto;

import java.io.Serializable;


public class NormalUserCreationDto implements Serializable {
    private Integer personId;

    public NormalUserCreationDto(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
