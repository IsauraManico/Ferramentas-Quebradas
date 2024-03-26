package com.ferramentas.ferramentasbackend.dto;

import java.util.List;


public class TechnicianCreationDto {
    private Integer fkPerson;
    private List<Integer> services;

    public TechnicianCreationDto(Integer fkPerson, List<Integer> services) {
        this.fkPerson = fkPerson;
        this.services = services;
    }

    public Integer getFkPerson() {
        return fkPerson;
    }

    public void setFkPerson(Integer fkPerson) {
        this.fkPerson = fkPerson;
    }

    public List<Integer> getServices() {
        return services;
    }

    public void setServices(List<Integer> services) {
        this.services = services;
    }
}
