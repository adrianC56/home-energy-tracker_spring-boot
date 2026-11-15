package com.macadribacks.ingestion_service.controller;

import com.macadribacks.ingestion_service.dto.EnergyUsageDTO;
import com.macadribacks.ingestion_service.service.IngestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {
    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDTO usageDTO) {
        ingestionService.ingestEnergyUsage(usageDTO);
    }
}
