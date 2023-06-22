package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findAllByUserId(int userId);
    @Query(value = "SELECT COUNT(*) FROM reservation as r WHERE r.confirmation=:confirmation AND r.training_schedule_id=:training_schedule_id" , nativeQuery = true)
    int numberReservation(@Param("confirmation") int confirmation, @Param("training_schedule_id") int training_schedule_id);
}
