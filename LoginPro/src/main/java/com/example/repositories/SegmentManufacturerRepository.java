package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.SegmentManufacturer;

@Repository
public interface SegmentManufacturerRepository
        extends JpaRepository<SegmentManufacturer , Integer> {

    List<SegmentManufacturer> findBySegment_SegmentId(int segmentId);
}
