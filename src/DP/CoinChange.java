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
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
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