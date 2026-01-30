package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.models.Manufacturer;
import com.example.repositories.ManufacturerRepository;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository repository;

    public List<Manufacturer> getAllManufacturers() {
        return repository.findAll();
    }
}
