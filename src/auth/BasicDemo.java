package auth;

import javax.xml.bind.DatatypeConverter;

public class BasicDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String str = new String(DatatypeConverter.parseBase64Binary("omanair:omanair123"));
          String res = DatatypeConverter.printBase64Binary(str.getBytes());
          System.out.println(res); 
	} 

}
 