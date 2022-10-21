
// Represents a List of Accounts
interface ILoAccount {
  // EFFECT: deposit a given amount into a given account
  void deposit(int accNum, int amt);
  
  // EFFECT: withdraw a given amount from a given account
  void withdraw(int accNum, int amt);
}