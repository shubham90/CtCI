/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shubham
 */
public class DomainNames {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Pattern domainPat = Pattern.compile(//Http
        "(?:https?://)"
        +"(?<domain>[-a-z0-9+&@#/%?=~_]{1,63}(?:\\.[-a-z0-9_~]{1,63}){1,126})"
        +"(?:/[^\\s?]*)?"
        +"(?:\\?[-a-z0-9+&@#/%?=~_]+=[-a-z0-9+&@#/%?=~_]*(?:&[-a-z0-9+&@#/%?=~_]+=[-a-z0-9+&@#/%?=~_]*)*)?",    
        Pattern.CASE_INSENSITIVE);
        Matcher domainMat = domainPat.matcher("");
        Set<String> store = new TreeSet<>();
        
        while(n>0){
            String line = s.nextLine();
            domainMat.reset(line);
            while(domainMat.find()){
                String dom = domainMat.group("domain").toLowerCase();
                if(dom.startsWith("www.") || dom.startsWith("ww2.") || dom.startsWith("web.")){
                    dom = dom.substring(4);
                }
                store.add(dom);
            }
            n--;
        }
        
        int flag = 0;
        for(String temp: store){
            if(flag==0){
                System.out.print(temp);
                flag =1;
            }
            else{
                System.out.print(";"+temp);
            }
        }
    }
}
