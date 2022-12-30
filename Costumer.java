package onlineBankingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Costumer implements Sever {
    private String name;
    private int pin;
    private double money;
  private   LocalTime time;
  private  LocalDate date;

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
        try {
            /*
            Here use try or catch because of here probability of inputMismatched Exception.
             */
            int nPin = s.nextInt();

            if (nPin == pin) {
                System.out.println("Enter your new pin :");
                int newPin = s.nextInt(4);
                if (isValid(newPin)){
                    /**
                    use of isValid(newPin) method to check the  length of newPin is four or not
                    if four then true. Which is compulsory to type four digit pin.Here new pin is
                    input to the user.Which is replaced by old pin.
                     */
                pin = newPin;
                System.out.println("Pin generate Successfully");
                this.local();
            }
                else {
                    System.out.println("Pin size should be 4");
                }
            }

            else{
                System.out.println("You enter wrong pin plz Enter correct pin and Try Again.\n&&Thank you&&");

            }
        }
        catch (Exception e){
            System.out.println("Input format Wrong or You can must type pin length is equal to four");
        }
    }
    public void deposit(String k){
        System.out.println("Enter amount to deposit:");
        Scanner s=new Scanner(System.in);
        int amt=s.nextInt();
        money=money+amt;
        this.inquare(k,money,amt,"Add");
        try {
            this.fileGenerate(k);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void withdraw(String str){
        System.out.println("Enter your pin:");
        Scanner s2=new Scanner(System.in);
        int k=s2.nextInt();
        if (pin==k){
            System.out.println("Enter amount to withdraw:");
            int amt= s2.nextInt();
            if (amt<=money){
               money-=amt;
                System.out.println("Withdraw Successfully\nAvailable amount is "+money);
                try {
                    this.fileGenerate(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                System.out.println("Insufficient Balance");
            }
        }
        else {
            System.out.println("Invalid pin Try again:");
        }
    }

    @Override
    public void balance(String str) {
        System.out.println("---------------------");
        System.out.println("Name:"+name);
        System.out.println("AccNO:"+str);
        System.out.println("Balance:"+money);
        this.local();
        System.out.println("---------------------");
        try {
            this.fileGenerate(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void moneyTransfer(String str){
        System.out.println("Enter account to Transfer Money:");
        Scanner s3=new Scanner(System.in);
        String str2= s3.nextLine();
        System.out.println("Enter amount to Transfer:");
        int amt= s3.nextInt();
        System.out.println("Enter your 4 digit  pin:");
        int c=s3.nextInt();
        if (pin==c) {
            if (amt <= money) {
                money = money - amt;
                this.inquare(str,money,amt,"Transfer");
                try {
                    this.fileGenerate(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Balance is "+money);
                System.out.println("Insufficient Balance");

            }
        }
        else {
            System.out.println("Invalid pin");
        }
    }
    public void inquare(String str,double amount,int amt,String s2){
        /**
        This method are used to code reuse ability to last of Every Transaction.
        to generate the Status .
         */
        System.out.println("---------------------------");
        System.out.println("Account no-"+str);
        System.out.printf("%d balance to %s\n",amt,s2);
        System.out.println("Total balance is "+amount);
        this.local();
        System.out.println("---------------------------");

        try {
            this.fileGenerate(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  public  void local(){
     time=LocalTime.now();
        date=LocalDate.now();
      System.out.println(time+" "+date);

  }
    void fileGenerate(String str) throws IOException {
        /**
        fileGenerate() method are used to generate the "example.txt" file
        in every  Transaction.And Add trows because the IoException will
        Come.
         */
        File f=new File("Banking.txt");
        FileWriter f1=new FileWriter(f);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------------\n");
        stringBuilder.append("Bank Of India  Mini statement:\n");
        stringBuilder.append("Name:");
        stringBuilder.append(name);
        stringBuilder.append("\n");
        stringBuilder.append("Account No:");
        stringBuilder.append(str);
        stringBuilder.append("\n");
        stringBuilder.append("Amount:");
        stringBuilder.append(money);
        stringBuilder.append("");
        stringBuilder.append("\n"+time+"  "+date);
        stringBuilder.append("\n---------------------");
        String p= stringBuilder.toString();
        f1.write(p);
        f1.close();
    }
   boolean isValid(int pin){
        /**
        Here check the input is four digit or not
         */
        int i=0;
        while (pin!=0){
            int r=pin/10;
            i++;
        }
        if (i==4){
            return  true;
        }
        return false;
   }
}
