package br.com.zup.proposta.excecoes;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorObject {

    private LocalDateTime currentTime;
    private List<FieldError> message;
    private List<ObjectError> messageList;

    public ErrorObject(LocalDateTime currentTime, List<FieldError> message, List<ObjectError> messageList) {
        this.currentTime = currentTime;
        this.message = message;
        this.messageList = messageList;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public List<FieldError> getMessage() {
        return message;
    }

    public List<ObjectError> getMessageList() {
        return messageList;
    }
}