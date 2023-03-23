package mygroup.myproject.model;

import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class Demo {
	
	public static void main(String[] args) throws IOException {



		String stringOfJSON = FileUtils.readFileToString(new File("jsonfiles/game.json"), StandardCharsets.UTF_8);

		//FileReader reader = new FileReader(".\\jsonfiles\\game.json");
		//loads json file
		//Object obj = jasonparser.parse(reader);
		//parse json file
		JSONObject gamejsonobj;
		try {
			gamejsonobj = new JSONObject(stringOfJSON);
			String gameId = gamejsonobj.optString("gameID", "defaultValue");
			//int pID = (int) gamejsonobj.get("playID");
			int playId = gamejsonobj.optInt("playId", 0);
			System.out.println("Game ID is :" + gameId);
			//System.out.println("Play ID:" + pID);
			JSONArray playsArray = gamejsonobj.optJSONArray("plays");
			;
			//JSONArray array = (JSONArray) gamejsonobj.get("plays");
			//this will read json object in array
			for(int i=0;i<playsArray.length();i++) {
				JSONObject plays = playsArray.getJSONObject(i);

				String clock = plays.getString("clockTime");
				Long playIdLong = plays.getLong("playId");





	    		/*writer.writeNext(new String[]{"plays", "clock", "gameId"});

	    		String[] record = new String[]{ clock, "test3"};
	    		writer.writeNext(record);
			*/
				System.out.println("Play " + i + " is " + playIdLong);
				System.out.println("Play Clock is: " + clock);
				//System.out.println("Play Clock is: "+clock);
			}
		} catch (JSONException e) {
			System.out.println("Exception: " + e);
		}
		}
		
	}

