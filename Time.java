import tester.Tester;

// represents a time of day
class Time {
  int hour;
  int minute;
  int second;
  boolean isAM;

  // constructor for data integrity
  Time(int hour, int minute, int second) {
    Utils u = new Utils();
    this.hour = u.checkRange(hour,  0, 23, "Invalid hour: " + hour);
    this.minute = u.checkRange(minute,  0, 59, "Invalid minute: " + minute);
    this.second = u.checkRange(second,  0, 59, "Invalid second: " + second);
  }

  // convenience constructor for minute
  Time(int hour, int minute) {
    this(hour, minute, 0);
  }

  // time constructor w/ am & pm
  Time(int hour, int minute, boolean isAM) {
    Utils u = new Utils();

    if (hour == 12) {
      if (isAM) {
        this.hour = 0;
      } else if (!isAM) {
        this.hour = 12;
      }
    } else {
      if (isAM) {
        this.hour = u.checkRange(hour,  1, 12, "Invalid hour: " + hour);
      } else if (!isAM) {
        this.hour = u.checkRange(hour,  1, 12, "Invalid hour: " + hour) + 12;
      }
    }

    this.minute = u.checkRange(minute,  0, 59, "Invalid minute: " + minute);
    this.second = 0;
    this.isAM = isAM;
  }

  /*
   * fields:
   *  this.hour ... int
   *  this.minute ... int
   *  this.second ... int
   *  this.isAM ... boolean
   * methods:
   *  this.sameTime(Time) ... boolean
   * methods for fields:
   *  none
   */

  public boolean sameTime(Time other) {
    return this.hour == other.hour
        && this.minute == other.minute
        && this.second == other.second;
  }
}

// contains utility classes
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

  // examples that use isAM boolean
  Time newDay = new Time(12, 0, true);
  Time midDay = new Time(12, 0, false);
  Time twoAm = new Time(2, 15, true);
  Time sixAm = new Time(6, 30,true);
  Time tenAm = new Time(10, 45, true);
  Time threePm = new Time(3, 0, false);
  Time fivePm = new Time(5, 15, false);
  Time sevenPm = new Time(7, 30, false);
  Time elevenPm = new Time(11, 45, false);

  // examples that use normal/convenience constructors
  Time newDaySec = new Time(0, 0);
  Time midDaySec = new Time(12, 0);
  Time oneAmSec = new Time(1, 15, 10);
  Time fiveAmSec = new Time(5, 30, 20);
  Time nineAmSec = new Time(9, 45, 30);
  Time twoPmSec = new Time(14, 0, 40);
  Time fourPmSec = new Time(16, 15, 50);
  Time sixPmSec = new Time(18, 30, 59);
  Time elevenPmSec = new Time(23, 45);

  // testing the sameTime method
  boolean testSameTime(Tester t) {
    return t.checkExpect(this.twoAm.sameTime(elevenPm), false)
        && t.checkExpect(this.twoAm.sameTime(twoAm), true)
        && t.checkExpect(this.twoAm.sameTime(tenAm), false)
        && t.checkExpect(this.threePm.sameTime(elevenPm), false)
        && t.checkExpect(this.threePm.sameTime(threePm), true)
        && t.checkExpect(this.threePm.sameTime(twoAm), false)
        && t.checkExpect(this.oneAmSec.sameTime(elevenPmSec), false)
        && t.checkExpect(this.oneAmSec.sameTime(oneAmSec), true)
        && t.checkExpect(this.oneAmSec.sameTime(nineAmSec), false)
        && t.checkExpect(this.sixPmSec.sameTime(elevenPmSec), false)
        && t.checkExpect(this.sixPmSec.sameTime(sixPmSec), true)
        && t.checkExpect(this.sixPmSec.sameTime(oneAmSec), false)
        && t.checkExpect(this.elevenPm.sameTime(elevenPmSec), true)
        && t.checkExpect(this.midDay.sameTime(midDaySec), true)
        && t.checkExpect(this.newDay.sameTime(newDaySec), true)
        && t.checkExpect(this.newDaySec.sameTime(elevenPm), false)
        && t.checkExpect(this.newDay.sameTime(elevenPmSec), false);
  }

  // test first time constructor
  boolean testFirstTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Invalid hour: 40"),
        "Time", 40, 4, 30)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 100"),
            "Time", 4, 100, 30)
        && t.checkConstructorException(new IllegalArgumentException("Invalid second: 300"),
            "Time", 4, 40, 300);
  }

  // test second time constructor
  boolean testSecondTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Invalid hour: 40"),
        "Time", 40, 4)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 100"),
            "Time", 4, 100);
  }

  // test third time constructor
  boolean testThirdTimeConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Invalid hour: 40"),
        "Time", 40, 4, true)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 100"),
            "Time", 4, 100, true)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 40"),
            "Time", 40, 4, false)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 100"),
            "Time", 4, 100, false);
  }
}