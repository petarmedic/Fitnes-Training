package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.WorkoutRoom;
import com.ftn.FitnesTraining.repositorys.WorkoutRoomRepository;
import com.ftn.FitnesTraining.services.WorkoutRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutRoomServiceImplementation implements WorkoutRoomService {

    @Autowired
    WorkoutRoomRepository workoutRoomRepository;

    @Override
    public Boolean createWorkoutRoom(int capacity, String name) {
        WorkoutRoom s = new WorkoutRoom();
        s.setCapacity(capacity);
        s.setName(name);
        workoutRoomRepository.save(s);
        return true;
    }
    @Override
    public List<WorkoutRoom> workoutRooms() {
        return workoutRoomRepository.findAll();
    }
    @Override
    public Boolean deleteWorkoutRoom(int idWorkoutRoom) {
        workoutRoomRepository.deleteById(idWorkoutRoom);
        return true;
    }
}
