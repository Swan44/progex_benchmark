import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalendarTest{

	@Test(timeout = 2000)
	public void test1(){
		int actualdays = Calendar.cal(1, 1, 1, 1, 2014);
		assertEquals(0, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test2(){
		int actualdays = Calendar.cal(1, 1, 1, 4, 2014);
		assertEquals(3, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test3(){
		int actualdays = Calendar.cal(1, 1, 6, 4, 2014); 
		assertEquals(154, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test4(){
		int actualdays = Calendar.cal(1, 1, 2, 29, 2012);
		assertEquals(59, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test5(){
		int actualdays = Calendar.cal(0, 1, 1, 4, 2014);
		assertEquals(-1, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test6(){
		int actualdays = Calendar.cal(1, 0, 1, 4, 2014);
		assertEquals(-1, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test7(){
		int actualdays = Calendar.cal(1, 1, 0, 4, 2014);
		assertEquals(-1, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test8(){
		int actualdays = Calendar.cal(1, 1, 1, 0, 2014);
		assertEquals(-1, actualdays);
	}	
	
	@Test(timeout = 2000)
	public void test9(){
		int actualdays = Calendar.cal(13, 1, 1, 4, 2013);
		assertEquals(-1, actualdays);
	}
	
	@Test(timeout = 2000)
	public void test10(){
		int actualdays = Calendar.cal(1, 32, 1, 4, 2013);
		assertEquals(-1, actualdays);
	}
}
