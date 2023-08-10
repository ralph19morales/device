package com.tech.device.domain.apis;

import com.tech.device.domain.models.AddDeviceReq;
import com.tech.device.domain.models.Device;

@FunctionalInterface
public interface IAddDevice {

    Device execute(AddDeviceReq device);
}

