package com.efisher.flatrent.repository;

import com.efisher.flatrent.domain.Apartment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends MongoRepository<Apartment, Long> {
}
