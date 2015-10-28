/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Shubham
 */
public class removeDupWords {
    
    public static String remove_dup(String str){
        String[] temp = str.split(" ");
        Set<String> store = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(String s : temp){
            if(!store.contains(s)){
                store.add(s);
                sb.append(s);
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
    public static void main(String args[]){
        System.out.println("Enter the String");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String out = remove_dup(str);
        System.out.println(out);
    }
}
