package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.LoyaltyCard;
import com.ftn.FitnesTraining.models.Reservation;
import com.ftn.FitnesTraining.models.TrainingSchedule;
import com.ftn.FitnesTraining.models.User;
import com.ftn.FitnesTraining.repositorys.LoyaltyCardRepository;
import com.ftn.FitnesTraining.repositorys.ReservationRepository;
import com.ftn.FitnesTraining.repositorys.TrainingScheduleRepository;
import com.ftn.FitnesTraining.repositorys.UserRepository;
import com.ftn.FitnesTraining.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImplementation implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    private List<Reservation> getReservationForUser(User user) {
        return reservationRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Reservation> viewReservation(boolean shoppingCart, String name) {
        User korisnik = userRepository.findByUsernameOrEmail(name, name);
        List<Reservation> rezervacijaList = getReservationForUser(korisnik);
        return rezervacijaList.stream().filter(rezervacija -> rezervacija.getConfirmation() == (byte) (shoppingCart ? 0 : 1)).collect(Collectors.toList());
    }

    @Override
    public Boolean shoppingCart(int idTrainingSchedule, int numberPoint, String name) {
        Optional<TrainingSchedule> trainingScheduleOptional = trainingScheduleRepository.findById(idTrainingSchedule);
        User user = userRepository.findByUsernameOrEmail(name, name);
        LoyaltyCard ck = user.getLoyaltyCard();
        if (numberPoint != 0 && (numberPoint > 5 || numberPoint > ck.getPoint())) {
            return false;
        }
        if (trainingScheduleOptional.isPresent()) {
            TrainingSchedule totr = trainingScheduleOptional.get();
            LocalDateTime pocetakTotr = totr.getDateTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime krajTotr = pocetakTotr.plusMinutes(totr.getTrening().getTrainingDuration());

            LocalDateTime currentDateTime = LocalDateTime.now();
            if (currentDateTime.isAfter(pocetakTotr)) {
                return false;
            }

            List<Reservation> rezervacijaList = getReservationForUser(user);
            for (Reservation reservation : rezervacijaList) {
                LocalDateTime pocetakT = reservation.getTrainingSchedule().getDateTime().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                LocalDateTime krajT = pocetakT.plusMinutes(reservation.getTrainingSchedule().getTrening().getTrainingDuration());
                if (pocetakTotr.isBefore(krajT) && pocetakT.isBefore(krajTotr)) {
                    return false;
                }
            }
            if (totr.getReservations().size() >= totr.getWorkoutRoom().getCapacity()) {
                return false;
            }
            Reservation reservation = new Reservation();
            reservation.setConfirmation((byte) 0);
            reservation.setUser(user);
            reservation.setTrainingSchedule(totr);
            reservation.setPoint(numberPoint);
            reservationRepository.saveAndFlush(reservation);
            if(numberPoint!=0){
                int stariBrojBodova = ck.getPoint();
                int noviBrojBodova = stariBrojBodova - numberPoint;
                ck.setPoint(noviBrojBodova);
                loyaltyCardRepository.save(ck);
            }
            return true;
        }
        return false;
    }


    @Override
    public Boolean processShoppingCart(boolean accepted, String name) {
        User user = userRepository.findByUsernameOrEmail(name, name);
        List<Reservation> rezervacijaList = getReservationForUser(user);
        for (Reservation reservation : rezervacijaList) {
            if(reservation.getConfirmation() == (byte) 1) continue;
            if (accepted) {
                reservation.setConfirmation((byte) 1);
                reservationRepository.save(reservation);

                int cena  = reservation.getTrainingSchedule().getTrening().getPrices();
                int brojBodvaNovih = cena / 500;
                LoyaltyCard ck = user.getLoyaltyCard();
                int brojStarihBodova = ck.getPoint();
                int noviBodovi = brojStarihBodova + brojBodvaNovih;
                ck.setPoint(noviBodovi);
                loyaltyCardRepository.save(ck);
            } else {
                reservationRepository.delete(reservation);
            }
        }
        return true;
    }

    @Override
    public Boolean deleteReservation(int idReservation) {
        reservationRepository.deleteById(idReservation);
        return true;
    }




}
