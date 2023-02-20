package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.LoyaltyCard;
import com.ftn.FitnesTraining.models.User;
import com.ftn.FitnesTraining.repositorys.LoyaltyCardRepository;
import com.ftn.FitnesTraining.repositorys.UserRepository;
import com.ftn.FitnesTraining.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftn.FitnesTraining.security.SecurityConfiguration;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private static final int Initial_Number_Point = -10;
    private static final int Number_Points_Request = -9;
    private static final int BROJ_POENA_ZAHTEV_ODBIJEN = -8;
    private static final int BROJ_POENA_ZAHTEV_PRIHVACEN= 0;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoyaltyCardRepository loyaltyCardRepository;
    @Autowired
    SecurityConfiguration configuration;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public boolean register(String name, String dateBirth, String adress, String password, String lastName, String username, String phoneNumber, String email) throws Exception {
        User user = new User();
        LoyaltyCard ly = new LoyaltyCard();
        ly.setPoint(Initial_Number_Point);
        loyaltyCardRepository.saveAndFlush(ly);
        user.setLoyaltyCard(ly);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = format.parse(dateBirth);
        user.setAdress(adress);
        user.setLastName(lastName);
        user.setName(name);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(configuration.passwordEncoder().encode(password));
        Date dateRegister = new Date();
        user.setDateRegister(dateRegister);
        user.setEmail(email);
        user.setDateRegister(dateOfBirth);
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }
}
