package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IExtendBooking;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;
import com.tech.device.domain.spis.IBookingProvider;

import java.time.ZonedDateTime;

@DomainService
public class ExtendBooking implements IExtendBooking {
    final IBookingProvider bookingProvider;

    public ExtendBooking(IBookingProvider bookingProvider) {
        this.bookingProvider = bookingProvider;
    }

    @Override
    public Booking execute(Long id, ZonedDateTime end) {
        BookingDuration bookingDuration = bookingProvider.getLatestDuration(id);
        if (end.isBefore(bookingDuration.getEnd())) {
            throw new RuntimeException("Invalid extension date");
        }

        return bookingProvider.addDuration(bookingDuration.getBooking().getId(),
                bookingDuration.getEnd(), end);
    }
}
