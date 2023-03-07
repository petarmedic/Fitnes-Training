package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.models.Comment;
import org.springframework.graphql.data.method.annotation.Argument;

import java.security.Principal;
import java.util.List;

public interface CommentService {

    Boolean processComment(int idComment,boolean approved);

    List<Comment> commentForApproval();

    List<Comment> commentForTraining(int idTraining);

    boolean createComment( String text, int rate, String anonymous, int idTraining, String username);
}
