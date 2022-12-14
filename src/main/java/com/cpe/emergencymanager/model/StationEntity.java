package com.cpe.emergencymanager.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "station", schema = "public", catalog = "emergency")
public class StationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "longitude")
    private Double longitude;
    @Basic
    @Column(name = "latitude")
    private Double latitude;
    @OneToMany(mappedBy = "idStation")
    private Collection<FiremanEntity> firemenById;
    @OneToMany(mappedBy = "idStation")
    private Collection<TruckEntity> trucksById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude);
    }

    public Collection<FiremanEntity> getFiremenById() {
        return firemenById;
    }

    public void setFiremenById(Collection<FiremanEntity> firemenById) {
        this.firemenById = firemenById;
    }

    public Collection<TruckEntity> getTrucksById() {
        return trucksById;
    }

    public void setTrucksById(Collection<TruckEntity> trucksById) {
        this.trucksById = trucksById;
    }
}
