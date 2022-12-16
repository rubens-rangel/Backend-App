package com.rubensrangel.BackendApp;

import com.rubensrangel.BackendApp.domain.*;
import com.rubensrangel.BackendApp.domain.enums.EstadoPagamento;
import com.rubensrangel.BackendApp.domain.enums.TipoCliente;
import com.rubensrangel.BackendApp.repositories.*;
import com.rubensrangel.BackendApp.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class BackendAppApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(BackendAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    s3Service.uploadFile("//home/rubens//Área de Trabalho//pessoal//discord.jpeg");

    }


}
