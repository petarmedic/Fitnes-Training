package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.models.Training;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

public interface TrainingService {
    List<Training> trainings(@Argument String filter, @Argument int priceFrom, @Argument int priceTo, @Argument String sort);

    Boolean createTraining( int prices,  String levelTraining,  int trainingDuration,  String trainer,  String trainingKind,  String description,  String name,  String photo);
}
