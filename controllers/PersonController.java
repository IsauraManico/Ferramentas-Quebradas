/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.PersonDto;
import com.ferramentas.ferramentasbackend.entities.Gender;
import com.ferramentas.ferramentasbackend.entities.Person;
import com.ferramentas.ferramentasbackend.exceptions.ErrorMessage;
import com.ferramentas.ferramentasbackend.repository.GenderRepository;
import com.ferramentas.ferramentasbackend.repository.PersonRepository;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yuridomingos
 * Data      : 29 - 01 - 2023
 * Objectivo : 
 */
@RestController
@RequestMapping ( value = RouterUtils.PERSON_MAIN_ROUTE)
public class PersonController 
{
    private final PersonRepository personRepository;
    private final GenderRepository genderRepository;
    

    public PersonController(PersonRepository personRepository, GenderRepository genderRepository) {
        this.personRepository = personRepository;
        this.genderRepository = genderRepository;
    }

   
    
    
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PersonDto personCreationDto)
    {
        Person newPerson = new Person();

        Integer genderId = personCreationDto.getGenderId();

        Optional<Gender> genderOptional = genderRepository.findById(genderId);

        if (genderOptional.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        newPerson.setName(personCreationDto.getName());
        newPerson.setPhoneNumber(personCreationDto.getPhoneNumber());
        newPerson.setBirthDate(personCreationDto.getBirthDate());
        newPerson.setLatitude(personCreationDto.getLatitude());
        newPerson.setLongitude(personCreationDto.getLongitude());
        newPerson.setLocationDescription(personCreationDto.getLocationDescription());
        newPerson.setFkGender(genderOptional.get());
        
        personRepository.save(newPerson);
       
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        
    }
  
    
    @GetMapping
    public List<PersonDto> getAll(){
        
      return personRepository
              .findAll()
              .stream()
              .map(person -> 
                      new PersonDto(person.getName(), person.getPhoneNumber())
              )
              .collect(Collectors.toList());
            
    }
    
    @DeleteMapping (params = {"id"})
    public ResponseEntity<Object> delete (@PathParam("id") Integer id)
    {
        Optional<Person> optionalPerson = personRepository.findById(id);
        
        if (! optionalPerson.isEmpty())
        {
            return new ResponseEntity<>(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        
        optionalPerson.ifPresent( Person -> {
            
            personRepository.deleteById(id);
        });
        
        return new ResponseEntity<> (HttpStatus.OK);
        
    }
   
    
    
    
}
