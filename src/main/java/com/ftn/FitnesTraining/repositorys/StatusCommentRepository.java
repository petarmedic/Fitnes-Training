package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.StatusComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCommentRepository extends JpaRepository<StatusComment,Integer> {
}
