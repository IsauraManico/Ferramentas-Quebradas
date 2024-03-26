package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.input.AuthInputDto;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.mappers.AccountMapper;
import com.ferramentas.ferramentasbackend.repository.AccountRepository;
import com.ferramentas.ferramentasbackend.services.TechnicianServiceAssistance;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(RouterUtils.AUTH_MAIN_ROUTE)
public class AuthController {
    private final AccountRepository accountRepository;
    private final TechnicianServiceAssistance technicianServiceAssistance;

    public AuthController(AccountRepository accountRepository, TechnicianServiceAssistance technicianServiceAssistance) {
        this.accountRepository = accountRepository;
        this.technicianServiceAssistance = technicianServiceAssistance;
    }

    @PostMapping
    public ResponseEntity<Object> verifyAuthentication(@RequestBody AuthInputDto authInputDto) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsernameEqualsAndPasswordEquals(authInputDto.getUsername(), authInputDto.getPassword());

        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Not valid login\"");
        }

        Account account = accountOptional.get();

        if (account.getFkTypeOfAccount().getPkTypeOfAccount() == 2) {
            Optional<TechnicianPresentationDto> technicianPresentationDtoOptional = technicianServiceAssistance.getSpecificTechnicianPresentationByPersonId(account.getFkPerson().getPkPerson());

            technicianPresentationDtoOptional.orElseThrow(() -> {
                throw new Error("No technician 1");
            });

            return ResponseEntity.ok().body(technicianPresentationDtoOptional.get());
        }


        return ResponseEntity.ok().body(AccountMapper.INSTANCE.accountToAccountPresentationDto(account));
    }
}
