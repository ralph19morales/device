package com.tech.device.domain.apis;

import com.tech.device.domain.models.BookDeviceReq;
import com.tech.device.domain.models.Booking;

@FunctionalInterface
public interface ICreateBooking {

    Booking execute(BookDeviceReq bookDeviceReq);
}
