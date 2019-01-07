package junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJunit {
	
	@Test
	public void testAdd() {
		//test data
	      int num = 5;
	      String temp = null;
	      String str = "Junit is working fine";

	      //check for equality
	      assertEquals("Junit is working fine", str);
	      
	      //check for false condition
	      assertFalse(num > 6);

	      //check for not null value
	      assertNotNull(str);
	      
	      
	}
	
	@Test
	public void testNull() {
		String str = null;
		assertNotNull(str);
	}
}
