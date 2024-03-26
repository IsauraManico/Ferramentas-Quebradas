package com.ferramentas.ferramentasbackend.dto.input;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class AccountUpdateDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 2147483647)
    private final String username;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String password;
    private final MultipartFile profilePicture;
    private final MultipartFile profileCoverPicture;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String email;

    public AccountUpdateDto(String username, String password, MultipartFile profilePicture, MultipartFile profileCoverPicture, String email) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.profileCoverPicture = profileCoverPicture;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public MultipartFile getProfileCoverPicture() {
        return profileCoverPicture;
    }

    public String getEmail() {
        return email;
    }
}
