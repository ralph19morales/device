package com.tech.device.domain.apis;

import com.tech.device.domain.models.Device;

@FunctionalInterface
public interface IDeactivateDevice {

    Device execute(Long id);
}

