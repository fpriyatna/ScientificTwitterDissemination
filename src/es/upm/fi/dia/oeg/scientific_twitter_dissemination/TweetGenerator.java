package es.upm.fi.dia.oeg.scientific_twitter_dissemination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetGenerator {
	Collection<Document> documents;
	Twitter twitter = TwitterFactory.getSingleton();
	
	public TweetGenerator(Collection<Document> documents) {
		this.documents = documents;
	}
	
	private void generateTweet(Document document, Tag tag) throws TwitterException {
		String doi = document.doi;
		if(doi.startsWith("\"")) {
			doi = doi.replaceFirst("\"", "");
		}
		if(doi.endsWith("\"")) {
			doi = doi.substring(0, doi.length() - 1);
		}
		doi = "http://www.dx.doi.org/" + doi;
		
		String tagValue = tag.tagValue;
		tagValue = tagValue.replaceAll(" ", "_");
		
		if(tagValue.startsWith("\"")) {
			tagValue= tagValue.replaceFirst("\"", "");
		}
		if(tagValue.endsWith("\"")) {
			tagValue = tagValue.substring(0, tagValue.length() - 1);
		}
		
		String stringToTweet = doi + " #" + tagValue;
		//System.out.println(stringToTweet);
		Status status = twitter.updateStatus(stringToTweet);
		System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	private void generateTweet(Document document) {
		Collection<Tag> tagsToTweet = document.getTagsToTweet();
		if(tagsToTweet.size() > 0) {
			Tag firstTag = tagsToTweet.iterator().next();
			try {
				this.generateTweet(document, firstTag);
				//Thread.sleep(10800000);
				System.out.println("sleeping zzzz ....");
				TimeUnit.SECONDS.sleep(180);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			firstTag.numberTweeted++;
		}
	}
	
	public void generateTweet() {
		while(true) {
			try {
				Collection<Document> documentsToTweet = this.getDocumentsToTweet();
				if(documentsToTweet.size() == 0) {
					break;
				}
				
				for(Document document:documentsToTweet) {
					this.generateTweet(document);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public Collection<Document> getDocumentsToTweet() {
		Collection<Document> documentsToTweet = new HashSet<Document>();
		for(Document document:this.documents) {
			Collection<Tag> tagsToTweet = document.getTagsToTweet();
			if(tagsToTweet.size() > 0) {
				documentsToTweet.add(document);
			}
		}
		
		return documentsToTweet;
	}
	
	public static void main(String args[]) {
		Collection<String> alexTags = new ArrayList(Arrays.asList("hardworking", "funny", "hardworking"));
		Document alexDocument = new Document("Alex", alexTags);

		Collection<String> freddyTags = new ArrayList(Arrays.asList("friendly", "serious"));
		Document freddyDocument = new Document("Freddy", freddyTags);

		//Collection<Document> documents = new ArrayList(Arrays.asList(alexDocument, freddyDocument));
		String csvFile = "doitag.csv";
		CSVReader obj = new CSVReader();
		Collection<Document> documents = obj.load(csvFile);
		
		TweetGenerator tweetGenerator = new TweetGenerator(documents);
		tweetGenerator.generateTweet();
		
		System.out.println("Bye");
	}
}
