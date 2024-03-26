package com.ferramentas.ferramentasbackend.dto.output;

import com.ferramentas.ferramentasbackend.dto.PersonDto;

import java.io.Serializable;


public class NormalUserDto implements Serializable {
    private final Integer pkNormalUser;
    private final PersonDto person;

    public NormalUserDto(Integer pkNormalUser, PersonDto person) {
        this.pkNormalUser = pkNormalUser;
        this.person = person;
    }

    public Integer getPkNormalUser() {
        return pkNormalUser;
    }

    public PersonDto getPerson() {
        return person;
    }
}
