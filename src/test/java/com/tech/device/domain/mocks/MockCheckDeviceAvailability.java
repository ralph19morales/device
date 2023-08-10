package com.tech.device.domain.mocks;

import com.tech.device.domain.apis.ICheckDeviceAvailability;

public class MockCheckDeviceAvailability implements ICheckDeviceAvailability {
    @Override
    public Boolean execute(long id) {
        if(id == 1) {
            return true;
        } else {
            return false;
        }
    }
}
