package com.example.devicemanagement.service;

import com.example.devicemanagement.enums.DeviceState;
import com.example.devicemanagement.model.Device;
import com.example.devicemanagement.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    DeviceRepository repository;

    @InjectMocks
    DeviceService service;

    @Test
    void shouldNotDeleteInUseDevice() {
        Device device = new Device();
        device.setState(DeviceState.IN_USE);

        when(repository.findById(any()))
                .thenReturn(Optional.of(device));

        Assertions.assertThrows(RuntimeException.class,
                () -> service.delete(UUID.randomUUID()));
    }
}
