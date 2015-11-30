/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class NonWeightedJobScheduling {
    
    public static void 
        jobscheduling(int[] start, int[] end, int size){
        int[] temp = new int[size];
        for(int k=0;k<size;k++){
            temp[k] = end[k];
        }
        Arrays.sort(end);
        int j =0;
        System.out.println(j);
        for(int i=1;i<size;i++){
            int index = Arrays.binarySearch(temp, end[i]);
            if(start[index] >= end[j]){
                System.out.println(i);
                j=i;
            }
        }
    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size");
        int size = s.nextInt();
        int[] start= new int[size];
        System.out.println("Enter the start times");
        for(int i=0; i<size;i++){
            start[i] = s.nextInt();
        }
        System.out.println("Enter the end time");
        int[] end= new int[size];
        for(int i=0; i<size;i++){
            end[i] = s.nextInt();
        }
        
        
        jobscheduling(start, end, size);
    }
}
