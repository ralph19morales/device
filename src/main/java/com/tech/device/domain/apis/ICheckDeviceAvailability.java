package com.tech.device.domain.apis;

@FunctionalInterface
public interface ICheckDeviceAvailability {

    Boolean execute(long id);
}
