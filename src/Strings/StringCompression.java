/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strings;

import java.util.Scanner;
/**
 *
 * @author Shubham
 */
public class StringCompression {
      public String compress(String str){
          char last = str.charAt(0);
          StringBuilder sb = new StringBuilder();
          int count =1;
          String ans="";
          for(int i=1;i<str.length();i++){
              if(str.charAt(i) == last){
                  count+=1;
              }
              else{
                  String temp = Character.toString(last);
                  temp += Integer.toString(count);
                  sb.append(temp);
                  last = str.charAt(i);
                  count = 1;
              }
              
          }
          String temp1 = Character.toString(last);
          temp1 += Integer.toString(count);
          sb.append(temp1);
          return sb.toString();
    }
    
    public static void main(String args[]){
        StringCompression sp= new StringCompression();
        System.out.println("Enter a String to check for Compression: ");
        Scanner s= new Scanner(System.in);
        String str= s.nextLine();
        String b = sp.compress(str);
        if(str.length()>b.length()){
            System.out.println("The String is: " + b);
        }
        else
                  System.out.println("The String is: " + str);  
    }
}
