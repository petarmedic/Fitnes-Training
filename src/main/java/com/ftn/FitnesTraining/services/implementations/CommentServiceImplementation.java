package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.repositorys.CommentRepository;
import com.ftn.FitnesTraining.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    CommentRepository commentRepository;
}
