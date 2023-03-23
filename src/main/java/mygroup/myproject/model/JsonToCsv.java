package mygroup.myproject.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonToCsv {

    public static void main(String[] args) throws IOException {

//// Read the JSON file
//File jsonFile = new File("jsonfiles/game.json");
//ObjectMapper objectMapper = new ObjectMapper();
//JsonNode rootNode = objectMapper.readTree(jsonFile);
//
//// Create the CSV file and header
//
//File csvFile = new File("resources/orderLines");
//
//System.out.println("rootnode: \n" + rootNode);
//
//
CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("playId", "gameId", "clockTime");
//FileWriter csvWriter = new FileWriter(csvFile);
//csvWriter.append("gameId,clockTime,playId").append("\n");
//
//// Iterate through the JSON data and write it to the CSV file
//
//Iterator<JsonNode> iterator = rootNode.iterator();
//while (iterator.hasNext()) {
//	JsonNode node = iterator.next();
//	System.out.println("Node: " + node.toPrettyString());
//	String game = node.get("gameId").asText();
//	String clock = node.get("clockTime").asText();
//	String play = node.get("playId").asText();
//	csvWriter.append(game).append(",").append(clock).append(",").append(play).append("\n");
//
//}
//csvWriter.flush();
//csvWriter.close();

        JsonNode jsonTree = new ObjectMapper().readTree(new File("jsonfiles/game.json"));

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema =  CsvSchema.builder().setUseHeader(true).setReorderColumns(true)
                .addColumn("playId").addColumn("gameId").addColumn("clockTime").build();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("resources/orderLines.csv"), jsonTree);

// Compare the CSV columns

        Map<String, String> map = new HashMap<>();
        File otherCsvFile = new File("resources/search_results_export - 2023-03-22T092241.316.csv");
        CSVParser otherCsvParser = new CSVParser(new FileReader(otherCsvFile), CSVFormat.DEFAULT);

        for (CSVRecord record : otherCsvParser) {
            map.put(record.get(0), record.get(1));

        }
        otherCsvParser.close();
        CSVParser csvParser = new CSVParser(new FileReader("resources/orderLines.csv"), csvFormat);
        Iterator<CSVRecord> csvIterator = csvParser.iterator();

        while (csvIterator.hasNext()) {
            CSVRecord record = csvIterator.next();
            String game = record.get("gameId");
            String clock = record.get("clockTime"); // this was description
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