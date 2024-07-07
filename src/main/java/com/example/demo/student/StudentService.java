package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
   return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email Already Exists");

        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
      boolean exists=  studentRepository.existsById(studentId);
      if(!exists) {
          throw new IllegalStateException("Id does not exists");
      }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStatement(long studentId,String name,String email){

        Student student =studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("student with id "+studentId+" already existss"));

        if(name!= null && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email!= null && !Objects.equals(student.getEmail(),email)){
            student.setEmail(email);
        }



    }



}
