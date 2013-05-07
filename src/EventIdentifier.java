package edu.learning.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.Version;

public class EventIdentifier {
	

	public static void main(String[] args) throws IOException {
		
		UniqueWords unq;
				
		TweetIndexer twtIndx = new TweetIndexer();
		unq = twtIndx.getUniqueWords();
		Set<String> temp  = unq.getUniqueWords();
		
		String indexPath = "index";
		Searcher searcher = new Searcher(indexPath);
		
		Map <String, Integer>termFrequencymap = new HashMap<String, Integer>();
		//Map<String, Integer>termFrequencymap = new TreeMap<String, Integer>();
		
		for(String word: temp)
		{	        	
			System.out.println("Word is: " + word);
			if (word != null && !word.isEmpty())
			{
				TopScoreDocCollector docCollector = searcher.search(word, 8, TweetLucDocCreator.Fields.TweetMsg.toString(), new StandardAnalyzer(Version.LUCENE_36));
				
				TopDocs topDocs = docCollector.topDocs();
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				
				int scoreDocsLength = scoreDocs.length;
				termFrequencymap.put(word, scoreDocsLength);
			}						
			
        }		
		//to be removed
		
		searcher.closeIndex();
		
		SortMap sortMap = new SortMap();
		Map<String,Integer> sortedMap =  sortMap.sortByComparator(termFrequencymap);
		
		try
		{
			//Set<String> keys = termFrequencymap.keySet();
			Set<String> keys = sortedMap.keySet();
			int count = 0;
			String queryStr = "";
			for(String key : keys) 
			{				
				if (count >= 5) break;
				int value = (int)termFrequencymap.get(key);
				System.out.println("Word:"+key+"  Frequency:"+value);
				queryStr = queryStr+" "+key;
				count++;
			}
			
			System.out.println("String is : "+queryStr);
			
			TweetSearcher tweetSearcher = new TweetSearcher();
			tweetSearcher.searchTweets(queryStr);
			
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
}
