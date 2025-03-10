package com.traini8.registry.mapper;

import com.traini8.registry.dto.AddressDTO;
import com.traini8.registry.dto.TrainingCenterRequestDTO;
import com.traini8.registry.dto.TrainingCenterResponseDTO;
import com.traini8.registry.model.Address;
import com.traini8.registry.model.TrainingCenter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TrainingCenterMapper {

    private final ModelMapper modelMapper;

    public TrainingCenterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convert DTO to Entity
    public TrainingCenter toEntity(TrainingCenterRequestDTO dto) {
        TrainingCenter trainingCenter = modelMapper.map(dto, TrainingCenter.class);

        // Manually handle Instant conversion for createdOn field
        trainingCenter.setCreatedOn(Instant.now());

        return trainingCenter;
    }

    // Convert Entity to DTO
    public TrainingCenterResponseDTO toDto(TrainingCenter entity) {
        return modelMapper.map(entity, TrainingCenterResponseDTO.class);
    }

//    public TrainingCenter toEntity(TrainingCenterRequestDTO dto){
//        Address address = new Address(
//                dto.getAddress().getDetailedAddress(),
//                dto.getAddress().getCity(),
//                dto.getAddress().getState(),
//                dto.getAddress().getPincode()
//        );
//
//        return new TrainingCenter(
//                dto.getCenterName(),
//                dto.getCenterCode(),
//                address,
//                dto.getStudentCapacity(),
//                dto.getCoursesOffered(),
//                Instant.ofEpochMilli(System.currentTimeMillis()),
//                dto.getContactEmail(),
//                dto.getContactPhone()
//        );
//    }
//
//    public TrainingCenterResponseDTO toDto(TrainingCenter entity) {
//        AddressDTO addressDTO = new AddressDTO(
//                entity.getAddress().getDetailedAddress(),
//                entity.getAddress().getCity(),
//                entity.getAddress().getState(),
//                entity.getAddress().getPincode()
//        );
//
//        return new TrainingCenterResponseDTO(
//                entity.getCenterName(),
//                entity.getCenterCode(),
//                addressDTO,
//                entity.getStudentCapacity(),
//                entity.getCoursesOffered(),
//                entity.getCreatedOn().toEpochMilli(),
//                entity.getContactEmail(),
//                entity.getContactPhone()
//        );
//    }
}
