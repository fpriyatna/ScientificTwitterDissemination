package es.upm.fi.dia.oeg.scientific_twitter_dissemination;

public class Tag {
	String tagValue;
	int numberTweeted = 0;
	
	public Tag(String tagValue) {
		this.tagValue = tagValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagValue == null) ? 0 : tagValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagValue == null) {
			if (other.tagValue != null)
				return false;
		} else if (!tagValue.equals(other.tagValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [tagValue=" + tagValue + ", numberTweeted=" + numberTweeted + "]";
	}
	

	
}
