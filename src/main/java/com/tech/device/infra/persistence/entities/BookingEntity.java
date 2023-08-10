package com.tech.device.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tech.device.domain.models.Booking;
import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "booking")
public class BookingEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty(value = "person")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty(value = "device")
    private DeviceEntity device;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingDurationEntity> duration;

    public Booking toDomainBooking() {
        return Booking.builder()
                .id(this.id)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .device(this.device.toDomainDevice(false))
                .person(this.person.toDomainPerson(false))
                .duration(this.duration.stream().map(bookingDurationEntity -> bookingDurationEntity.toDomainBookingDuration(false)).toList())
                .build();
    }
}
