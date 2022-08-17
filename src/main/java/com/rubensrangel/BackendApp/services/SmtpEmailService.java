package com.rubensrangel.BackendApp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOGGER.info("Enviando email...");
        mailSender.send(msg);
        LOGGER.info("enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOGGER.info("Enviando email html...");
        javaMailSender.send(msg);
        LOGGER.info("enviado");
    }

}
