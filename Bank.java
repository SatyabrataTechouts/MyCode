package onlineBankingSystem;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) throws Exception {

        /**
         Here we declare the Costumer object to enter into hash map
         in below declared as hashmap key is Account number and values is Customer object which have
         customer  name ,Atm pin,money.
         * */
        HashMap<String, Costumer> hashMap = new HashMap<>();
        hashMap.put("\uD83D\uDE00", new Costumer("Satyabrata Barik", 1234, 12000));
        hashMap.put("7890123470", new Costumer("Sk Tajudhin", 4456, 12300));
        hashMap.put("7890128890", new Costumer("Padma nava Tripathy", 7890, 14000));
        hashMap.put("7890122356", new Costumer("Hrusikesh Nayak", 3456, 120000));
        hashMap.put("7890123345", new Costumer("Harekrushna Das", 7789, 1200023));
        hashMap.put("7890123235", new Costumer("Jagannath Barik", 7873, 120004));

        int d = 0;
        /**
         Here try to handle the InputMismatchException using try and catch block.
         **/
        int p = 0;

        do {
            System.out.println("***\uD83D\uDE4F Welcome to Bank Of India \uD83D\uDE4F***");

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your Account Number:");

            String str = sc.next();
            while (!str.matches("[0-9]+")) {
                /**
                 * Here use Regex because we can take the account number as a String so Any input
                 * can be store into the str variable.This is writing for  only taken integer as
                 * a string.
                 */
                System.out.println("You Enter a wrong format please enter a correct account Number: ");
                str = sc.next();

            }
            int t = 0;
            for (Map.Entry<String, Costumer> h : hashMap.entrySet()) {
                String r = h.getKey();
                if (r.equals(str)) {
//                    try {
                        do {

                            /**
                             * here use if Statement because of if the account number match then it goes
                             * for transaction.
                             */
                            int choice = 0;
                            System.out.println("  Enter \uD83D\uDC46:\n 1->Deposit Money\n 2->Withdraw Money");
                            System.out.println(" 3->Money Transfer\n 4->Balance inquire");
                            System.out.println(" 5->Pin Change\n 6->Mini Statement\n 7->Logout");


                            while (true) {
                                try {
                                    choice = sc.nextInt();
                                    if (choice > 0 && choice < 8) {
                                        break;
                                    } else {
                                        System.err.println("You enter a wrong input please enter a number between 1 to 7");
                                    }
                                }
                                catch (Exception e){
                                    System.err.println("Please Enter A correct format");
                                    sc.nextLine();
                                }

                            }

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
                                case 6:
                                    for (Map.Entry<String, Costumer> m : hashMap.entrySet()) {
                                        String k = m.getKey();
                                        try {
                                            if (k.equals(str)) {

                                                m.getValue().statement(str);
                                            }
                                        } catch (FileNotFoundException e) {
                                            System.err.println("No Transaction Yet");
                                        }

                                    }

                                    break;
                                case 7:
                                    d = d + 1;
                                    System.err.println("Thank You Visit Again");
                                    break;
                                default:
                                    System.err.println("**Invalid Entry\uD83D\uDE15**\n$$Thank You $$");
                            }
                        }

                        while (true && d == 0);
                }
                else {
                    t++;
                    if (t == hashMap.size()) {
                        /**
                         * here if str check the Account number then not match.t will be equal to
                         * the hashmap size then it execute the above if() statement and show the
                         * output Account Mismatched.
                         */
                        System.err.println("Account Mismatched \uD83D\uDE15!");

                    }
                }

            }


        }
        while (true);
    }
}
