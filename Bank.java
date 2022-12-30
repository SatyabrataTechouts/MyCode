package onlineBankingSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {

        /**
        Here we declare the Costumer object to enter into hash map
        in below declared as hashmap key is Account number and values is Customer object which have
        customer  name ,Atm pin,money.
        * */
        HashMap<String, Costumer> hashMap=new HashMap<>();
        hashMap.put("\uD83D\uDE00",new Costumer("Satyabrata Barik",1234,12000));
        hashMap.put("7890123470",new Costumer("Sk Tajudhin",4456,12300));
        hashMap.put("7890128890",new Costumer("Padma nava Tripathy",7890,14000));
        hashMap.put("7890122356",new Costumer("Hrusikesh Nayak",3456,120000));
        hashMap.put("7890123345",new Costumer("Harekrushna Das",7789,1200023));
        hashMap.put("7890123235",new Costumer("Jagannath Barik",7873,120004));
        System.out.println("***Welcome to Bank Of India***");
        /**
        Here try to handle the InputMismatchException using try and catch block.

         **/do {
            try {
                Scanner sc =new Scanner(System.in);
                System.out.println("Enter your Account Number:");

                String str = sc.next();
                while(!str.matches("[0-9]+")){
                    /**
                    * Here use Regex because we can take the account number as a String so Any input
                    * can be store into the str variable.This is writing for  only taken integer as
                    * a string.
                     */
                    System.out.println("You Enter a wrong format please enter a correct account Number: ");
                    str = sc.next();
                }
                int t=0;
                for (Map.Entry<String,Costumer> h: hashMap.entrySet()) {
                    String r = h.getKey();
                    if (r.equals(str)) {
                        /**
                       * here use if Statement because of if the account number match then it goes
                       * for transaction.
                         */
                        System.out.println("Enter to 1->Deposit Money\nEnter to 2->Withdraw Money");
                        System.out.println("Enter to 3->Money Transfer\nEnter to 4->Mini Statement");
                        System.out.println("Enter to 5->Pin Change");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                    String k = m.getKey();
                                    if (k.equals(str)) {
                                        m.getValue().deposit(k);
                                    }
                                }

                                break;
                            case 2:
                                for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                    String k = m.getKey();
                                    if (k.equals(str)) {
                                        m.getValue().withdraw(k);
                                    }
                                }
                                break;
                            case 3:
                                for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                    String k = m.getKey();
                                    if (k.equals(str)) {
                                        m.getValue().moneyTransfer(k);
                                    }
                                }
                                break;
                            case 4:
                                for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                    String k = m.getKey();
                                    if (k.equals(str)) {
                                        m.getValue().balance(k);
                                    }
                                }
                                break;
                            case 5:
                                for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                    String k = m.getKey();
                                    if (k.equals(str)) {
                                        m.getValue().setPin();
                                    }
                                }
                                break;
                            default:
                                System.err.println("**Invalid Entry\uD83D\uDE15**\n$$Thank You $$");
                        }
                    }
                    else {
                        t++;
                        if (t== hashMap.size()){
                            /**
                             * here if str check the Account number then not match.t will be equal to
                             * the hashmap size then it execute the above if() statement and show the
                             * output Account Mismatched.
                             */
                            System.err.println("Account Mismatched \uD83D\uDE15!");
                        }
                    }
                }


            } catch (Exception e) {
                System.err.println("You input wrong or something went to Wrong ");
            }
        }
        while (true);
    }
}
