/*
Given a number n, create an array of size 2n such that the array 
contains 2 instances of every number from 1 to n, and the number 
of elements between two instances of a number i is equal to i. 
If such a configuration is not possible, then print the same.
 */
package Other;

import java.util.Arrays;

/**
 *
 * @author Shubham
 */
public class Backtracking {
    public static boolean bt(int[] out, int curr, int n){
        if(curr==0)
            return true;
        for(int i=0;i<2*n-curr-1;i++){
            if(out[i] == 0 && out[i+curr+1]==0){
                out[i] = curr;
                out[i+curr+1]=curr;
                if(bt(out, curr-1,n)){
                    return true;
                }else{
                    out[i]=0;
                    out[i+curr+1]=0;
                }
                
            }
        }
        return false;
    }
    public static void main(String args[]){
        int a =4;
        int[] out = new int[2*a];
        for(int i=0;i<out.length;i++){
            out[i]=0;
        }
        if(bt(out, a, a)){
            System.out.print(Arrays.toString(out));
        }
        else{
            System.out.println("Not Possible");
        }
    }
}
