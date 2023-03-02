package com.ftn.FitnesTraining.services.implementations;

import com.ftn.FitnesTraining.models.Training;
import com.ftn.FitnesTraining.repositorys.TrainingRepository;
import com.ftn.FitnesTraining.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Service
public class TrainingServiceImplementation implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @PersistenceContext
    private EntityManager em;

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
    public List<Training> trainings(String filter, int priceFrom, int priceTo, String sort) {

        StringBuilder whereBuilder = new StringBuilder();
        boolean missingWhere = true;
        if (Objects.nonNull(filter) && !filter.isBlank()) {
            missingWhere = false;
            whereBuilder.append(" WHERE (tr.name like '%").append(filter).append("%'");
            whereBuilder.append(" OR tr.description like '%").append(filter).append("%'");
            whereBuilder.append(" OR tr.trainer like '%").append(filter).append("%')");
        }
        if (priceFrom > 0) {
            if (missingWhere) {
                whereBuilder.append(" WHERE");
            } else {
                whereBuilder.append(" AND");
            }
            whereBuilder.append(" tr.prices > ").append(priceFrom);
        }

        if (priceTo > 0) {
            if (missingWhere) {
                whereBuilder.append(" WHERE");
            } else {
                whereBuilder.append(" AND");
            }
            whereBuilder.append(" tr.prices < ").append(priceTo);
        }
        String where = whereBuilder.toString();
        String order = "";
        if (Objects.nonNull(sort) && !sort.isBlank()) {
            order = String.format(" order by tr.%s", sort);
        }

        String query = String.format("SELECT tr FROM Training tr%s%s", where, order);
        return em.createQuery(query).getResultList();
    }
}
