    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Shubham
 */
public class VersionNumber implements Comparator{

    public static void main(String args[]){
        List versionList = new ArrayList<>();
        versionList.add("3.2.5");
        versionList.add("6.1.01");
        versionList.add("3.1.9");
        versionList.add("3.2.10");
        versionList.add("3.3");
        versionList.add("4.1.10");
        Collections.sort(versionList, new VersionNumber());
        System.out.println(versionList);
    }

    @Override
    public int compare(Object o1, Object o2) {
            if (o1.equals(o2)) {
        return 0;
    } else {
        String[] verArray1 = getVersionAsArray(o1.toString());
        String[] verArray2 = getVersionAsArray(o2.toString());
 
        if (verArray1.length > verArray2.length) {
 
            return compareValues(verArray1, verArray2);
        }
        return compareValues(verArray2, verArray1);
    }
    }
    
    private Integer compareValues(String[] array1, String[] array2) {
        Integer v1;
        Integer v2;
        for (int i = 0; i < array1.length; i++) {
            v1 = Integer.parseInt(array1[i]);
            v2 = Integer.parseInt(array2[i]);
 
            if (!Objects.equals(v1, v2)) {
                return v2-v1;
            }
        }
 
        return 0;
    }
    
    public static String[] getVersionAsArray(String s1){
        return s1.split("\\.");
    }

}
