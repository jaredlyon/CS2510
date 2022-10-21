
// Represents a credit line account
class Credit extends Account {

  int creditLine;  // Maximum amount accessible
  double interest; // The interest rate charged
    
  Credit(int accountNum, int balance, String name, int creditLine, double interest) {
    super(accountNum, balance, name);
    this.creditLine = creditLine;
    this.interest = interest;
  }
  
  //EFFECT: Withdraw the given amount
  //Return the new balance
  public int withdraw(int amount) {
    if (balance + amount > creditLine) {
      throw new RuntimeException("Insufficient credit!");
    } else {
      this.balance = this.balance + amount;
      return this.balance;
    }
  }
  
  //EFFECT: Deposit the given amount
  //Return the new balance
  public int deposit(int amount) {
    if (amount > balance) {
      throw new RuntimeException("Excessive deposit!");
    } else {
      this.balance = this.balance - amount;
      return this.balance;
    }
  }
  
  // checks if this is the right account
  public boolean compareAccNum(int accNum) {
    return this.accountNum == accNum;
  }
}