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
public class BinarySearch {
    public int binSearch(int a[],int i, int j, int n){
        if(i>j){
            return -1; 
        }
        int mid= (i+j)/2;
        if(a[mid]==n){
            return mid;
        }else if(n>a[mid]){
            return binSearch(a , mid+1 , j , n);
        }else 
            return binSearch(a , 0 , mid , n);
    }
    
    
    public static void main(String args[]){
        BinarySearch b = new BinarySearch();
        System.out.println("Please Enter the array size:");
        Scanner s=new Scanner(System.in);
        int n= s.nextInt();
        int[] arr=new int[n];
        System.out.println("Enter the elements of array: ");
        for(int i=0; i<n; i++){
            Scanner s1=new Scanner(System.in);
            arr[i]=s1.nextInt();
        }
        System.out.println("Please Enter element to search: ");
        Scanner s2=new Scanner(System.in);
        int n1= s2.nextInt();
        int k = arr.length-1;
        int ans= b.binSearch(arr,0,k,n1);
        System.out.println("Position of the element: " + ans);
    }
}
