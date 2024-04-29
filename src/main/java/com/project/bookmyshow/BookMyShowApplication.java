package com.project.bookmyshow;

import com.project.bookmyshow.Dtos.SignUpUserRequestDto;
import com.project.bookmyshow.Dtos.SignUpUserResponseDto;
import com.project.bookmyshow.controllers.UserController;
import com.project.bookmyshow.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    private UserController userController;
    @Autowired
    public BookMyShowApplication(UserController userController){
        this.userController = userController;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");

        SignUpUserRequestDto request1 = new SignUpUserRequestDto();
        request1.setEmail("dj@gmail.com");
        request1.setPassword("12345");


        SignUpUserResponseDto response = userController.signUpUser(request1);
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());

    }

    public static void main(String[] args) {

        SpringApplication.run(BookMyShowApplication.class, args);


    }

}
