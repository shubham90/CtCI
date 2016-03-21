/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Arrays;

/**
 *
 * @author Shubham
 */
public class Palantir {
    public static int getMinTimeDifference(String[] times){
        int len = times.length;
        int output = 24*60;
        int[] minutes = new int[len];
        for(int i=0;i<len;i++){
            String[] min = times[i].split(":");
            int time = Integer.valueOf(min[0])*60 + Integer.valueOf(min[1]);
            minutes[i] = time;
        }
        
        Arrays.sort(minutes);
        
        for(int i=0;i<len;i++){
            int j = (len+i-1)%len;
            int current_time = minutes[i];
            int last_time = minutes[j];
            
            int current_diff = 0;
            int lag = Math.abs(current_time-last_time);
            
            if(lag<1440-lag){
                current_diff = lag;
            }
            else{
                current_diff = 1440-lag;
            }
            if(current_diff < output){
                output = current_diff;
            }
        }
        
        return output;
    }
    public static void main(String args[]){
        String[] input = {"10:00", "19:20", "06:45", "00:12", "23:50", "04:22"};
        int output = getMinTimeDifference(input);
        System.out.println(output);
    }
}
