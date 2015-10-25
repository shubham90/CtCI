/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Shubham
 */
public class SetProb {
    
    public static void main(String[] args){
    Set<String> s1 = new HashSet<String>();
    Set<String> s2 = new HashSet<String>();
    Set<Set<String>> s3 = new HashSet<Set<String>>();
   
    s1.add("cat");
    s1.add("dog");
    s1.add("snake");
    Set<String> ans=null;
    s2.add("tom");
    s2.add("jerry");
    s2.add("gilly");
    int cost=0;
    cost += 2;
    for(String i:s1){
        for(String j:s2){
         ans= new HashSet<String>();
         ans.add(i);
         ans.add(j);
         s3.add(ans);
        }
    }
        System.out.println(s3);
    }
}
