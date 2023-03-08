package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.models.Reservation;
import org.springframework.graphql.data.method.annotation.Argument;

import java.security.Principal;
import java.util.List;

public interface ReservationService {

    Boolean  shoppingCart( int idTrainingSchedule,  int numberPoint, String name);

    List<Reservation> viewReservation(boolean shoppingCart, String name);

    Boolean processShoppingCart(boolean accepted, String name);
}
