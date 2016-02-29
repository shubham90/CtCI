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
public class OnesComplement {
    public static String comp2(String input){
        StringBuilder output = new StringBuilder(input);
        int i;
        for(i= input.length()-1;i>=0;i--){
            char temp = input.charAt(i);
            if(temp=='1')
                output.setCharAt(i,'0');
            else{
                output.setCharAt(i,'1');
                break;
            }
        }
        String output1;
        if(i== -1){
            output1 = "1" + output.toString();
        }
        else{
            output1 = output.toString();
        }
        
        return output1;
    }
    
    public static String comp1(String input){
        StringBuilder output = new StringBuilder();
        for(int i=0;i<input.length();i++){
            char temp = input.charAt(i);
            if(temp == '0')
                output.append('1');
            else
                output.append('0');
        }   
        return output.toString();
    }
    public static void main(String args[]){
        String input = "00000";
        String output = comp1(input);
        System.out.println("Original Number:"  + input);
        System.out.println("");
        System.out.println("1s Complement- " + output);
        String output1 = comp2(output);
        System.out.println("");
        System.out.println("2s Complement- " + output1);
    }
}
