package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard,Integer> {
}
