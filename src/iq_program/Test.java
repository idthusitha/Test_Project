package iq_program;


public class Test {

	public static void main(String[] args) {
		String devServiceName = "transfer-setup_es_es";
		String client = "interjet";
		
		String contentKeyStr = devServiceName.indexOf("rezbase_v3") != -1 ? ("".equals(client) ? devServiceName.replace("rezbase_v3-", "") : devServiceName.replace("rezbase_v3", client)) : devServiceName;
		
		System.out.println("contentKeyStr==> " + contentKeyStr);
		

	}

}
