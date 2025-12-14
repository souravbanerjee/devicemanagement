package com.example.devicemanagement.model;

import com.example.devicemanagement.enums.DeviceState;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity

@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceState state;

    @Column(nullable = false, updatable = false)
    private Instant creationTime;

    @PrePersist
    void onCreate() {
        this.creationTime = Instant.now();
    }
}
