package com.ftn.FitnesTraining.dto;

public class MessageDTO {

    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public MessageDTO(String message) {
        Message = message;
    }

    public MessageDTO() {
    }
}
