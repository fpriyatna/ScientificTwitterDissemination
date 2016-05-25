package es.upm.fi.dia.oeg.scientific_twitter_dissemination;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CSVReader {

	public static void main(String[] args) {
		String csvFile = "doitag.csv";
		CSVReader obj = new CSVReader();
		Collection<Document> documents = obj.load(csvFile);
		System.out.println("Done");

	}

	public Collection<Document> load(String csvFile) {
		Collection<Document> documents = new HashSet<Document>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Map<String, Collection<String>> mapDoiTags = new HashMap<String, Collection<String>>();
		try {

			Map<String, String> maps = new HashMap<String, String>();

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] doiTag = line.split(cvsSplitBy);
				String doi = doiTag[0];
				String tagValue = doiTag[1];
				Collection<String> tagsValues = mapDoiTags.get(doi);
				if(tagsValues == null) {
					tagsValues = new HashSet<String>();
					mapDoiTags.put(doi, tagsValues);
				}
				tagsValues.add(tagValue);
			}

			//loop map
			for (Map.Entry<String, Collection<String>> entry : mapDoiTags.entrySet()) {
				String doi = entry.getKey();
				Collection<String> tagsValues = entry.getValue();

				Document document = new Document(doi, tagsValues);
				System.out.println("document = " + document);

				documents.add(document);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return documents;

	}

}
