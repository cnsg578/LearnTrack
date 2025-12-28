package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public Course addCourse(String name, String description, int durationInWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, name, description, durationInWeeks);
        courseRepository.save(course);
        return course;
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findById(int id) {
        return courseRepository.findById(id);
    }

    public void activateCourse(int id) {
        Course c = courseRepository.findById(id);
        c.setStatus(CourseStatus.ACTIVE);
    }

    public void deactivateCourse(int id) {
        Course c = courseRepository.findById(id);
        c.setStatus(CourseStatus.INACTIVE);
    }
}
