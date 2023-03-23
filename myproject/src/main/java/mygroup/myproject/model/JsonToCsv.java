package mygroup.myproject.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToCsv {

public static void main(String[] args) throws IOException {

// Read the JSON file
File jsonFile = new File(".\\jsonfiles\\game.json");
ObjectMapper objectMapper = new ObjectMapper();
JsonNode rootNode = objectMapper.readTree(jsonFile);

// Create the CSV file and header

File csvFile = new File("C:\\Users\\bryson.Pacocha\\Documents\\brysons_Workspace\\myproject\\resources\\orderLines");
@SuppressWarnings("deprecation")

CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("playId", "gameId", "clockTime");
FileWriter csvWriter = new FileWriter(csvFile);
csvWriter.append("gameId,clockTime,playId").append("\n");

// Iterate through the JSON data and write it to the CSV file

Iterator<JsonNode> iterator = rootNode.iterator();
while (iterator.hasNext()) {
	JsonNode node = iterator.next();
	String game = node.get("gameId").asText();
	String clock = node.get("clockTime").asText();
	String play = node.get("playId").asText();
	csvWriter.append(game).append(",").append(clock).append(",").append(play).append("\n");

}
csvWriter.flush();
csvWriter.close();

// Compare the CSV columns

Map<String, String> map = new HashMap<>();
File otherCsvFile = new File("/myproject/resources/search_results_export - 2023-03-22T092241.316.csv");
CSVParser otherCsvParser = new CSVParser(new FileReader(otherCsvFile), CSVFormat.DEFAULT);

for (CSVRecord record : otherCsvParser) {
	map.put(record.get(0), record.get(1));

}
otherCsvParser.close();
CSVParser csvParser = new CSVParser(new FileReader(csvFile), csvFormat);
Iterator<CSVRecord> csvIterator = csvParser.iterator();

while (csvIterator.hasNext()) {
	CSVRecord record = csvIterator.next();
	String game = record.get("gameId");
	String clock = record.get("description");
	String expectedPlay = map.get(game);
	
if (expectedPlay != null && !clock.equals(expectedPlay)) {
	System.out.println("Mismatch for Playid " + game);
	System.out.println("Actual description: " + clock);
	System.out.println("Expected description: " + expectedPlay);

	}

}
csvParser.close();

	}

}