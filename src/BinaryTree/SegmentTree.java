/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author Shubham
 */
public class SegmentTree {
    int[] st;
    
    SegmentTree(int[] arr, int n){
        int height = (int)(Math.ceil(Math.log(n)/Math.log(2)));
        int total_nodes = (int)(2* (Math.pow(2, height)) - 1);
        this.st = new int[total_nodes];
        constructTree(arr, 0, n-1, 0);
    }
    
    // Method to contruct a segment tree from an array
    public int constructTree(int[] arr, int start, int end, int st_index){
        if(start==end){
            st[st_index] = arr[start];
            return st[st_index];
        }
        int mid = start + (end-start)/2;
        st[st_index] = constructTree(arr, start, mid, 2*st_index + 1)
                + constructTree(arr, mid+1, end, 2*st_index + 2);
        return st[st_index];
    }
    
    public int getSum(int n , int start, int end){
        return 0;
    }
    
    public static void main(String args[]){
        int[] arr = {1,3,5,7,9,11};
        int n = arr.length;
        SegmentTree tree = new SegmentTree(arr, n);
        for(int i=0;i<tree.st.length;i++){
            System.out.print(tree.st[i] + ",");
        }
        
        int sum = tree.getSum(n, 1 , 3);
    }
}
