/*
Find the Kth element by using Partition.
 */
package Arrays;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class KthElement_Partition {
    public static int partition(int[] arr, int s, int n){
        if(arr==null)
            return -1;
        if(n-s < 1)
            return s;
        int pivot = n;
        int i=s-1;
        int j=s;
        int temp;
        while(j<pivot-1 && i<=j){
            if(arr[pivot] <= arr[j]){
                j++;
                continue;
            }
            if(arr[pivot] > arr[j]){
                i++;
                temp = arr[j];
                arr[i] = arr[j];
                arr[i] = temp;
                j++;       
            }
        }
        temp = arr[i+1];
        arr[i+1] = arr[pivot];
        arr[pivot] = temp;
        pivot = i+1;
        return pivot;
    }
    
    public static int k_small(int[] arr, int k){
        if(arr==null)
            return -1;
        if(k > arr.length-1)
            return -1;
        int low = 0;
        int high = arr.length -1;
        while(true){
            int l = partition(arr, low, high);
            if(k==l)
                return arr[high];
            else if(k>l)
                low = l+1;
            else
                high = l-1;
        }
    }
    
     public static void main(String args[]){
        System.out.println("Enter the size of integer Array: ");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        System.out.println("Enter the Array: ");       
        int[] arr= new int[size];
        for(int i=0; i<size;i++){
            arr[i] = s.nextInt();
        }
         System.out.println("Enter value of k");
         int k = s.nextInt();
        
        int sum = k_small(arr, k);
        System.out.println("Kth element: " + sum);        
    }
}
