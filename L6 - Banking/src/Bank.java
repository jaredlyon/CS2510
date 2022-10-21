// Represents a Bank with list of accounts
class Bank {

  String name;
  ILoAccount accounts;

  Bank(String name) {
    this.name = name;

    // Each bank starts with no accounts
    this.accounts = new MtLoAccount();
  }

  // EFFECT: Add a new account to this bank
  void add(Account acc) {
    this.accounts = new ConsLoAccount(acc, this.accounts);
  }

  // EFFECT: deposit a given amount into a given account
  void deposit(int accNum, int amt) {
    this.accounts.deposit(accNum, amt);
  }

  // EFFECT: withdraw a given amount from a given account
  void withdraw(int accNum, int amt) {
    this.accounts.withdraw(accNum, amt);
  }
}