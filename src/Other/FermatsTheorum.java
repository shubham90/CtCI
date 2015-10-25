/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Finding if a number is prime or not. */

package Other;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class FermatsTheorum {
    
    public void fermat(int n){
        Random rand=new Random();
        for(int i=1; i<5 ;i++){
            int k=rand.nextInt(4) + 1;
            int m=n-1;
            long z= (long)(Math.pow(k, m));
            int x= (int) (z % n);
            if(x!=1){
                System.out.println("This number is not prime");
                return;
            }
        }
        System.out.println("This number is prime");
    }
    
    
    public static void main(String args[]){
        FermatsTheorum f= new FermatsTheorum();
        System.out.println("Enter Number to check if it is Prime:");
        Scanner s= new Scanner(System.in);
        int i=s.nextInt();
        f.fermat(i);
    }
    
}
