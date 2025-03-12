package com.traini8.registry.controller;

import com.traini8.registry.dto.TrainingCenterRequestDTO;
import com.traini8.registry.dto.TrainingCenterResponseDTO;
import com.traini8.registry.model.TrainingCenter;
import com.traini8.registry.service.TrainingCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingCenterService trainingCenterService;

    @PostMapping("/training-centers")
    public ResponseEntity<TrainingCenterResponseDTO> createTrainingCenter(@RequestBody @Valid TrainingCenterRequestDTO requestDTO) {

        TrainingCenterResponseDTO savedCenter = trainingCenterService.createTrainingCenter(requestDTO);

        log.info("✅ Successfully created training center: centerCode={}, studentCapacity={}",
                savedCenter.getCenterCode(), savedCenter.getStudentCapacity());

        return ResponseEntity.ok(savedCenter);
    }

    @GetMapping("/getAll-training-centers")
    public ResponseEntity<List<TrainingCenterResponseDTO>> getAllTrainingCenters() {
        log.info("Fetching all training centers...");
        List<TrainingCenterResponseDTO> centers = trainingCenterService.getAllTrainingCenters();
        log.info("Fetched {} training centers", centers.size());
        return ResponseEntity.ok(centers);
    }
}
