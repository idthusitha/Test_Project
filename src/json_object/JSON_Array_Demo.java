package json_object;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSON_Array_Demo {

	public static void main(String[] args) {

		String str = "{\"OuterObject\": {    \"Names\": {    \"name\": \"John Doe\"    }   }}";

		JSONObject jsonP = JSONObject.fromObject(str).getJSONObject("OuterObject");

		JSONArray array = null ;
		
		array = getJSONArray(jsonP, "Names");
		
		
		for (int i = 0; i < array.size(); i++) {

			JSONObject j = array.getJSONObject(i);

			System.out.println(j.getString("name"));
		}
	}

	private static JSONArray getJSONArray(JSONObject json, String field) {
		JSONArray array;
		if(json.get(field) instanceof JSONObject){
			array = new JSONArray();
			array.add(json.get(field));
		}else{
			array = json.getJSONArray(field);
		}
		return array;
	}

}
