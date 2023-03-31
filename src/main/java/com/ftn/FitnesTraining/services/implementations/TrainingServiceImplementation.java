package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.dto.StatisticsDTO;
import com.ftn.FitnesTraining.models.Training;
import com.ftn.FitnesTraining.models.TrainingSchedule;
import com.ftn.FitnesTraining.models.WorkoutRoom;
import com.ftn.FitnesTraining.repositorys.ReservationRepository;
import com.ftn.FitnesTraining.repositorys.TrainingRepository;
import com.ftn.FitnesTraining.repositorys.TrainingScheduleRepository;
import com.ftn.FitnesTraining.repositorys.WorkoutRoomRepository;
import com.ftn.FitnesTraining.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TrainingServiceImplementation implements TrainingService {

    private static final String YYYY_MM_DD_T_HH_MM = "yyyy-MM-dd'T'HH:mm";

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Autowired
    WorkoutRoomRepository workoutRoomRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Boolean createTraining( int prices,  String levelTraining,  int trainingDuration,  String trainer,  String trainingKind,  String description,  String name,  String photo) {
        Training t = new Training();
        t.setPrices(prices);
        t.setTrainer(trainer);
        t.setDescription(description);
        t.setName(name);
        t.setLevelTraining(levelTraining);
        t.setTrainingDuration(trainingDuration);
        t.setTrainingKind(trainingKind);
        t.setPhoto(photo);
        trainingRepository.save(t);
        return true;
    }

    @Override
    public Boolean editTraining(int idTraining, int prices, String levelTraining, int trainingDuration, String trainer, String trainingKind, String description, String name, String photo) {
        Optional<Training> tOpt = trainingRepository.findById(idTraining);
        if (tOpt.isPresent()) {
            Training t = tOpt.get();
            t.setPrices(prices);
            t.setTrainer(trainer);
            t.setDescription(description);
            t.setName(name);
            t.setLevelTraining(levelTraining);
            t.setTrainingDuration(trainingDuration);
            t.setTrainingKind(trainingKind);
            t.setPhoto(photo);
            trainingRepository.save(t);
        }
        return false;
    }

    @Override
    public List<Training> trainings( int priceFrom, int priceTo) {

        if (Objects.equals(priceFrom, null) || Objects.equals(priceTo, null) || priceFrom == 0 && priceTo == 0) {
            // dodeli vrednost 0
            priceFrom = 0;
            priceTo = 0;
        }

        StringBuilder whereBuilder = new StringBuilder();
        boolean missingWhere = true;

        if (priceFrom != 0 || priceTo != 0) {
            if (missingWhere) {
                whereBuilder.append(" WHERE");
            } else {
                whereBuilder.append(" AND");
            }
            whereBuilder.append(" tr.prices > ").append(priceFrom).append(" AND tr.prices < ").append(priceTo);
        }

        String query = String.format("SELECT tr FROM Training tr%s", whereBuilder.toString());
        return em.createQuery(query).getResultList();
    }
    @Override
    public Training training(int idTraining) {
        return trainingRepository.findById(idTraining).get();
    }

    @Override
    public Boolean createTrainingSchedule(int trainingId, int workoutRoomId, String dateTime) {
        Optional<WorkoutRoom> sOpt = workoutRoomRepository.findById(workoutRoomId);
        Optional<Training> tOpt = trainingRepository.findById(trainingId);
        if (sOpt.isPresent() && tOpt.isPresent()) {
            WorkoutRoom s = sOpt.get();
            Training t = tOpt.get();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM);
            LocalDateTime pocetak = LocalDateTime.parse(dateTime, dateTimeFormatter);
            if (pocetak.isBefore(LocalDateTime.now())) return false;
            LocalDateTime kraj = pocetak.plusMinutes(t.getTrainingDuration());
            List<TrainingSchedule> terminiOdrzavanjaTreninga = trainingScheduleRepository.findAll();
            for (TrainingSchedule termin : terminiOdrzavanjaTreninga) {
                if (termin.getWorkoutRoom().getId() == workoutRoomId) {
                    LocalDateTime pocetakTerminaT = termin.getDateTime().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    ;
                    LocalDateTime krajTerminaT = pocetakTerminaT.plusMinutes(termin.getTrening().getTrainingDuration());
                    if (pocetak.isBefore(krajTerminaT) && pocetakTerminaT.isBefore(kraj)) {
                        return false;
                    }
                }
            }
            return createTrainingSchedule(s, t, pocetak);
        }
        return false;
    }

    private boolean createTrainingSchedule(WorkoutRoom s, Training t, LocalDateTime pocetak) {
        TrainingSchedule totr = new TrainingSchedule();
        totr.setTrening(t);
        totr.setWorkoutRoom(s);
        totr.setDateTime(Date.from(pocetak.atZone(ZoneId.systemDefault()).toInstant()));
        trainingScheduleRepository.save(totr);
        return true;
    }


    @Override
    public List<StatisticsDTO> statistics(String datumOd, String datumDo) {
        List<StatisticsDTO> statistika = new ArrayList<>();
        List<Training> treningList = trainingRepository.findAll();
        treningList.forEach(tr -> {
            AtomicInteger brojZakazivanja = new AtomicInteger();
            tr.getTrainingSchedules().forEach(termin -> {

                if (!datumDo.isBlank() && !datumDo.isBlank()) {
                    try {

                        Date datumOdDate = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
                        Date datumDoDate = new SimpleDateFormat("yyyy-MM-dd").parse(datumDo);
                        if (termin.getDateTime().after(datumOdDate) && termin.getDateTime().before(datumDoDate)) {
                            brojZakazivanja.set((int) termin.getReservations().stream().filter(rezervacija -> rezervacija.getConfirmation() == (byte) 1).count());
                        }
                    } catch (Exception ignored) {
                    }
                } else {
                    brojZakazivanja.set((int) termin.getReservations().stream().filter(rezervacija -> rezervacija.getConfirmation() == (byte) 1).count());

                }
                int earnings = tr.getPrices() * brojZakazivanja.get();
                StatisticsDTO s = new StatisticsDTO(tr.getId(), brojZakazivanja.get(), earnings, tr.getTrainer(), tr.getName());
       //         StatisticsDTO s = new StatisticsDTO(tr.getId(), brojZakazivanja.get(), earnings, tr.getTrainer(), tr.getName(), termin.getDateTime());
                // add in constructor date
                statistika.add(s);
            });

        });
        return statistika;
    }


}
