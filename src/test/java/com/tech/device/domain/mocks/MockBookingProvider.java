package com.tech.device.domain.mocks;

import com.tech.device.domain.enums.DeviceType;
import com.tech.device.domain.enums.Team;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.models.Person;
import com.tech.device.domain.spis.IBookingProvider;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;

public class MockBookingProvider implements IBookingProvider {

    @Override
    public Booking addBooking(Long personId, Long deviceId, ZonedDateTime start, ZonedDateTime end) {
        return generateValidBooking();
    }

    @Override
    public Booking addDuration(Long bookingId, ZonedDateTime start, ZonedDateTime end) {
        return null;
    }

    @Override
    public Booking endDuration(Long bookingDurationId, ZonedDateTime end) {
        return null;
    }

    @Override
    public BookingDuration getLatestDuration(Long bookingId) {
        return null;
    }

    public static Booking generateValidBooking() {
        return Booking.builder()
                .id(1L)
                .person(Person.builder()
                        .id(1L)
                        .firstname("Ralph")
                        .lastname("Morales")
                        .team(Team.BACKEND)
                        .build())
                .device(Device.builder()
                        .id(1L)
                        .name("Samsung Galaxy")
                        .type(DeviceType.MOBILE)
                        .serial("S123456")
                        .active(true)
                        .build())
                .duration(Collections.singletonList(
                        BookingDuration.builder()
                                .start(ZonedDateTime.now())
                                .end(ZonedDateTime.now().plusHours(2))
                                .build()
                ))
                .build();
    }
}