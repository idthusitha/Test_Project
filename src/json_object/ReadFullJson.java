package json_object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReadFullJson {

	public static void main(String[] args) {
		String fileName = "/rezsystem/workspace_java8/Test/src/abc.json";
		String text = readFile(fileName);

		decarateJSON(text);

	}

	private static void decarateJSON(String text) {
		
		text = text.replace("\\[[0-9]\\]", "").replace("\\[[0-9][0-9]\\]", "");
//		System.out.println(text);
//		
//		JsonParser jsonParser = new JsonParser();		
//		JsonElement element = jsonParser.parse(text);
//		
//		System.out.println(element.toString());
		
		JSONObject json = JSONObject.fromObject(text.replace("\t", ""));
		
		HashMap<String, JSONModel> fullMap = new HashMap<String, JSONModel>();
		//System.out.println(json.toString());
		jsonToPojoConverter(json, fullMap);

		for (String key : fullMap.keySet()) {
			JSONModel jsonModel = fullMap.get(key);

			System.out.println(jsonModel.getClassName() + "===================>");
			System.out.println(jsonModel.getJsonArray().toString());
			System.out.println("");
		}
	}
	static String referanceClassValue = "";
	public static void jsonToPojoConverter(JSONObject json, HashMap<String, JSONModel> fullMap) {
		//System.out.println("bbbbbbbbbbbbbbbbbb"+json.toString());
		
		Iterator<?> keys = json.keys();
		String referanceClassName = "DEFAULT";
		
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = json.get(key).toString();
			JSONModel jsonModel = new JSONModel();

			
			
			if (key.startsWith("@")) {
				//System.out.println(key);
				
				jsonModel = new JSONModel();
				jsonModel.setClassName(key.replace("@", ""));
				jsonModel.setId(isNumeric(value) ? Integer.parseInt(value) : null);
				jsonModel.setJsonValue(value);
				referanceClassName = key.replace("@", "");
				referanceClassValue = value;
			}	
			

			if (fullMap.containsKey(referanceClassName)) {
				jsonModel = fullMap.get(referanceClassName);
			}

			if (json.get(key) instanceof JSONObject) {				
				jsonToPojoConverter(JSONObject.fromObject(value), fullMap);		

			} else if (json.get(key) instanceof JSONArray) {
				
				if("setDepositPaymentSv".equals(key)){
					//System.out.println("bbbbbbbbbbbbbbbbbb"+json.get(key).toString());
				}
				try {
					String jsonString = json.getString(key);
					
					if(!(jsonString.matches("\\[\\]") || jsonString.matches("\\[[0-9]\\]") || jsonString.matches("\\[[0-9][0-9]\\]"))){
												
						JSONArray array = JSONArray.fromObject(jsonString);
						for (int i = 0; i < array.size(); i++) {
							String jsonStringTemp = array.getString(i);
							
							if(!(jsonStringTemp.matches("[0-9]") || jsonStringTemp.matches("[0-9][0-9]"))){								
								
								JSONObject obj = array.getJSONObject(i);
								jsonToPojoConverter(obj, fullMap);
							}
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}				

			} else {
				
				if("setDepositPaymentSv".equals(key)){
					//System.out.println("bbbbbbbbbbbbbbbbbb"+value);
				}
				
				JSONObject jsonObject = new JSONObject();
				boolean isNewRecord = true;
				
				if (jsonModel.getJsonArray().size() > 0) {
					
					for (int i = 0; i < jsonModel.getJsonArray().size(); i++) {						
						jsonObject = (JSONObject) jsonModel.getJsonArray().get(i);
						
						//System.out.println(jsonObject.toString());
						
						if (jsonObject != null && jsonObject.get("REFERACE_KEY") != null && jsonObject.get("REFERACE_KEY").equals(referanceClassValue)) {							
							isNewRecord = false;
							break;												
						}
					}					
					
					if(isNewRecord){
						jsonObject = new JSONObject();
						jsonObject.accumulate("REFERACE_KEY", referanceClassValue);	
						jsonObject.accumulate(key, value);
						jsonModel.getJsonArray().add(jsonObject);
					}else{
						jsonObject.accumulate(key, value);	
					}
					
					
				}else{
					jsonObject.accumulate("REFERACE_KEY", referanceClassValue);
					jsonObject.accumulate(key, value);
					jsonModel.getJsonArray().add(jsonObject);					
				}				
			}
			fullMap.put(referanceClassName, jsonModel);
		}
	}

	private static String readFile(String fileName) {
		String returnText = "";
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);				
				returnText += sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return returnText;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
