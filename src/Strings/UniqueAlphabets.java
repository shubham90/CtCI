/*
Implement a program to find whether all alphabets in String are unique or not.
 */
package Strings;

import java.util.BitSet;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class UniqueAlphabets {
    public boolean isUnique(String str){
        int i;
        BitSet b = new BitSet();
        for(i=0;i<str.length();i++){
        int a= str.charAt(i) - 'a';
        if(b.get(a))
            return false;
        else {
                b.set(a);
                }
        }
        System.out.println(b);
        return true;
    }
    
    public static void main(String args[]){
        UniqueAlphabets q=new UniqueAlphabets();
        System.out.println("Enter the String:");
        Scanner s= new Scanner(System.in);
        boolean result = q.isUnique(s.nextLine());
        System.out.println(result);
    }
}
