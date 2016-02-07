/*
Code to remove space from a sentence
 */
package Strings;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class RemoveSpaceFromString {
    public static void main(String args[]){
        System.out.println("Enter the string to be trimmed-");
        Scanner s=new Scanner(System.in);
        String input=s.nextLine();
        int j=0;
        StringBuilder st= new StringBuilder();
        for(int i=0; i<input.length();i++){
            char c=input.charAt(i);
            if(c==' ' && st.length()!=0){
                if(st.charAt(j-1)==' ')
                continue;
                else{
                    st.append(c);
                    j++;
                }
            }
            else if(c==' ' && st.length()==0){
                continue;
            }
            else{
                st.append(c);
                j++;
            }

        }
        System.out.println(st.toString());
    }
}
