package cm;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HotelRatePlanLoader {

	public static void main(String[] args) {

		try {
			String xmlStringTemp = "<OTA_HotelRatePlanRS xmlns=\"http://www.opentravel.org/OTA/2003/05\"\n"
					+ "                     Version=\"1.0\"\n"
					+ "                     TimeStamp=\"2019-12-10T10:11:07.644+05:30\"\n"
					+ "                     EchoToken=\"001-1466531393\">\n" + "   <Success/>\n"
					+ "   <RatePlans HotelCode=\"1111\">\n"
					+ "      <RatePlan RatePlanCode=\"BBrek\" Start=\"2020-03-01\" End=\"2020-03-01\">\n"
					+ "         <Rates>\n" + "            <Rate InvTypeCode=\"TopKing\" CurrencyCode=\"USD\">\n"
					+ "               <BaseByGuestAmts>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"200\" NumberOfGuests=\"1\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"3\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"4\"/>\n"
					+ "               </BaseByGuestAmts>\n" + "               <AdditionalGuestAmounts/>\n"
					+ "            </Rate>\n" + "         </Rates>\n" + "      </RatePlan>\n"
					+ "      <RatePlan RatePlanCode=\"BBrek\" Start=\"2020-03-02\" End=\"2020-03-02\">\n"
					+ "         <Rates>\n" + "            <Rate InvTypeCode=\"TopKing\" CurrencyCode=\"USD\">\n"
					+ "               <BaseByGuestAmts>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"200\" NumberOfGuests=\"1\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"3\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"4\"/>\n"
					+ "               </BaseByGuestAmts>\n" + "               <AdditionalGuestAmounts/>\n"
					+ "            </Rate>\n" + "         </Rates>\n" + "      </RatePlan>\n"
					+ "      <RatePlan RatePlanCode=\"BBrek\" Start=\"2020-03-03\" End=\"2020-03-03\">\n"
					+ "         <Rates>\n" + "            <Rate InvTypeCode=\"TopKing\" CurrencyCode=\"USD\">\n"
					+ "               <BaseByGuestAmts>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"200\" NumberOfGuests=\"1\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"3\"/>\n"
					+ "                  <BaseByGuestAmt AmountBeforeTax=\"100\" NumberOfGuests=\"4\"/>\n"
					+ "               </BaseByGuestAmts>\n" + "               <AdditionalGuestAmounts/>\n"
					+ "            </Rate>\n" + "         </Rates>\n" + "      </RatePlan>\n" + "   </RatePlans>\n"
					+ "</OTA_HotelRatePlanRS>\n" + "";
			
			
			String xmlString = "<OTA_HotelRatePlanRS xmlns=\"http://www.opentravel.org/OTA/2003/05\" Version=\"1.0\" TimeStamp=\"2019-12-13T08:52:20.947+05:30\" EchoToken=\"001-1466531393\">\n" + 
					"   <Success/>\n" + 
					"   <RatePlans HotelCode=\"Test001\">\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-14\" End=\"2019-12-14\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-15\" End=\"2019-12-15\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-16\" End=\"2019-12-16\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"\" End=\"\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts/>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts/>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-14\" End=\"2019-12-14\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-15\" End=\"2019-12-15\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"      <RatePlan RatePlanCode=\"AP\" Start=\"2019-12-16\" End=\"2019-12-16\">\n" + 
					"         <Rates>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BCV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"600.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"800.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"900.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"700.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"            <Rate CurrencyCode=\"USD\" InvTypeCode=\"BOV\">\n" + 
					"               <BaseByGuestAmts>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"100.0\" NumberOfGuests=\"1\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"200.0\" NumberOfGuests=\"3\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"250.0\" NumberOfGuests=\"4\"/>\n" + 
					"                  <BaseByGuestAmt AmountBeforeTax=\"150.0\" NumberOfGuests=\"2\"/>\n" + 
					"               </BaseByGuestAmts>\n" + 
					"               <AdditionalGuestAmounts/>\n" + 
					"            </Rate>\n" + 
					"         </Rates>\n" + 
					"      </RatePlan>\n" + 
					"   </RatePlans>\n" + 
					"</OTA_HotelRatePlanRS>" ;

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));

			Document doc = db.parse(is);
			NodeList hotelRatePlanRSNodes = doc.getElementsByTagName("OTA_HotelRatePlanRS");
			NodeList rateNodes = doc.getElementsByTagName("Rate");
			NodeList baseByGuestAmtsNodes = doc.getElementsByTagName("BaseByGuestAmts");

			Element TimeStampElement = (Element) hotelRatePlanRSNodes.item(0);
			String TimeStamp = TimeStampElement.getAttribute("TimeStamp");
			String EchoToken = TimeStampElement.getAttribute("EchoToken");
			
			NodeList ratePlansNodes = doc.getElementsByTagName("RatePlans");
			Element hotelElement = (Element) ratePlansNodes.item(0);
			String hotelCode = hotelElement.getAttribute("HotelCode");
			LinkedHashMap<String, LinkedHashSet<String>> dataMap = new LinkedHashMap<>();

			NodeList ratePlanNode = doc.getElementsByTagName("RatePlan");

			HashMap<String, String> hashMap = new LinkedHashMap();
			NodeList baseByGuestAmtNode = null;
			String rateKeyFinal = null;

			for (int k = 0; k < ratePlanNode.getLength(); k++) {
				Element ratePlanElement = (Element) ratePlanNode.item(k);

				String ratePlanCode = ratePlanElement.getAttribute("RatePlanCode");
				String start = ratePlanElement.getAttribute("Start");
				String end = ratePlanElement.getAttribute("End");

				NodeList rateNode = ratePlanElement.getElementsByTagName("Rate");
				for (int l = 0; l < rateNode.getLength(); l++) {
					Element rateElement = (Element) rateNode.item(l);

					String invTypeCode = rateElement.getAttribute("InvTypeCode");
					String currencyCode = rateElement.getAttribute("CurrencyCode");

					baseByGuestAmtNode = rateElement.getElementsByTagName("BaseByGuestAmt");
					LinkedHashSet<String> dataSet = new LinkedHashSet<>();
					String guestKey = "";
					for (int i = 0; i < baseByGuestAmtNode.getLength(); i++) {
						Element guestAmtElement = (Element) baseByGuestAmtNode.item(i);
						String amountBeforeTax = guestAmtElement.getAttribute("AmountBeforeTax");
						String numberOfGuests = guestAmtElement.getAttribute("NumberOfGuests");

			
						String newKeyTemp = amountBeforeTax + "@@" + numberOfGuests;
						dataSet.add(newKeyTemp);						
						guestKey += amountBeforeTax+"|" + numberOfGuests+",";
					}
					String newKey = ratePlanCode + "@@" + invTypeCode + "@@" + start+"@@" + guestKey + "@@" + currencyCode;
					dataMap.put(newKey, dataSet);	
				}
			}

			LinkedHashMap<String, LinkedHashSet<String>> dataMapFinal = new LinkedHashMap<>();

			String dateStrTemp = "";
			String nextDateTemp = "";
			for (int i = 0; i < dataMap.keySet().size(); i++) {

				ArrayList<String> keyList = new ArrayList<>(dataMap.keySet());
				String inventoryKey = keyList.get(i);
				String dateStr = inventoryKey.split("@@")[2];
				nextDateTemp = inventoryKey.split("@@")[2];
				dateStrTemp = "".equals(dateStrTemp) ? dateStr : dateStrTemp;
				
				String guestKey = inventoryKey.split("@@")[3];
				String currencyCode = inventoryKey.split("@@")[4];
			
				String nextDate = nextDate(dateStr);
				String nextDateKey = inventoryKey.split("@@")[0] + "@@" + inventoryKey.split("@@")[1] + "@@" + nextDate + "@@" + guestKey + "@@" + currencyCode;
				if (dataMap.containsKey(nextDateKey)) {
					nextDateTemp = nextDate;
				} else {
					String nextDateKeyNew = inventoryKey.split("@@")[0] + "@@" + inventoryKey.split("@@")[1] + "@@" + dateStrTemp + "@@" + nextDateTemp+"@@" + guestKey + "@@" + currencyCode;
					dataMapFinal.put(nextDateKeyNew, dataMap.get(inventoryKey));
					dateStrTemp = "";
				}
			}

			String responseXML = "";
			responseXML += "\n<OTA_HotelRatePlanRS xmlns=\"http://www.opentravel.org/OTA/2003/05\" Version=\"1.0\" ";
			responseXML += "TimeStamp=\""+TimeStamp+"\" EchoToken=\""+EchoToken+"\">\n";

			responseXML += "<Success/>\n";

			responseXML += "<RatePlans HotelCode=\"" + hotelCode + "\">\n";

			for (int i = 0; i < dataMapFinal.keySet().size(); i++) {
				ArrayList<String> keyList = new ArrayList<>(dataMapFinal.keySet());
				String inventoryKey = keyList.get(i);

				responseXML += " <RatePlan RatePlanCode=\"" + inventoryKey.split("@@")[0] + "\" ";
				responseXML += "Start=\"" + inventoryKey.split("@@")[2] + "\" End=\"" + inventoryKey.split("@@")[3] +"\"> \n";

				responseXML += "<Rate InvTypeCode=\"" + inventoryKey.split("@@")[1] + "\" CurrencyCode=\""+inventoryKey.split("@@")[5]+"\">\n";

				
				String guestKey[] = inventoryKey.split("@@")[4].split(",");
				System.out.println("aaaaaaaaaaaaaaaaaaa"+inventoryKey.split("@@")[4]);
				responseXML += "<BaseByGuestAmts>\n";
				for(String guest : guestKey) {
					responseXML += "<BaseByGuestAmt AmountBeforeTax=\""+guest.split("\\|")[0]+"\" NumberOfGuests=\""+guest.split("\\|")[1]+"\"/>\n";
				}				
				responseXML += "</BaseByGuestAmts>\n";
				
				responseXML += "</Rate>\n";

				responseXML += "</RatePlan>\n";

				responseXML += "</RatePlans>\n";
			}
			responseXML += "</OTA_HotelRatePlanRS>\n";

			System.out.println("====>" + responseXML);			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static String nextDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}

		c.add(Calendar.DAY_OF_MONTH, 1);

		String newDate = sdf.format(c.getTime());
		return newDate;
	}

}
