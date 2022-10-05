import tester.Tester;

interface IVehicle {
  
  /* TEMPLATE
   * 
   * fields: none
   * 
   * methods:
   * this.totalRevenue() -- double
   * this.fuelCost() -- double
   * this.perPassengerProfit -- double
   * this.format() -- String
   * this.sameVehicle(IVehicle) -- boolean
   * this.isAirplane() -- boolean
   * this.isTrain() -- boolean
   * this.isBus() -- boolean
   * this.sameAirplane(Airplane) -- boolean
   * this.sameTrain(Train) -- boolean
   * this.sameBus(Bus) -- boolean
   *
   * methods for fields: none
   */
  
  // computes the total revenue of this Vehicle
  double totalRevenue();

  // computes the cost of fully fueling this Vehicle
  double fuelCost();

  // computes the per-passenger profit of this Vehicle
  double perPassengerProfit();

  // produce a String that shows the name and passenger capacity of this Vehicle
  String format();

  // is this IVehicle the same as that one?
  boolean sameVehicle(IVehicle that);

  // is this an airplane?
  boolean isAirplane();

  // is this a train?
  boolean isTrain();

  // is this a bus?
  boolean isBus();

  // is this airplane the same as the given airplane?
  boolean sameAirplane(Airplane a);

  // is this train the same as the given airplane?
  boolean sameTrain(Train t);

  // is this bus the same as the given bus?
  boolean sameBus(Bus b);
}

abstract class AVehicle implements IVehicle {
  String name;
  int passengerCapacity;
  double fare;
  int fuelCapacity;

  AVehicle(String name, int passengerCapacity, double fare, int fuelCapacity) {
    this.name = name;
    this.passengerCapacity = passengerCapacity;
    this.fare = fare;
    this.fuelCapacity = fuelCapacity;
  }

  /* TEMPLATE
   * 
   * fields:
   * this.name -- String
   * this.passengerCapacity -- int
   * this.fare -- double
   * this.fuelCapacity -- int
   * 
   * methods:
   * this.totalRevenue() -- double
   * this.fuelCost() -- double
   * this.perPassengerProfit -- double
   * this.format() -- String
   * this.isAirplane() -- boolean
   * this.isTrain() -- boolean
   * this.isBus() -- boolean
   * this.sameAirplane(Airplane) -- boolean
   * this.sameTrain(Train) -- boolean
   * this.sameBus(Bus) -- boolean
   *
   * methods for fields:
   * none
   */
  
  // computes the total revenue of operating this Airplane
  public double totalRevenue() {
    return this.passengerCapacity * this.fare;
  }

  // computes the cost of fully fueling this Bus
  public double fuelCost() {
    return this.fuelCapacity * 2.55;
  }

  // computes the per-passenger profit of this Train
  public double perPassengerProfit() {
    return ((this.totalRevenue() - this.fuelCost()) / this.passengerCapacity);
  }

  // produce a String that shows the name and passenger capacity of this Train
  public String format() {
    return this.name + ", " + this.passengerCapacity;
  }
  
  // is this an airplane?
  public boolean isAirplane() {
    return false;
  }

  // is this a train?
  public boolean isTrain() {
    return false;
  }

  // is this a bus?
  public boolean isBus() {
    return false;
  }
  
  // is this airplane the same as the given airplane?
  public boolean sameAirplane(Airplane a) {
    return false;
  }

  // is this train the same as the given airplane?
  public boolean sameTrain(Train t) {
    return false;
  }

  // is this bus the same as the given bus?
  public boolean sameBus(Bus b) {
    return false;
  }

}

class Airplane extends AVehicle {
  String code; // ICAO type designator
  boolean isWideBody; // twin-aisle aircraft

