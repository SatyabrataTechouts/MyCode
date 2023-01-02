package onlineBankingSystem;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.String.*;

public class Costumer implements Server {
    ;
    String acc="";
    private String name;
    private int pin;
    private double money;
  private   LocalTime time;
  private  LocalDate date;
    String[] names=new String[]{"7890123470","7890128890","7890122356",
            "7890123345","7890123235"};

    public Costumer(String name,int pin,double money){
        this.name=name;
        this.pin=pin;
        this.money=money;

    }

    public int getPin() {
        return pin;
    }
/**
    here setPin use to Set a new pin .method is non  because
    of all are implement in that class .In other word input to
    new pin in inside setPin() method.
 */
    public void setPin() {
        System.out.println("Enter your current pin:");
        Scanner s=new Scanner(System.in);
            /*
            Here use try or catch because of here probability of inputMismatched Exception.
             */
        try {
            int nPin = s.nextInt();
        
     
         if (nPin == pin) {
                System.out.println("Enter your new pin :");
              //  int newPin = s.nextInt();
        int newPin;
                while (true) {
                    try {
                        newPin= s.nextInt();
                        if (newPin>0&&isValid(newPin)) {
                            break;
                        } else {
                            System.err.println("You enter a wrong input please enter a number between 1 to 7");
                        }
                    }
                    catch (Exception e){
                        System.err.println("Please Enter A valid password");
                        s.nextLine();
                    }

                }
             //   if (isValid(newPin)&&newPin>0){
                    /**
                    use of isValid(newPin) method to check the  length of newPin is four or not
                    if four then true. Which is compulsory to type four digit pin.Here new pin is
                    input to the user.Which is replaced by old pin.
                     */
                pin = newPin;
                System.out.println("Pin generate Successfully \uD83D\uDE00");
                this.local();
            }

        }
        catch (Exception e){
            System.err.println("Input format Wrong or You can not type pin length is equal to four");
        }
    }
    public void deposit(String k){
        System.out.println("Enter amount to deposit:");
        Scanner s=new Scanner(System.in);
        int amt;
        while(true){
            try {
                amt = s.nextInt();
                if (amt > 0) {
                    break;
                }
                else {
                    System.out.println("Enter valid amount");
                }
            }
            catch (Exception e){
                System.out.println("Enter Wrong format");
                s.nextLine();
            }
        }

            money = money + amt;
            System.out.println("Deposit  successfully \uD83D\uDE00");
            this.inquare(k, money, amt, "Add");
            try {
                //this.fileGenerate(k, "Deposit", amt);
                this.statement1(k,0,amt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }


    public void withdraw(String str){
        System.out.println("Enter your pin:");
        Scanner s2=new Scanner(System.in);
            int k;
            while(true) {
                try {
                    k = s2.nextInt();
                    if (k > 0 && isValid(k)) {
                        break;
                    } else {
                        System.out.println("Try again");
                    }
                }
                catch(Exception e){
                    System.out.println("Try right format");
                    s2.nextLine();
                }
            }
            if (pin == k) {
                System.out.println("Enter amount to withdraw:");
                int amt;
                while(true) {
                    try {
                         amt = s2.nextInt();

                        if (amt > 0) {
                            break;
                        } else {
                            System.err.println("Try again");
                        }
                    }
                    catch (Exception e){
                        System.err.println("Enter valid amount");
                        s2.nextLine();
                    }
                }
                if (amt <= money && amt >= 0) {
                    money -= amt;
                    System.out.println("Withdraw Successfully \uD83D\uDE00 \nAvailable amount is " + money);
                    try {
                        this.local();
                      //  this.fileGenerate(str, "Withdraw", amt);
                        this.statement1(str, amt, 0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.err.println("Insufficient Balance  \uD83D\uDE15!");
                }
            } else {
                System.err.println("Invalid pin Try again\uD83D\uDE15!");
            }
        }


    @Override
    public void balance(String str){
        System.out.println("Enter your pin:");
        Scanner s=new Scanner(System.in);
        try{
        int c=s.nextInt();
        while (!isValid(c)){
            System.out.println("Enter 4 digit pin ");
            c=s.nextInt();
        }
        if(pin==c) {
            System.out.println("---------------------");
            System.out.println("Name:" + name);
            System.out.println("AccNO:" + str);
            System.out.println("Balance:" + money);
            this.local();
            System.out.println("---------------------");
//            try {
//                //this.fileGenerate(str, "balance Generate ", (int) money);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
        else {
            System.err.println("Enter wrong pin ");
        }
        }
        catch (Exception e){
            System.err.println("Wrong Enter \n" +
                    "Thank you");
        }
    }

    public void moneyTransfer(String str) throws InputMismatchException{
        
            System.out.println("Enter account Transfer->1\n Enter upi Transfer->2");

            Scanner s3 = new Scanner(System.in);
            int trans;
            while (true) {
                try {
                    trans = s3.nextInt();
                    if (trans > 0) {
                        break;
                    } else {
                        System.out.println("Enter right input");
                    }
                }
                catch (Exception e){
                    System.out.println("Enter right choice");
                    s3.nextLine();
                }
            }
            switch (trans) {
                case 1:
                    System.out.println("Enter acc number:");
                    acc = s3.next();
                    while(!acc.matches("[0-9]{10}+") ){

                        System.err.println("Input a valid Account number which length is should be 10");
                        acc=s3.nextLine();
                    }

                    this.transfer(str);
                   break;
                case 2:
                    System.out.println("Enter upi id:");
                     acc=s3.next();
                    while (!acc.matches("^[\\w.-]+@[\\w.-]+$")){
                        System.err.println("email/phone number@bankname/payment gateway");
                        acc=s3.next();


                    }
                    this.transfer(str);
                    break;
                default:

            }

        
    }
    public void inquare(String str,double amount,int amt,String s2){
        /**
        This method are used to code reuse ability to last of Every Transaction.
        to generate the Status .
         */
        System.out.println("---------------------------");
        System.out.println("Account no-"+str);
        System.out.printf("%d balance is %s %s\n",amt,s2,acc);
        System.out.println("Total balance is "+amount);
        this.local();
        System.out.println("---------------------------");
        acc="";
//        try {
//          //  this.fileGenerate(str," Balance Generate Successfully", 0);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
  public  void local(){
     time=LocalTime.now();
        date=LocalDate.now();
      System.out.println(time+" "+date);

  }
//    void fileGenerate(String str,String str2,int a) throws IOException {
//        /**
//        fileGenerate() method are used to generate the "BOI.txt" file
//        in every  Transaction.And Add throws because the IoException will
//        Come.It will be maintained the record of bank.
//         */
//        File f=new File("BOI.txt");
//        FileWriter f1=new FileWriter(f,true);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("----------------------------------\n");
//        stringBuilder.append("Bank Of India  Mini statement:\n");
//        stringBuilder.append("Name:");
//        stringBuilder.append(name);
//        stringBuilder.append("\n");
//        stringBuilder.append("Account No:");
//        stringBuilder.append(str);
//        stringBuilder.append("\n"+str2+" "+a+"\n");
//        stringBuilder.append("Amount:");
//        stringBuilder.append(money);
//        stringBuilder.append("");
//        stringBuilder.append("\n"+time+"  "+date);
//        stringBuilder.append("\n----------------------------------");
//        String p= stringBuilder.toString();
//        f1.write(p);
//        f1.close();
//    }

    boolean isValid(int pin){
        /**
        Here check the input is four digit or not
         */
        int i=0;
        while (pin!=0){
            pin =pin/10;
            i++;
        }
        if (i==4){
            return  true;
        }
        return false;
   }

  public  void statement1(String str,int amd,int amc) throws IOException {
        /**
         * it uses to generate the account Statement .

         */

      StringBuffer strContent = new StringBuffer("");
      for (String name : names) {
          File f = new File(name);
          if (str.equals(valueOf(f))) {

              FileWriter f1 = new FileWriter(f, true);
              f1.append("date :" + date + "  Debited:" + amd + "   credited:" + amc + "  Total:" + money+"\n");

              f1.close();
          }
      }
  }
    void statement(String str) throws IOException {
        /**
         * it uses to the show of account statement
         * This method uses to read the txt file
         * which is genarates  by the individual file of
         * every Account.

         */

        System.out.println("Enter your pin  :");

        Scanner t = new Scanner(System.in);
        int k = 0;
        try {

            do {
            int tp = t.nextInt();


                if (tp == pin) {
                    System.out.println("Account number:" + str);
                    for (String name : names) {
                        if (str.equals(name)) {
                            BufferedReader br = new BufferedReader(new FileReader(name));
                            String st;
                            while ((st = br.readLine()) != null) {
                                System.out.println(st);

                            }
                        }
                    }
                    k++;
                } else {
                    System.err.println("Enter wrong pin");

                }

            }
            while (k != 1);

        }
        catch (Exception e){
            System.err.println("Wrong format");
        }
    }
    void transfer(String str){
        Scanner s3=new Scanner(System.in);
        if (!str.equals(acc)) {
            System.out.println("Enter amount to Transfer:");
            int amt = 0;

            while (true) {
                try {
                    amt = s3.nextInt();
                    if (amt > 0) {

                        break;
                    } else {
                        System.err.println("Amount should be greater then Zer0");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Try again!");
                    s3.nextLine();
                }
            }


            try {
                System.out.println("Enter your 4 digit  pin:");
                int c = s3.nextInt();
                if (pin == c) {
                    if (amt <= money) {
                        money = money - amt;
                        this.inquare(str, money, amt, "Transfer");
                        try {
                           // this.fileGenerate(str, "Transfer", amt);
                            this.statement1(str, amt, 0);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Balance is " + money);
                        System.err.println("Insufficient Balance\uD83D\uDE15 !");

                    }
                } else {
                    System.err.println("Invalid pin \uD83D\uDE15");
                }
            } catch (Exception e) {
                System.err.println("Sorry pin we cant find !");
            }
        } else {
            System.err.println("You cant transfer amount yourself \uD83D\uDE15");
        }
    }

}
