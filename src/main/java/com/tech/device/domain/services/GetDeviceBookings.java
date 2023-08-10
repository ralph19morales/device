package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IGetDeviceBookings;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.spis.IDeviceProvider;

import java.util.List;

@DomainService
public class GetDeviceBookings implements IGetDeviceBookings {
    final IDeviceProvider deviceProvider;

    public GetDeviceBookings(IDeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    @Override
    public List<Booking> execute(Long id) {
        return deviceProvider.getBookings(id);
    }
}
