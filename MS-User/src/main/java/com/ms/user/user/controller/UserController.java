package com.ms.user.user.controller;

import com.ms.user.user.UserEntity;
import com.ms.user.user.dtos.UserRecordsDTO;
import com.ms.user.user.service.RegisterUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping()
    public ResponseEntity<Object> register(@Valid @RequestBody UserRecordsDTO userRecordsDTO){

        try{

            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userRecordsDTO, userEntity);

            UserEntity user = this.registerUserService.execute(userEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
