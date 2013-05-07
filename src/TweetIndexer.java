package edu.learning.lucene;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

/**
 *  
 *  
 */

public class TweetIndexer {	
	private final double BUFFERRAMSIZE = 256.0;
	private String indexDirPath;
	private Indexer indexer;
	private UniqueWords unique = new UniqueWords();
		
	public TweetIndexer() throws IOException{		
		indexDirPath = "index";
		openWriter();
		createIndex();
	}

	private void openWriter() {		
		try {			
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36));
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);		
			Directory index = getIndex(indexDirPath);
			if(IndexReader.indexExists(index)) 
				//config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
				config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			config.setRAMBufferSizeMB(BUFFERRAMSIZE);
			indexer = new Indexer(config, index);		
		}
		catch (IOException e) {
			e.printStackTrace();
		}			
	}	

	private Directory getIndex(String indexPath) {
		Directory index = null;
		try {
			index = new SimpleFSDirectory(new File(indexPath));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return index;
	}
	
	/*Added later*/
	public static String cleanString(String queryString, Analyzer analyzer) {
		QueryParser queryParser = new QueryParser(Version.LUCENE_36, "", analyzer );			
		Query query;
		try {
			query = queryParser.parse(queryString);
			return query.toString("");			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void indexTweets(List<String> tweets) {
		TweetLucDocCreator lucDocCreator = new TweetLucDocCreator();
		
		/*Added later*/
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		
		for(String tweet : tweets) {
			StringTokenizer tokenizer = new StringTokenizer(tweet);
			while(tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				String stringtoken = cleanString(token, analyzer);
				unique.addWord(stringtoken);
			}
			lucDocCreator.addTweetMsgField(tweet);
			lucDocCreator.addLanguageField("en");
			indexer.addDoc(lucDocCreator.getLucDoc());
			lucDocCreator.reset();			
		}
		indexer.closeIndexer();
	}	

	public UniqueWords getUniqueWords(){
		return unique;
	}
	
	/*public static void main(String[] args) {	
		TweetIndexer indexer = new TweetIndexer();
		String tweet1 = "I am going to the market.";
		String tweet2 = "Market is near to university the university.";
		String tweet3 = "Students like to go the university";
		String tweet4 = "Right now now now";		
		List<String> tweets = new ArrayList<String>();
		tweets.add(tweet1);
		tweets.add(tweet2);
		tweets.add(tweet3);
		tweets.add(tweet4);
		indexer.indexTweets(tweets);
		System.out.println("Indexing Finished");
	}*/
	
	public void createIndex() throws IOException
	{
		//TweetIndexer indexer = new TweetIndexer();
		//FileReader r = new FileReader("C:/Users/Prashant/workspaces/tweeting/LuceneTest/tempFiles/dataB.tsv");
		
		FileReader r = new FileReader("E:/dataB.tsv");
		BufferedReader CSVFile = new BufferedReader(r);
		
		List<String> tweets = new ArrayList<String>();
		
		String dataRow = CSVFile.readLine();
		while (dataRow != null){
            String[] dataArray = dataRow.split("\t");
			int n = dataArray.length;
            //for (String item:dataArray) { System.out.print(item + "\t"); }
			//System.out.print(dataArray[n-1] + "\t"); 
            //System.out.println(); 
            
            tweets.add(dataArray[n-1]);
            
            dataRow = CSVFile.readLine(); 
        }
        // Close the file once all data has been read.
		
        CSVFile.close();
        
		/*String tweet1 = "I am going to the market.";
		String tweet2 = "Market is near to university the university.";
		String tweet3 = "Students like to go the university";
		String tweet4 = "Right now now now";	*/	
		
		/*tweets.add(tweet1);
		tweets.add(tweet2);
		tweets.add(tweet3);
		tweets.add(tweet4);*/
        
		indexTweets(tweets);
		System.out.println("Indexing Finished");
	}

}