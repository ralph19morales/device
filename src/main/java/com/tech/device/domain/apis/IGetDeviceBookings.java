package com.tech.device.domain.apis;

import com.tech.device.domain.models.Booking;

import java.util.List;

@FunctionalInterface
public interface IGetDeviceBookings {

    List<Booking> execute(Long id);
}
