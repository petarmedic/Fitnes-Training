package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.repositorys.TrainingRepository;
import com.ftn.FitnesTraining.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImplementation implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;
}
