package com.example.models;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer")
    private List<SegmentManufacturer> segmentManufacturers;

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public List<SegmentManufacturer> getSegmentManufacturers() {
		return segmentManufacturers;
	}

	public void setSegmentManufacturers(List<SegmentManufacturer> segmentManufacturers) {
		this.segmentManufacturers = segmentManufacturers;
	}

    // getters & setters
}
