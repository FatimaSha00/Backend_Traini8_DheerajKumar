package com.traini8.registry.controller;

import com.traini8.registry.dto.TrainingCenterRequestDTO;
import com.traini8.registry.dto.TrainingCenterResponseDTO;
import com.traini8.registry.service.TrainingCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingCenterService trainingCenterService;

    @PostMapping("/training-centers")
    public TrainingCenterResponseDTO createTrainingCenter(@RequestBody @Valid TrainingCenterRequestDTO requestDTO) {
        return trainingCenterService.createTrainingCenter(requestDTO);
    }

    @GetMapping("/getAll-training-centers")
    public List<TrainingCenterResponseDTO> getAllTrainingCenters() {
        return trainingCenterService.getAllTrainingCenters();
    }
}
