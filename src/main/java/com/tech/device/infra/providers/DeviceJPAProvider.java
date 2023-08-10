package com.tech.device.infra.providers;

import com.tech.device.domain.models.AddDeviceReq;
import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.Device;
import com.tech.device.domain.spis.IDeviceProvider;
import com.tech.device.infra.persistence.entities.DeviceEntity;
import com.tech.device.infra.persistence.repositories.DeviceRepository;

import java.time.ZonedDateTime;
import java.util.List;

public class DeviceJPAProvider implements IDeviceProvider {

    final DeviceRepository deviceRepository;

    public DeviceJPAProvider(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device add(AddDeviceReq addDeviceReq) {
        return deviceRepository.save(
                DeviceEntity.builder()
                        .name(addDeviceReq.getName())
                        .type(addDeviceReq.getType())
                        .serial(addDeviceReq.getSerial())
                        .build()
        ).toDomainDevice();
    }

    @Override
    public Device activate(Long id) {
        return deviceRepository.findById(id).map(deviceEntity -> {
            deviceEntity.setActive(true);
            return deviceRepository.save(
                    deviceEntity);
        }).get().toDomainDevice();
    }

    @Override
    public Device deactivate(Long id) {
        return deviceRepository.findById(id).map(deviceEntity -> {
            deviceEntity.setActive(false);
            return deviceRepository.save(
                    deviceEntity);
        }).get().toDomainDevice();
    }

    @Override
    public Device delete(Long id) {
        return deviceRepository.findById(id).map(deviceEntity -> {
            deviceEntity.setDeletedAt(ZonedDateTime.now());
            return deviceRepository.save(
                    deviceEntity);
        }).get().toDomainDevice();
    }

    @Override
    public List<Booking> getBookings(Long id) {
        return deviceRepository.findById(id).get().toDomainDevice().getBookings();
    }
}
