package com.tech.device.infra.controller;

import com.tech.device.domain.apis.IAddPerson;
import com.tech.device.domain.models.AddPersonReq;
import com.tech.device.domain.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {
    final IAddPerson addPerson;

    @Autowired
    public PersonController(IAddPerson addPerson) {
        this.addPerson = addPerson;
    }

    @PostMapping("add")
    public Person add(@RequestBody AddPersonReq addPersonReq) {
        return addPerson.execute(addPersonReq);
    }
}
