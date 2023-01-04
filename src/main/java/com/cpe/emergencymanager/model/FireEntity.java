package com.cpe.emergencymanager.model;

import com.cpe.emergencymanager.model.enums.ActionStatus;
import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "fire", schema = "public", catalog = "emergency")
public class FireEntity implements LocalizedEntity{
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
    @Column(name = "intensity")
    private Integer intensity;
    @Basic
    @Column(name = "status")
    private ActionStatus status;
    @OneToMany(mappedBy = "idFire")
    private Collection<AlertEntity> alertsById;
    @OneToMany(mappedBy = "idFire")
    private Collection<InterventionEntity> interventionsById;

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

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireEntity that = (FireEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude) && Objects.equals(intensity, that.intensity) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, intensity, status);
    }

    public Collection<AlertEntity> getAlerts() {
        return alertsById;
    }

    public void setAlertsById(Collection<AlertEntity> alertsById) {
        this.alertsById = alertsById;
    }

    public Collection<InterventionEntity> getInterventions() {
        return interventionsById;
    }

    public void setInterventionsById(Collection<InterventionEntity> interventionsById) {
        this.interventionsById = interventionsById;
    }

    public Double getRadius() {
        // TODO : Calculer un radius cohérent par rapport à la carte
        // Uitiliser un coéfficient mutliplicateur en fonction de l'échelle de l'intensité
        return Double.valueOf(this.intensity);
    }
}
