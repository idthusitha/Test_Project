package java_8_featrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class IteratorDemo extends Object{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// fail-fast 
		ArrayList<String> list = new ArrayList();
		list.add("aaaaaaaa1");
		list.add("aaaaaaaa2");
		list.add("aaaaaaaa3");
		list.add("aaaaaaaa4");
		list.add("aaaaaaaa5");
		list.add("aaaaaaaa6");
		list.add("aaaaaaaa7");
		list.add("aaaaaaaa8");
		list.add("aaaaaaaa9");
		list.add("aaaaaaaa10");
		list.add("aaaaaaaa11");
		list.add("aaaaaaaa12");
		list.add("aaaaaaaa13");
		list.add("aaaaaaaa14");

		System.out.println("=================================");		
		ListIterator lIt = list.listIterator(list.size());

		while (lIt.hasPrevious()) {
			System.out.println(lIt.previous());
		}
		System.out.println("=================================");
		
		

		System.out.println("=================================");
		ListIterator lItTemp = list.listIterator();

		while (lItTemp.hasNext()) {
			System.out.println(lItTemp.next());
		}
		System.out.println("=================================");
		
		
		
		System.out.println("=================================");
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			// list.add("aaaaaaaa14"); //ConcurrentModificationException
		}
		System.out.println("=================================");
		
		
		
		System.out.println("=================================");
		//fail-safe iterators
		//ConcurrentHashMap, CopyOnWriteArrayList
		
		CopyOnWriteArrayList<String> listTemp = new CopyOnWriteArrayList<String>();
		listTemp.add("CopyOnWriteArrayList1");
		listTemp.add("CopyOnWriteArrayListaaaaa2");
		listTemp.add("aaaaaaaa3");
		listTemp.add("aaaaaaaa4");
		listTemp.add("aaaaaaaa5");
		listTemp.add("aaaaaaaa6");
		listTemp.add("aaaaaaaa7");
		listTemp.add("aaaaaaaa8");
		listTemp.add("aaaaaaaa9");
		listTemp.add("aaaaaaaa10");
		listTemp.add("aaaaaaaa11");
		listTemp.add("aaaaaaaa12");
		listTemp.add("aaaaaaaa13");
		listTemp.add("aaaaaaaa14");
		
		Iterator  itTemp = listTemp.iterator();
		while(itTemp.hasNext()){
			System.out.println(itTemp.next());
			listTemp.add("aaaaaaaa15");
		}
		System.out.println("=================================");
		
		itTemp = listTemp.iterator();
		while(itTemp.hasNext()){
			System.out.println(itTemp.next());
			listTemp.add("aaaaaaaa16");
		}
		System.out.println("=================================");
	}

}
