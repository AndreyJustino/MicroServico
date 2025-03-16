package com.ms.email.email.service;

import com.ms.email.StatusEmail;
import com.ms.email.exception.EmailFoundException;
import com.ms.email.email.EmailEntity;
import com.ms.email.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailEntity sendEmail(EmailEntity emailEntity){

        try{
            emailEntity.setSendDataEmail(LocalDateTime.now());
            emailEntity.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEntity.getEmailTo());
            message.setText(emailEntity.getText());
            message.setSubject(emailEntity.getSubject());
            mailSender.send(message);

            emailEntity.setStatusEmail(StatusEmail.ENVIADO);

        } catch (MailException e) {
            emailEntity.setStatusEmail(StatusEmail.ERRO);
        }finally {
            return this.emailRepository.save(emailEntity);
        }
    }
}
