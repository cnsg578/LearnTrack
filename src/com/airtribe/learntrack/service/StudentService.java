package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        studentRepository.save(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        studentRepository.save(student);
        return student;
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    public void deactivateStudent(int id) {
        Student s = studentRepository.findById(id);
        s.setActive(false);
    }

    public void updateStudentEmail(int id, String newEmail) {
        Student s = studentRepository.findById(id);
        s.setEmail(newEmail);
    }
}
