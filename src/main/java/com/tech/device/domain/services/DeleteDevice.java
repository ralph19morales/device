package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IActivateDevice;
import com.tech.device.domain.apis.IDeleteDevice;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.spis.IDeviceProvider;

@DomainService
public class DeleteDevice implements IDeleteDevice {
    final IDeviceProvider deviceProvider;

    public DeleteDevice(IDeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    @Override
    public Device execute(Long id) {
        return deviceProvider.delete(id);
    }
}
