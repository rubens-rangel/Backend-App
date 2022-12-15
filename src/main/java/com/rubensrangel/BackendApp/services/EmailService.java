package com.rubensrangel.BackendApp.services;

import com.rubensrangel.BackendApp.domain.Cliente;
import com.rubensrangel.BackendApp.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
