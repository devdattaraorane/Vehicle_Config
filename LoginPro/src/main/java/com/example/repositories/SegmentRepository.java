package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Segment;

public interface SegmentRepository
        extends JpaRepository<Segment, Integer> {
}
