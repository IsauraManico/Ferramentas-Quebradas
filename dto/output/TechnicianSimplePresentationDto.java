package com.ferramentas.ferramentasbackend.dto.output;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


public class TechnicianSimplePresentationDto implements Serializable {
    private Integer id;
    private String name;
    @Size(max = 2147483647)
    private String location;

    public TechnicianSimplePresentationDto(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