  Airplane(String name, int passengerCapacity, double fare, int fuelCapacity, String code,
      boolean isWideBody) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.code = code;
    this.isWideBody = isWideBody;
  }

  /* TEMPLATE
   * 
   * fields:
   * this.name -- String
   * this.passengerCapacity -- int
   * this.fare -- double
   * this.fuelCapacity -- int
   * this.code -- String
   * this.isWideBody -- boolean
   * 
   * methods:
   * this.totalRevenue() -- double
   * this.fuelCost() -- double
   * this.perPassengerProfit -- double
   * this.format() -- String
   * this.isAirplane() -- boolean
   * this.isTrain() -- boolean
   * this.isBus() -- boolean
   * this.sameAirplane(Airplane) -- boolean
   * this.sameTrain(Train) -- boolean
   * this.sameBus(Bus) -- boolean
   *
   * methods for fields:
   * none
   */
  
  // computes the cost of fully fueling this Airplane
  public double fuelCost() {
    return this.fuelCapacity * 1.94;
  }

  // is this IVehicle the same as that?
  public boolean sameVehicle(IVehicle that) {
    return that.sameAirplane(this);
  }
  
  // is this airplane the same as the given one?
  public boolean sameAirplane(Airplane a) {
    return this.name.equals(a.name) 
        && this.passengerCapacity == a.passengerCapacity
        && this.fare == a.fare
        && this.fuelCapacity == a.fuelCapacity
        && this.code.equals(a.code)
        && this.isWideBody == a.isWideBody;
  }

  //is this a airplane?
  public boolean isAirplane() {
    return true;
  }
}

class Train extends AVehicle {
  int numberOfCars; // cars per trainset
  int gauge; // track gauge in millimeters

  Train(String name, int passengerCapacity, double fare, int fuelCapacity, int numberOfCars,
      int gauge) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.numberOfCars = numberOfCars;
    this.gauge = gauge;
  }

  /* TEMPLATE
   * 
   * fields:
   * this.name -- String
   * this.passengerCapacity -- int
   * this.fare -- double
   * this.fuelCapacity -- int
   * this.numberOfCars -- int
   * this.gauge -- int
   * 
   * methods:
   * this.totalRevenue() -- double
   * this.fuelCost() -- double
   * this.perPassengerProfit -- double
   * this.format() -- String
   * this.isAirplane() -- boolean
   * this.isTrain() -- boolean
   * this.isBus() -- boolean
   * this.sameAirplane(Airplane) -- boolean
   * this.sameTrain(Train) -- boolean
   * this.sameBus(Bus) -- boolean
   *
   * methods for fields:
   * none
   */
  
  // is this IVehicle the same as that?
  public boolean sameVehicle(IVehicle that) {
    return that.sameTrain(this);
  }
  
  // is this train the same as the given one?
  public boolean sameTrain(Train t) {
    return this.name.equals(t.name) 
        && this.passengerCapacity == t.passengerCapacity
        && this.fare == t.fare 
        && this.fuelCapacity == t.fuelCapacity
        && this.numberOfCars == t.numberOfCars 
        && this.gauge == t.gauge;
  }

  // is this a train?
  public boolean isTrain() {
    return true;
  }
}

class Bus extends AVehicle {
  int length; // length in feet

  Bus(String name, int passengerCapacity, double fare, int fuelCapacity, int length) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.length = length;
  }
  
  /* TEMPLATE
   * 
   * fields:
   * this.name -- String
   * this.passengerCapacity -- int
   * this.fare -- double
   * this.fuelCapacity -- int
   * this.length -- int
   * 
   * methods:
   * this.totalRevenue() -- double
   * this.fuelCost() -- double
   * this.perPassengerProfit -- double
   * this.format() -- String
   * this.isAirplane() -- boolean
   * this.isTrain() -- boolean
   * this.isBus() -- boolean
   * this.sameAirplane(Airplane) -- boolean
   * this.sameTrain(Train) -- boolean
   * this.sameBus(Bus) -- boolean
   *
   * methods for fields:
   * none
   */

  // is this IVehicle the same as that?
  public boolean sameVehicle(IVehicle that) {
    return that.sameBus(this);
  }
  
  // is this bus the same as the given one?
  public boolean sameBus(Bus b) {
    return this.name.equals(b.name) 
        && this.passengerCapacity == b.passengerCapacity
        && this.fare == b.fare
        && this.fuelCapacity == b.fuelCapacity
        && this.length == b.length;
  }

  // is this a bus?
  public boolean isBus() {
    return true;
  }
}

