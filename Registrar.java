import tester.Tester;
import java.util.function.Predicate;
import java.util.function.*;

//represents a generic list interface
interface IList<T> {

  // ormap this list by the given predicate
  boolean ormap(Predicate<T> pred);

}

//represents an empty list
class MtList<T> implements IList<T> {
  MtList() {

  }

  // ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return false;
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

}

class CheckStudent implements Predicate<Student> {
  Student c;
  
  CheckStudent(Student c) {
    this.c = c;
  }
  
  // checks if two students are the same
  public boolean test(Student stu) {
    return c.compareId(stu);
  }
  
}

//class ReplaceCourse implements Predicate<Course> {
//  Course old;
//  
//  ReplaceCourse(Course old) {
//    this.old = old;
//  }
//  
//  
//  public boolean test(Course c) {
//    if (old.isSameCourse(c)) {
//      old = c;
//    }
//    return new ConsList<Course>(old, new MtList<Course>());
//  }
//}

class CheckCourseList implements Predicate<Course> {
  Student s;
  
  CheckCourseList(Student s) {
    this.s = s;
  }
  
  public boolean test(Course c) {
    return c.students.ormap(new CheckStudent(s));
  }
  
}

class CheckDejavu implements Predicate<Course> {
  Student s;
  int acc;
  
  CheckDejavu(Student s, int acc) {
    this.s = s;
    this.acc = acc;
  }
  
  public boolean test(Course c) {
    if (c.students.ormap(new CheckStudent(s))) {
      this.acc = this.acc + 1;
    }
    return this.acc >= 2;
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
  
  // checks if two courses are the same
  boolean isSameCourse(Course c) {
    return c.name == this.name;
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
    return this.courses.ormap(new CheckDejavu(c, 0));
  }
  
  void find(Course c) {
    this.courses.ormap(new ReplaceCourse(c));
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

  // checks if two students ids are the same
  boolean compareId(Student s) {
    return this.id == s.id;
  }
  
  // EFFECT: adds a course to a student and a student to a course
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
    c.students = new ConsList<Student>(this, c.students);
//    c.prof.find(c);
  }
  
  // determines if the student and given student are in any of the same classes
  boolean classmates(Student c) {
    // return this.courses.checkCourse(c);
    return this.courses.ormap(new CheckCourseList(c));
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
  
  // tests for compareId method
  
  void testCompareId(Tester t) {
    
    initData();
    
    t.checkExpect(this.s1.compareId(this.s2), false);
    t.checkExpect(this.s2.compareId(this.s1), false);
    t.checkExpect(this.s1.compareId(this.s1), true);
    t.checkExpect(this.s2.compareId(this.s2), true);
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
