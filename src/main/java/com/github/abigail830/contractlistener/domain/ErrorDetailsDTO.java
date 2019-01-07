package com.github.abigail830.contractlistener.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter
@ToString
public class ErrorDetailsDTO {

    private Date date;
    private String message;
    private String description;
    private HttpStatus status;

    public ErrorDetailsDTO(Date date, String message, String description, HttpStatus unProcessableEntity) {
        this.message = message;
        this.description = description;
        this.date = date;
        this.status = unProcessableEntity;

    }
}
