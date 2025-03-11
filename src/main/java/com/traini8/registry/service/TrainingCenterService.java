package com.traini8.registry.service;

import com.traini8.registry.dto.TrainingCenterRequestDTO;
import com.traini8.registry.dto.TrainingCenterResponseDTO;
import com.traini8.registry.exception.DuplicateTrainingCenterException;
import com.traini8.registry.mapper.TrainingCenterMapper;
import com.traini8.registry.model.TrainingCenter;
import com.traini8.registry.repository.TrainingCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingCenterService {

    private final TrainingCenterRepository repository;
    private final TrainingCenterMapper trainingCenterMapper;

    public TrainingCenterResponseDTO createTrainingCenter(TrainingCenterRequestDTO requestDTO){
        if (repository.existsByCenterCode(requestDTO.getCenterCode())) {
            throw new DuplicateTrainingCenterException("Training center with code " + requestDTO.getCenterCode() + " already exists!");
        }
        TrainingCenter trainingCenter = trainingCenterMapper.toEntity(requestDTO);
        trainingCenter = repository.save(trainingCenter);
        return trainingCenterMapper.toDto(trainingCenter);
    }

    public List<TrainingCenterResponseDTO> getAllTrainingCenters() {
        return repository.findAll()
                .stream()
                .map(trainingCenterMapper::toDto)
                .collect(Collectors.toList());
    }
}
