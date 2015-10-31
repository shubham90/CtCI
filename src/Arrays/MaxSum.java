/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class MaxSum {
    public static int sumMaximum(int[] arr){
        int current_max=0;
        int global_max=0;
        for(int i=0;i<arr.length;i++){
            current_max = Math.max(0, current_max+arr[i]);
            global_max = Math.max(current_max, global_max);
        }
        return global_max;
    }
    public static void main(String args[]){
        System.out.println("Enter the size of integer Array: ");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        System.out.println("Enter the Array: ");
        s = new Scanner(System.in);        
        int[] arr= new int[size];
        for(int i=0; i<size;i++){
            arr[i] = s.nextInt();
        }
        int sum = sumMaximum(arr);
        System.out.println("Sum of the array elements: " + sum);        
    }
}
