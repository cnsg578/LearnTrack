package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        System.out.println("Welcome to " + AppConstants.APP_NAME);
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            int choice = readInt("Enter option: ");
            switch (choice) {
                case MenuOptions.MAIN_STUDENT:
                    handleStudentMenu();
                    break;
                case MenuOptions.MAIN_COURSE:
                    handleCourseMenu();
                    break;
                case MenuOptions.MAIN_ENROLLMENT:
                    handleEnrollmentMenu();
                    break;
                case MenuOptions.MAIN_EXIT:
                    exit = true;
                    System.out.println("Exiting LearnTrack. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n==== LearnTrack Main Menu ====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    // ---------- Student menu ----------

    private static void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Student Management --");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate student");
            System.out.println("0. Back to main menu");

            int choice = readInt("Enter option: ");
            try {
                switch (choice) {
                    case MenuOptions.STUDENT_ADD:
                        addStudentFlow();
                        break;
                    case MenuOptions.STUDENT_LIST:
                        listStudentsFlow();
                        break;
                    case MenuOptions.STUDENT_SEARCH:
                        searchStudentFlow();
                        break;
                    case MenuOptions.STUDENT_DEACTIVATE:
                        deactivateStudentFlow();
                        break;
                    case MenuOptions.STUDENT_BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudentFlow() {
        System.out.println("\nAdd New Student:");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email (optional, press enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student;
        if (email == null || email.trim().isEmpty()) {
            student = studentService.addStudent(firstName, lastName, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }

        System.out.println("Student added with ID: " + student.getId());
    }

    private static void listStudentsFlow() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nStudents:");
        for (Student s : students) {
            System.out.println(
                    s.getId() + " - " + s.getDisplayName()
                            + " | batch=" + s.getBatch()
                            + " | active=" + s.isActive()
            );
        }
    }

    private static void searchStudentFlow() {
        int id = readInt("Enter student ID: ");
        Student s = studentService.findById(id);
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getDisplayName());
        System.out.println("Email: " + s.getEmail());
        System.out.println("Batch: " + s.getBatch());
        System.out.println("Active: " + s.isActive());
    }

    private static void deactivateStudentFlow() {
        int id = readInt("Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    // ---------- Course menu ----------

    private static void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Course Management --");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate course");
            System.out.println("4. Deactivate course");
            System.out.println("0. Back to main menu");

            int choice = readInt("Enter option: ");
            try {
                switch (choice) {
                    case MenuOptions.COURSE_ADD:
                        addCourseFlow();
                        break;
                    case MenuOptions.COURSE_LIST:
                        listCoursesFlow();
                        break;
                    case MenuOptions.COURSE_ACTIVATE:
                        activateCourseFlow();
                        break;
                    case MenuOptions.COURSE_DEACTIVATE:
                        deactivateCourseFlow();
                        break;
                    case MenuOptions.COURSE_BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCourseFlow() {
        System.out.println("\nAdd New Course:");
        System.out.print("Course name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        int duration = readInt("Duration in weeks: ");

        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added with ID: " + course.getId());
    }

    private static void listCoursesFlow() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\nCourses:");
        for (Course c : courses) {
            System.out.println(
                    c.getId() + " - " + c.getCourseName()
                            + " | duration=" + c.getDurationInWeeks() + " weeks"
                            + " | active=" + c.isActive()
            );
        }
    }

    private static void activateCourseFlow() {
        int id = readInt("Enter course ID to activate: ");
        courseService.activateCourse(id);
        System.out.println("Course activated.");
    }

    private static void deactivateCourseFlow() {
        int id = readInt("Enter course ID to deactivate: ");
        courseService.deactivateCourse(id);
        System.out.println("Course deactivated.");
    }

    // ---------- Enrollment menu ----------

    private static void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Enrollment Management --");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment as completed");
            System.out.println("4. Cancel enrollment");
            System.out.println("0. Back to main menu");

            int choice = readInt("Enter option: ");
            try {
                switch (choice) {
                    case MenuOptions.ENROLLMENT_ENROLL:
                        enrollStudentFlow();
                        break;
                    case MenuOptions.ENROLLMENT_VIEW_BY_STUDENT:
                        viewEnrollmentsForStudentFlow();
                        break;
                    case MenuOptions.ENROLLMENT_COMPLETE:
                        markEnrollmentCompletedFlow();
                        break;
                    case MenuOptions.ENROLLMENT_CANCEL:
                        cancelEnrollmentFlow();
                        break;
                    case MenuOptions.ENROLLMENT_BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollStudentFlow() {
        int studentId = readInt("Enter student ID: ");
        int courseId = readInt("Enter course ID: ");

        studentService.findById(studentId);
        courseService.findById(courseId);

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment created with ID: " + enrollment.getId());
    }

    private static void viewEnrollmentsForStudentFlow() {
        int studentId = readInt("Enter student ID: ");
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }
        System.out.println("\nEnrollments:");
        for (Enrollment e : enrollments) {
            System.out.println(
                    "Enrollment ID: " + e.getId()
                            + " | Course ID: " + e.getCourseId()
                            + " | Date: " + e.getEnrollmentDate()
                            + " | Status: " + e.getStatus()
            );
        }
    }

    private static void markEnrollmentCompletedFlow() {
        int enrollmentId = readInt("Enter enrollment ID: ");
        enrollmentService.markCompleted(enrollmentId);
        System.out.println("Enrollment marked as COMPLETED.");
    }

    private static void cancelEnrollmentFlow() {
        int enrollmentId = readInt("Enter enrollment ID: ");
        enrollmentService.cancel(enrollmentId);
        System.out.println("Enrollment CANCELLED.");
    }

    // ---------- Helpers ----------

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }
}
