package com.tech.device.domain.apis;

import com.tech.device.domain.models.Booking;

import java.time.ZonedDateTime;

@FunctionalInterface
public interface IExtendBooking {

    Booking execute(Long id, ZonedDateTime end);
}
