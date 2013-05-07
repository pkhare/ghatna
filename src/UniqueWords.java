package edu.learning.lucene;

import java.util.HashSet;
import java.util.Set;

public class UniqueWords {
	
	private Set<String> uniqueWords = new HashSet<String>();
	
	public void addWord(String word) {
		uniqueWords.add(word);		
	}
	
	public Set<String> getUniqueWords() {
		return uniqueWords;
	}
	
//	public static void main (String[] args)
//	{
//		UniqueWords words = new UniqueWords();
//		
//        words.addWord("zyzzyva");
//        words.addWord("Dave");
//        words.addWord("Dave");
//        words.addWord("zyzzyva");
//        words.addWord("defenestrate");
//        words.addWord("1234");
//        Set<String> temp = words.getUniqueWords();
//        //System.out.println("Number of unique words: " + temp.size());
//       
//        
//        for(String word: temp)
//        {
//        	System.out.println("Word is: " + word);
//        }
//        
//	}
	
}
