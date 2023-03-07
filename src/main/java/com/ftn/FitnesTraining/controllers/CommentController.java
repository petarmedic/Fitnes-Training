package com.ftn.FitnesTraining.controllers;

import com.ftn.FitnesTraining.models.Comment;
import com.ftn.FitnesTraining.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @QueryMapping
    public List<Comment> commentForTraining(@Argument int idTraining) {
        return commentService.commentForTraining(idTraining);
    }

    @MutationMapping
    public Boolean createComment(@Argument int idTraining, @Argument int rate, @Argument String anonymous, @Argument String text, Principal principal) {
        return commentService.createComment(text, rate, anonymous, idTraining, principal.getName());
    }

    @QueryMapping
 //   @PreAuthorize("hasAuthority('ADMIN')")
    public List<Comment> commentForApproval() {
        return commentService.commentForApproval();
    }


    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean processComment(@Argument int idComment, @Argument boolean approved) {
        return commentService.processComment(idComment, approved);
    }

}