class ExamplesVehicle {
  
  IVehicle dreamliner = new Airplane("Boeing 787", 242, 835.0, 33340, "B788", false);
  IVehicle united = new Airplane("Airbus A380", 196, 760.0, 30000, "D429", true);
  IVehicle commuterRail = new Train("MPI HSP46", 500, 11.50, 2000, 6, 1435);
  IVehicle metroNorth = new Train("M8", 1000, 22.5, 4200, 12, 1738);
  IVehicle silverLine = new Bus("Neoplan AN460LF", 77, 1.70, 100, 60);
  IVehicle greyhound = new Bus("D4505", 64, 5.70, 750, 90);

  // testing total revenue method
  boolean testTotalRevenue(Tester t) {
    return t.checkInexact(this.dreamliner.totalRevenue(), 242 * 835.0, .0001)
        && t.checkInexact(this.united.totalRevenue(), 196 * 760.0, .0001)
        && t.checkInexact(this.commuterRail.totalRevenue(), 500 * 11.5, .0001)
        && t.checkInexact(this.metroNorth.totalRevenue(), 1000 * 22.5, .0001)
        && t.checkInexact(this.silverLine.totalRevenue(), 77 * 1.7, 0.001)
        && t.checkInexact(this.greyhound.totalRevenue(), 64 * 5.70, .0001);
  }

  // testing fuel cost method
  boolean testFuelCost(Tester t) {
    return t.checkInexact(this.dreamliner.fuelCost(), 33340 * 1.94, 0.0001)
        && t.checkInexact(this.united.fuelCost(), 30000 * 1.94, 0.0001)
        && t.checkInexact(this.commuterRail.fuelCost(), 2000 * 2.55, 0.0001)
        && t.checkInexact(this.metroNorth.fuelCost(), 4200 * 2.55, 0.0001)
        && t.checkInexact(this.silverLine.fuelCost(), 100 * 2.55, 0.0001)
        && t.checkInexact(this.greyhound.fuelCost(), 750 * 2.55, 0.0001);
  }

  // testing the per passenger profit
  boolean testPerPassengerProfit(Tester t) {
    return t.checkInexact(this.dreamliner.perPassengerProfit(),
        (this.dreamliner.totalRevenue() - this.dreamliner.fuelCost()) / 242, 0.0001)
        && t.checkInexact(this.united.perPassengerProfit(),
            (this.united.totalRevenue() - this.united.fuelCost()) / 196, 0.0001)
        && t.checkInexact(this.commuterRail.perPassengerProfit(),
            (this.commuterRail.totalRevenue() - this.commuterRail.fuelCost()) / 500, 0.0001)
        && t.checkInexact(this.metroNorth.perPassengerProfit(),
            (this.metroNorth.totalRevenue() - this.metroNorth.fuelCost()) / 1000, 0.0001)
        && t.checkInexact(this.silverLine.perPassengerProfit(),
            (this.silverLine.totalRevenue() - this.silverLine.fuelCost()) / 77, 0.0001)
        && t.checkInexact(this.greyhound.perPassengerProfit(),
            (this.greyhound.totalRevenue() - this.greyhound.fuelCost()) / 64, 0.0001);
  }

  // testing the format method
  boolean testFormat(Tester t) {
    return t.checkExpect(this.dreamliner.format(), "Boeing 787, 242")
        && t.checkExpect(this.united.format(), "Airbus A380, 196")
        && t.checkExpect(this.commuterRail.format(), "MPI HSP46, 500")
        && t.checkExpect(this.metroNorth.format(), "M8, 1000")
        && t.checkExpect(this.silverLine.format(), "Neoplan AN460LF, 77")
        && t.checkExpect(this.greyhound.format(), "D4505, 64");
  }

