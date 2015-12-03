/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank.DP;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class LCSPractise {
 
    public static int lcs_repeat(String first, String second){
        int first_size = first.length();
        int second_size = second.length();
        
        if(first_size == 0 || second_size==0){
            return 0;
        }
        
        int[][] store = new int[first_size+1][second_size+1];
        
        for(int i=0; i<first_size+1;i++){
            store[i][0] = 0;
        }
        
        for(int i=0; i<second_size+1;i++){
            store[0][i] = 0;
        }
        
        for(int i=1;i<first_size+1;i++){
            for(int j=1;j<second_size+1;j++){
                if(first.charAt(i-1) == second.charAt(j-1)){
                    store[i][j] = store[i-1][j-1] + 1;
                }
                else{
                    store[i][j] = Math.max(store[i-1][j], store[i][j-1]);
                }
            }
        }
        return store[first_size][second_size];
    }
    
    public static void main(String args[]){
        System.out.println("Please enter the first String");
        Scanner s= new Scanner(System.in);
        String first = s.nextLine();
        
        
        System.out.println("Please enter the second String");
        String second = s.nextLine();
        
        int output = lcs_repeat(first, second);
        
        System.out.println("Length of longest substring is : " + output);
        
        
    }
}
