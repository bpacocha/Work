package mygroup.myproject.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

public class ThesOne {
	public static void main(String[] args) throws IOException{
	 ObjectMapper objectMapper = new ObjectMapper();
	 
	 JsonNode jsonNode = objectMapper.readTree(new File("jsonfiles/game.json"));
	 
	 CSVWriter csvWriter = new CSVWriter(new FileWriter("resources/orderLines"));
	 
	 csvWriter.writeNext(new String[]{"playDescription", "clockTime"});
	 //JSONArray array = (JSONArray)((List) objectMapper).get("plays");
	 for (JsonNode node : jsonNode) {
		 //String playId = node.get("playId").asText();
		 String clock = node.get("clockTime").asText();
		 String pd = node.get("playDescription").asText();
		 
		 csvWriter.writeNext(new String[]{ clock, pd});
	 }
	 csvWriter.close();
}
	}




