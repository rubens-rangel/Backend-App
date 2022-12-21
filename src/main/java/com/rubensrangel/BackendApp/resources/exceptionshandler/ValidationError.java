package com.rubensrangel.BackendApp.resources.exceptionshandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }


    public  void addError(String fildName, String messagem){
        erros.add(new FieldMessage(fildName, messagem));
    }

    public List<FieldMessage> getErrors() {
        return erros;
    }
}
