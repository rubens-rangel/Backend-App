package com.rubensrangel.BackendApp.resources.exceptionshandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FildMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fildName;
    private  String msg;
}
