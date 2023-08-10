package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IActivateDevice;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.spis.IDeviceProvider;

@DomainService
public class ActivateDevice implements IActivateDevice {
    final IDeviceProvider deviceProvider;

    public ActivateDevice(IDeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    @Override
    public Device execute(Long id) {
        return deviceProvider.activate(id);
    }
}
