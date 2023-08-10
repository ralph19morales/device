package com.tech.device.domain.apis;

import com.tech.device.domain.models.Booking;

@FunctionalInterface
public interface IEndBooking {

    Booking execute(Long id);
}
