package com.ms.email.consumers;

import com.ms.email.dto.EmailRecordDTO;
import com.ms.email.email.EmailEntity;
import com.ms.email.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO){
        var emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailRecordDTO, emailEntity);

        this.emailService.sendEmail(emailEntity);
    }
}
