package com.tech.device.domain.spis;

import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;

import java.time.ZonedDateTime;

public interface IBookingProvider {

    Booking addBooking(Long personId, Long deviceId, ZonedDateTime start, ZonedDateTime end);

    Booking addDuration(Long bookingId, ZonedDateTime start, ZonedDateTime end);

    Booking endDuration(Long bookingDurationId, ZonedDateTime end);

    BookingDuration getLatestDuration(Long bookingId);
}
