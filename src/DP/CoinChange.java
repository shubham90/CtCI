/*
Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
how many ways can we make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For N = 10 and S = {2, 5, 3, 6}, 
there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.
*/
package DP;

import java.util.Scanner;

public class CoinChange {
    public static long coin_change(int n, int m, int[] coins){
        if(n<1){
            return 0;
        }
        if(m<1){
            return 0;
        }
        long[] temp = new long[n+1];
        temp[0]=1;
        for(int i=0;i<m;i++){
            for(int j=coins[i];j<=n;j++){
                temp[j] += temp[j-coins[i]];
        }
        }
        return temp[n];
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int m=s.nextInt();
        int[] coins = new int[m];
        for(int i=0;i<m;i++){
            coins[i]=s.nextInt();
        }
        long out = coin_change(n,m,coins);
        System.out.println(out);
    }
}