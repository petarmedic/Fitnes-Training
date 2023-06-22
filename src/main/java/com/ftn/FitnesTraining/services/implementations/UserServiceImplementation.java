package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.LoyaltyCard;
import com.ftn.FitnesTraining.models.User;
import com.ftn.FitnesTraining.repositorys.LoyaltyCardRepository;
import com.ftn.FitnesTraining.repositorys.UserRepository;
import com.ftn.FitnesTraining.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import com.ftn.FitnesTraining.security.SecurityConfiguration;

import java.text.ParseException;
import java.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private static final int Initial_Number_Point = -10;
    private static final int NUMBER_POINT_REQUEST = -9;
    private static final int NUMBER_POINT_REQUEST_REJECT = -8;
    private static final int NUMBER_POINT_REQUEST_ACCEPT= 10;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoyaltyCardRepository loyaltyCardRepository;
    @Autowired
    SecurityConfiguration configuration;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> Boolean.TRUE.equals(user.getActive()))
                .collect(Collectors.toList());
        // ako je u mysql 1 ili true
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
        LocalDateTime localDateTime = LocalDateTime.now();
        Date dateOfRegistration = format.parse(String.valueOf(localDateTime));
        user.setAdress(adress);
        user.setLastName(lastName);
        user.setName(name);
        Optional<User> user1 = userRepository.findByUsername(username);
        if(user1.isPresent()){
            return false;
        }else{
            user.setUsername(username);
        }
        user.setPhoneNumber(phoneNumber);
        user.setPassword(configuration.passwordEncoder().encode(password));
        Date dateRegister = new Date();
        user.setDateRegister(dateOfRegistration);
        user.setEmail(email);
        user.setDateBirth(dateOfBirth);
        user.setRole("USER");
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public User user(int idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public Boolean editUser(int idUser, String name, String lastName, String email, String adress, String phoneNumber, String dateBirth, String username) {
        Optional<User> tOpt = userRepository.findById(idUser);
        if (tOpt.isPresent()) {
            User u = tOpt.get();
            u.setName(name);
            u.setLastName(lastName);
            u.setEmail(email);
            u.setAdress(adress);
            u.setPhoneNumber(phoneNumber);
            u.setUsername(username);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date birthDate = dateFormat.parse(dateBirth);
                u.setDateBirth(birthDate);
            }catch (ParseException e){
                return false;
            }


            userRepository.save(u);
        }
        return true;
    }

    @Override
    public Boolean deleteUser(int idUser) {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            userRepository.save(user);
            return true; // vraća true samo ako je korisnik uspešno ažuriran
        } else {
            return false;
        }
    }

    @Override
    public Boolean requestCard(String name) {
        User korisnik = userRepository.findByUsernameOrEmail(name, name);
        LoyaltyCard ck = korisnik.getLoyaltyCard();

        if(ck.getPoint() == Initial_Number_Point || ck.getPoint() == NUMBER_POINT_REQUEST_REJECT){
            ck.setPoint(NUMBER_POINT_REQUEST);
            loyaltyCardRepository.save(ck);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> requestForCard() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(korisnik -> korisnik.getLoyaltyCard().getPoint() == NUMBER_POINT_REQUEST).collect(Collectors.toList());
    }

    @Override
    public Boolean processCard(boolean process, int idUser) {
        User k = userRepository.getReferenceById(idUser);
        LoyaltyCard ck = k.getLoyaltyCard();
        if(process){
            ck.setPoint(NUMBER_POINT_REQUEST_ACCEPT);
        }else{
            ck.setPoint(NUMBER_POINT_REQUEST_REJECT);
        }
        loyaltyCardRepository.save(ck);
        return true;
    }

}
