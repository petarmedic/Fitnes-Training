package com.ftn.FitnesTraining.controllers;

import com.ftn.FitnesTraining.dto.StatisticsDTO;
import com.ftn.FitnesTraining.models.Training;
import com.ftn.FitnesTraining.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean createTraining(@Argument int prices, @Argument String levelTraining, @Argument int trainingDuration, @Argument String trainer, @Argument String trainingKind, @Argument String description, @Argument String name, @Argument String photo){
        return trainingService.createTraining(prices, levelTraining, trainingDuration, trainer, trainingKind, description, name, photo);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean editTraining(@Argument int idTraining, @Argument int prices, @Argument String levelTraining, @Argument int trainingDuration, @Argument String trainer, @Argument String trainingKind, @Argument String description, @Argument String name, @Argument String photo){
        return trainingService.editTraining(idTraining, prices, levelTraining, trainingDuration, trainer, trainingKind, description, name, photo);
    }

    @QueryMapping
    public List<Training> trainings(@Argument int priceFrom, @Argument int priceTo) {
        if (Objects.equals(priceFrom, null) || Objects.equals(priceTo, null) || priceFrom == 0 && priceTo == 0) {
            // dodeli vrednost 0
            priceFrom = 0;
            priceTo = 0;
        }
        return trainingService.trainings(priceFrom, priceTo);
        
    }
    @QueryMapping
    public Training training(@Argument int idTraining) {
        return trainingService.training(idTraining);
    }

    @MutationMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean createTrainingSchedule(@Argument int trainingId, @Argument int workoutRoomId, @Argument String dateTime){
        return trainingService.createTrainingSchedule(trainingId, workoutRoomId, dateTime);
    }

    @QueryMapping
    public List<StatisticsDTO> statistics(@Argument String dateFrom, @Argument String dateTo) {
        return trainingService.statistics(dateFrom, dateTo);
    }
}
