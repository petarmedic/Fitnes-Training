package com.ftn.FitnesTraining.repositorys;

import com.ftn.FitnesTraining.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

}
