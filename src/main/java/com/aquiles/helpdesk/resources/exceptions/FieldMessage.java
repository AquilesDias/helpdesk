package com.aquiles.helpdesk.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1l;

    private String field;
    private String message;

}
