package com.tech.device.domain.spis;

import com.tech.device.domain.models.AddPersonReq;
import com.tech.device.domain.models.Person;

public interface IPersonProvider {

    Person add(AddPersonReq addPersonReq);
}
