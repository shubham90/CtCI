/*
Given a sequence of matrices, find the most efficient way to multiply these matrices together. 
The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 */
package DP;

/**
 *
 * @author Shubham
 */
public class MatrixMulEff {
    
    
    public static void efficient_mul(int[] arr, int n){
    int[][] m = new int[n][n];
 
    int i, j, k, L, q;
 
    // cost is zero when multiplying one matrix.
    for (i = 0; i < n; i++)
        m[i][i] = 0;
 
    // L is chain length.  
    for (L=2; L<n; L++)   
    {
        for (i=1; i<n-L+1; i++)
        {
            j = i+L-1;
            m[i][j] = 999999;
            for (k=i; k<j; k++)
            {
                // q = cost/scalar multiplications
                q = m[i][k] + m[k+1][j] + arr[i-1]*arr[k]*arr[j];
                if (q < m[i][j])
                    m[i][j] = q;
            }
        }
    }
 
    System.out.print(m[1][n-1]);
}
    
    public static void main(String args[]){
        int[] arr= {10,20,30,40};
        efficient_mul(arr, arr.length);
    }
w}
