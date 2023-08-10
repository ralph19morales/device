package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.ICheckDeviceAvailability;
import com.tech.device.domain.apis.IGetDeviceBookings;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;

import java.time.ZonedDateTime;
import java.util.List;

@DomainService
public class CheckDeviceAvailability implements ICheckDeviceAvailability {
    final IGetDeviceBookings getDeviceBookings;

    public CheckDeviceAvailability(IGetDeviceBookings getDeviceBookings) {
        this.getDeviceBookings = getDeviceBookings;
    }

    @Override
    public Boolean execute(long id) {
        List<Booking> bookings = getDeviceBookings.execute(id);
        if (bookings.isEmpty()) {
            return true;
        }

        Booking lastBooking = bookings.get(bookings.size() - 1);
        List<BookingDuration> durations = lastBooking.getDuration();
        BookingDuration lastBookingDuration = durations.get(durations.size() - 1);

        if (ZonedDateTime.now().isAfter(lastBookingDuration.getStart()) && lastBookingDuration.getEnd() == null) {
            return false;
        } else
            return !ZonedDateTime.now().isAfter(lastBookingDuration.getStart()) || !ZonedDateTime.now().isBefore(lastBookingDuration.getEnd());
    }
}
