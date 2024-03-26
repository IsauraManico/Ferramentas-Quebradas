package com.ferramentas.ferramentasbackend.services;

import com.ferramentas.ferramentasbackend.repository.TechnicianRepository;
import com.ferramentas.ferramentasbackend.repository.TechnicianServiceRepository;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.entities.Technician;
import com.ferramentas.ferramentasbackend.repository.AccountRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianServiceAssistance {
    private final TechnicianRepository technicianRepository;
    private final TechnicianServiceRepository technicianServiceRepository;
    private final AccountRepository accountRepository;

    public TechnicianServiceAssistance(TechnicianRepository technicianRepository, TechnicianServiceRepository technicianServiceRepository, AccountRepository accountRepository) {
        this.technicianRepository = technicianRepository;
        this.technicianServiceRepository = technicianServiceRepository;
        this.accountRepository = accountRepository;
    }

    public List<TechnicianPresentationDto> listMostRequiredTechnicians() {
        List<TechnicianPresentationDto> technicians = technicianRepository
                .findAll(Pageable.ofSize(3))
                .map(technician -> {
                    Optional<Account> accountOptional = accountRepository.findAccountByFkPerson_PkPerson(technician.getFkPerson().getPkPerson());

                    accountOptional.orElseThrow(() -> {
                        throw new Error("Error account not founded");
                    });

                    Account account = accountOptional.get();

                    return new TechnicianPresentationDto(
                            technician.getPkTechnician(),
                            account.getPkAccount(),
                            account.getProfilePicture(),
                            technician.getFkPerson().getName(),
                            technician.getFkPerson().getLocationDescription(),
                            technicianServiceRepository
                                    .findAllByFkTechnician_PkTechnician(technician.getPkTechnician())
                    );
                }).toList();

        return technicians;
    }

    public List<TechnicianPresentationDto> listTechnicians() {

        List<TechnicianPresentationDto> technicians = technicianRepository
                .findAll()
                .stream()
                .map(technician -> {
                    Optional<Account> accountOptional = accountRepository.findAccountByFkPerson_PkPerson(technician.getFkPerson().getPkPerson());

                    accountOptional.orElseThrow(() -> {
                        throw new Error("Error account not founded");
                    });

                    Account account = accountOptional.get();

                    return new TechnicianPresentationDto(
                            technician.getPkTechnician(),
                            account.getPkAccount(),
                            account.getProfilePicture(),
                            technician.getFkPerson().getName(),
                            technician.getFkPerson().getLocationDescription(),
                            technicianServiceRepository
                                    .findAllByFkTechnician_PkTechnician(technician.getPkTechnician())
                    );
                })
                .collect(Collectors.toList());

        return technicians;
    }

    public Optional<TechnicianPresentationDto> getSpecificTechnicianPresentation(Integer id) {
        Optional<Technician> optionalTechnician = technicianRepository
                .findById(id);

        if (optionalTechnician.isEmpty()) {
            return Optional.empty();
        }

        Technician technician = optionalTechnician.get();

        Optional<Account> accountOptional = accountRepository.findAccountByFkPerson_PkPerson(technician.getFkPerson().getPkPerson());

        accountOptional.orElseThrow(() -> {
            throw new Error("Error account not founded");
        });

        Account account = accountOptional.get();

        return Optional.of(new TechnicianPresentationDto(
                technician.getPkTechnician(),
                account.getPkAccount(),
                account.getProfilePicture(),
                technician.getFkPerson().getName(),
                technician.getFkPerson().getLocationDescription(),
                technicianServiceRepository
                        .findAllByFkTechnician_PkTechnician(technician.getPkTechnician()))
        );
    }

    public Optional<TechnicianPresentationDto> getSpecificTechnicianPresentationByPersonId(Integer id) {
        Optional<Technician> optionalTechnician = technicianRepository.findByFkPerson_PkPerson(id);

        if (optionalTechnician.isEmpty()) {
            return Optional.empty();
        }

        Technician technician = optionalTechnician.get();

        Optional<Account> accountOptional = accountRepository.findAccountByFkPerson_PkPerson(technician.getFkPerson().getPkPerson());

        accountOptional.orElseThrow(() -> {
            throw new Error("Error account not founded");
        });

        Account account = accountOptional.get();

        return Optional.of(new TechnicianPresentationDto(
                technician.getPkTechnician(),
                account.getPkAccount(),
                account.getProfilePicture(),
                technician.getFkPerson().getName(),
                technician.getFkPerson().getLocationDescription(),
                technicianServiceRepository
                        .findAllByFkTechnician_PkTechnician(technician.getPkTechnician()))
        );
    }
}
