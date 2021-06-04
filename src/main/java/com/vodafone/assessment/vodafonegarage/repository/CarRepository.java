package com.vodafone.assessment.vodafonegarage.repository;

import com.vodafone.assessment.vodafonegarage.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Optional<Car> getCarById(Integer id);

}

