package com.traini8.registry.repository;

import com.traini8.registry.model.TrainingCenter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingCenterRepository extends MongoRepository<TrainingCenter,String> {
}
