package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.repository.AlertRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    private final FireService fireService;
    private final AlertRepository alertRepository;

    public AlertService(FireService fireService, AlertRepository alertRepository) {
        this.fireService = fireService;
        this.alertRepository = alertRepository;
    }
}
