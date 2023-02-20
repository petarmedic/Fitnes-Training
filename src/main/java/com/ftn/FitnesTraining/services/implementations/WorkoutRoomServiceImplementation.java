package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.repositorys.WorkoutRoomRepository;
import com.ftn.FitnesTraining.services.WorkoutRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutRoomServiceImplementation implements WorkoutRoomService {

    @Autowired
    WorkoutRoomRepository workoutRoomRepository;
}
