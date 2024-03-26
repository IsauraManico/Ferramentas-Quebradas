package com.ferramentas.ferramentasbackend.dto.input;

import com.ferramentas.ferramentasbackend.utils.endpoints.ImageUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class AccountResponseDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 2147483647)
    private final String username;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String profilePicture;
    private final String profileCoverPicture;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private final String email;

    public AccountResponseDto(String username, String profilePicture, String profileCoverPicture, String email) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.profileCoverPicture = profileCoverPicture;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePicture() {
        return profilePicture == null || profilePicture.equals("") || profilePicture.isBlank() ? ImageUtils.DEFAULT_APPLICATION_IMAGE : profilePicture;
    }

    public String getProfileCoverPicture() {
        return profileCoverPicture  == null || profileCoverPicture.equals("") ? ImageUtils.DEFAULT_APPLICATION_IMAGE : profileCoverPicture;
    }

    public String getEmail() {
        return email;
    }
}