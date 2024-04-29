package com.project.bookmyshow.controllers;

import com.project.bookmyshow.Dtos.SignUpUserRequestDto;
import com.project.bookmyshow.Dtos.SignUpUserResponseDto;
import com.project.bookmyshow.exceptions.EmailAlreadyExistsException;
import com.project.bookmyshow.models.User;
import com.project.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto request){
        String email = request.getEmail();
        String password = request.getPassword();
        SignUpUserResponseDto response = new SignUpUserResponseDto();
        try{
            User user =  userService.signUpUser(email,password);
            response.setUserId(user.getId());
            response.setStatus("Success");
            response.setMessage("User signed up successfully..");
        }catch(EmailAlreadyExistsException emailAlreadyExistsException){
            response.setStatus("Failure");
            response.setMessage("Email already exists..!");

        }

        return response;
    }


}
