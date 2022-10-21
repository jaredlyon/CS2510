
// Represents a non-empty List of Accounts...
class ConsLoAccount implements ILoAccount {

  Account first;
  ILoAccount rest;

  ConsLoAccount(Account first, ILoAccount rest) {
    this.first = first;
    this.rest = rest;
  }
  
  // EFFECT: deposit a given amount into a given account
  public void deposit(int accNum, int amt) {
    if (this.first.compareAccNum(accNum)) {
      this.first.deposit(amt);
    }
    else {
      this.rest.deposit(accNum, amt);
    }
  }
  
  // EFFECT: withdraw a given amount from a given account
  public void withdraw(int accNum, int amt) {
    if (this.first.compareAccNum(accNum)) {
      this.first.withdraw(amt);
    }
    else {
      this.rest.withdraw(accNum, amt);
    }
  }
  
}