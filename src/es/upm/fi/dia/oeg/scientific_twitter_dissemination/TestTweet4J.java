package es.upm.fi.dia.oeg.scientific_twitter_dissemination;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TestTweet4J {

	public static void main(String[] args) {
		String latestStatus = "Hello World!";
		Twitter twitter = TwitterFactory.getSingleton();
	    Status status;
		try {
			status = twitter.updateStatus(latestStatus);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	}

}
