package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.Comment;
import com.ftn.FitnesTraining.models.StatusComment;
import com.ftn.FitnesTraining.models.Training;
import com.ftn.FitnesTraining.models.User;
import com.ftn.FitnesTraining.repositorys.CommentRepository;
import com.ftn.FitnesTraining.repositorys.StatusCommentRepository;
import com.ftn.FitnesTraining.repositorys.TrainingRepository;
import com.ftn.FitnesTraining.repositorys.UserRepository;
import com.ftn.FitnesTraining.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    StatusCommentRepository statusCommentRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public List<Comment> commentForTraining(int idTraining) {
        return commentRepository.findAllByTrainingId(idTraining).stream().filter(kom -> kom.getStatusKomentara().getId() == 2).collect(Collectors.toList());

    }

    @Override
    public boolean createComment( String text, int rate, String anonymous, int idTraining, String username){
        Optional<StatusComment> podrazumevaniStatusOpt = statusCommentRepository.findById(1);
        Optional<Training> trainingOpt = trainingRepository.findById(idTraining);
        User user = userRepository.findByUsernameOrEmail(username, username);
        if (podrazumevaniStatusOpt.isPresent() && trainingOpt.isPresent()) {
            StatusComment status = podrazumevaniStatusOpt.get();
            Training training = trainingOpt.get();
            Comment comment = new Comment();
            if (anonymous.equalsIgnoreCase("ANONYMOUS")) {
                comment.setAnonymous((byte) 1);
            } else {
                comment.setAnonymous((byte) 0);
            }
            comment.setKorisnik(user);
            comment.setTrening(training);
            comment.setRate(rate);
            comment.setStatusKomentara(status);
            comment.setText(text);
            comment.setDateTime(new Date());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }




    @Override
    public List<Comment> commentForApproval() {
        return commentRepository.findAll().stream().filter(kom -> kom.getStatusKomentara().getId() == 1).collect(Collectors.toList());
    }


    @Override
    public Boolean processComment(int idComment, boolean approved) {
        Optional<Comment> commentOpt = commentRepository.findById(idComment);
        if(commentOpt.isPresent()){
            Comment comment = commentOpt.get();
            if(approved){
                comment.setStatusKomentara(statusCommentRepository.getById(2));
            }else{
                comment.setStatusKomentara(statusCommentRepository.getById(3));
            }
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

}

