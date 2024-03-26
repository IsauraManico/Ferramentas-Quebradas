package com.ferramentas.ferramentasbackend.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthInputDto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;

    public AuthInputDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username.trim();
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
