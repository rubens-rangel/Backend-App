package com.rubensrangel.BackendApp.resources.exceptionshandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandartError {
    private Integer status;
    private String msg;
    private Long timeStamp;
}
