package com.ms.user.user.service;

import com.ms.user.exception.UserFoundException;
import com.ms.user.producer.UserProducer;
import com.ms.user.user.UserEntity;
import com.ms.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProducer userProducer;

    @Transactional
    public UserEntity execute(UserEntity userEntity){
        this.userRepository.findByEmail(userEntity.getEmail())
                .ifPresent(value -> {
                    throw new UserFoundException("Usuario ja cadastrado!");
        });

        UserEntity user = this.userRepository.save(userEntity);
        this.userProducer.publishMessageEmail(userEntity);

        return user;
    }
}
