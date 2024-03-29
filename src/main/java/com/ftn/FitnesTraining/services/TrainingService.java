package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.dto.StatisticsDTO;
import com.ftn.FitnesTraining.models.Training;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

public interface TrainingService {
    List<Training> trainings( @Argument int priceFrom, @Argument int priceTo);

    Training training(int idTraining);
    Boolean createTraining( int prices,  String levelTraining,  int trainingDuration,  String trainer,  String trainingKind,  String description,  String name,  String photo);

    Boolean editTraining(int idTraining, int prices, String levelTraining, int trainingDuration, String trainer, String trainingKind, String description, String name, String photo);
    Boolean createTrainingSchedule( int trainingId,  int workoutRoomId,  String dateTime);

    List<StatisticsDTO> statistics(String dateFrom, String dateTo);
}
