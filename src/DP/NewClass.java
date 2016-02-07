/*
Given a sequence, find the length of the longest palindromic subsequence in it. For example, if the given sequence is “BBABCBCAB”, 
then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it. 
“BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.
 */
package DP;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class NewClass {
    public static void len_palin(String str){
        char[] input=str.toCharArray();
        int n= str.length();
        int[][] store = new int[n][n];
        int first =0;
        if(n == first)
            return;
        if(n==1){
            System.out.println("Length of palindrom is 1");
            return;
        }
        for(int k=0;k<n;k++){
            store[k][k]=1;
        }
        for(int offset=2;offset<=n;offset++){
            for(int i=0;i<n-offset+1;i++){
                int j= i+offset-1;
                if(input[i]==input[j] && offset==2){
                    store[i][j]=2;
                  
                }
                if(input[i]==input[j] && store[i+1][j-1] == j-i-1){
                    store[i][j]=store[i+1][j-1]+2;
                    
                }
                else
                    store[i][j]= Math.max(store[i+1][j], store[i][j-1]);
            }
        }

        System.out.println("Length of maximum palindrome is: " + store[0][n-1]);
        
    }
    
    public static void main(String args[]){
        System.out.println("Enter String to find the longest palindrome");
        Scanner s = new Scanner(System.in);
        len_palin("MCATACKM");
    }
}
