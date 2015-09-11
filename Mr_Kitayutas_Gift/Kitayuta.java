import java.util.*;
import java.io.*;

public class Kitayuta{
    
    public static boolean isPalindrome(String word){
        return (word.equals(new StringBuilder(word).reverse().toString()));
    }

    public static String[] setUp(){
        String alpha = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        return alpha.split(" ");
    }

    public static void main(String args[]){
        //System.out.println(isPalindrome("poop"));
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        boolean breakCheck = false;

        // Ex: Given word poop --> poaop
        if (isPalindrome(input) && (input.length()%2==0)){
            StringBuilder s = new StringBuilder(input.substring(0,input.length()/2));
            s.append("a");
            s.append(input.substring(input.length()/2));
            System.out.println(s.toString());
            breakCheck = true;
        }

        if (!breakCheck){
            String[] alpha = setUp();
            for (int i = 0; i < input.length()+1; i++){
                StringBuilder s = new StringBuilder();
                s.append(input.substring(0,i));

                for (String a : alpha){
                    StringBuilder r = new StringBuilder();
                    r.append(s);
                    //System.out.println("After first append: " + r.toString());
                    r.append(a);
                    //System.out.println("After sec append: " + r.toString());
                    r.append(input.substring(i));
                    //System.out.println("After 3 append: " + r.toString());
                    if (isPalindrome(r.toString())){
                        breakCheck = true;
                        s = r;
                        break;
                    }
                }
                
                //System.out.println("Checking : "+s.toString());
                if (breakCheck){
                    System.out.println(s.toString());
                    break;
                }
            }
        }

        if (!breakCheck)
            System.out.println("NA");

    }
}
