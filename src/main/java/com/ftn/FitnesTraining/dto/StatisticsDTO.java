package com.ftn.FitnesTraining.dto;

import java.util.Date;

public class StatisticsDTO {

    int idTraining;
    int numberReservation;
    int earnings;
    String trainer;
    String nameTraining;

    Date date;

    public StatisticsDTO(int idTraining, int numberReservation, int earnings, String trainer, String nameTraining, Date date) {
        this.idTraining = idTraining;
        this.numberReservation = numberReservation;
        this.earnings = earnings;
        this.trainer = trainer;
        this.nameTraining = nameTraining;
        this.date = date;
    }


    public StatisticsDTO() {

    }

    public int getIdTraining() {
        return idTraining;
    }

    public int getNumberReservation() {
        return numberReservation;
    }

    public int getEarnings() {
        return earnings;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getNameTraining() {
        return nameTraining;
    }

    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
    }

    public void setNumberReservation(int numberReservation) {
        this.numberReservation = numberReservation;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public void setNameTraining(String nameTraining) {
        this.nameTraining = nameTraining;
    }
}
