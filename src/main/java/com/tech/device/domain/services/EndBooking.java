package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IEndBooking;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;
import com.tech.device.domain.spis.IBookingProvider;

import java.time.ZonedDateTime;

@DomainService
public class EndBooking implements IEndBooking {
    final IBookingProvider bookingProvider;

    public EndBooking(IBookingProvider bookingProvider) {
        this.bookingProvider = bookingProvider;
    }

    @Override
    public Booking execute(Long id) {
        BookingDuration bookingDuration = bookingProvider.getLatestDuration(id);
        return bookingProvider.endDuration(bookingDuration.getId(), ZonedDateTime.now());
    }
}
