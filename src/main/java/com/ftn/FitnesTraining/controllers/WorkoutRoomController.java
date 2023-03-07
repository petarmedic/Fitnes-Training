package com.ftn.FitnesTraining.controllers;

import com.ftn.FitnesTraining.models.WorkoutRoom;
import com.ftn.FitnesTraining.services.WorkoutRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WorkoutRoomController {

    @Autowired
    WorkoutRoomService workoutRoomService;

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean createWorkoutRoom(@Argument int capacity, @Argument String name){
        return workoutRoomService.createWorkoutRoom(capacity, name);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<WorkoutRoom> workoutRooms() {
        return workoutRoomService.workoutRooms();
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean deleteWorkoutRoom(@Argument int idWorkoutRoom){
        return workoutRoomService.deleteWorkoutRoom(idWorkoutRoom);
    }
}
