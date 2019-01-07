package junit;

import junit.framework.TestCase;

public class MathTest extends TestCase {

	private int value1;
	private int value2;

	public MathTest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		super.setUp();

		value1 = 3;
		value2 = 5;
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		value1 = 0;
		value2 = 0;
	}

	public void testAdd() {
		int total = 8;

		int sum = Calculation.add(value1, value2);

		assertEquals(sum, total);
	}

	public void testFailedAdd() {

		int total = 9;

		int sum = Calculation.add(value1, value2);

		assertNotSame(sum, total);
	}

	public void testSub() {

		int total = 0;

		int sub = Calculation.sub(4, 4);

		assertEquals(sub, total);
	}
}

class Calculation {

	public static int add(int a, int b) {
		return a + b;
	}

	public static int sub(int a, int b) {
		return a - b;
	}
}
//assertArrayEquals(byte[] expecteds, byte[] actuals)
//Asserts that two byte arrays are equal.
//static void

//
//assertArrayEquals(char[] expecteds, char[] actuals)
//
//Asserts that two char arrays are equal.
//
//static void
//
//assertArrayEquals(double[] expecteds, double[] actuals, double delta)
//
//Asserts that two double arrays are equal.
//
//static void
//
//assertArrayEquals(float[] expecteds, float[] actuals, float delta)
//
//Asserts that two float arrays are equal.
//
//static void
//
//assertArrayEquals(int[] expecteds, int[] actuals)
//
//Asserts that two int arrays are equal.
//
//static void
//
//assertArrayEquals(long[] expecteds, long[] actuals)
//
//Asserts that two long arrays are equal.
//
//static void
//
//assertArrayEquals(Object[] expecteds, Object[] actuals)
//
//Asserts that two object arrays are equal.
//
//static void
//
//assertArrayEquals(short[] expecteds, short[] actuals)
//
//Asserts that two short arrays are equal.
//
//static void
//
//assertArrayEquals(String message, byte[] expecteds, byte[] actuals)
//
//Asserts that two byte arrays are equal.
//
//static void
//
//assertArrayEquals(String message, char[] expecteds, char[] actuals)
//
//Asserts that two char arrays are equal.
//
//static void
//
//assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta)
//
//Asserts that two double arrays are equal.
//
//static void
//
//assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta)
//
//Asserts that two float arrays are equal.
//
//static void
//
//assertArrayEquals(String message, int[] expecteds, int[] actuals)
//
//Asserts that two int arrays are equal.
//
//static void
//
//assertArrayEquals(String message, long[] expecteds, long[] actuals)
//
//Asserts that two long arrays are equal.
//
//static void
//
//assertArrayEquals(String message, Object[] expecteds, Object[] actuals)
//
//Asserts that two object arrays are equal.
//
//static void
//
//assertArrayEquals(String message, short[] expecteds, short[] actuals)
//
//Asserts that two short arrays are equal.
//
//static void
//
//assertEquals(double expected, double actual)
//
//Deprecated. Use assertEquals(double expected, double actual, double epsilon) instead
//
//static void
//
//assertEquals(double expected, double actual, double delta)
//
//Asserts that two doubles or floats are equal to within a positive delta.
//
//static void
//
//assertEquals(long expected, long actual)
//
//Asserts that two longs are equal.
//
//static void
//
//assertEquals(Object[] expecteds, Object[] actuals)
//
//Deprecated. use assertArrayEquals
//
//static void
//
//assertEquals(Object expected, Object actual)
//
//Asserts that two objects are equal.
//
//static void
//
//assertEquals(String message, double expected, double actual)
//
//Deprecated. Use assertEquals(String message, double expected, double actual, double epsilon) instead
//
//static void
//
//assertEquals(String message, double expected, double actual, double delta)
//
//Asserts that two doubles or floats are equal to within a positive delta.
//
//static void
//
//assertEquals(String message, long expected, long actual)
//
//Asserts that two longs are equal.
//
//static void
//
//assertEquals(String message, Object[] expecteds, Object[] actuals)
//
//Deprecated. use assertArrayEquals
//
//static void
//
//assertEquals(String message, Object expected, Object actual)
//
//Asserts that two objects are equal.
//
//static void
//
//assertFalse(boolean condition)
//
//Asserts that a condition is false.
//
//static void
//
//assertFalse(String message, boolean condition)
//
//Asserts that a condition is false.
//
//static void
//
//assertNotNull(Object object)
//
//Asserts that an object isn't null.
//
//static void
//
//assertNotNull(String message, Object object)
//
//Asserts that an object isn't null.
//
//static void
//
//assertNotSame(Object unexpected, Object actual)
//
//Asserts that two objects do not refer to the same object.
//
//static void
//
//assertNotSame(String message, Object unexpected, Object actual)
//
//Asserts that two objects do not refer to the same object.
//
//static void
//
//assertNull(Object object)
//
//Asserts that an object is null.
//
//static void
//
//assertNull(String message, Object object)
//
//Asserts that an object is null.
//
//static void
//
//assertSame(Object expected, Object actual)
//
//Asserts that two objects refer to the same object.
//
//static void
//
//assertSame(String message, Object expected, Object actual)
//
//Asserts that two objects refer to the same object.
//
//static <T> void
//
//assertThat(String reason, T actual, Matcher<T> matcher)
//
//Asserts that actual satisfies the condition specified by matcher.
//
//static <T> void
//
//assertThat(T actual, Matcher<T> matcher)
//
//Asserts that actual satisfies the condition specified by matcher.
//
//static void
//
//assertTrue(boolean condition)
//
//Asserts that a condition is true.
//
//static void
//
//assertTrue(String message, boolean condition)
//
//Asserts that a condition is true.
//
//static void
//
//fail()
//
//Fails a test with no message.
//
//static void
//
//fail(String message)
//
//Fails a test with the given message.