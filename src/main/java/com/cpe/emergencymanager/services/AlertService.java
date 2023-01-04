package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.AlertEntity;
import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.repository.AlertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlertService {
    private final FireService fireService;
    private final AlertRepository alertRepository;

    public AlertService(FireService fireService, AlertRepository alertRepository) {
        this.fireService = fireService;
        this.alertRepository = alertRepository;
    }

    public AlertEntity addAlert(AlertEntity alertEntity) {
        return this.alertRepository.save(alertEntity);
    }

    public AlertEntity editAlert(AlertEntity alertEntity) {
        return this.alertRepository.save(alertEntity);
    }

    public void deleteAlert(int alertId) {
        this.alertRepository.deleteById(alertId);
    }

    public AlertEntity getAlert(int alertId) {
        return this.alertRepository.findById(alertId);
    }

    public List<AlertEntity> getAlerts() {
        return this.alertRepository.findAll();
    }

    /**
     * Réception d'une alerte
     * En fonction de l'intensité, appel la méthode de détection de feu
     * @param alertEntity
     */
    public void receiveAlert(AlertEntity alertEntity) {
        log.info("Alerte reçue d'intensité : " + alertEntity.getIntensity());
        this.addAlert(alertEntity);
        // Si l'alerte à une intensité au dessus de x
        if(alertEntity.getIntensity() > 1) {
            this.fireService.detectAlert(alertEntity);
        }
        log.info("Alerte traitée");
    }
}
