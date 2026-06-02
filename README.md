# College Management System

A console-based college management system written in Java as an object-oriented programming exercise. It manages lecturers, departments, committees, and academic degrees through an interactive command-line menu.

Co-authored with Guy Forsht.

## Features

- Add lecturers (including `Doctor` and `Professor` types) and assign them to departments
- Create and manage departments, including average-salary reporting (overall and per department)
- Create committees, assign/remove lecturers, and update a committee's chairman
- Academic degrees (`BachelorDegree`, `MasterDegree`) and a `Publishable` interface for publications

## OOP concepts demonstrated

Inheritance (`Lecturer` → `Doctor` / `Professor`), interfaces (`Publishable`), method overloading (`isExisting`), and custom exceptions (`CollegeException`, `InvalidChairmanException`, `CommitteeOperationException`).

## Build & run

```bash
cd src
javac *.java
java Main
```

Then follow the on-screen menu — it first prompts for the college name, then offers options to add lecturers, departments, committees, and view reports.
