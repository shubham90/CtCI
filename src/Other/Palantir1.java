/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Shubham
 */
public class Palantir1 {
 public static void stringSort(String[] times){
        int len = times.length;
        Map<Integer, String> output = new TreeMap<>();
        Map<String, String[]> store = new HashMap<>();
        
        int last_time  = 0 ;
        for(int i=0;i<len;i++){
            String name = times[i].split("\\|")[0];
            int amount = Integer.valueOf(times[i].split("\\|")[1]);       
            String place = times[i].split("\\|")[2];
            int time = Integer.valueOf(times[i].split("\\|")[3]);
            if(amount > 3000){
                if(!output.containsValue(name)){
                    output.put(time, name);
                }
                continue;
            }
            if(time > last_time+60){
                last_time = time;
            }
            if(!store.containsKey(name) && !output.containsValue(name)){
                String[] outp = {String.valueOf(time), place};
                store.put(name, outp);
            }else if(!output.containsValue(name)){
                String[] outp = store.get(name);
                int timeOld = Integer.valueOf(outp[0]);
                int difference =0;
                if(Math.abs(time-timeOld)<1440-Math.abs(time-timeOld)){
                    difference = Math.abs(time-timeOld);
                }
                else{
                    difference = 1440 - Math.abs(time-timeOld);
                }
                if(difference > 60){
                    outp[0] = String.valueOf(time);
                    outp[1] = place;
                    store.put(name, outp);                 
                }else{
                    if(outp[1].equals(place)){
                        outp[0] = String.valueOf(time);
                        store.put(name, outp);
                    }else{
                        output.put(timeOld, name);
                    }
                }
            }
            
        }
        for(String s: output.values()){
            System.out.println(s);
        }
    }
    public static void main(String args[]){
        String[] input = {"Shilpa|500|California|63", "Krasi|9000|California|1230", "Tom|25|New York|1235", "Tom|25|New York|1238", "Shilpa|50|Michigan|1300","Matt|90000|Georgia|1305","Krasi|49|Florida|1320", "Shilpa|49|Florida|1350"};
        stringSort(input);
    }
}
