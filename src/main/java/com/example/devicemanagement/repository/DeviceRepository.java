package com.example.devicemanagement.repository;

import com.example.devicemanagement.enums.DeviceState;
import com.example.devicemanagement.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findByBrandIgnoreCase(String brand);

    List<Device> findByState(DeviceState state);
}