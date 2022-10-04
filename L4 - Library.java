interface IBook {
  int daysOverdue(int today);
  
  boolean isOverdue(int today);
  
  int computeFine(int today);
}

abstract class ABook implements IBook {
  String title;
  int dayTaken;
  
  ABook(String title, int dayTaken) {
    this.title = title;
    this.dayTaken = dayTaken;
  }
  
  public int daysOverdue(int today) {
    return today - dayTaken - 14;
  }
  
  public boolean isOverdue(int today) {
    return this.daysOverdue(today) < 0;
  }
  
  public int computeFine(int today) {
    return this.daysOverdue(today) * 10;
  }
}

class Book extends ABook {
  String author;
  
  Book(String title, int dayTaken, String author) {
    super(title, dayTaken);
    this.author = author;
  }
}

class RefBook extends ABook {
  
  RefBook(String title, int dayTaken) {
    super(title, dayTaken);
  }
  
  public int daysOverdue(int today) {
    return today - dayTaken - 2;
  }
}

class AudioBook extends ABook {
  String author;
  
  AudioBook(String title, int dayTaken, String author) {
    super(title, dayTaken);
    this.author = author;
  }
  
  public int computeFine(int today) {
    return this.daysOverdue(today) * 20;
  }
}