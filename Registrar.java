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
    int counter = 0;
    for (int i = 0; i < this.courses.size(); i++) {
      if (this.courses.get(i).students.contains(c)) {
        counter = counter + 1;
      }
    }
    return counter >= 1;
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
    boolean check = false;
    for (int i = 0; i < this.courses.size(); i++) {
      for (int j = 0; j < c.courses.size(); j++) {
        if (c.courses.get(j).equals(this.courses.get(i))) {
          check = true;
        }
      }
    }
    return check;
  }
}

class ExamplesRegistrar {
  
  Student s1 = new Student("Liam", 01, new ArrayList<Course>());
  Student s2 = new Student("Jared", 02, new ArrayList<Course>());
  Student s3 = new Student("Sally", 03, new ArrayList<Course>());
  Student s4 = new Student("Jack", 04, new ArrayList<Course>());
  Student s5 = new Student("Dan", 05, new ArrayList<Course>());

  Instructor i1 = new Instructor("Razzaq", new ArrayList<Course>());
  Instructor i2 = new Instructor("Smith", new ArrayList<Course>());

  Course c1 = new Course("Fundies II", this.i1, new ArrayList<Student>());
  Course c2 = new Course("Discrete Structures", this.i1, new ArrayList<Student>());
  Course c3 = new Course("Physics I", this.i2, new ArrayList<Student>());
  Course c4 = new Course("Calculus", this.i2, new ArrayList<Student>());
  
  void initData() {
    
  this.s1 = new Student("Liam", 01, new ArrayList<Course>());
  this.s2 = new Student("Jared", 02, new ArrayList<Course>());
  this.s3 = new Student("Sally", 03, new ArrayList<Course>());
  this.s4 = new Student("Jack", 04, new ArrayList<Course>());
  this.s5 = new Student("Dan", 05, new ArrayList<Course>());

  this.i1 = new Instructor("Razzaq", new ArrayList<Course>());
  this.i2 = new Instructor("Smith", new ArrayList<Course>());
  
  this.i1.courses.add(this.c1);
  this.i1.courses.add(this.c2);
  this.i2.courses.add(this.c3);
  this.i2.courses.add(this.c4);
  

  this.c1 = new Course("Fundies II", this.i1, new ArrayList<Student>());
  this.c2 = new Course("Discrete Structures", this.i1, new ArrayList<Student>());
  this.c3 = new Course("Physics I", this.i2, new ArrayList<Student>());
  this.c4 = new Course("Calculus", this.i2, new ArrayList<Student>());
  }

  // tests enroll method
  void testEnroll(Tester t) {
    
    initData();
    
    this.s1.enroll(this.c1);
    t.checkExpect(this.c1.students.contains(this.s1), true);
    t.checkExpect(this.c1.students.contains(this.s2), false);
    this.s2.enroll(this.c1);
    t.checkExpect(this.c1.students.contains(this.s2), true);
    t.checkExpect(this.c1.students.contains(this.s3), false);
    t.checkExpect(this.c1.students.contains(this.s4), false);
  }
  
  // tests classmates method
  void testClassmates(Tester t) {
    
    initData();
    
    this.s1.enroll(this.c1);
    this.s2.enroll(this.c1);
    this.s3.enroll(this.c2);
    this.s4.enroll(this.c2);
    
    t.checkExpect(this.s1.classmates(this.s2), true);
    t.checkExpect(this.s2.classmates(this.s1), true);
    t.checkExpect(this.s1.classmates(this.s3), false);
    t.checkExpect(this.s3.classmates(this.s2), false);
    t.checkExpect(this.s3.classmates(this.s5), false);
  }
  
  // tests dejavu method
  
  void testDejavu(Tester t) {
    
    initData();
    
    this.s1.enroll(this.c1);
    this.s1.enroll(this.c2);
    this.s2.enroll(this.c3);
    this.s2.enroll(this.c4);
    this.s3.enroll(this.c1);
    this.s4.enroll(this.c1);
    this.s4.enroll(this.c3);
  
  t.checkExpect(this.i1.dejavu(this.s1), true);
  t.checkExpect(this.i1.dejavu(this.s2), false);
  t.checkExpect(this.i2.dejavu(this.s1), false);
  t.checkExpect(this.i2.dejavu(this.s2), true);
  t.checkExpect(this.i1.dejavu(this.s3), false);
  t.checkExpect(this.i1.dejavu(this.s4), false);
  t.checkExpect(this.i1.dejavu(this.s4), false);
  t.checkExpect(this.i2.dejavu(this.s4), false);
  t.checkExpect(this.i1.dejavu(this.s5), false);
  
  
  }
  
}

