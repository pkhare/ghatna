package edu.learning.lucene;

import java.util.*;

class treeMap {
    public static void main(String[] args) {
        System.out.println("the main");
        
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("de",10);
        map.put("ab", 20);
        map.put("a",5);
		map.put("b",5);
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 17);
		map.put("E", 10);
		map.put("F", 7);
		map.put("G", 7);
		
	System.out.println(map);
	
	Iterator it = entriesSortedByValues(map).iterator();
	while (it.hasNext()) {
	  System.out.println(it.next());
	  Object obj = it.next();
	  
	 
	}
   // System.out.println(entriesSortedByValues(map));
    }
    
    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
					int res = e2.getValue().compareTo(e1.getValue());
					return res != 0 ? res : 1;
                    
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}