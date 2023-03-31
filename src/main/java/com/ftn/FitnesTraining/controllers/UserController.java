package com.ftn.FitnesTraining.controllers;

import com.ftn.FitnesTraining.dto.LoggedInUserDTO;
import com.ftn.FitnesTraining.dto.MessageDTO;
import com.ftn.FitnesTraining.models.User;
import com.ftn.FitnesTraining.services.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import com.ftn.FitnesTraining.security.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserService userService;

    @QueryMapping
    public LoggedInUserDTO login(@Argument String username, @Argument String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user = userService.findByUsernameOrEmail(username, username);
        return new LoggedInUserDTO(user.getId(), tokenUtils.generateToken(userDetails),
                user.getUsername(), userDetails.getUsername(), userDetails.getAuthorities());
    }

    @MutationMapping
    public boolean register(@Argument String name, @Argument String dateBirth, @Argument String adress, @Argument String password, @Argument String lastName, @Argument String username, @Argument String phoneNumber, @Argument String email){
        try{
            return userService.register(name, dateBirth, adress, password, lastName, username, phoneNumber, email);
        }catch (Exception e){
            return false;
        }
    }

    @QueryMapping
    public User profil(Principal principal){
        return userService.findByUsernameOrEmail(principal.getName(), principal.getName());
    }

    @QueryMapping
    List<User> allUsers(){
        return userService.getAllUsers();
    }

    @QueryMapping
    public User user(@Argument int idUser) {
        return userService.user(idUser);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean editUser(@Argument int idUser, @Argument String name, @Argument String lastName, @Argument String email, @Argument String adress, @Argument String phoneNumber, @Argument String dateBirth, @Argument String username){
        return userService.editUser(idUser, name, lastName, email, adress, phoneNumber, dateBirth, username);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean deleteUser(@Argument int idUser){

        return userService.deleteUser(idUser);
    }

    @PreAuthorize("hasAuthority('USER')")
    @QueryMapping
    public MessageDTO requestCard(Principal principal) {
        return userService.requestCard(principal.getName()) ? new MessageDTO("Successfully") : new MessageDTO("You already have a card or have already sent a request");
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @QueryMapping
    public List<User> requestForCard(){
        return userService.requestForCard();
    }

    @MutationMapping
    public Boolean processCard(@Argument boolean process, @Argument int idUser){
        return userService.processCard(process, idUser);
    }

}
