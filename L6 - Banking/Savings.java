
// Represents a savings account
class Savings extends Account {

  double interest; // The interest rate

  Savings(int accountNum, int balance, String name, double interest) {
    super(accountNum, balance, name);
    this.interest = interest;
  }
  
  //EFFECT: Withdraw the given amount
  //Return the new balance
  public int withdraw(int amount) {
    if (balance - amount < 0) {
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
