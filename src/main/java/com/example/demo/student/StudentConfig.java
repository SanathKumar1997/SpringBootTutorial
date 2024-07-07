package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {
            Student sanath =new Student(
                    "Sanath",
                    "Sanath@gmail.com",
            LocalDate.of(1997, Month.DECEMBER,12)

                    );

            Student kittu =new Student(
                    "kittuuu",
                    "kittu@gmail.com",
                    LocalDate.of(1998, Month.DECEMBER,13)
            );
            repository.saveAll(List.of(sanath,kittu));
        };

    }

}
