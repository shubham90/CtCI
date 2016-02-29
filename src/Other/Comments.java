/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shubham
 */
// This is the main Progarm
// I am shubham
public class Comments {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder buffer = new StringBuilder();
        String line=null;
        while((line = br.readLine())!=null){

            buffer.append(line).append("\n");
        }
        System.out.println("I am here3");
        Pattern commentsPat = Pattern.compile("\\/\\/.*?(?=\\n)|\\/\\*.*?\\*\\/", 
            Pattern.DOTALL | Pattern.MULTILINE);
        Matcher commentsMat = commentsPat.matcher(buffer.toString());
        StringBuilder output = new StringBuilder();
        while (commentsMat.find()) {
            output.append(commentsMat.group()).append("\n");
        }
        System.out.println(output);
    }
}
