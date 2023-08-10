package com.tech.device.domain.models;

import com.tech.device.domain.enums.Team;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPersonReq {

    private String firstname;
    private String lastname;
    private Team team;
}
