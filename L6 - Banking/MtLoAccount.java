
// Represents the empty List of Accounts
class MtLoAccount implements ILoAccount {
    
    MtLoAccount() {}
    
    // EFFECT: deposit a given amount into a given account
    public void deposit(int accNum, int amt) {
      throw new RuntimeException("Account not found!");
    }
    
    // EFFECT: withdraw amount from a given account
    public void withdraw(int accNum, int amt) {
      throw new RuntimeException("Account not found!");
    }
}