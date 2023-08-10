package com.tech.device.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tech.device.domain.models.BookingDuration;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(exclude = "id")
@EqualsAndHashCode(callSuper = false)
@Table(name = "booking_duration")
public class BookingDurationEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty(value = "booking")
    private BookingEntity booking;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime end;

    public BookingDuration toDomainBookingDuration(boolean includeBooking) {
        BookingDuration bookingDuration = BookingDuration.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .start(this.getStart())
                .end(this.getEnd())
                .build();

        if(includeBooking) {
            bookingDuration.setBooking(this.booking.toDomainBooking());
        }

        return bookingDuration;
    }

    public BookingDuration toDomainBookingDuration() {
        return toDomainBookingDuration(true);
    }
}
