package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IAddPerson;
import com.tech.device.domain.models.AddPersonReq;
import com.tech.device.domain.models.Person;
import com.tech.device.domain.spis.IPersonProvider;

@DomainService
public class AddPerson implements IAddPerson {
    final IPersonProvider personProvider;

    public AddPerson(IPersonProvider personProvider) {
        this.personProvider = personProvider;
    }

    @Override
    public Person execute(AddPersonReq addPersonReq) {
        return personProvider.add(addPersonReq);
    }
}
