package com.traini8.registry;

import com.traini8.registry.dto.TrainingCenterRequestDTO;
import com.traini8.registry.dto.TrainingCenterResponseDTO;
import com.traini8.registry.exception.DuplicateTrainingCenterException;
import com.traini8.registry.mapper.TrainingCenterMapper;
import com.traini8.registry.model.TrainingCenter;
import com.traini8.registry.repository.TrainingCenterRepository;
import com.traini8.registry.service.TrainingCenterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingCenterServiceTest {

    @Mock
    private TrainingCenterRepository repository;

    @Mock
    private TrainingCenterMapper trainingCenterMapper;

    @InjectMocks
    private TrainingCenterService trainingCenterService;

    @Test
    void testCreateTrainingCenter_Success(){
        TrainingCenterRequestDTO requestDTO = new TrainingCenterRequestDTO();
        requestDTO.setCenterCode("TC123");
        requestDTO.setCenterName("Java Training Center");

        TrainingCenter trainingCenter = new TrainingCenter();
        trainingCenter.setCenterCode("TC123");
        trainingCenter.setCenterName("Java Training Center");

        TrainingCenterResponseDTO responseDTO = new TrainingCenterResponseDTO();
        responseDTO.setCenterCode("TC123");
        responseDTO.setCenterName("Java Training Center");

        // Mock behavior BEFORE execution
        when(repository.existsByCenterCode(requestDTO.getCenterCode())).thenReturn(false);
        when(trainingCenterMapper.toEntity(requestDTO)).thenReturn(trainingCenter);
        when(repository.save(trainingCenter)).thenReturn(trainingCenter);
        when(trainingCenterMapper.toDto(trainingCenter)).thenReturn(responseDTO);

        // Execute the method
        TrainingCenterResponseDTO result = trainingCenterService.createTrainingCenter(requestDTO);

        // Assertions
        assertNotNull(result);
        assertEquals("TC123", result.getCenterCode());
        assertEquals("Java Training Center", result.getCenterName());

        // Verify AFTER execution
        verify(repository, times(1)).existsByCenterCode(requestDTO.getCenterCode());  // ✅ Correct
        verify(repository, times(1)).save(trainingCenter);
        verify(trainingCenterMapper, times(1)).toEntity(requestDTO);
        verify(trainingCenterMapper, times(1)).toDto(trainingCenter);
    }

    @Test
    void testCreateTrainingCenter_DuplicateException() {
        // Initialize test data
        TrainingCenterRequestDTO requestDTO = new TrainingCenterRequestDTO();
        requestDTO.setCenterCode("TC123");

        // Mock behavior BEFORE execution
        when(repository.existsByCenterCode(requestDTO.getCenterCode())).thenReturn(true);

        // Execute and verify exception
        assertThrows(DuplicateTrainingCenterException.class, () -> {
            trainingCenterService.createTrainingCenter(requestDTO);
        });

        // Verify AFTER execution
        verify(repository, times(1)).existsByCenterCode(requestDTO.getCenterCode());  // Checked once
        verify(repository, never()).save(any(TrainingCenter.class)); // Should NOT be called
        verify(trainingCenterMapper, never()).toEntity(any(TrainingCenterRequestDTO.class)); // Should NOT be called
    }

    @Test
    void testGetAllTrainingCenters() {
        // Initialize test data inside the test method
        TrainingCenter trainingCenter1 = new TrainingCenter();
        trainingCenter1.setCenterCode("TC101");
        trainingCenter1.setCenterName("Java Training Center");

        TrainingCenter trainingCenter2 = new TrainingCenter();
        trainingCenter2.setCenterCode("TC102");
        trainingCenter2.setCenterName("Spring Boot Training Center");

        TrainingCenterResponseDTO responseDTO1 = new TrainingCenterResponseDTO();
        responseDTO1.setCenterCode("TC101");
        responseDTO1.setCenterName("Java Training Center");

        TrainingCenterResponseDTO responseDTO2 = new TrainingCenterResponseDTO();
        responseDTO2.setCenterCode("TC102");
        responseDTO2.setCenterName("Spring Boot Training Center");

        // Mock repository response
        when(repository.findAll()).thenReturn(Arrays.asList(trainingCenter1, trainingCenter2));

        // Mock mapping
        when(trainingCenterMapper.toDto(trainingCenter1)).thenReturn(responseDTO1);
        when(trainingCenterMapper.toDto(trainingCenter2)).thenReturn(responseDTO2);

        // Call service method
        List<TrainingCenterResponseDTO> result = trainingCenterService.getAllTrainingCenters();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("TC101", result.get(0).getCenterCode());
        assertEquals("TC102", result.get(1).getCenterCode());

        // Verify interactions
        verify(repository, times(1)).findAll();
        verify(trainingCenterMapper, times(2)).toDto(any(TrainingCenter.class));
    }


}
