package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IAddDevice;
import com.tech.device.domain.models.AddDeviceReq;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.spis.IDeviceProvider;

@DomainService
public class AddDevice implements IAddDevice {
    final IDeviceProvider deviceProvider;

    public AddDevice(IDeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    @Override
    public Device execute(AddDeviceReq addDeviceReq) {
        return deviceProvider.add(addDeviceReq);
    }
}
