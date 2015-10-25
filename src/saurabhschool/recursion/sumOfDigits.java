/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saurabhschool.recursion;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class sumOfDigits {
    
    public static int sumD(int num){
        if(num/10 == 0){
            return num;
        }
        return(sumD(num/10) + num%10);
    }
    public static void main(String args[]){
        System.out.println("-----------------Printing the Sum of Digits------------------");
        System.out.println("Enter the Number:");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int sum = sumD(num);
        System.out.println("Sum of number is: " + sum);
    }
}
