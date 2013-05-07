package edu.learning.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.Version;

public class TweetSearcher {

	/*public static void main(String[] args) {
		String indexPath = "index";
		Searcher searcher = new Searcher(indexPath);
		TopScoreDocCollector docCollector = searcher.search("Students right university", 8, TweetLucDocCreator.Fields.TweetMsg.toString(), new StandardAnalyzer(Version.LUCENE_36));
		TopDocs topDocs = docCollector.topDocs();
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		for(ScoreDoc scoredDoc : scoreDocs) {		
			float score = scoredDoc.score;
			int docID = scoredDoc.doc;
			Document document = searcher.getDocumentWithDocID(docID);
			String tweet = document.get(TweetLucDocCreator.Fields.TweetMsg.toString());
			System.out.println(tweet + "\t" + score);					
		}
		searcher.closeIndex();
	}*/
	
	public void searchTweets(String queryString) {
		String indexPath = "index";
		Searcher searcher = new Searcher(indexPath);
		//TopScoreDocCollector docCollector = searcher.search("Students right university", 8, TweetLucDocCreator.Fields.TweetMsg.toString(), new StandardAnalyzer(Version.LUCENE_36));
		TopScoreDocCollector docCollector = searcher.search(queryString, 8, TweetLucDocCreator.Fields.TweetMsg.toString(), new StandardAnalyzer(Version.LUCENE_36));
		TopDocs topDocs = docCollector.topDocs();
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		for(ScoreDoc scoredDoc : scoreDocs) {		
			float score = scoredDoc.score;
			int docID = scoredDoc.doc;
			Document document = searcher.getDocumentWithDocID(docID);
			String tweet = document.get(TweetLucDocCreator.Fields.TweetMsg.toString());
			System.out.println(tweet + "\t" + score);					
		}
		searcher.closeIndex();
		
	}
}
