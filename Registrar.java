import java.util.ArrayList;
import tester.Tester;

class Course {
  String name;
  Instructor prof;
  ArrayList<Student> students;
  
  Course(String name, Instructor prof, ArrayList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;
    
  }
}

class Instructor {
  String name;
  ArrayList<Course> courses;
  
  Instructor(String name, ArrayList<Course> courses) {
    this.name = name;
    this.courses = new ArrayList<Course>();
  }
  
  // determines if a student is in the instructors class more than once
  boolean dejavu(Student c) {
    for(int i = 0; i < this.courses.size(); i++) {             // needs to operate like an andmap
      this.courses.get(i).students.contains(c);
      }
      
    }
  }

class Student {
  String name;
  int id;
  ArrayList<Course> courses;
  
  Student(String name, int id, ArrayList<Course> courses) {
    this.name = name;
    this.id = id;
    this.courses = new ArrayList<Course>();
  }
  
  // EFFECT: adds a course to a student and a student to a course
  void enroll(Course c) {
    this.courses.add(c);
    c.students.add(this);
  }
  
  // determines if the student and given student are in any of the same classes
  boolean classmates(Student c) {
    for(int i = 0; i < this.courses.size(); i++) {
      for(int j = 0; j < c.courses.size(); j++) {
        c.courses.get(j).equals(this.courses.get(i));
      }
      
    }
    return false;
  }
}

class ExamplesRegistrar {
                                                                            // initial data
  Student s1 = new Student("Liam", 01, new ArrayList<Course>());
  Student s2 = new Student("Jared", 02, new ArrayList<Course>());
  Student s3 = new Student("Sally", 03, new ArrayList<Course>());
  Student s4 = new Student("Jack", 04, new ArrayList<Course>());
  Student s5 = new Student("Dan", 05, new ArrayList<Course>());
  
  Instructor i1 = new Instructor("Razzaq", new ArrayList<Course>());
  Instructor i2 = new Instructor("Smith", new ArrayList<Course>());
  
  Course c1 = new Course("Fundies II", this.i1,new ArrayList<Student>());
  Course c2 = new Course("Discrete Structures", this.i1, new ArrayList<Student>());
  Course c3 = new Course("Physics I", this.i2, new ArrayList<Student>());
  Course c4 = new Course("Calculus", this.i2, new ArrayList<Student>());
}
