package api.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.*;
//import org.json.simple.*;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SkillsHistogram {
	private String skills;
	private static String URL = 
	"http://api.linkedin.com/v1/people-search:(facets:(code,buckets:(code,name,count)))?facets=";//location,network&start=0&count=25&format=json";
	
	private JSONObject aJSON;
	private JSONObject bJSON;
	private String a;
	private String b;
	
	//private static final int TOTAL = 2;
	
	private Integer aTotal;
	private Integer bTotal;
	SkillsHistogram(String skills, String a, String b) {
		this.skills = skills;
		this.a = a;
		this.b = b;
	}
	/* public JSONObject readFile(String fileName) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {

	        Object obj = parser.parse(new FileReader(fileName));

	        jsonObject =  (JSONObject) obj;

	       //return jsonObject;

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return jsonObject;
	} */
	public void decode() throws JSONException {
		URL = URL+a+","+b+"&start=0&count=25&format=json"+"&keywords="+this.skills;
		JSONObject finalJSON = /*readFile("C:\\Users\\naikb\\Desktop\\json.txt");*/ScribeClient.getResponse(URL);
		JSONObject temp = finalJSON.getJSONObject("facets");
		JSONArray values = temp.getJSONArray("values");
		temp = values.getJSONObject(0);
		JSONObject location = temp.getJSONObject("buckets");
		setATotal(location.getInt("_total"));
		temp = values.getJSONObject(1);
		JSONObject degree = temp.getJSONObject("buckets");
		setBTotal(degree.getInt("_total"));
		
		setAJSON(location);
		setBJSON(degree);
		temp = values.getJSONObject(0);
		setA((String)temp.get("code"));
		temp = values.getJSONObject(1);
		setB((String)temp.get("code"));
		return;
	}
	public void setA(String a) {
		this.a = a;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getA() {
		return this.a;
	}
	public String getB() {
		return this.b;
	}
	public void setAJSON(JSONObject json)
	  {
	    this.aJSON = json;
	  }
	public void setBJSON(JSONObject json) {
		this.bJSON = json;
	}
	public void setATotal(Integer total) {
		this.aTotal = total;
	}
	public void setBTotal(Integer long1) {
		this.bTotal = long1;
	}
	public Integer getATotal() {
		return this.aTotal;
	}
	public Integer getBTotal() {
		return this.bTotal;
	}
	public JSONObject getAJSON() {
		return this.aJSON;
	}
	public JSONObject getBJSON() {
		return this.bJSON;
	}
	public static void main(String[] args) throws Exception {
		SkillsHistogram obj = new SkillsHistogram("java","location","network");
		obj.decode();
		System.out.println(obj.getAJSON().toString());
		System.out.println(obj.getBJSON().toString());
	}
}

