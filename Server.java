package onlineBankingSystem;
/**
 Here Declare  only interface because I archived 100%
 Data abstraction.This method internal implementation
 doesn't necessary to show the costumer
 */
public interface Server {



        void deposit(String str);
        void withdraw(String str1);
        void balance(String str2);
        void moneyTransfer(String str3);


}
