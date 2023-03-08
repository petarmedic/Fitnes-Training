package com.ftn.FitnesTraining.controllers;

import com.ftn.FitnesTraining.models.Reservation;
import com.ftn.FitnesTraining.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @MutationMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    public Boolean shoppingCart(@Argument int idTrainingSchedule, @Argument int numberPoint, Principal principal){
        return reservationService.shoppingCart(idTrainingSchedule, numberPoint, principal.getName());
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN') ")
    public List<Reservation> viewReservation(@Argument boolean shoppingCart, Principal principal) {
        return reservationService.viewReservation(shoppingCart, principal.getName());
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN') ")
    public Boolean processShoppingCart(@Argument boolean accepted, Principal principal){
        return reservationService.processShoppingCart(accepted, principal.getName());
    }

}
