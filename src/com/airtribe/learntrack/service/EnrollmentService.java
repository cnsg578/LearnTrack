package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public Enrollment enrollStudent(int studentId, int courseId) {
        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(
                id,
                studentId,
                courseId,
                LocalDate.now(),
                EnrollmentStatus.ACTIVE
        );
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment findById(int id) {
        return enrollmentRepository.findById(id);
    }

    public void markCompleted(int enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId);
        e.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void cancel(int enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId);
        e.setStatus(EnrollmentStatus.CANCELLED);
    }

    public List<Enrollment> listAll() {
        return enrollmentRepository.findAll();
    }
}
