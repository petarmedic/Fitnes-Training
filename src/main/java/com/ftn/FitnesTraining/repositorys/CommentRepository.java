package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByTrainingId(int trainingId);
}
