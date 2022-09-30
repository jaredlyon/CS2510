import tester.Tester;

interface ILoInt {
  int longest();

  int longestAcc(int sol, int solLength, int current, int currentLength);


}

class MtLoInt implements ILoInt {
  MtLoInt() {}

  public int longest() {
    return 0;
  }

  public int longestAcc(int sol, int solLength, int current, int currentLength) {
    return sol;
  }
}

class ConsLoInt implements ILoInt {
  int first;
  ILoInt rest;

  ConsLoInt(int first, ILoInt rest) {
    this.first = first;
    this.rest = rest;
  }

  public int longest() {
    return this.longestAcc(this.first, 1, this.first, 1);
  }

  public int longestAcc(int sol, int solLength, int current, int currentLength) {
    if (currentLength > solLength) {
      return this.rest.longestAcc(current, currentLength, this.first, 1);
    } else if (current == this.first) {
      return this.rest.longestAcc(sol, solLength, current, currentLength + 1);
    } else {
      return this.rest.longestAcc(sol, solLength, this.first, 1);
    }
  }
}

class Task {
  int id;
  ILoTask tasks;

  Task(int id, ILoTask tasks) {
    this.id = id;
    this.tasks = tasks;
  }

  public ILoInt totalTasks() {
    return new ConsLoInt(this.id, this.tasks.totalTasks());
  }

  public int getID() {
    return this.id;
  }
}

interface ILoTask {
  ILoInt totalTasks();
}

class MtLoTask implements ILoTask {
  MtLoTask() {}

  public ILoInt totalTasks() {
    return new MtLoInt();
  }

  public int getID() {
    return 0;
  }
}

class ConsLoTask implements ILoTask {
  Task first;
  ILoTask rest;

  ConsLoTask(Task first, ILoTask rest) {
    this.first = first;
    this.rest = rest;
  }


  public ILoInt totalTasks() {
    return new ConsLoInt(this.first.getID(), this.rest.totalTasks());
  }
}

class ExamplesInts {
  ExamplesInts() {}

  ILoInt mt = new MtLoInt();

  ILoInt list1 = new ConsLoInt(1,
      new ConsLoInt(1,
          new ConsLoInt(5,
              new ConsLoInt(5,
                  new ConsLoInt(5,
                      new ConsLoInt(4,
                          new ConsLoInt(3,
                              new ConsLoInt(4,
                                  new ConsLoInt(4,
                                      new ConsLoInt(4, mt))))))))));

  ILoInt list2 = new ConsLoInt(2,
      new ConsLoInt(2,
          new ConsLoInt(6,
              new ConsLoInt(5,
                  new ConsLoInt(6,
                      new ConsLoInt(7,
                          new ConsLoInt(7,
                              new ConsLoInt(7,
                                  new ConsLoInt(7,
                                      new ConsLoInt(4, mt))))))))));

  ILoTask mtTask = new MtLoTask();
  Task task1 = new Task(1, mtTask);
  Task task2 = new Task(2, mtTask);
  Task task3 = new Task(3, mtTask);

  ILoTask taskList1 = new ConsLoTask(task1,
      new ConsLoTask(task2,
          new ConsLoTask(task3, mtTask)));

  Task task4 = new Task(4, taskList1);

  ILoInt taskListID1 = new ConsLoInt(1,
      new ConsLoInt(2,
          new ConsLoInt(3, mt)));
  
  ILoInt taskListID2 = new ConsLoInt(4,
      new ConsLoInt(1,
          new ConsLoInt(2, 
              new ConsLoInt(3, mt))));

  boolean testLongest(Tester t) {
    return t.checkExpect(list1.longest(), 5)
        && t.checkExpect(list2.longest(), 7);
  }

  boolean testTotalTasks(Tester t) {
    return t.checkExpect(taskList1.totalTasks(), taskListID1)
        && t.checkExpect(task4.totalTasks(), taskListID2);
  }
}