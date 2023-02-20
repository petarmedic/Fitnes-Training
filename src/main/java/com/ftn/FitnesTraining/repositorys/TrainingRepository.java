package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
}
