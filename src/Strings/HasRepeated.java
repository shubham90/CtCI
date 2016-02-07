/*
First non repeated character in the string
 */
package Strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Shubham
 */
public class HasRepeated {
    
    
    public char hasRepeated(String word) { 
    Set<Character> h = new HashSet<>();
    for(int i=0; i<word.length();i++){
        if(h.contains(word.charAt(i))){
            return word.charAt(i);
        }
        else{
            h.add(word.charAt(i));
           
        }
    }
    return 0;
    }
    public static void main(String args[]){
        System.out.println("Enter String to check for Repeated characters:");
        Scanner s= new Scanner(System.in);
        HasRepeated h=new HasRepeated();
        char l= h.hasRepeated(s.nextLine());
        System.out.println(" First Non Repeated char is:" + l);
    }
}
