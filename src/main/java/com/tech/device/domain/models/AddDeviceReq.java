package com.tech.device.domain.models;

import com.tech.device.domain.enums.DeviceType;
import lombok.Data;

@Data
public class AddDeviceReq {
    private String name;
    private String serial;
    private DeviceType type;
    private Boolean active = true;
}
