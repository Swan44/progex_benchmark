package MLbenchmark;

import junit.framework.*;
public class TelephoneBillTest extends TestCase{
	
	public void testbills1(){
		double actualamt  = TelephoneBill.cal_bill(10, 500);
		assertEquals(-1.0, actualamt);
	}
	
	public void testbills2(){
		double actualamt  = TelephoneBill.cal_bill(20001, 500);
		assertEquals(-1.0, actualamt);// invalid telephone no
	}
	public void testbills3(){
		double actualamt  = TelephoneBill.cal_bill(10000, -1);
		assertEquals(-2.0, actualamt);// invalid calls
	}
	public void testbills4(){
		double actualamt  = TelephoneBill.cal_bill(10000, 75);
		assertEquals(500.0, actualamt);
	}
	public void testbills5(){
		double actualamt  = TelephoneBill.cal_bill(10000, 100);
		assertEquals(520.0, actualamt);
	}
	public void testbills6(){
		double actualamt  = TelephoneBill.cal_bill(10000, 400);
		assertEquals(700.0, actualamt);
	}
	public void testbills7(){
		double actualamt  = TelephoneBill.cal_bill(10000, 510);
		assertEquals(512.0, actualamt);
	}
	public void testbills8(){
		double actualamt  = TelephoneBill.cal_bill(11, 500);
		assertEquals(800.0, actualamt);
	}
	public void testbills9(){
		double actualamt  = TelephoneBill.cal_bill(10000, 500);
		assertEquals(800.0, actualamt);
	}	
}
