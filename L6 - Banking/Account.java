
// Represents a bank account
abstract class Account {

  int accountNum;  // Must be unique
  int balance;     // Must remain above zero (others Accts have more restrictions)
  String name;     // Name on the account

  Account(int accountNum, int balance, String name){
    this.accountNum = accountNum;
    this.balance = balance;
    this.name = name;
  }

  //EFFECT: Withdraw the given amount
  //Return the new balance
  abstract int withdraw(int amount);
  
  //EFFECT: Deposit the given amount
  //Return the new balance
  abstract int deposit(int amount);
  
  // checks if this is the right account
  abstract boolean compareAccNum(int accNum);
}
