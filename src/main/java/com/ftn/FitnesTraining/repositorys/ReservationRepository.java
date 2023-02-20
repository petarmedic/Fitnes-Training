package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findAllByUserId(int userId);
}
