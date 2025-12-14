package com.example.devicemanagement.controller;

import com.example.devicemanagement.enums.DeviceState;
import com.example.devicemanagement.model.Device;
import com.example.devicemanagement.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @PostMapping
    public Device create(@RequestBody @Valid Device device) {
        return service.create(device);
    }

    @GetMapping("/{id}")
    public Device get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<Device> getAll(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) DeviceState state) {

        if (brand != null) return service.getByBrand(brand);
        if (state != null) return service.getByState(state);
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Device update(@PathVariable UUID id, @RequestBody Device device) {
        return service.update(id, device);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
