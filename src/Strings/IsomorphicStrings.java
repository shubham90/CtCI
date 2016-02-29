/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Shubham
 */
public class IsomorphicStrings {
    public static void is_iso(String s1, String s2){
        if(s1.length()!=s2.length()){
            System.out.println("Not Isomorphic");
            return;
        }
        Map<Character, Character> store = new HashMap<>();
        Set<Character> visited2 = new HashSet<>();
        Set<Character> visited1 = new HashSet<>();
        for(int i=0;i<s2.length();i++){
            char temp = s2.charAt(i);
            char temp1 = s1.charAt(i);
            if(!visited2.contains(temp)){
                if(visited1.contains(temp1)){
                    System.out.println("Not Isomorphic");
                    return;
                }
                else{
                    visited2.add(temp);
                    visited1.add(temp1);
                    store.put(temp, temp1);
                }
            }
            else{
                if(temp1!=store.get(temp)){
                    System.out.println("Not Isomorphic");
                    return;
                }
            }
        }
        System.out.print("Isomorphic!");
            
    }
    public static void main(String args[]){
        String input1 = "aab";
        String input2 = "xzy";
        is_iso(input1,input2);
    }


}
