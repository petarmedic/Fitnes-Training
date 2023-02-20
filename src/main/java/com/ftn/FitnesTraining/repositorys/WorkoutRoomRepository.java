package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.WorkoutRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRoomRepository extends JpaRepository<WorkoutRoom, Integer> {
}
