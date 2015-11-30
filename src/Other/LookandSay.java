/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class LookandSay {
    
    
    public String printSeq(String num){
        
        StringBuilder sb = new StringBuilder();
        char repeat = num.charAt(0);
        int time=1;
        num= num.substring(1) + " ";
        for(char c:num.toCharArray()){  
            if(c != repeat){
                sb.append(time);
                sb.append(repeat);
                time=1;
                repeat=c;
            }
            else
                time=time+1;
        }
        return sb.toString();
               
    }
    
    
     public static void main(String args[]){
        System.out.print("Enter Start number: ");
        Scanner s =new Scanner(System.in);
        String num = s.nextLine();
        System.out.print("Enter number of times: ");
        Scanner s1 =new Scanner(System.in);
        int times = s1.nextInt();
        LookandSay ls = new LookandSay();
        
        for (int i=1;i<=times;i++) {
            System.out.println(num);
            num = ls.printSeq(num);             
	}
    }
}
