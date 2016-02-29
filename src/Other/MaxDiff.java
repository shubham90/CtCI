/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 *
 * @author Shubham
 */
public class MaxDiff {
    public static int max_diff(int[] input){
        int diff = 0;
        int len = input.length;
        int index = input[len-1];
        
        for(int i=len-2;i>=0;i--){
            if(input[i]>index){
                index = input[i];
            }
            else{
                int local_diff = index - input[i];
                if(local_diff>diff)
                    diff = local_diff;
            }
        }
        return diff;
    }
    
    public static void main(String args[]){
        int[] inp = {7,2,3,10,2,4,8,1};
        int output = max_diff(inp);
        System.out.println(output);
    }
}
