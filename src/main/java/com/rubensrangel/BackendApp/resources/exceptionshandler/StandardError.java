package com.rubensrangel.BackendApp.resources.exceptionshandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
