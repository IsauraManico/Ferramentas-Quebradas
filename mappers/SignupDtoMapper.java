package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Person;
import com.ferramentas.ferramentasbackend.entities.Service;
import com.ferramentas.ferramentasbackend.dto.input.SignupDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.entities.TypeOfAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignupDtoMapper {
    public Person fromSignupDtoToPerson(SignupDto signupDto) {
        Person person = new Person();

        person.setName(signupDto.getName());
        person.setPhoneNumber(signupDto.getPhoneNumber());

        return person;
    }

    public Account fromSignupDtoToAccount(SignupDto signupDto) {
        Account account = new Account();

        account.setUsername(signupDto.getEmail());
        account.setEmail(signupDto.getEmail());
        account.setPassword(signupDto.getPassword());

        return account;
    }

    public List<Service> fromSignupDtoToServiceList(SignupDto signupDto) {
        List<Service> services = new ArrayList<>();

        signupDto.getServiceList().forEach(service -> {
            services.add(new Service(service));
        });

        return services;
    }

    public TypeOfAccount fromSignupDtoToAccountType(SignupDto signupDto) {
        Integer accountType = signupDto.getAccountType();


        return new TypeOfAccount(accountType);
    }
}
