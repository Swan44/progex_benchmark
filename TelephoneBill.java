package com.chapter.one;
public class TelephoneBill {
	public static double cal_bill(int custnum, int numcalls){
		int valid = 0;
		double netAmount=0.0 ;
		if(custnum >10 && custnum <20000){
			valid = 1;		
			if(numcalls <0){
				valid = -1;
			}
		}
		if (valid ==1){
			if(numcalls<76){
				netAmount  =500;
			}
			else if(numcalls>75 && numcalls<201){
				netAmount = 500 + 0.80*(numcalls-75);
			}
			else if(numcalls>200 && numcalls<501){
				netAmount = 500 + 1.00*(numcalls-200);
			}
			else{
				netAmount = 500 + 1.20*(numcalls-500);
			}
			return netAmount;
		}
		else if(valid==0){			
			return -1;			
		}
		else{			
			return -2;			
		}	
	}
}
