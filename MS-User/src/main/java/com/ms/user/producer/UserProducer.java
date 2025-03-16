package com.ms.user.producer;

import com.ms.user.dto.EmailDTO;
import com.ms.user.user.UserEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserEntity userEntity){
        EmailDTO emailDto = new EmailDTO();
        emailDto.setUserId(userEntity.getId());
        emailDto.setEmailTo(userEntity.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userEntity.getName() + ", seja bem-vindo! \nAgradecemos o seu cadastro, aproveite nossos serviços.");

        rabbitTemplate.convertAndSend("",routingKey, emailDto);
    }
}
