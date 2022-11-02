import tester.Tester;
import java.util.function.Predicate;

//represents a generic list interface
interface IList<T> {
  // ormap this list by the given predicate
  boolean ormap(Predicate<T> pred);

  // filter this list by the given predicate
  IList<T> filter(Predicate<T> pred);

  // get the length of this list
  int length();
}

//represents an empty list
class MtList<T> implements IList<T> {
  MtList() {

  }

  // ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return false;
  }

  // filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    return this;
  }

  // get the length of this list
  public int length() {
    return 0;
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

  // filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  // get the length of this list
  public int length() {
    return 1 + this.rest.length();
  }
}

// checks if this student matches a given one
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

//checks if this student is in a course
class CheckCourseList implements Predicate<Course> {
  Student s;

  CheckCourseList(Student s) {
    this.s = s;
  }

  public boolean test(Course c) {
    return c.students.ormap(new CheckStudent(s));
  }
}

// represents a course
class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;

  }

  // EFFECT: adds a student to this course
  public void addStudent(Student s) {
    this.students = new ConsList<Student>(s, this.students);
  }
}

// represents a course instructor
class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = new MtList<Course>();
  }

  public boolean dejavu(Student s) {
    //filters out courses the student isnt in
    IList<Course> temp = this.courses.filter(new CheckCourseList(s));
    // get the size of the above list
    int length = temp.length();
    // return true if list length is above one
    return length > 1;
  }
}

// represents a student
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
    c.addStudent(this);
  }

  // determines if the student and given student are in any of the same classes
  boolean classmates(Student c) {
    return this.courses.ormap(new CheckCourseList(c));
  }
}

class ExamplesRegistrar {
  ExamplesRegistrar() {

  }

  // student/instructor/course examples
  Student s1 = new Student("Liam", 01, new MtList<Course>());
  Student s2 = new Student("Jared", 02, new MtList<Course>());
  Student s3 = new Student("Sally", 03, new MtList<Course>());
  Student s4 = new Student("Jack", 04, new MtList<Course>());
  Student s5 = new Student("Dan", 05, new MtList<Course>());
  Student s6 = new Student("Jeff", 05, new MtList<Course>());

  Instructor i1 = new Instructor("Razzaq", new MtList<Course>());
  Instructor i2 = new Instructor("Smith", new MtList<Course>());

  Course c1 = new Course("Fundies II", this.i1, new MtList<Student>());
  Course c2 = new Course("Discrete Structures", this.i1, new MtList<Student>());
  Course c3 = new Course("Physics I", this.i2, new MtList<Student>());
  Course c4 = new Course("Calculus", this.i2, new MtList<Student>());
  Course c5 = new Course("Fundies I", this.i1, new ConsList<Student>(this.s6,
      new MtList<Student>()));

  // initialize test data
  void initData() {
    this.s1 = new Student("Liam", 01, new MtList<Course>());
    this.s2 = new Student("Jared", 02, new MtList<Course>());
    this.s3 = new Student("Sally", 03, new MtList<Course>());
    this.s4 = new Student("Jack", 04, new MtList<Course>());
    this.s5 = new Student("Dan", 05, new MtList<Course>());
    this.s6 = new Student("Jeff", 05, new MtList<Course>());

    this.i1 = new Instructor("Razzaq", new MtList<Course>());
    this.i2 = new Instructor("Smith", new MtList<Course>());

    this.i1.courses = new ConsList<Course>(this.c1, this.i1.courses);
    this.i1.courses = new ConsList<Course>(this.c2, this.i1.courses);
    this.i2.courses = new ConsList<Course>(this.c3, this.i2.courses);
    this.i2.courses = new ConsList<Course>(this.c4, this.i2.courses);

    this.c1 = new Course("Fundies II", this.i1, new ConsList<Student>(this.s1,
        new MtList<Student>()));
    this.c2 = new Course("Discrete Structures", this.i1, new MtList<Student>());
    this.c3 = new Course("Physics I", this.i2, new MtList<Student>());
    this.c4 = new Course("Calculus", this.i2, new MtList<Student>());
    this.c5 = new Course("Fundies I", this.i1, new ConsList<Student>(this.s6,
        new ConsList<Student>(this.s1,
            new MtList<Student>())));
  }

