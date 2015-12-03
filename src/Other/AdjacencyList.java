/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class AdjacencyList {
    
    LinkedList<Integer>[] adlist;
    
    public void createList(){
        System.out.println("Enter the number of vertex");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        adlist = new LinkedList[size];
        for(int i=0; i<size;i++){
            System.out.println("Enter the number of connection to " + i);
            Scanner s1= new Scanner(System.in);
            int edge = s1.nextInt();
            System.out.println("Enter the vertex connected to node " + i);
            adlist[i] = new LinkedList<>();
            s1= new Scanner(System.in);
            for(int j=0;j<edge;j++){
                adlist[i].add(s1.nextInt());
            }
        }
    }
    
    public void printList(){
        for(int i =0; i<adlist.length;i++){
            System.out.println(i + "-->" + adlist[i]);
        }
    }
    
    public int numberOfVertices(){
        return(adlist.length);
    }
    
    public void bfs(){
        int[] visited = new int[adlist.length];
        LinkedList<Integer> sol = new LinkedList<>();
        Queue q = new LinkedList<>();
        
        for(int i=0;i<adlist.length;i++){
            if(visited[i]==0){
                q.add(i);
            }
            while(!q.isEmpty()){
                int x = (int)q.poll();
                if(visited[x]!=0)
                    continue;
                visited[x]=1;
                sol.add(x);
                LinkedList<Integer> l = adlist[x];
                for(int j=0; j<l.size();j++){
                    int neighbour = (int)l.get(j);
                    q.add(neighbour);
                }
            }
        }
        System.out.println("BFS TRAVERSAL: " + sol);
    }
    public static void main(String args[]){
        System.out.println("------------------Adjacency List Code-----------------");
        AdjacencyList al = new AdjacencyList();
        al.createList();
        al.printList();
        System.out.println("Number of vertices in the array is : " + al.numberOfVertices());
        System.out.println("-------------------BFS-----------------------");
        al.bfs();
    }
}
