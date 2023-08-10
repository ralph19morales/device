package com.tech.device.domain.models;

import com.tech.device.domain.enums.Team;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class Person {

    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedAt;

    private String firstname;
    private String lastname;
    private Team team;

    private List<Booking> bookings;
}
