package edu.learning.lucene;


import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;




/**
 *  
 * 
 */

public class TweetLucDocCreator {

	private Document tweetLucDoc = new Document();	

	public enum Fields {
		TweetMsg, LanguageCode;	
	}

	public void addTweetMsgField(String tweetMsg) {		
		Field tweetMsgField = new Field(Fields.TweetMsg.toString(), tweetMsg, Field.Store.YES, Field.Index.ANALYZED);		
		tweetLucDoc.add(tweetMsgField);	
	}
	
	public void addLanguageField(String languageCode) {
		Field languageField = new Field(Fields.LanguageCode.toString(), languageCode, Field.Store.YES, Field.Index.NOT_ANALYZED);
		tweetLucDoc.add(languageField);			
	}

	public Document getLucDoc() {		
		return tweetLucDoc;
	}
	
	public void reset(){
		tweetLucDoc = new Document();	
	}
	
}