package com.efisher.flatrent.service;

import com.efisher.flatrent.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    private final MongoOperations mongoOperations;

    private final SequenceGeneratorService generatorService;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, MongoOperations mongoOperations, SequenceGeneratorService generatorService) {
        this.apartmentRepository = apartmentRepository;
        this.mongoOperations = mongoOperations;
        this.generatorService = generatorService;
    }

    private boolean collectionExists(final String collectionName) {
        return mongoOperations.getCollectionNames().contains(collectionName);
    }
}
