import java.io.*;
import java.util.*;

public class twoButtonsFinal{

	public static void main(String args[]){

	Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int current = Integer.parseInt(line[0]);
        int goal = Integer.parseInt(line[1]);

        boolean breakCheck = false;
        if (current > goal){
        	System.out.println(current-goal);
            breakCheck = true;
        }

        if (!breakCheck){
            int ret = 0;
            while(current < goal){
            	if (goal%2 == 0){
            		goal = goal/2;
            	}
            	else{
            		goal++;
            	}
            	ret++;
            }
            ret = ret + (current-goal);
            System.out.println(ret);

        }
    }

}
