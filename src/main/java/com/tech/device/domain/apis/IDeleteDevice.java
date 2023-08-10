package com.tech.device.domain.apis;

import com.tech.device.domain.models.Device;

@FunctionalInterface
public interface IDeleteDevice {

    Device execute(Long id);
}

