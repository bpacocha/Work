package mygroup.myproject.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Demo {
	
	public static void main(String[] args) throws IOException, ParseException {

      
		JSONParser jasonparser = new JSONParser();
		
		FileReader reader = new FileReader(".\\jsonfiles\\game.json");
		//loads json file
		Object obj = jasonparser.parse(reader);
		//parse json file
		JSONObject gamejsonobj = (JSONObject)obj; 
		
		String gameId =  (String) gamejsonobj.get("gameId");
		//int pID = (int) gamejsonobj.get("playID");
		System.out.println("Game ID is :" + gameId);
		//System.out.println("Play ID:" + pID);
	
;		JSONArray array = (JSONArray)gamejsonobj.get("plays");
		//this will read json object in array
		for(int i=0;i<array.size();i++)
		{
			JSONObject plays =(JSONObject) array.get(i);
			
			String clock = (String) plays.get("clockTime");
			Long playId = (Long)plays.get("playId");
		
		
		
		
		
	    		/*writer.writeNext(new String[]{"plays", "clock", "gameId"});
	    	  
	    		String[] record = new String[]{ clock, "test3"};
	    		writer.writeNext(record);
			*/
			System.out.println("Play " +i+ " is " + playId);
			System.out.println("Play Clock is: "+clock);
			//System.out.println("Play Clock is: "+clock);
		}
		
	}
}