  // testing the same vehicle method
  boolean testSameVehicle(Tester t) {
    return t.checkExpect(this.dreamliner.sameVehicle(this.dreamliner), true)
        && t.checkExpect(this.dreamliner.sameVehicle(this.united), false)
        && t.checkExpect(this.dreamliner.sameVehicle(this.commuterRail), false)
        && t.checkExpect(this.dreamliner.sameVehicle(this.silverLine), false)
        && t.checkExpect(this.commuterRail.sameVehicle(this.commuterRail), true)
        && t.checkExpect(this.commuterRail.sameVehicle(this.metroNorth), false)
        && t.checkExpect(this.commuterRail.sameVehicle(this.silverLine), false)
        && t.checkExpect(this.commuterRail.sameVehicle(this.dreamliner), false)
        && t.checkExpect(this.silverLine.sameVehicle(this.silverLine), true)
        && t.checkExpect(this.silverLine.sameVehicle(this.greyhound), false)
        && t.checkExpect(this.silverLine.sameVehicle(this.united), false)
        && t.checkExpect(this.silverLine.sameVehicle(this.commuterRail), false)
        && t.checkExpect(this.united.sameVehicle(this.united), true)
        && t.checkExpect(this.metroNorth.sameVehicle(this.metroNorth), true)
        && t.checkExpect(this.greyhound.sameVehicle(this.greyhound), true);
  }
  
  // testing the same airplane method
  boolean testSameAirplane(Tester t) {
    return t.checkExpect(this.dreamliner.sameAirplane((Airplane)this.dreamliner), true)
        && t.checkExpect(this.dreamliner.sameAirplane((Airplane)this.united), false)
        && t.checkExpect(this.united.sameAirplane((Airplane)this.united), true);
  }
  
  // testing the same train method
  boolean testSameTrain(Tester t) {
    return t.checkExpect(this.commuterRail.sameTrain((Train)this.commuterRail), true)
    && t.checkExpect(this.commuterRail.sameTrain((Train)this.metroNorth), false)
    && t.checkExpect(this.metroNorth.sameTrain((Train)this.metroNorth), true);
  }
  
  // testing the same bus method
  boolean testSameBus(Tester t) {
    return t.checkExpect(this.silverLine.sameBus((Bus)this.silverLine), true)
    && t.checkExpect(this.silverLine.sameBus((Bus)this.greyhound), false)
    && t.checkExpect(this.greyhound.sameBus((Bus)this.greyhound), true);
  }
  
  // testing the is airplane method
  boolean testIsAirplane(Tester t) {
    return t.checkExpect(this.united.isAirplane(), true)
        && t.checkExpect(this.dreamliner.isAirplane(), true)
        && t.checkExpect(this.commuterRail.isAirplane(), false)
        && t.checkExpect(this.metroNorth.isAirplane(), false)
        && t.checkExpect(this.silverLine.isAirplane(), false)
        && t.checkExpect(this.greyhound.isAirplane(), false);
  }
  
  // testing the is train method
  boolean testIsTrain(Tester t) {
    return t.checkExpect(this.united.isTrain(), false)
        && t.checkExpect(this.dreamliner.isTrain(), false)
        && t.checkExpect(this.commuterRail.isTrain(), true)
        && t.checkExpect(this.metroNorth.isTrain(), true)
        && t.checkExpect(this.silverLine.isTrain(), false)
        && t.checkExpect(this.greyhound.isTrain(), false);
  }
  
  // testing the is bus method
  boolean testIsBus(Tester t) {
    return t.checkExpect(this.united.isBus(), false)
        && t.checkExpect(this.dreamliner.isBus(), false)
        && t.checkExpect(this.commuterRail.isBus(), false)
        && t.checkExpect(this.metroNorth.isBus(), false)
        && t.checkExpect(this.silverLine.isBus(), true)
        && t.checkExpect(this.greyhound.isBus(), true);
  }
}