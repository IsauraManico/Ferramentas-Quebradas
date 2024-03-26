package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.input.SignupDto;
import com.ferramentas.ferramentasbackend.services.SignupService;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(RouterUtils.SIGNUP_MAIN_ROUTE)
public class SignupController {

    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody SignupDto signupDto) {
        return signupService.createUser(signupDto);
    }
}
