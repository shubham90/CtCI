/*
Given a string you need to print longest possible substring that has exactly M unique characters. If there are more than one substring of longest possible length, then print any one of them.

Examples:

"aabbcc", k = 1
Max substring can be any one from {"aa" , "bb" , "cc"}.

"aabbcc", k = 2
Max substring can be any one from {"aabb" , "bbcc"}.

"aabbcc", k = 3
There are substrings with exactly 3 unique characters
{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
Max is "aabbcc" with length 6.

"aaabbb", k = 3
There are only two unique characters, thus show error message. 
 */
package Strings;

/**
 *
 * @author Shubham
 */
public class SubsKUniqueChar {
    
    public static boolean isKUnique(int[] input, int k){
        int temp=0;
        for(int i=0;i<input.length;i++){
            if(input[i]>0)
                temp++;  
        }
        return k>=temp;  
    }
    
    public static String kUnique(String str, int k){
        int[] store = new int[256];
        for(int i=0;i<str.length();i++){
            store[str.charAt(i) - 'a'] += 1;
        }
        if(isKUnique(store, k)){
            return "The K value is less than unique characters in String.";
        }
        
        int max_start =0;
        int max_size =0;
        int curr_start = 0;
        int curr_end = 0;
        
        store = new int[256];
        store[str.charAt(0)-'a']++;
        
        for(int j =1;j<str.length();j++){
           store[str.charAt(j)-'a']++; 
           curr_end++;
           
           while(!isKUnique(store, k)){
               store[str.charAt(curr_start)-'a']--;
               curr_start++;
           }
           
           if(curr_end-curr_start+1 > max_size){
               max_start= curr_start;
               max_size = curr_end-curr_start+1;
           }
        }
        
        return str.substring(max_start, max_start+max_size);

    }
    public static void main(String args[]){
        String input = "aabacbebebe";
        int k = 3;
        String output = kUnique(input, k);
        System.out.println("Maximum Substring with K unique characters is:"
                + " " + output);
    }
}
