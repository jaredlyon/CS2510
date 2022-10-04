import tester.Tester;

class Time {
  int hour;
  int minute;
  int second;
  boolean isAM;

  // constructor for data integrity
  Time(int hour, int minute, int second) {
    Utils u = new Utils();
    this.hour = u.checkRange(hour,  0, 23, "Hour must be between 0 and 23, inclusive");
    this.minute = u.checkRange(minute,  0, 59, "Minute must be between 0 and 59, inclusive");
    this.second = u.checkRange(second,  0, 59, "Second must be between 0 and 59, inclusive");
  }

  // convenience constructor for minute
  Time(int hour, int minute) {
    this(hour, minute, 0);
  }

  // am time constructor
  Time(int hour, int minute, boolean isAM) {
    Utils u = new Utils();

    if (isAM) {
      this.hour = u.checkRange(hour,  0, 12, "Hour must be between 0 and 12, inclusive");
    } else {
      this.hour = u.checkRange(hour,  0, 12, "Hour must be between 0 and 12, inclusive") + 12;
    }

    this.minute = u.checkRange(minute,  0, 59, "Minute must be between 0 and 59, inclusive");
    this.second = 0;
  }

  public boolean sameTime(Time other) {
    if (this.isAM && other.isAM) {
      return other.hour == this.hour
          && other.minute == this.minute
          && other.second == this.second;
    } else if (this.isAM && !other.isAM) {
      return (other.hour + 12) == this.hour
          && other.minute == this.minute
          && other.second == this.second;
    } else if (!this.isAM && other.isAM) {
      return other.hour == (this.hour + 12)
          && other.minute == this.minute
          && other.second == this.second;
    } else if (!this.isAM && !other.isAM) {
      return other.hour == this.hour
          && other.minute == this.minute
          && other.second == this.second;
    } else {
      return false;
    }
  }
}

class Utils {
  //checks if a given number is in the given range
  int checkRange(int n, int low, int high, String message) {
    if (n >= low && n <= high) {
      return n;
    } else {
      throw new IllegalArgumentException(message);
    }
  }
}

class ExamplesTime {

  Time midnight = new Time(12, 0, true);
  Time twoAm = new Time(2, 15, true);
  Time sixAm = new Time(6, 30,true);
  Time tenAm = new Time(10, 45, true);
  Time threePm = new Time(3, 0, false);
  Time fivePm = new Time(5, 15, false);
  Time sevenPm = new Time(7, 30, false);
  Time elevenPm = new Time(11, 45, false);

  // testing the sameTime method
  boolean testSameTime(Tester t) {
    return t.checkExpect(this.twoAm.sameTime(elevenPm), false)
        && t.checkExpect(this.twoAm.sameTime(twoAm), true)
        && t.checkExpect(this.twoAm.sameTime(tenAm), false)
        && t.checkExpect(this.threePm.sameTime(elevenPm), false)
        && t.checkExpect(this.threePm.sameTime(threePm), true)
        && t.checkExpect(this.threePm.sameTime(twoAm), false);
  }

  // test first time constructor
  boolean testFirstTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Hour must be between 0 and 23, inclusive"),
        "Time", 40, 4, 30)
        && t.checkConstructorException(new IllegalArgumentException("Minute must be between 0 and 59, inclusive"),
            "Time", 4, 100, 30)
        && t.checkConstructorException(new IllegalArgumentException("Second must be between 0 and 59, inclusive"),
            "Time", 4, 40, 300);
  }

  // test second time constructor
  boolean testSecondTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Hour must be between 0 and 23, inclusive"),
        "Time", 40, 4)
        && t.checkConstructorException(new IllegalArgumentException("Minute must be between 0 and 59, inclusive"),
            "Time", 4, 100);
  }

  // test third time constructor
  boolean testThirdTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Hour must be between 0 and 12, inclusive"),
        "Time", 40, 4, true)
        && t.checkConstructorException(new IllegalArgumentException("Minute must be between 0 and 59, inclusive"),
            "Time", 4, 100, true)
        && t.checkConstructorException(new IllegalArgumentException("Hour must be between 0 and 12, inclusive"),
            "Time", 40, 4, false)
        && t.checkConstructorException(new IllegalArgumentException("Minute must be between 0 and 59, inclusive"),
            "Time", 4, 100, false);
  }
}