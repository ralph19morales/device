package com.tech.device.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tech.device.domain.apis.*;
import com.tech.device.domain.models.AddDeviceReq;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {
    final IAddDevice addDevice;
    final IActivateDevice activateDevice;
    final IDeleteDevice deleteDevice;
    final IDeactivateDevice deactivateDevice;
    final IGetDeviceBookings getDeviceBookings;
    final ICheckDeviceAvailability checkDeviceAvailability;

    @Autowired
    public DeviceController(IAddDevice addDevice, IActivateDevice activateDevice, IDeleteDevice deleteDevice, IDeactivateDevice deactivateDevice, IGetDeviceBookings getDeviceBookings, ICheckDeviceAvailability checkDeviceAvailability) {
        this.addDevice = addDevice;
        this.activateDevice = activateDevice;
        this.deleteDevice = deleteDevice;
        this.deactivateDevice = deactivateDevice;
        this.getDeviceBookings = getDeviceBookings;
        this.checkDeviceAvailability = checkDeviceAvailability;
    }

    @PostMapping("add")
    public Device add(@RequestBody AddDeviceReq addDeviceReq) {
        return addDevice.execute(addDeviceReq);
    }

    @PutMapping("{id}/activate")
    public Device activate(@PathVariable(value = "id") Long id) {
        return activateDevice.execute(id);
    }

    @PutMapping("{id}/deactivate")
    public Device deactivate(@PathVariable(value = "id") Long id) {
        return deleteDevice.execute(id);
    }

    @PutMapping("{id}/delete")
    public Device delete(@PathVariable(value = "id") Long id) {
        return deleteDevice.execute(id);
    }

    @GetMapping("/{id}/bookings")
    public List<Booking> getBookings(@PathVariable(value = "id") Long id) {
        return getDeviceBookings.execute(id);
    }

    @GetMapping("/{id}/isAvailable")
    public ObjectNode isAvailable(@PathVariable(value = "id") Long id) {
        return new ObjectMapper().createObjectNode().put("available", checkDeviceAvailability.execute(id));
    }
}
