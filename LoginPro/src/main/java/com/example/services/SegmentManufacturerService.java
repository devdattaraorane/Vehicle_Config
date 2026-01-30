package com.example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Manufacturer;
import com.example.models.SegmentManufacturer;
import com.example.repositories.SegmentManufacturerRepository;

@Service
public class SegmentManufacturerService {

    @Autowired
    private SegmentManufacturerRepository smRepo;

    /**
     * Get all manufacturers belonging to a given segment
     *
     * @param segmentId segment_id from DB
     * @return list of manufacturers
     */
    public List<Manufacturer> getManufacturersBySegment(int segmentId) {

        List<SegmentManufacturer> mappings =
                smRepo.findBySegment_SegmentId(segmentId);

        return mappings.stream()
                       .map(SegmentManufacturer::getManufacturer)
                       .collect(Collectors.toList());
    }
}
