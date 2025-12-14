package com.example.devicemanagement.service;

import com.example.devicemanagement.enums.DeviceState;
import com.example.devicemanagement.model.Device;
import com.example.devicemanagement.repository.DeviceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public Device create(Device device) {
        device.setId(null);
        return repository.save(device);
    }

    public Device update(UUID id, Device updated) {
        Device existing = get(id);

        if (existing.getState() == DeviceState.IN_USE) {
            if (!existing.getName().equals(updated.getName()) ||
                !existing.getBrand().equals(updated.getBrand())) {
                throw new RuntimeException("Cannot update name or brand when device is IN_USE");
            }
        }

        existing.setState(updated.getState());
        return repository.save(existing);
    }

    public Device get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public List<Device> getAll() {
        return repository.findAll();
    }

    public List<Device> getByBrand(String brand) {
        return repository.findByBrandIgnoreCase(brand);
    }

    public List<Device> getByState(DeviceState state) {
        return repository.findByState(state);
    }

    public void delete(UUID id) {
        Device device = get(id);
        if (device.getState() == DeviceState.IN_USE) {
            throw new RuntimeException("Cannot delete device in use");
        }
        repository.delete(device);
    }
}
