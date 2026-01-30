package com.example.models;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "segment")
public class Segment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_id")
    private int segmentId;

    @Column(name = "segment_name", nullable = false)
    private String segmentName;

    @OneToMany(mappedBy = "segment")
    private List<SegmentManufacturer> segmentManufacturers;

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public List<SegmentManufacturer> getSegmentManufacturers() {
		return segmentManufacturers;
	}

	public void setSegmentManufacturers(List<SegmentManufacturer> segmentManufacturers) {
		this.segmentManufacturers = segmentManufacturers;
	}

    // getters & setters
}
