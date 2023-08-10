package com.tech.device.infra.providers;

import com.tech.device.domain.models.AddPersonReq;
import com.tech.device.domain.models.Person;
import com.tech.device.domain.spis.IPersonProvider;
import com.tech.device.infra.persistence.entities.PersonEntity;
import com.tech.device.infra.persistence.repositories.PersonRepository;

public class PersonJPAProvider implements IPersonProvider {
    final PersonRepository personRepository;

    public PersonJPAProvider(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person add(AddPersonReq addPersonReq) {
        return personRepository.save(
                PersonEntity.builder()
                        .firstname(addPersonReq.getFirstname())
                        .lastname(addPersonReq.getLastname())
                        .team(addPersonReq.getTeam())
                        .build()
        ).toDomainPerson();
    }
}
