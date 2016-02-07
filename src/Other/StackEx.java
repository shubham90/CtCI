/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Shubham
 */
public class StackEx {
    public static void main(String args[]){
        System.out.println("Enter String to check");
        Scanner s1= new Scanner(System.in);
        Stack s = new Stack(); 

        String input = s1.nextLine();
        int length=input.length();
        for(int i=0;i<length;i++){
            if(s.isEmpty())
                s.push(input.charAt(i));
            else{
            char c = input.charAt(i);
            char current=(char)s.peek();
            
            if(current==c){
                s.pop();
            }
            else{
                s.push(c);
            }
        }
        }
        if(s.isEmpty())
            System.out.println("The string is right");
        else{
            System.out.println("The string is wrong");
        }
        

    }
}
