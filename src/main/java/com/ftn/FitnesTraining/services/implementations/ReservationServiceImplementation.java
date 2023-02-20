package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.repositorys.ReservationRepository;
import com.ftn.FitnesTraining.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImplementation implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
}
