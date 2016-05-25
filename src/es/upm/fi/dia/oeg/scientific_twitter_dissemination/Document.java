package es.upm.fi.dia.oeg.scientific_twitter_dissemination;

import java.util.Collection;
import java.util.HashSet;

public class Document {
	String doi;
	Collection<Tag> tags;

	public Document(String doi, Collection<String> tagsValues) {
		this.doi = doi;
		this.tags = new HashSet<Tag>();
		if(tagsValues != null && tagsValues.size() > 0) {
			for(String tagValue : tagsValues) {
				Tag tag = new Tag(tagValue);
				this.tags.add(tag);
			}
		}
	}
	
	
	Collection<Tag> getTagsToTweet() {
		Collection<Tag> tagsToTweet = new HashSet<Tag>();
		for(Tag tag:tags) {
			if(tag.numberTweeted == 0) {
				tagsToTweet.add(tag);
			}
		}
		return tagsToTweet;
	}


	@Override
	public String toString() {
		return "Document [doi=" + doi + ", tags=" + tags + "]";
	}
	
	
}
