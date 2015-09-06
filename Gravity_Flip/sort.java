import java.util.*;
import java.io.*;


public class sort{

        public static String[] split(String[] nums, String option){
                int length = nums.length;
                String[] ret;
                if (option.equals("first")){
                        ret = new String[length/2];
                        for (int i = 0; i < length/2; i++){
                                ret[i] = nums[i];
                        }
                }

                else { //if (option.equals("second")){
                        ret = new String[length-(length/2)];
                        for (int i = 0; i < (length-(length/2)); i++){
                                ret[i] = nums[(length/2) + i];
                        }
                }

                return ret;
        }

        public static String[] merge(String[] f, String[] s){

                int fCounter = 0;
                int sCounter = 0;
                String[] ret = new String[f.length + s.length];
                while (fCounter < f.length && sCounter < s.length){
                        if (Integer.parseInt(f[fCounter]) < Integer.parseInt(s[sCounter])){
                                ret[fCounter+sCounter] = f[fCounter];
                                fCounter++;
                        }
                        else {
                                ret[fCounter+sCounter] = s[sCounter];
                                sCounter++;
                        }
                }
                while(fCounter < f.length){
                        ret[fCounter+sCounter] = f[fCounter];
                        fCounter++;
                }
                while(sCounter < s.length){
                        ret[fCounter+sCounter] = s[sCounter];
                        sCounter++;
                }

                return ret;
        }

        public static String[] mergesort(String[] nums){
            if (nums.length < 2)
                return nums;
            String[] first = split(nums,"first");
            String[] second = split(nums,"sec");

            return merge(mergesort(first),mergesort(second));
            /*for (int i = 0; i < r.length; i++){
                System.out.println(r[i]);
            }*/
        //      split
        //      sort halves
        //      merge

        }

        public static String handle(String[] nums){

                if (nums.length < 2)
                        return nums[0];

                String[] sorted = mergesort(nums);
                String ret = "";
                for (int i = 0; i < sorted.length; i++){
                        ret += (sorted[i] + " ");
                }
                return ret;
        }

        public static void main(String args[]){

                Scanner stdin = new Scanner(System.in);
                int columns = Integer.parseInt(stdin.nextLine());
                String nums = stdin.nextLine();
                String[] a = nums.split(" ");

                System.out.println(handle(a));
                stdin.close();

        }
}
                                                                                             94,1          Bot



