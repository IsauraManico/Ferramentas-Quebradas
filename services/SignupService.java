package com.ferramentas.ferramentasbackend.services;

import com.ferramentas.ferramentasbackend.entities.*;
import com.ferramentas.ferramentasbackend.repository.*;
import com.ferramentas.ferramentasbackend.dto.input.SignupDto;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.xtragou.xtragoubackend.entities.*;
import com.ferramentas.ferramentasbackend.mappers.AccountMapper;
import com.ferramentas.ferramentasbackend.mappers.SignupDtoMapper;
import com.xtragou.xtragoubackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SignupService {
    private final SignupDtoMapper signupDtoMapper;
    private final PersonRepository personRepository;
    private final TypeOfAccountRepository typeOfAccountRepository;
    private final ServiceRepository serviceRepository;
    private final AccountRepository accountRepository;
    private final TechnicianRepository technicianRepository;
    private final TechnicianServiceRepository technicianServiceRepository;
    private final TechnicianServiceAssistance technicianServiceAssistance;
    private final NormalUserRepository normalUserRepository;

    public SignupService(
            SignupDtoMapper signupDtoMapper,
            PersonRepository personRepository,
            TypeOfAccountRepository typeOfAccountRepository,
            ServiceRepository serviceRepository,
            AccountRepository accountRepository, TechnicianRepository technicianRepository, TechnicianServiceRepository technicianServiceRepository, TechnicianServiceAssistance technicianServiceAssistance, NormalUserRepository normalUserRepository) {
        this.signupDtoMapper = signupDtoMapper;
        this.personRepository = personRepository;
        this.typeOfAccountRepository = typeOfAccountRepository;
        this.serviceRepository = serviceRepository;
        this.accountRepository = accountRepository;
        this.technicianRepository = technicianRepository;
        this.technicianServiceRepository = technicianServiceRepository;
        this.technicianServiceAssistance = technicianServiceAssistance;
        this.normalUserRepository = normalUserRepository;
    }

    @Transactional
    public ResponseEntity<Object> createUser(SignupDto signupDto) {

        Optional<TypeOfAccount> typeOfAccountOptional = findTypeOfAccount(signupDto);

        if (typeOfAccountOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Body");
        }

        Person person = createPerson(signupDto);


        switch (typeOfAccountOptional.get().getPkTypeOfAccount()) {
            case 1:
                ResponseEntity<Object> clientResponse = createClient(person, signupDto);

                return clientResponse;
            case 2:
                ResponseEntity<Object> technicianResponse = createTechnician(person, signupDto);

                return technicianResponse;
            default:
                return ResponseEntity.badRequest().body("Invalid Body");
        }


    }

    private ResponseEntity<Object> createTechnician(Person person, SignupDto signupDto) {
        List<Service> services = findServices(signupDto);

        if (services.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Technician newTechnician = new Technician();

        newTechnician.setFkPerson(person);

        technicianRepository.save(newTechnician);

        List<TechnicianService> technicianServices = services.stream().map(service -> {
            TechnicianService technicianService = new TechnicianService();

            technicianService.setFkTechnician(newTechnician);
            technicianService.setFkService(service);

            return technicianService;
        }).collect(Collectors.toList());

        technicianServiceRepository.saveAll(technicianServices);

        Account account = signupDtoMapper.fromSignupDtoToAccount(signupDto);

        account.setFkPerson(person);
        account.setFkTypeOfAccount(new TypeOfAccount(2));


        accountRepository.save(account);

        Optional<TechnicianPresentationDto> technicianPresentationDtoOptional = technicianServiceAssistance
                .getSpecificTechnicianPresentationByPersonId(account.getFkPerson().getPkPerson());

        technicianPresentationDtoOptional.orElseThrow(() -> {
            throw new Error("No technician 1");
        });

        return ResponseEntity.ok().body(technicianPresentationDtoOptional.get());
    }

    private ResponseEntity<Object> createClient(Person person, SignupDto signupDto) {

        NormalUser normalUser = new NormalUser();

        normalUser.setFkPerson(person);
        normalUserRepository.save(normalUser);

        Account account = signupDtoMapper.fromSignupDtoToAccount(signupDto);

        account.setFkPerson(person);
        account.setFkTypeOfAccount(new TypeOfAccount(1));
        accountRepository.save(account);


        return ResponseEntity.ok(AccountMapper.INSTANCE.accountToAccountPresentationDto(account));
    }

    private List<Service> findServices(SignupDto signupDto) {
        Set<Integer> serviceList = signupDto.getServiceList();

        List<Service> servicesRetrived = serviceRepository.findAllById(serviceList);

        if (servicesRetrived.size() != serviceList.size()) {
            return List.of();
        }
        return servicesRetrived;
    }

    private Person createPerson(SignupDto signupDto) {
        Person person = signupDtoMapper.fromSignupDtoToPerson(signupDto);

        personRepository.save(person);

        return person;
    }

    private Optional<TypeOfAccount> findTypeOfAccount(SignupDto signupDto) {
        Integer accountType = signupDto.getAccountType();

        Optional<TypeOfAccount> typeOfAccountOptional = typeOfAccountRepository.findById(accountType);

        return typeOfAccountOptional;
    }
}
