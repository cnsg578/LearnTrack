# LearnTrack - Student & Course Management System

LearnTrack is a console-based Core Java application that helps manage students, courses, and enrollments in memory.  
It is designed to practice Java fundamentals such as classes and objects, inheritance, encapsulation, ArrayList-based storage, basic exception handling, and a menu-driven console UI.

## Features

- Student Management
    - Add new students (with/without email)
    - View all students
    - Search student by ID
    - Deactivate a student (soft delete using `active = false`)

- Course Management
    - Add new courses
    - View all courses
    - Activate / Deactivate a course

- Enrollment Management
    - Enroll a student into a course
    - View enrollments for a student
    - Mark enrollment as COMPLETED or CANCELLED

- Implementation Details
    - Core Java, no external frameworks
    - OOP concepts: inheritance (`Student extends Person`), polymorphism, encapsulation
    - In-memory storage using `ArrayList`
    - Custom exceptions for missing entities and invalid input

---

## How to Compile and Run

### Prerequisites

- Java JDK 22 (or compatible version) installed
- IntelliJ IDEA (or any Java IDE)

### Run with IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Click **File → Open…** and select the `LearnTrack` project folder.
3. Ensure the Project SDK is set to your installed JDK (e.g., 22).
4. In the Project view, open:  
   `src/com/airtribe/learntrack/Main.java`
5. Right-click inside the `main` method → **Run 'Main.main()'**.
6. Use the numbered menu in the Run console to manage students, courses, and enrollments.

### Compile and Run from Command Line

From the project root (where `src` folder exists):

Compile all Java files
javac -d out src/com/airtribe/learntrack/.java src/com/airtribe/learntrack//*.java

Run the application
java -cp out com.airtribe.learntrack.Main


The console menu will appear, and you can follow the prompts to interact with the system.
