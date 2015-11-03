/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitmanipulation;

/**
 *Implementing getBit for Bit Manipulation
 * @author Shubham
 */
public class Example {
    public boolean getBit(int num, int i){
        return((num & (1 << i)) !=0);
    }
    
public static void main(String args[]){
    int num=8;
    int i=4;
    Example e= new Example();
    int k=1;
    System.out.print(k << 1);
    System.out.println("Bit at position "+i+" is 1:" + e.getBit(num, i));
    //Calculating the hamming distance between two numbers num1 & num2
    // Also calculating the number of ones in their XOR
    int num1=16;
    int num2=15;
    int num3 = num1^num2;
    System.out.println(Integer.toBinaryString(num3));
    int count=0;
    while(num3>0){
        num3=num3&(num3-1);
        count++;
    }
    System.out.println(count);
}
    
}
