package xml_json_converter;

import org.json.JSONObject;
import org.json.XML;

public class JSON_XMLConverterDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonStr = "{\"Customer\": {" + "\"address\": {" + "\"street\": \"NANTERRE CT\"," + "\"postcode\": 77471" + "}," + "\"name\": \"Mary\"," + "\"age\": 37" + "}}";

		JSONObject json = new JSONObject(jsonStr);
		String xml = XML.toString(json);

		System.out.println(xml);
	}

}
