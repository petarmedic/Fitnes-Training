package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.models.WorkoutRoom;

import java.util.List;

public interface WorkoutRoomService {

    Boolean createWorkoutRoom(int capacity, String name);

    Boolean deleteWorkoutRoom(int idWorkoutRoom);
    List<WorkoutRoom> workoutRooms();
}
