import tester.*;

// Bank Account Examples and Tests
class Examples {
  Account acc1;
  Account acc2;
  Account acc3;
  Account acc4;
  Account acc5;
  Account acc6;
  Account acc7;
  Account acc8;
  Account acc9;

  ILoAccount accList;

  Bank bank;

  // Initializes accounts to use for testing with effects.
  // We place inside reset() so we can "reuse" the accounts
  void reset() {
    acc1 = new Checking(1, 2500, "Jared", 2000);
    acc2 = new Checking(2, 3000, "Liam", 2000);
    acc3 = new Checking(3, 4000, "Madeleine", 2000);
    acc4 = new Savings(4, 2500, "Jared", 2.0);
    acc5 = new Savings(5, 3000, "Liam", 2.0);
    acc6 = new Savings(6, 4000, "Madeleine", 2.5);
    acc7 = new Credit(7, 600, "Jared", 3000, 2.0);
    acc8 = new Credit(8, 1000, "Liam", 3000, 2.0);
    acc9 = new Credit(9, 100, "Madeleine", 3000, 2.5);
    bank = new Bank("Bank of America");
    accList = new ConsLoAccount(this.acc1,
        new ConsLoAccount(this.acc2,
            new ConsLoAccount(this.acc3,
                new MtLoAccount())));
  }
  
//test deposit on account class
 void testDeposit(Tester t) {
   this.reset();

   t.checkExpect(this.acc1.deposit(500), 3000);
   t.checkExpect(this.acc2.deposit(600), 3600);
   t.checkExpect(this.acc3.deposit(700), 4700);

   t.checkExpect(this.acc4.deposit(500), 3000);
   t.checkExpect(this.acc5.deposit(600), 3600);
   t.checkExpect(this.acc6.deposit(700), 4700);

   t.checkExpect(this.acc7.deposit(500), 100);
   t.checkExpect(this.acc8.deposit(600), 400);
   t.checkException(new RuntimeException("Excessive deposit!"), this.acc9, "deposit", 101);
 }

 // test withdraw on account class
 void testWithdraw(Tester t) {
   this.reset();

   t.checkExpect(this.acc1.withdraw(500), 2000);
   t.checkExpect(this.acc2.withdraw(600), 2400);
   t.checkException(new RuntimeException("Insufficient funds!"), this.acc3, "withdraw", 2001);

   t.checkExpect(this.acc4.withdraw(500), 2000);
   t.checkExpect(this.acc5.withdraw(600), 2400);
   t.checkException(new RuntimeException("Insufficient funds!"), this.acc6, "withdraw", 4001);

   t.checkExpect(this.acc7.withdraw(500), 1100);
   t.checkExpect(this.acc8.withdraw(600), 1600);
   t.checkException(new RuntimeException("Insufficient credit!"), this.acc9, "withdraw", 2901);
 }

 // test add account
 void testAdd(Tester t) {
   this.reset();

   this.bank.add(this.acc1);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc1,
       new MtLoAccount()));
   this.bank.add(this.acc2);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc2,
       new ConsLoAccount(this.acc1,
           new MtLoAccount())));
   this.bank.add(this.acc3);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc3,
       new ConsLoAccount(this.acc2,
           new ConsLoAccount(this.acc1,
               new MtLoAccount()))));
   this.bank.add(this.acc4);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc4,
       new ConsLoAccount(this.acc3,
           new ConsLoAccount(this.acc2,
               new ConsLoAccount(this.acc1,
                   new MtLoAccount())))));
 }

 // test deposit on bank class
 void testBankDeposit(Tester t) {
   this.reset();

   // add four accounts to this bank
   this.bank.add(this.acc1);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc1,
       new MtLoAccount()));
   this.bank.add(this.acc2);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc2,
       new ConsLoAccount(this.acc1,
           new MtLoAccount())));
   this.bank.add(this.acc3);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc3,
       new ConsLoAccount(this.acc2,
           new ConsLoAccount(this.acc1,
               new MtLoAccount()))));
   this.bank.add(this.acc4);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc4,
       new ConsLoAccount(this.acc3,
           new ConsLoAccount(this.acc2,
               new ConsLoAccount(this.acc1,
                   new MtLoAccount())))));

   this.bank.deposit(1, 500);
   t.checkExpect(this.acc1.balance, 3000);

   this.bank.deposit(2, 600);
   t.checkExpect(this.acc2.balance, 3600);

   t.checkException(new RuntimeException("Account not found!"), this.bank, "deposit", 12, 500);
 }

 // test withdraw on bank class
 void testBankWithdraw(Tester t) {
   this.reset();

   // add four accounts to this bank
   this.bank.add(this.acc1);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc1,
       new MtLoAccount()));
   this.bank.add(this.acc2);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc2,
       new ConsLoAccount(this.acc1,
           new MtLoAccount())));
   this.bank.add(this.acc3);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc3,
       new ConsLoAccount(this.acc2,
           new ConsLoAccount(this.acc1,
               new MtLoAccount()))));
   this.bank.add(this.acc4);
   t.checkExpect(this.bank.accounts, new ConsLoAccount(this.acc4,
       new ConsLoAccount(this.acc3,
           new ConsLoAccount(this.acc2,
               new ConsLoAccount(this.acc1,
                   new MtLoAccount())))));

   this.bank.withdraw(1, 500);
   t.checkExpect(this.acc1.balance, 2000);

   this.bank.withdraw(2, 600);
   t.checkExpect(this.acc2.balance, 2400);

   t.checkException(new RuntimeException("Account not found!"), this.bank, "withdraw", 12, 500);
 }

 // test deposit on ILoAccount class
 void testILoAccountDeposit(Tester t) {
   this.reset();

   this.accList.deposit(1, 500);
   t.checkExpect(this.acc1.balance, 3000);

   this.accList.deposit(2, 600);
   t.checkExpect(this.acc2.balance, 3600);

   t.checkException(new RuntimeException("Account not found!"), this.accList, "deposit", 12, 500);
 }

 // test withdraw on ILoAccount class
 void testILoAccountwithdraw(Tester t) {
   this.reset();

   this.accList.withdraw(1, 500);
   t.checkExpect(this.acc1.balance, 2000);

   this.accList.withdraw(2, 600);
   t.checkExpect(this.acc2.balance, 2400);

   t.checkException(new RuntimeException("Account not found!"), this.accList, "withdraw", 12, 500);
 }

 // test compareAccNum
 void testCompareAccNum(Tester t) {
   t.checkExpect(this.acc1.compareAccNum(1), true);
   t.checkExpect(this.acc2.compareAccNum(2), true);
   t.checkExpect(this.acc3.compareAccNum(3), true);
   t.checkExpect(this.acc4.compareAccNum(767), false);
   t.checkExpect(this.acc5.compareAccNum(121), false);
 }
}
