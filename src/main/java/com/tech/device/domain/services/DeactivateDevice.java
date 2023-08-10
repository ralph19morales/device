package com.tech.device.domain.services;

import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.apis.IActivateDevice;
import com.tech.device.domain.apis.IDeactivateDevice;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.spis.IDeviceProvider;

@DomainService
public class DeactivateDevice implements IDeactivateDevice {
    final IDeviceProvider deviceProvider;

    public DeactivateDevice(IDeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    @Override
    public Device execute(Long id) {
        return deviceProvider.deactivate(id);
    }
}
