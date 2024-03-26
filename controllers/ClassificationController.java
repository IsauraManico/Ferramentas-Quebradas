package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.repository.TechnicianRepository;
import com.ferramentas.ferramentasbackend.dto.ClassificationVerificationDto;
import com.ferramentas.ferramentasbackend.dto.input.AllowClassificationDto;
import com.ferramentas.ferramentasbackend.dto.output.ClassificationSubServiceDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.entities.SubService;
import com.ferramentas.ferramentasbackend.entities.Technician;
import com.ferramentas.ferramentasbackend.entities.UserSubServiceClassification;
import com.ferramentas.ferramentasbackend.mappers.ClassificationMapper;
import com.ferramentas.ferramentasbackend.repository.AccountRepository;
import com.ferramentas.ferramentasbackend.repository.SubServiceRepository;
import com.ferramentas.ferramentasbackend.repository.UserSubServiceClassificationRepository;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(RouterUtils.CLASSIFICATION_MAIN_ROUTE)
public class ClassificationController {
    private final UserSubServiceClassificationRepository userSubServiceClassificationRepository;
    private final AccountRepository accountRepository;
    private final SubServiceRepository subServiceRepository;
    private final TechnicianRepository technicianRepository;

    public ClassificationController(
            UserSubServiceClassificationRepository userSubServiceClassificationRepository,
            AccountRepository accountRepository,
            SubServiceRepository subServiceRepository,
            TechnicianRepository technicianRepository) {
        this.userSubServiceClassificationRepository = userSubServiceClassificationRepository;
        this.accountRepository = accountRepository;
        this.subServiceRepository = subServiceRepository;
        this.technicianRepository = technicianRepository;
    }

    @PostMapping("/user/sub_service")
    public ResponseEntity<ClassificationSubServiceDto> userCanClassifySubService(@RequestBody ClassificationVerificationDto classificationVerificationDto) {
        Optional<Account> userAccountOptional = accountRepository.findById(classificationVerificationDto.getAccount());
        Optional<SubService> subServiceOptional = subServiceRepository.findById(classificationVerificationDto.getSubService());


        if (userAccountOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (subServiceOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserSubServiceClassification> userSubServiceClassificationOptional = userSubServiceClassificationRepository
                .findByFkAccount_PkAccountAndFkSubService_PkSubService(
                        classificationVerificationDto.getAccount(),
                        classificationVerificationDto.getSubService()
                );

        if (userSubServiceClassificationOptional.isEmpty()) {
            UserSubServiceClassification userSubServiceClassification = new UserSubServiceClassification();

            userSubServiceClassification.setFkAccount(userAccountOptional.get());
            userSubServiceClassification.setFkSubService(subServiceOptional.get());
            userSubServiceClassification.setTechnicianPerformAction(false);
            userSubServiceClassification.setIsAllowedClassification(false);

            ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                    .INSTANCE
                    .userSubServiceClassificationToClassificationSubService(userSubServiceClassification);

            return ResponseEntity.ok().body(classificationSubServiceDto);
        }

        ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                .INSTANCE
                .userSubServiceClassificationToClassificationSubService(userSubServiceClassificationOptional.get());

        return ResponseEntity.ok().body(classificationSubServiceDto);
    }

    @PostMapping("/allow")
    public ResponseEntity<ClassificationSubServiceDto> allowClassification(@RequestBody AllowClassificationDto allowClassificationDto) {
        Optional<Account> technicianAccountOptional = accountRepository.findById(allowClassificationDto.getTechnicianAccount());
        Optional<Account> userAccountOptional = accountRepository.findById(allowClassificationDto.getUserAccount());


        if (technicianAccountOptional.isEmpty() || userAccountOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Technician>  technicianOptional = technicianRepository.findByFkPerson_PkPerson(
                technicianAccountOptional.get().getFkPerson().getPkPerson()
        );

        if (technicianOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        Optional<SubService> technicianSubServiceOptional = subServiceRepository.findSubServiceByPkSubServiceAndFkTechnician_PkTechnician(
                allowClassificationDto.getSubService(),
                technicianOptional.get().getPkTechnician()
        );

        if (technicianSubServiceOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserSubServiceClassification> result = userSubServiceClassificationRepository
                .findByFkAccount_PkAccountAndFkSubService_PkSubService(
                        allowClassificationDto.getUserAccount(),
                        allowClassificationDto.getSubService()
                );

        if (result.isPresent()) {
            ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                    .INSTANCE
                    .userSubServiceClassificationToClassificationSubService(result.get())
                    ;
            return ResponseEntity.ok().body(classificationSubServiceDto);
        }


        UserSubServiceClassification userSubServiceClassification = new UserSubServiceClassification();

        userSubServiceClassification.setFkAccount(userAccountOptional.get());
        userSubServiceClassification.setFkSubService(technicianSubServiceOptional.get());
        userSubServiceClassification.setIsAllowedClassification(true);
        userSubServiceClassification.setTechnicianPerformAction(true);

        userSubServiceClassificationRepository.save(userSubServiceClassification);


        ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                .INSTANCE
                .userSubServiceClassificationToClassificationSubService(userSubServiceClassification);

        return ResponseEntity.ok().body(classificationSubServiceDto);
    }

    @PostMapping("/disallow")
    public ResponseEntity<ClassificationSubServiceDto> disallowClassification(@RequestBody AllowClassificationDto allowClassificationDto) {
        Optional<Account> technicianAccountOptional = accountRepository.findById(allowClassificationDto.getTechnicianAccount());
        Optional<Account> userAccountOptional = accountRepository.findById(allowClassificationDto.getUserAccount());

        if (technicianAccountOptional.isEmpty() || userAccountOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Technician>  technicianOptional = technicianRepository.findByFkPerson_PkPerson(
                technicianAccountOptional.get().getFkPerson().getPkPerson()
        );

        if (technicianOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        Optional<SubService> technicianSubServiceOptional = subServiceRepository.findSubServiceByPkSubServiceAndFkTechnician_PkTechnician(
                allowClassificationDto.getSubService(),
                technicianOptional.get().getPkTechnician()
        );

        if (technicianSubServiceOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserSubServiceClassification> result = userSubServiceClassificationRepository
                .findByFkAccount_PkAccountAndFkSubService_PkSubService(
                        allowClassificationDto.getUserAccount(),
                        allowClassificationDto.getSubService()
                );

        if (result.isPresent()) {
            ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                    .INSTANCE
                    .userSubServiceClassificationToClassificationSubService(result.get())
                    ;
            return ResponseEntity.ok().body(classificationSubServiceDto);
        }


        UserSubServiceClassification userSubServiceClassification = new UserSubServiceClassification();

        userSubServiceClassification.setFkAccount(userAccountOptional.get());
        userSubServiceClassification.setFkSubService(technicianSubServiceOptional.get());
        userSubServiceClassification.setIsAllowedClassification(false);
        userSubServiceClassification.setTechnicianPerformAction(true);


        userSubServiceClassificationRepository.save(userSubServiceClassification);


        ClassificationSubServiceDto classificationSubServiceDto = ClassificationMapper
                .INSTANCE
                .userSubServiceClassificationToClassificationSubService(userSubServiceClassification);

        return ResponseEntity.ok().body(classificationSubServiceDto);
    }
}
