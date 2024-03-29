package com.cpe.emergencymanager.model;

import com.cpe.emergencymanager.model.enums.ResourceStatus;
import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "truck", schema = "public", catalog = "emergency")
public class TruckEntity implements LocalizedEntity {
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
    @Basic
    @Column(name = "power")
    private Integer power;
    @Basic
    @Column(name = "id_station")
    private Integer idStation;

    @Basic
    @Column(name = "status")
    private ResourceStatus status;

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

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckEntity that = (TruckEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude) && Objects.equals(power, that.power) && Objects.equals(idStation, that.idStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, power, idStation);
    }
}
