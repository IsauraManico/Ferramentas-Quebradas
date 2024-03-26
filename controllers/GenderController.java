package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.GenderDto;
import com.ferramentas.ferramentasbackend.entities.Gender;
import com.ferramentas.ferramentasbackend.exceptions.ErrorMessage;
import com.ferramentas.ferramentasbackend.repository.GenderRepository;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController(value = "Gender Controller")
@RequestMapping(value = RouterUtils.GENDER_MAIN_ROUTE)
public class GenderController {
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @PostMapping
    public ResponseEntity<GenderDto> create(@Valid @RequestBody GenderDto genderCreationDto) {
        Gender newGender = new Gender();

        newGender.setDesignation(genderCreationDto.getDesignation());

        genderRepository.save(newGender);

        return new ResponseEntity<>(genderCreationDto, HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Object> delete(@PathParam("id") Integer id) {
        Optional<Gender> genderOptional = genderRepository.findById(id);

        if (genderOptional.isEmpty())
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);

        genderOptional.ifPresent(gender -> {
            genderRepository.deleteById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Object> update(@PathParam("id") Integer id, @Valid @RequestBody GenderDto genderDto) {
        Optional<Gender> genderOptional = genderRepository.findById(id);

        if (genderOptional.isEmpty())
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);

        genderOptional.ifPresent(gender -> {
            gender.setDesignation(genderDto.getDesignation());

            genderRepository.save(gender);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<GenderDto> genders = genderRepository
                .findAll()
                .stream()
                .map(gender -> new GenderDto(gender.getDesignation()))
                .collect(Collectors.toList());


        return new ResponseEntity<>(genders, HttpStatus.OK);
    }
}
