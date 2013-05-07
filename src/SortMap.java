package edu.learning.lucene;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class SortMap{

   /*public static void main(String[] args) {

    System.out.println("Unsort Map......");
    Map<String,Integer> unsortMap = new HashMap<String,Integer>();
    unsortMap.put("A", 1);
    unsortMap.put("B", 2);
    unsortMap.put("C", 3);
    unsortMap.put("D", 17);
    unsortMap.put("E", 10);
    unsortMap.put("F", 7);
    unsortMap.put("G", 7);

    Iterator iterator=unsortMap.entrySet().iterator();

        for (Map.Entry entry : unsortMap.entrySet()) {
            System.out.println("Key : " + entry.getKey() 
                + " Value : " + entry.getValue());
        }

        System.out.println("Sorted Map......");
        Map<String,Integer> sortedMap =  sortByComparator(unsortMap);

        for (Map.Entry entry : sortedMap.entrySet()) {
            System.out.println("Key : " + entry.getKey() 
                + " Value : " + entry.getValue());
        }
   }*/

   public static Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        //sort list based on comparator
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o2)).getValue())
               .compareTo(((Map.Entry) (o1)).getValue());
             }
    });

        //put sorted list into map again
    Map sortedMap = new LinkedHashMap();
    for (Iterator it = list.iterator(); it.hasNext();) {
         Map.Entry entry = (Map.Entry)it.next();
         sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
   }    
}