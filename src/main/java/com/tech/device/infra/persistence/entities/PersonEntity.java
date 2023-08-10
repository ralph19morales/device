package com.tech.device.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech.device.domain.enums.Team;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.models.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(exclude = "id")
@EqualsAndHashCode(callSuper = false)
@Table(name = "person")
public class PersonEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Team team;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

    public Person toDomainPerson(boolean includeBookings) {
        List<Booking> domainBookings = null;
        if(includeBookings) {
            domainBookings = this.getBookings() == null ? null : this.getBookings().stream().map(BookingEntity::toDomainBooking).toList();
        }
        return Person.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .firstname(this.getFirstname())
                .lastname(this.getLastname())
                .team(this.getTeam())
                .bookings(domainBookings)
                .build();
    }

    public Person toDomainPerson() {
        return toDomainPerson(true);
    }
}
