
import java.io.Console;
import java.util.*;

public class atm {

    public void accounting(int mach, int[][] transaction,String[][] users,int ulen) {
        int a = 10;
        int trancounter = 1;
        // System.out.println(ulen);
        while (a > 0) {
            System.out.println(
                    "main_menu press 0\nadd_balance press 1\nwithdraw_balance press 2\nshow_balance press 3\ntransactions press 4\ntransfer press 5\n");
            Scanner s = new Scanner(System.in);
            int input = s.nextInt();
            if (input==0) {
                break;
            }
            else if (input == 1) { // adding balance
                System.out.println("Enter amount for add...");
                int amount = s.nextInt();
                transaction[mach][trancounter] = amount;
                trancounter=trancounter+1;
                transaction[mach][0] += amount;
            } else if (input == 2) { // withdraw balance
                System.out.println("Enter amount for withdrawl ...");
                int amount = s.nextInt();
                transaction[mach][trancounter] = -amount;
                trancounter=trancounter+1;
                if(amount>transaction[mach][0]){
                    System.out.println("you have no enough money...");
                    continue;
                }
                transaction[mach][0] -= amount;
            } 
            else if (input == 3) { // view balance
                System.out.println("\nYour current balance is.." + transaction[mach][0]+"\n");
            } 
            else if (input == 4) { // view transactions
                for (int j = 0; j < trancounter-1; j++) {
                    System.out.println("\nyour "+(j+1)+" transaction is "+transaction[mach][j+1]);
                }
            } 
            else if(input == 5){ // tranfer amount
                int machindex = -1;
                System.out.print("transfer to : ");
                String uId = s.next();
                // System.out.println(uId);
                for (int i = 0; i < ulen; i++) {
                    String cur=users[i][0];
                    // System.out.println(cur+ " "+ uId);
                    if(cur.equals(uId)){
                        // System.out.println("Hi");
                        machindex=i;
                        break;
                    }
                }
                
                if(machindex!=-1){
                    System.out.println("Enter amount for transfer : ");
                    int amount = s.nextInt();
                    if(amount<=transaction[mach][0]){
                        transaction[machindex][trancounter] = amount;
                        trancounter=trancounter+1;
                        transaction[machindex][0] += amount;
                        transaction[mach][trancounter] = -amount;
                        trancounter=trancounter+1;
                        transaction[mach][0] -= amount;
                        System.out.println("transaction of "+amount+" made successfully...");
                    }else{
                        System.out.println("you have no enoght balance in your account...");
                    }
                }else{
                    System.out.println("account not found...");
                }
            }
            else {
                System.out.println("invalid input...");
            }
            System.out.println();
            if(trancounter>5){
                trancounter--;
                for (int i = 0; i < 5; i++) {
                    transaction[mach][i+1]=transaction[mach][i+2];
                }
            }
        }
    }

    public static void logInAccount() {
        Scanner s = new Scanner(System.in);
        int counter = 0;
        int mached = 0;
        int user = 0;
        boolean signal = false;
        String[][] data = new String[20][3];
        int transaction[][] = new int[10][7];
        for (int i = 0; i < transaction.length; i++) {
            transaction[i][0] = 0;
        }
        System.out.println("    Welcome to atm machine...\n");
        while (counter >= 0) {
            if (user > 10) {
                System.out.println("due to high trafic service is not available...");
                break;
            }
            System.out.println("Sign_Up press 1");
            System.out.println("Sign_In press 0");
            System.out.println("Quit press 3");
            int account = s.nextInt();
            signal = false;
            if(account==3){
                return;
            }
            if (account == 1) {
                System.out.println("Enter user name...");
                String username = s.next();
                System.out.println(username + " Enter your pin...");
                String password1 = s.next();
                System.out.println(username + " Confirm your pin...");
                String password2 = s.next();
                boolean isequals = password1.equals(password2);
                if (!isequals) {
                    System.out.println("password does not mached...");
                    continue;
                }
                System.out.println(username + " Enter your phone number...");
                String phone = s.next();
                // storing data
                data[counter][0] = username;
                data[counter][1] = password1;
                data[counter][2] = phone;
                counter++;
                user++;
                System.out.println("Congratulations your account is successfully created..");
            }
            else if (account == 0) {
                System.out.println("Enter your user name...");
                String username0 = s.next();
                System.out.println(username0 + " Enter your pin...");
                String password0 = s.next();
                // check id username or password is correct or not.
                if (user == 0) {
                    System.out.println("User not exist...");
                    continue;
                }
                for (int i = 0; i <= counter; i++) {
                    if(data[i][0]!=null && data[i][1]!=null &&data[i][0].equals(username0) && data[i][1].equals(password0)) {
                        System.out.println("You are login successfully...\n");
                        signal = true;
                        mached = i;
                        break;
                    }
                }
                // main logic start form here;
                if (signal) {
                    atm a = new atm();
                    a.accounting(mached, transaction,data,counter);
                } else {
                    System.out.println("wrong username or password..");
                    continue;
                }
            }
        }
    }

    public static void main(String[] arg) {
        logInAccount();
    }
}