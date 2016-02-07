/*
A Derangement is a permutation of n elements, such that no element appears in its original position. For example, a derangement of {0, 1, 2, 3} is {2, 3, 1, 0}.

Given a number n, find total number of Derangements of a set of n elements.
 */
package DP;

/**
 *
 * @author Shubham
 */
public class Dearrangements {
    public static int der(int[] inp){
        int len = inp.length;
        int[] store = new int[len+1];
        store[0] =0;
        store[1] = 0;
        store[2] = 1;
        
        for(int i=3; i<=len;i++){
            store[i] = (i-1)*(store[i-1] + store[i-2]);
        }
        return store[len];
    }
    public static void main(String args[]){
        int[] inp = {1,2,3,4,6,1};
        int output = der(inp);
        System.out.println(output);
    }
}
