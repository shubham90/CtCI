    /*
Simple ArrayHopper Code 
 */
package Other;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class ArrayHopper {
    
    public static void main(String args[]){
        int[] input = {3};
        findHopsHelper(input);
        Scanner s= new Scanner(System.in);
       
    }
    	public static void findHopsHelper(int[] input) {
                int output_count =0;
		int min_index = 0, max_index = 0, prev_index = 0;
                int last_update =0;
		int i;
                if(input.length==0)
                    System.out.println("failure");
                if(input.length==1 && input[0]==0)
                    System.out.println("failure");
                else
                    output_count++;
                
		for (i = 0; i < input.length; i++) {
                    if (i > max_index) 
                        break;
                    else if (i > min_index) {
                            last_update = prev_index;
			min_index = max_index;
                        output_count += 1;
                    }
                        // update current reach
                    if (i + input[i] > max_index){
                        prev_index = i;
                        max_index = i + input[i];
                    }
		}
		if ((i > min_index) && output_count > 1 && last_update != prev_index) {
			// if i was beyond last reach and hops are present then add the last selected index
                        output_count += 1;
		}

		if (min_index < input.length - 1){   // if last reachable index was greater than or equal to last index of array
                    System.out.println("failure");
                        
                }
		else
                    System.out.println(output_count);
			
	}
}
