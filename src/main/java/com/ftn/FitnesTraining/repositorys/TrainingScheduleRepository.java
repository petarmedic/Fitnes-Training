package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Integer> {
}
