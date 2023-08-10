package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.ICheckDeviceAvailability;
import com.tech.device.domain.apis.ICreateBooking;
import com.tech.device.domain.models.BookDeviceReq;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.spis.IBookingProvider;

import java.time.ZonedDateTime;

@DomainService
public class CreateBooking implements ICreateBooking {
    final IBookingProvider bookingProvider;
    final ICheckDeviceAvailability checkDeviceAvailability;

    public CreateBooking(IBookingProvider bookingProvider, ICheckDeviceAvailability checkDeviceAvailability) {
        this.bookingProvider = bookingProvider;
        this.checkDeviceAvailability = checkDeviceAvailability;
    }

    @Override
    public Booking execute(BookDeviceReq bookDeviceReq) {

        if(bookDeviceReq.getStart() != null) {
            if(bookDeviceReq.getStart().isBefore(ZonedDateTime.now().minusMinutes(1))) {
                throw new RuntimeException("Start must be future date");
            }

            if(bookDeviceReq.getEnd() != null) {
                if(bookDeviceReq.getEnd().isBefore(ZonedDateTime.now())) {
                    throw new RuntimeException("End must be future date");
                }

                if(bookDeviceReq.getEnd().isBefore(bookDeviceReq.getStart())) {
                    throw new RuntimeException("End date must be after start date");
                }
            }
        } else {
            bookDeviceReq.setStart(ZonedDateTime.now());
        }

        if(!checkDeviceAvailability.execute(bookDeviceReq.getDeviceId())) {
            throw new RuntimeException("Device is unavailable");
        }

        return bookingProvider.addBooking(bookDeviceReq.getPersonId(), bookDeviceReq.getDeviceId(), bookDeviceReq.getStart(), bookDeviceReq.getEnd());
    }
}
