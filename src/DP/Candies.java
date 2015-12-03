/*
Alice is a kindergarden teacher. She wants to give some candies to the children in her class. 
All the children sit in a line, and each  of them has a rating score according to his or her performance in the class. 
Alice wants to give at least 1 candy to each child. If two children sit next to each other, 
then the one with the higher rating must get more candies. Alice wants to save money, so she needs to minimize the total number of candies.

Input Format

The first line of the input is an integer N, the number of children in Alice's class. Each of the following N lines contains an integer that indicates the rating of each child.

1 <= N <= 105 
1 <= ratingi <= 105

Output Format

Output a single line containing the minimum number of candies Alice must buy.

Sample Input

3  
1  
2  
2
Sample Output

4
Explanation

Here 1, 2, 2 is the rating. Note that when two children have equal rating, they are allowed to have different number of candies.
Hence optimal distribution will be 1, 2, 1.
 */
package DP;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class Candies {
    public static int candies(int[] c , int n){
        if(n<1)
            return 0;
        int out =0;
        int[] temp = new int[n];
        for(int i=0;i<n;i++){
            temp[i]=1;
        }
        for(int i=1;i<n;i++){
            if(c[i-1]<c[i]){
                temp[i] = temp[i-1]+1;
            }
        }
        for(int i=n-1;i>0;i--){
            if(c[i-1]>c[i]){
                temp[i-1]= Math.max(temp[i-1] , temp[i]+1);
            }
        }
        for(int i=0;i<n;i++){
            out += temp[i];
        }
        return out;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] c = new int[n];
        for(int i=0;i<n;i++){
            c[i]=s.nextInt();
        }
        int out = candies(c,n);
        System.out.println(out);
    }
}
