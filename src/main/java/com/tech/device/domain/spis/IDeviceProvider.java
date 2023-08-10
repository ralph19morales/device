package com.tech.device.domain.spis;

import com.tech.device.domain.models.AddDeviceReq;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.Device;

import java.util.List;

public interface IDeviceProvider {

    Device add(AddDeviceReq addDeviceReq);

    Device activate(Long id);

    Device deactivate(Long id);

    Device delete(Long id);

    List<Booking> getBookings(Long id);
}
