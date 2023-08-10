package com.tech.device.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech.device.domain.enums.DeviceType;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.Device;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
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
@Table(name = "device")
public class DeviceEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    private String name;
    private String serial;

    @Builder.Default
    private Boolean active = Boolean.TRUE;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime deletedAt;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

    public Device toDomainDevice(boolean includeBookings) {
        List<Booking> domainBookings = null;
        if(includeBookings) {
            domainBookings = this.getBookings() == null ? null : this.getBookings().stream().map(BookingEntity::toDomainBooking).toList();
        }
        return Device.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .name(this.getName())
                .type(this.getType())
                .serial(this.getSerial())
                .active(this.getActive())
                .deletedAt(this.getDeletedAt())
                .bookings(domainBookings)
                .build();
    }

    public Device toDomainDevice() {
        return toDomainDevice(true);
    }
}
