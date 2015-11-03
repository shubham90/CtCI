/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class SortReverse {
    public static void main(String args[]){
    Scanner s = new Scanner(System.in);
        int length = s.nextInt();    
        Integer[] arr= new Integer[length];
        for(int i=0; i<length;i++){
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr, Collections.reverseOrder());
        for(int i=0; i<length;i++){
            System.out.println(arr[i]);
        }
     
    }
}
