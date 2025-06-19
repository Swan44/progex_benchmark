package com.chapter.one;
//import classes.WeekDay;
import junit.framework.*;


public class WeekDayTest extends TestCase{
		public void testDay1(){
			String actualout = WeekDay.get_day(1, 6, 1979); 
			assertEquals("Friday", actualout);
		}
		public void testDay2(){
			String actualout = WeekDay.get_day(2, 6, 1979);
			assertEquals("Saturday", actualout);
		}
		public void testDay3(){
			String actualout = WeekDay.get_day(15, 6, 1979);
			assertEquals("Friday", actualout);
		}
		public void testDay4(){
			String actualout = WeekDay.get_day(30, 6, 1979); 
			assertEquals("Saturday", actualout);
		}
		public void testDay5(){
			String actualout = WeekDay.get_day(31, 6, 1979); 
			assertEquals("invalid date", actualout);
		}
		public void testDay6(){
			String actualout = WeekDay.get_day(15, 1, 1979);
			assertEquals("Monday", actualout);
		}
		public void testDay7(){
			String actualout = WeekDay.get_day(15, 2, 1979);
			assertEquals("Thursday", actualout);
		}
		public void testDay8(){
			String actualout = WeekDay.get_day(15, 11, 1979);
			assertEquals("Thursday", actualout);
		}
		public void testDay9(){
			String actualout = WeekDay.get_day(15, 12, 1979);
			assertEquals("Saturday", actualout);
		}
		public void testDay10(){
			String actualout = WeekDay.get_day(15, 6, 1900);
			assertEquals("Friday", actualout);
		}
		public void testDay11(){
			String actualout = WeekDay.get_day(15, 6, 1901);
			assertEquals("Saturday", actualout);
		}
		public void testDay12(){
			String actualout = WeekDay.get_day(15, 6, 2057);
			assertEquals("Friday", actualout);
		}
		public void testDay13(){
			String actualout = WeekDay.get_day(15, 6, 2058);
			assertEquals("Saturday", actualout);
		}
		public void testDay14(){
			String actualout = WeekDay.get_day(15, -1, 1979);
			assertEquals("invalid date", actualout);
		}
		public void testDay15(){
			String actualout = WeekDay.get_day(15, 13, 1979);
			assertEquals("invalid date", actualout);
		}
		public void testDay16(){
			String actualout = WeekDay.get_day(-1, 6, 1979);
			assertEquals("invalid date", actualout);
		}
		public void testDay17(){
			String actualout = WeekDay.get_day(32, 6, 1979);
			assertEquals("invalid date", actualout);
		}
		public void testDay18(){
			String actualout = WeekDay.get_day(15, 6, 1899);
			assertEquals("invalid date", actualout);
		}
		public void testDay19(){
			String actualout = WeekDay.get_day(15, 6, 2059);
			assertEquals("invalid date", actualout);
		}	
		public void testDay20(){
			String actualout = WeekDay.get_day(28, 2, 2013);
			assertEquals("Thursday", actualout);
		}
		public void testDay21(){
			String actualout = WeekDay.get_day(29, 2, 2013);
			assertEquals("invalid date", actualout);
		}
		public void testDay22(){
			String actualout = WeekDay.get_day(29, 2, 2013);
			assertEquals("invalid date", actualout);
		}	
	}