  // examples for ormap/filter tests
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings = new ConsList<String>("hello",
      new ConsList<String>("world",
          new ConsList<String>("fundies 2", mtStrings)));
  IList<Integer> mtInts = new MtList<Integer>();
  IList<Integer> ints = new ConsList<Integer>(1,
      new ConsList<Integer>(2,
          new ConsList<Integer>(3, mtInts)));

  // test ormap
  void testOrMap(Tester t) {
    t.checkExpect(this.mtStrings.ormap(s -> s.length() > 4), false);
    t.checkExpect(this.strings.ormap(s -> s.length() > 5), true);
    t.checkExpect(this.ints.ormap(i -> i == 34), false);
  }

  // test filter
  void testFilter(Tester t) {
    t.checkExpect(this.mtStrings.filter(s -> s.length() > 4), this.mtStrings);
    t.checkExpect(this.strings.filter(s -> s.length() > 5),
        new ConsList<String>("fundies 2", mtStrings));
    t.checkExpect(this.ints.filter(i -> i == 3), new ConsList<Integer>(3, mtInts));
  }

  // test length
  void testLength(Tester t) {
    t.checkExpect(this.mtStrings.length(), 0);
    t.checkExpect(this.strings.length(), 3);
    t.checkExpect(this.ints.length(), 3);
  }

  // test CheckStudent predicate
  void testCheckStudent(Tester t) {
    t.checkExpect(new CheckStudent(this.s1).test(this.s2), false);
    t.checkExpect(new CheckStudent(this.s5).test(this.s6), true);
  }

  // test CheckCourseList predicate
  void testCheckCourseList(Tester t) { 
    t.checkExpect(new CheckCourseList(this.s6).test(this.c5), true);
    t.checkExpect(new CheckCourseList(this.s1).test(this.c5), true);
    t.checkExpect(new CheckCourseList(this.s1).test(this.c3), false);
  }

  // test addStudent
  void testAddStudent(Tester t) {
    this.initData();

    t.checkExpect(this.c4.students, new MtList<Student>());
    this.c4.addStudent(this.s1);
    t.checkExpect(this.c4.students, new ConsList<Student>(this.s1,
        new MtList<Student>()));
    this.c4.addStudent(this.s2);
    t.checkExpect(this.c4.students, new ConsList<Student>(this.s2,
        new ConsList<Student>(this.s1,
            new MtList<Student>())));
  }

  // test dejavu
  void testDejavu(Tester t) {
    this.initData();
    this.i1.courses = new ConsList<Course>(this.c1, new ConsList<Course>(this.c5,
        new MtList<Course>()));

    t.checkExpect(this.i1.dejavu(this.s1), true);
    t.checkExpect(this.i1.dejavu(this.s6), false);
    t.checkExpect(this.i2.dejavu(this.s1), false);
  }

  // tests for compareId method
  void testCompareId(Tester t) {
    initData();

    t.checkExpect(this.s1.compareId(this.s2), false);
    t.checkExpect(this.s2.compareId(this.s1), false);
    t.checkExpect(this.s1.compareId(this.s1), true);
    t.checkExpect(this.s2.compareId(this.s2), true);
  }

  // tests enroll method
  void testEnroll(Tester t) {
    initData();

    t.checkExpect(this.c1.students, new ConsList<Student>(this.s1,
        new MtList<Student>()));
    t.checkExpect(this.s1.courses, new MtList<Course>());

    this.s1.enroll(this.c1);

    t.checkExpect(this.s1.courses, new ConsList<Course>(this.c1,
        new MtList<Course>()));
    t.checkExpect(this.s2.courses, new MtList<Course>());
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
}
