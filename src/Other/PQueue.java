/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Shubham
 */

public class PQueue {
    public static void main(String args[]){
        PriorityQueue<Integer> p = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> ar = new ArrayList<>();
        p.add(5);
        p.add(6);
        p.add(1);
        p.add(3);
        p.add(65);
        
        ar.add(1);
        ar.add(5);
        ar.add(8);
        Collections.shuffle(ar);
        System.out.println(ar);

        Iterator<Integer> through = p.iterator() ;
        System.out.println(p.peek());
        while(through.hasNext() ) {
                System.out.print(through.next() + " ") ;
        }
        p.remove();
        System.out.println("After 1st remove"+p.peek());
        p.remove();
        System.out.println("After 2nd remove"+p.peek());
        p.remove();
        System.out.println("After 3rd remove"+p.peek());
        p.remove();
        System.out.println("After 4th remove"+p.peek());

        
    }
}
