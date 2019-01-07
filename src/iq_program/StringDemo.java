package iq_program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class StringDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			ConcurrentHashMap<String, String> mappp= new ConcurrentHashMap<String, String>();
			
			String temp = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque dignissim erat vel ligula aliquet euismod. Donec et mi velit. Fusce sem neque, pretium eget leo nec, convallis accumsan purus. Proin a turpis eu nisl cursus molestie ac et massa. Duis purus nulla, rutrum et metus sit amet, pretium tempus odio. Curabitur mauris ex, laoreet ut orci vitae, placerat laoreet massa. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam non lorem nisl. Cras sapien orci, iaculis eu pharetra eget, euismod eget ante. Aenean maximus ante sapien, sed lobortis turpis cursus interdum. Vestibulum fringilla efficitur sem, at semper dolor efficitur ac. Phasellus dolor est, lacinia nec sem at, vehicula ultrices turpis. Phasellus eget urna nec mauris lacinia laoreet id quis est. Aliquam vel turpis vel metus pretium fermentum. Sed aliquet lacus nec auctor mollis. Mauris at fermentum arcu. Curabitur neque ligula, pulvinar ut mauris id, vehicula pellentesque diam. In et nibh sollicitudin, accumsan velit sed, tristique mauris. Vestibulum consequat, metus vel cursus auctor, ligula augue faucibus sapien, vel malesuada ante felis sit amet nisi. Integer nec neque egestas, cursus mi imperdiet, tristique ipsum. Morbi auctor, diam et ornare dapibus, dolor arcu pharetra nibh, sed lacinia felis diam eget augue. Nunc at leo eu lectus pellentesque mollis et ac est. Curabitur molestie neque non massa pulvinar fermentum. Nam at finibus augue, ac maximus turpis. Sed hendrerit odio dignissim diam congue egestas. Aliquam ut ante sit amet orci dictum cursus ullamcorper et enim. Aenean sem enim, tempor eget lobortis ac, tincidunt ac nibh. Quisque hendrerit posuere consectetur. Nullam sed ante at erat feugiat ultrices vitae et ligula. Integer sollicitudin, felis sit amet feugiat tincidunt, felis diam blandit arcu, in lobortis ex erat vel velit. Sed congue semper accumsan. Ut neque nisi, fermentum a vestibulum et, luctus sed nisi. Vivamus ultrices consectetur sem. Nam suscipit lacus eget iaculis posuere. Cras vitae dolor erat. Integer et ipsum maximus, tincidunt libero ac, commodo elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec a nulla id augue scelerisque egestas. Sed ultrices in lectus vitae aliquam. Proin tempor congue mauris suscipit tincidunt. Mauris malesuada laoreet nulla. Ut vel ornare libero. ";
			  Map<String, Integer> map = new TreeMap<String, Integer>();
			  System.out.println("aa=>"+temp.toCharArray().length);
			long time = System.currentTimeMillis();

			for (String t : temp.split("")) {
				// System.out.println(t);
				Thread.sleep(1);
				if (map.containsKey(t)) {
					map.put(t, map.get(t) + 1);
				} else {
					map.put(t, 1);
				}
			}
			System.out.println(map.toString());
			System.out.println(System.currentTimeMillis() - time);

			Map<String, Integer> mapmap = new TreeMap<String, Integer>();

			

			//List<String> li = Arrays.asList(temp.split(""));
			
			time = System.currentTimeMillis();
						
			Arrays.asList(temp.split("")).parallelStream().forEach(t -> {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (mapmap.containsKey(t)) {
					mapmap.put(t, mapmap.get(t) + 1);
				} else {
					mapmap.put(t, 1);
				}
				

			});

			System.out.println(mapmap.toString());
			
			int tempVal = 0;
			mapmap.values().forEach(val ->{
				val +=val;
				if(val >1){
					System.out.println("val==>"+val);
				}
			});
			
			System.out.println(System.currentTimeMillis() - time);
			//temp.chars().filter(num -> num == '$').count();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
