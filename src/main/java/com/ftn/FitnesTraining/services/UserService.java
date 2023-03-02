package com.ftn.FitnesTraining.services;

import com.ftn.FitnesTraining.models.User;

import java.util.List;

public interface UserService {

    User findByUsernameOrEmail(String username, String username1);
    List<User> getAllUsers();
    boolean register(String name, String dateBirth, String adress, String password, String lastName, String username, String phoneNumber, String email) throws Exception;

    User user(int idUser);

    Boolean editUser(int idUser, String name, String lastName, String email, String adress, String phoneNumber, String dateBirth, String username);

    Boolean deleteUser(int idUser);

}
