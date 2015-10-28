/*
The following is a description of the instance of this famous puzzle involving n=2 eggs and a building with k=36 floors.

Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from, and which will cause the eggs to break on landing. We make a few assumptions:

…..An egg that survives a fall can be used again.
…..A broken egg must be discarded.
…..The effect of a fall is the same for all eggs.
…..If an egg breaks when dropped, then it would break if dropped from a higher floor.
…..If an egg survives a fall then it would survive a shorter fall.
…..It is not ruled out that the first-floor windows break eggs, nor is it ruled out that the 36th-floor do not cause an egg to break.

If only one egg is available and we wish to be sure of obtaining the right result, the experiment can be carried out in only one way. 
Drop the egg from the first-floor window; if it survives, drop it from the second floor window. Continue upward until it breaks. 
In the worst case, this method may require 36 droppings. Suppose 2 eggs are available. 
What is the least number of egg-droppings that is guaranteed to work in all cases?
 */
package DP;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class EggProblem {
    public static void eggpr(int e, int f){
       int[][] store=new int[e+1][f+1];
       int temp;
       int[] floor = new int[f*f];
       int q=0;
       for (int i = 0; i < e; i++)
        {
        store[i][1] = 1;
        store[i][0] = 0;
        }
       
        for (int j = 0; j < f; j++)
        store[1][j] = j;
        
        for(int i=2;i<=e;i++){
            for (int j = 2; j<=f; j++){
                store[i][j] = 9999;
                for (int x = 1; x <= j; x++)
                {
                    temp = 1 + Math.max(store[i-1][x-1], store[i][j-x]);
                    if (temp < store[i][j]){
                        store[i][j] = temp;
                        floor[q]=j;
                        q++;
                    }
                }
            }
        }
        for(int y=0;y<=e;y++){
            System.out.println();
            System.out.println();
            for(int z=0;z<=f;z++)
                System.out.print(store[y][z] + " ");
        }
        System.out.println();
        System.out.println(store[e][f]);
     
    }
    public static void main(String args[]){
        System.out.println("Enter the number of eggs");
        Scanner s = new Scanner(System.in);
        int egg = s.nextInt();
        System.out.println("Enter Floors: ");
        int floor = s.nextInt();
        eggpr(egg, floor);
    }
}
