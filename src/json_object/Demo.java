package json_object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		//String jsonArray = "{\"setOfResFlightRateInfoSv\":[{\"@ResFlightRateInfoSv\":8,\"rateType\":\"ADT\",\"netRate\":80,\"taxes\":180,\"nettax\":0,\"sellRate\":80,\"taComm\":null,\"tax1\":null,\"tax2\":null,\"tax3\":null,\"id\":142,\"resFlightConfirmSv\":1}]}";
		
		//Map<String, Object> setOfRateInfoSv = processJSONData(jsonArray);
			
		
		//System.out.println(setOfRateInfoSv.toString());
		
		////////////////////////////////////////
		String fileName = "/rezsystem/workspace_java8/Test/src/test.json";
		String text = readFile(fileName);

		JSONObject json = JSONObject.fromObject(text);

		String searchJson= "setResCustPayHistorySv";
		String jsonResult = readJSONObject(json,searchJson);

		

		// System.out.println(""+json.toString());
		//System.out.println(jsonResult);
		
		Map<String, Object> setOfRateInfoSvTemp = processJSONData(jsonResult);
			
		
		System.out.println(setOfRateInfoSvTemp.toString());
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static HashMap<String, Object> processJSONData(String operationJSON) throws JsonProcessingException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;

		/* Extract Jason part */
		// operationJSON = operationJSON.substring(operationJSON.indexOf(":{")+1,operationJSON.lastIndexOf("},")+1);
		//logger.debug("************************************************ ");
		// //logger.debug("Json Root After " + operationJSON);
		rootNode = mapper.readTree(operationJSON);
		//logger.debug("************************************************ ");

		HashMap<String, Object> results = jsonToMapProcess(rootNode, mapper, map);

		//logger.debug("Json Root After " + results);
		return results;
	}
	
	public static HashMap<String, Object> jsonToMapProcess(JsonNode rootNode, ObjectMapper mapper, HashMap<String, Object> map) {
		boolean correctNode=true;
		if((rootNode.getNodeType().toString().equals("NUMBER"))) {
			try{
				rootNode.get(0).toString();//maps would pass but not int
				correctNode=true;
			}catch(Exception e){
				correctNode=false;
				//logger.debug("Duplicate Json array Node");
			}
		}
	  //if(!(rootNode.getNodeType().toString().equals("NUMBER"))) {
		@SuppressWarnings("unchecked")
		Map<String, Object> result = mapper.convertValue(rootNode, Map.class);

			JsonNode nodex = null;
			recursive:
			for (String key : result.keySet()) {
			nodex = rootNode.get(key);

			if ((nodex.getNodeType().toString().equals("ARRAY"))/*&&(correctNode)*/) {// Node FieldType is ARRAY type
				/* Call recursively */
				/*logger.debug("key1 = " + key +" Node Type = "+nodex.getNodeType());*/
				JsonNode objArray = rootNode.get(key);
				
				ArrayList <HashMap<String, Object>> jsonArrayList = new ArrayList <HashMap<String, Object>>();
				
				if((null != objArray)&&(objArray.size()>0)&&(!(objArray.get(0).getNodeType().toString().equals("NUMBER")))){
					
                for(int i=0; i < objArray.size() ; i ++ ){
                	if(!(objArray.get(i).getNodeType().toString().equals("NUMBER"))){
                	jsonArrayList.add(jsonToMapProcess(objArray.get(i), mapper, new HashMap<String, Object>()));
                   }
                 }
				}else{
                	continue recursive;
                }
				
              /*logger.debug("ObjArray Size" + objArray.size() +" jsonArrayList size "+jsonArrayList.size());
                logger.debug(" jsonArrayList object "+jsonArrayList.toString());*/
              //map.put(key, "[" + jsonToMapProcess(objArray, mapper, new HashMap<String, Object>()) + "]");
                map.put(key, jsonArrayList); 
			} else {
				/*TODO: enum based keys for uniform use in reports*/
                if(!(map.containsKey(key))){
				map.put(key, result.get(key));
		      //map.put(getUniqueKey(key), result.get(key));
                }else{
                	//logger.debug("Duplicate Key = " + key);	
                }

				/*logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				logger.debug("map  afetr addition = " + map.toString());
				logger.debug("map  afetr addition = " + map.size());
				logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");*/			}
		  }
		//}
	    /*logger.debug("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		logger.debug("Final " + map.toString());
		logger.debug("map  afetr all additions = " + map.size());
		logger.debug("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");*/		
		return map;
	}
	
	private static String readJSONObject(JSONObject json,String searchJson) {
		String jsonResult ="";
		Iterator<?> keys = json.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			if (json.get(key) instanceof JSONObject) {
				if(key.toLowerCase().contains(searchJson.toLowerCase())){
					jsonResult = json.get(key).toString();
					//System.out.println(jsonResult);
					break;
				}
				
				readJSONObject(JSONObject.fromObject(json.get(key)),searchJson);
				
				if (json.get(key).toString().contains("{")) {
					//map.put(key, json.get(key));
				} else {
					
				}
				

			} else if (json.get(key) instanceof JSONArray) {
				// readJSONObject(JSONObject.fromObject(json.get(key)));
				try {
					JSONArray array = JSONArray.fromObject(json.get(key));
					if(key.toLowerCase().contains(searchJson.toLowerCase())){
						jsonResult = json.get(key).toString();
						//System.out.println(jsonResult);
						break;
					}
					
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.getJSONObject(i);
						readJSONObject(obj,searchJson);
					}
				} catch (Exception e) {
				}

			} else {
				if(key.toLowerCase().contains(searchJson.toLowerCase())){
					jsonResult = json.get(key).toString();
					//System.out.println(jsonResult);
					break;
				}
				
			}
		}
		jsonResult = "{\""+searchJson+"\":"+jsonResult+"}";
		
		return jsonResult;
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

}
