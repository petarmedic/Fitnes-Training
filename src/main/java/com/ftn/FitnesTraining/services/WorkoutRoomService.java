package com.ftn.FitnesTraining.services;

public interface WorkoutRoomService {

    Boolean createWorkoutRoom(int capacity, String name);

    Boolean deleteWorkoutRoom(int idWorkoutRoom);
}
