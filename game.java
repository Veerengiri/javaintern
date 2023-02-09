import java.util.Random;
import java.util.Scanner;

public class game {
    static int generatenum(int rang){
        Random rand = new Random();
        int ret = rand.nextInt(rang - 0 + 1) + 0;
        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome to guess the number Game");
        int rang = 20;
        int level = 1;
        while(true){
            System.out.println("LEVEL:"+level+" press 1 to play and  press 0 to quite");
            int res = sc.nextInt();
            if(res!=0){
                if(rang%10!=0){
                    System.out.println("invalid rang (must be multiple of 10)");
                    continue;
                }
                int chance = 10;
                int gussnum = generatenum(rang);
                System.out.println("LET'S START THE GAME Guss number RANGE="+rang);
                int curchace= 0;
                Boolean nochace=false;
                while(true) {
                    nochace = false;
                    curchace++;
                    if(curchace>chance){
                        nochace=true;
                        break;
                    }
                    // System.out.println("guss the num");
                    int gs = sc.nextInt();
                    if(gs==gussnum){
                        System.out.println("CONGRATULATIONS.. YOU COMPLETE GAME IN "+(curchace)+" CHANCES");
                        break;
                    }else if(gs>gussnum){
                        System.out.println("guss lower number");
                    }else{
                        System.out.println("guss higher number");
                    }
                }
                if(!nochace){

                    rang=rang*2;
                    level++;
                }
            }else{
                return;
            }
        }
        
    }
}
