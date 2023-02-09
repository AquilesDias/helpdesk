package com.aquiles.helpdesk.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1l;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path ){
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors(){
        return errors;
    }

    public void addErrors(String fieldName, String messsage) {
        this.errors.add(new FieldMessage(fieldName, messsage));
    }
}
