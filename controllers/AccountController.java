package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.input.AccountResponseDto;
import com.ferramentas.ferramentasbackend.dto.input.AccountUpdateDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.exceptions.ErrorMessage;
import com.ferramentas.ferramentasbackend.repository.AccountRepository;
import com.ferramentas.ferramentasbackend.services.files.FilesStorageServiceImpl;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(RouterUtils.ACCOUNT_ROUTE)
public class AccountController {

    private final AccountRepository accountRepository;
    private final  FilesStorageServiceImpl filesStorageService;

    public  AccountController(AccountRepository accountRepository, FilesStorageServiceImpl filesStorageService)
    {
        this.accountRepository = accountRepository;
        this.filesStorageService = filesStorageService;
    }

    @GetMapping(value = "/username_email/verification", params = {"username_email"})
    public ResponseEntity<Object> veriryUsernameAndEmail(@RequestParam("username_email") String username_email) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsernameEqualsOrEmailEquals(username_email);

        if (accountOptional.isEmpty()) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping(params = {"id"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateAccount(@RequestParam("id") Integer id, @Valid @ModelAttribute AccountUpdateDto accountUpdateDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Account account = optionalAccount.get();

        optionalAccount.ifPresent(a -> {
            if (!account.getUsername().equals(accountUpdateDto.getUsername())) {
                account.setUsername(accountUpdateDto.getUsername());
            }

            if (!account.getPassword().equals(accountUpdateDto.getPassword())) {
                account.setPassword(accountUpdateDto.getPassword());
            }

            if (accountUpdateDto.getProfilePicture() != null) {
                String fileName = filesStorageService.save(accountUpdateDto.getProfilePicture());
                account.setProfilePicture(fileName);
            }


            if (accountUpdateDto.getProfileCoverPicture() != null) {
                String fileName = filesStorageService.save(accountUpdateDto.getProfilePicture());

                account.setProfileCoverPicture(fileName);
            }

            accountRepository.save(account);

        });

        return ResponseEntity.ok().body(
                new AccountResponseDto(
                        account.getUsername(),
                        account.getProfilePicture(),
                        account.getProfileCoverPicture(),
                        account.getEmail()
                )
        );
    }
}
