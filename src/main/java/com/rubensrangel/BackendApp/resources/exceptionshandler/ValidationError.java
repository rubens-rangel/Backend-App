package com.rubensrangel.BackendApp.resources.exceptionshandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public  void addError(String fildName, String messagem){
        erros.add(new FieldMessage(fildName, messagem));
    }

    public List<FieldMessage> getErrors() {
        return erros;
    }
}
