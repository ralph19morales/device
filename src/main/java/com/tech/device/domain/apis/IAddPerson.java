package com.tech.device.domain.apis;

import com.tech.device.domain.models.AddPersonReq;
import com.tech.device.domain.models.Person;

@FunctionalInterface
public interface IAddPerson {

    Person execute(AddPersonReq device);
}

