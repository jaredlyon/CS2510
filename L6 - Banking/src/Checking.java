
// Represents a checking account
class Checking extends Account {

  int minimum; // The minimum account balance allowed

  Checking(int accountNum, int balance, String name, int minimum) {
    super(accountNum, balance, name);
    this.minimum = minimum;
  }
  
  //EFFECT: Withdraw the given amount
  //Return the new balance
  public int withdraw(int amount) {
    if (balance - amount < minimum) {
      throw new RuntimeException("Insufficient funds!");
    } else {
      this.balance = this.balance - amount;
      return this.balance;
    }
  }
  
  //EFFECT: Deposit the given amount
  //Return the new balance
  public int deposit(int amount) {
    this.balance = this.balance + amount;
    return this.balance;
  }
  
  // checks if this is the right account
  public boolean compareAccNum(int accNum) {
    return this.accountNum == accNum;
  }
}
