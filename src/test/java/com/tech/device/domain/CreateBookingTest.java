package com.tech.device.domain;

import com.tech.device.domain.apis.ICreateBooking;
import com.tech.device.domain.mocks.MockBookingProvider;
import com.tech.device.domain.mocks.MockCheckDeviceAvailability;
import com.tech.device.domain.models.BookDeviceReq;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.services.CreateBooking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class CreateBookingTest {

    ICreateBooking createBooking = new CreateBooking(new MockBookingProvider(), new MockCheckDeviceAvailability());

    @Test
    void whenRequestIsValidNoDuration_returnBooking() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);
        Booking booking = createBooking.execute(request);

        Assertions.assertEquals(booking.getPerson(), MockBookingProvider.generateValidBooking().getPerson());
        Assertions.assertEquals(booking.getDevice(), MockBookingProvider.generateValidBooking().getDevice());
        Assertions.assertNotNull(booking.getDuration());
    }

    @Test
    void whenRequestIsValidDuration_returnBooking() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);
        request.setStart(ZonedDateTime.now());
        request.setEnd(ZonedDateTime.now().plusHours(2));
        Booking booking = createBooking.execute(request);

        Assertions.assertEquals(booking.getPerson(), MockBookingProvider.generateValidBooking().getPerson());
        Assertions.assertEquals(booking.getDevice(), MockBookingProvider.generateValidBooking().getDevice());
        Assertions.assertNotNull(booking.getDuration());
    }


    @Test
    void whenRequestIsInvalidPastStartDate_throwsException() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);
        request.setStart(ZonedDateTime.now().minusMinutes(5));
        request.setEnd(ZonedDateTime.now().plusHours(2));
        Assertions.assertThrows(RuntimeException.class, () -> createBooking.execute(request));
    }

    @Test
    void whenRequestIsInvalidPastFutureDate_throwsException() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);
        request.setStart(ZonedDateTime.now());
        request.setEnd(ZonedDateTime.now().minusMinutes(10));
        Assertions.assertThrows(RuntimeException.class, () -> createBooking.execute(request));
    }

    @Test
    void whenRequestIsInvalidPastDateIsBeforeStartDate_throwsException() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);
        request.setStart(ZonedDateTime.now().plusHours(5));
        request.setEnd(ZonedDateTime.now().plusHours(4));
        Assertions.assertThrows(RuntimeException.class, () -> createBooking.execute(request));
    }

    @Test
    void whenRequestIsValidDeviceIsUnavailable_throwsException() {
        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(2L);
        request.setStart(ZonedDateTime.now());
        request.setEnd(ZonedDateTime.now().plusHours(1));
        Assertions.assertThrows(RuntimeException.class, () -> createBooking.execute(request));
    }
}
