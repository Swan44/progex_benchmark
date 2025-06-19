package com.chapter.one;

public class WeekDay {
	public static String get_day(int date, int month, int year){
		
		int century=0, Y=0, y1=0, M=0, day=-1, validDate=0, leap=0;
		String output="";
		if(year>=1900 && year <=2058){ // check date is valid or not
			if(year %4 ==0){
				leap =1;
				if((year%100==0) && (year%400!=0)){
					leap =0;
				}
			}
			if(month==4 ||month==6||month==9||month==11){
				if(date>=1 && date<=30){
					validDate =1;
				}
				else{
					validDate =0;
				}
			}
			else if(month ==2){
				if(leap ==1 && (date >=1 && date<=29)){
					validDate =1;
				}
				else if(date>=1 && date<=28){
					validDate =1;
				}
				else{
				 validDate =0;
				}
			}
			else if((month >=1 && month <=12) && (date >=1 && date <=31)){
				validDate =1;
			}
			else{
				validDate =0;
			}
		}
		if(validDate ==1){// cal of day in week
			if(year >=1900 && year <=2000){
				century = 0;
				y1 =year -1900;				
			}
			else{
				century = 6;
				y1 =year-2000;
			}
			Y = y1+ (y1/4);
			if(month ==1){
				if(leap==0){
					M= 0;//for a nonleap year
				}
				else{
					M=6;// for leap year
				}
			}
			else if(month==2){
				if(leap ==0){
					M=3;//for non leap year
				}
				else{
					M=2; //for leap year
				}
			}
			else if(month ==3 || month ==11){
				M=3;
			}
			else if(month ==4 || month ==7){
				M=6;
			}else if(month==5){
				M=1;
			}
			else if(month ==6){
				M=4;
			}else if(month ==8){
				M=2;
			}else if((month ==9 || month ==12)){
				M=5;
			}
			else{
				M=0;
			}
			day = (century + Y+ M+ date)%7;
			if(day ==0){
				output = "Sunday";
			}
			else if(day ==1){
				output = "Monday";
			}		
			else if(day ==2){
				output = "Tuesday";
			}
			else if(day ==3){
				output = "Wednesday";
			}
			else if(day ==4){
				output = "Thursday";
			}
			else if(day ==5){
				output = "Friday";
			}
			else{
				output = "Saturday";
			}				
		}
		else{
			output = "invalid date";
		}
		return output;
	}	
}