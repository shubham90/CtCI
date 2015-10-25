/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class MergeSort {
    static int[] arr;
    public static void merge(int low, int mid, int high){
        int[] temp = new int[high-low+1];
        int i=low;
        int k = 0;
        int j = mid+1;
        
        while(i<=mid && j<=high){
            if(arr[i] >= arr[j]){
                temp[k] = arr[j];
                j++;
            }
            else if(arr[i]<arr[j]){
                temp[k] = arr[i];
                i++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            k++;
            i++;
        }
        while (j <= high) {
            temp[k] = arr[j];
            k++;
            j++;
        }
        for(int m = low, l=0;m<=high;m++,l++){
            arr[m] = temp[l];
        }
        
    }
    
    public static void merge_sort(int low, int high){
        if(high <= low)
            return;
        int mid = (high + low)/2;
        merge_sort(low, mid);
        merge_sort(mid+1, high);
        merge(low, mid, high);
    }
    
    public static void main(String args[]){
        System.out.println("Please Enter the array size:");
        Scanner s=new Scanner(System.in);
        int n= s.nextInt();
        arr=new int[n];
        System.out.println("Enter the elements of array: ");
        for(int i=0; i<n; i++){
            Scanner s1=new Scanner(System.in);
            arr[i]=s1.nextInt();
        }
        
        merge_sort(0, n-1);
        for(int i=0; i<n; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
