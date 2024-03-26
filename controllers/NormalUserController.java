package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.repository.NormalUserRepository;
import com.ferramentas.ferramentasbackend.repository.PersonRepository;
import com.ferramentas.ferramentasbackend.dto.NormalUserCreationDto;
import com.ferramentas.ferramentasbackend.dto.output.NormalUserDto;
import com.ferramentas.ferramentasbackend.entities.NormalUser;
import com.ferramentas.ferramentasbackend.entities.Person;
import com.ferramentas.ferramentasbackend.exceptions.ErrorMessage;
import com.ferramentas.ferramentasbackend.mappers.PersonDtoMapper;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController(value = "Normal User Controller")
@RequestMapping(value = RouterUtils.NORMAL_USER_MAIN_ROUTE)
public class NormalUserController {
    private final NormalUserRepository normalUserRepository;
    private final PersonRepository personRepository;

    public NormalUserController(NormalUserRepository normalUserRepository, PersonRepository personRepository) {
        this.normalUserRepository = normalUserRepository;
        this.personRepository = personRepository;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody NormalUserCreationDto normalUserCreationDto) {
        Integer personId = normalUserCreationDto.getPersonId();

        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalPerson.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        NormalUser newNormalUser = new NormalUser();
        Person person = optionalPerson.get();

        newNormalUser.setFkPerson(person);
        //newNormalUser.setFkPerson(normalUserDto.getPerson());

        normalUserRepository.save(newNormalUser);

        return new ResponseEntity<>(new NormalUserDto(newNormalUser.getPkNormalUser(), PersonDtoMapper.fromPersonToBigPersonDto(person)), HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Object> delete(@PathParam("id") Integer id) {
        Optional<NormalUser> normalUserOptional = normalUserRepository.findById(id);

        if (normalUserOptional.isEmpty())
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);

        normalUserOptional.ifPresent(normalUser -> {
            normalUserRepository.deleteById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Object> update(@PathParam("id") Integer id, @Valid @RequestBody NormalUserCreationDto normalUserCreationDto) {
        Optional<NormalUser> normalUserOptional = normalUserRepository.findById(id);

        if (normalUserOptional.isEmpty())
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);

        Optional<Person> optionalPerson = personRepository.findById(normalUserCreationDto.getPersonId());

        if (optionalPerson.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        normalUserOptional.ifPresent(normalUser -> {
            normalUser.setFkPerson(optionalPerson.get());

            normalUserRepository.save(normalUser);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<NormalUserDto> people = normalUserRepository
                .findAll()
                .stream()
                .map(normalUser ->
                        new NormalUserDto(
                                normalUser.getPkNormalUser(),
                                PersonDtoMapper.fromPersonToBigPersonDto(normalUser.getFkPerson())
                        )
                ).collect(Collectors.toList());


        return new ResponseEntity<>(people, HttpStatus.OK);
    }
}
