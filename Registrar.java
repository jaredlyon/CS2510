import tester.Tester;
import java.util.function.Predicate;

//represents a generic list interface
interface IList<T> {

  // ormap this list by the given predicate
  boolean ormap(Predicate<T> pred);

  // checks if a student is in a list of courses
  boolean checkCourse(Student c);
  
  // checks if a student is in a list of students
  public boolean checkStudentList(Student c);

  // helper for dejavu
  boolean dejavuHelper(Student c, int acc);
}

//represents an empty list
class MtList<T> implements IList<T> {
  MtList() {

  }

  // ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return false;
  }

  // checks if a student is in a list of courses
  public boolean checkCourse(Student c) {
    return false;
  }
  
  // checks if a student is in a list of students
  public boolean checkStudentList(Student c) {
    return false;
  }

  // helper for dejavu
  public boolean dejavuHelper(Student c, int acc) {
    return acc >= 2;
  }
}

//represents a generic list with data T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return pred.test(this.first) || this.rest.ormap(pred);
  }

  // checks if a student is in a list of courses
  public boolean checkCourse(Student c) {
    return (this.first.checkStudentList(c) || this.rest.checkCourse(c);
  }
  
  // checks if a student is in a list of students
  public boolean checkStudentList(Student c) {
    return this.students.ormap(new checkStudent(c));
  }

  // helper for dejavu
  public boolean dejavuHelper(Student c, int acc) {
    if (this.first.checkStudentList(c)) {
      return this.rest.dejavuHelper(c, acc + 1);
    }
    else {
      return this.rest.dejavuHelper(c, acc);
    }
  }

}

class checkStudent implements Predicate<Student> {
  Student c;
  
  checkStudent(Student c) {
    this.c = c;
  }
  
  // checks if two students are the same
  public boolean test(Student stu) {
    return c.equals(stu);
  }
  
}

class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;

  }
}

class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = new MtList<Course>();
  }

  // determines if a student is in the instructors class more than once
  boolean dejavu(Student c) {
    return this.courses.dejavuHelper(c, 0);
  }

}

class Student {
  String name;
  int id;
  IList<Course> courses;

  Student(String name, int id, IList<Course> courses) {
    this.name = name;
    this.id = id;
    this.courses = new MtList<Course>();
  }

  // EFFECT: adds a course to a student and a student to a course
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
    c.students = new ConsList<Student>(this, c.students);
  }

  // determines if the student and given student are in any of the same classes
  boolean classmates(Student c) {
    return this.courses.checkCourse(c);
  }

}

class ExamplesRegistrar {

  Student s1 = new Student("Liam", 01, new MtList<Course>());
  Student s2 = new Student("Jared", 02, new MtList<Course>());
  Student s3 = new Student("Sally", 03, new MtList<Course>());
  Student s4 = new Student("Jack", 04, new MtList<Course>());
  Student s5 = new Student("Dan", 05, new MtList<Course>());

  Instructor i1 = new Instructor("Razzaq", new MtList<Course>());
  Instructor i2 = new Instructor("Smith", new MtList<Course>());

  Course c1 = new Course("Fundies II", this.i1, new MtList<Student>());
  Course c2 = new Course("Discrete Structures", this.i1, new MtList<Student>());
  Course c3 = new Course("Physics I", this.i2, new MtList<Student>());
  Course c4 = new Course("Calculus", this.i2, new MtList<Student>());

  void initData() {

    this.s1 = new Student("Liam", 01, new MtList<Course>());
    this.s2 = new Student("Jared", 02, new MtList<Course>());
    this.s3 = new Student("Sally", 03, new MtList<Course>());
    this.s4 = new Student("Jack", 04, new MtList<Course>());
    this.s5 = new Student("Dan", 05, new MtList<Course>());

    this.i1 = new Instructor("Razzaq", new MtList<Course>());
    this.i2 = new Instructor("Smith", new MtList<Course>());

    this.i1.courses = new ConsList<Course>(this.c1, this.i1.courses);
    this.i1.courses = new ConsList<Course>(this.c2, this.i1.courses);
    this.i2.courses = new ConsList<Course>(this.c3, this.i2.courses);
    this.i2.courses = new ConsList<Course>(this.c4, this.i2.courses);

    this.c1 = new Course("Fundies II", this.i1, new MtList<Student>());
    this.c2 = new Course("Discrete Structures", this.i1, new MtList<Student>());
    this.c3 = new Course("Physics I", this.i2, new MtList<Student>());
    this.c4 = new Course("Calculus", this.i2, new MtList<Student>());
  }

  // tests enroll method
  void testEnroll(Tester t) {

    initData();
    
    t.checkExpect(this.c1.students, new MtList<Student>());
    t.checkExpect(this.s1.courses, new MtList<Course>());
    this.s1.enroll(this.c1);
    t.checkExpect(this.s1.courses, new ConsList<Course>(this.c1, new MtList<Course>()));
    t.checkExpect(this.s2.courses, new MtList<Course>());
    this.s2.enroll(this.c1);
    t.checkExpect(this.s2.courses, new ConsList<Course>(this.c1, new MtList<Course>()));
    t.checkExpect(this.c1.students, new ConsList<Student>(this.s2, 
                                      new ConsList<Student>(this.s1,
                                         new MtList<Student>())));
    t.checkExpect(this.c2.students, new MtList<Course>());
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

  // examples for ormap test
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings = new ConsList<String>("hello",
      new ConsList<String>("world",
          new ConsList<String>("fundies 2", mtStrings)));
  IList<Integer> mtInts = new MtList<Integer>();
  IList<Integer> ints = new ConsList<Integer>(1,
      new ConsList<Integer>(2,
          new ConsList<Integer>(3, mtInts)));
  
  // tests for ormap
  void testOrMap(Tester t) {
    t.checkExpect(this.mtStrings.ormap(s -> s.length() > 4), false);
    t.checkExpect(this.strings.ormap(s -> s.length() > 5), true);
    t.checkExpect(this.ints.ormap(i -> i == 34), false);
}
}
