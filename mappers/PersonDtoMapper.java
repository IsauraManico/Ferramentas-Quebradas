package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Person;
import com.ferramentas.ferramentasbackend.repository.PersonRepository;
import com.ferramentas.ferramentasbackend.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoMapper {
    private final PersonRepository personRepository;

    public PersonDtoMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static PersonDto fromPersonToSmallPersonDto(Person person) {
        return new PersonDto(person.getName(), person.getPhoneNumber());
    }

    public static PersonDto fromPersonToBigPersonDto(Person person) {
        return new PersonDto(
                person.getName(),
                person.getPhoneNumber(),
                person.getBirthDate(),
                person.getLatitude(),
                person.getLongitude(),
                person.getLocationDescription(),
                person.getFkGender() != null ? person.getFkGender().getPkGender() : 0
        );
    }

    public PersonDto fromPersonDtoToPerson(PersonDto person) {
        return null;
    }
}
