package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Manufacturer;

public interface ManufacturerRepository
        extends JpaRepository<Manufacturer, Integer> {
}
