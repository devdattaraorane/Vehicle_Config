package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.models.Segment;
import com.example.repositories.SegmentRepository;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository repository;

    public List<Segment> getAllSegments() {
        return repository.findAll();
    }
}
