package MLbenchmark;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
	
	public List<Integer> factorzation(int n){

		List<Integer> ans = new ArrayList<Integer>();

		if(n <= 1)
		{
			return ans;
		}

		boolean isPrime = true;
		
		for(int i =2;i < n;i++)
		{
			if(n % i == 0)
			{
				isPrime = false;
				break;
			}
		}

		if(isPrime)
		{
			ans.add(n);
			return ans;
		}

		for(int i = 2;i <= n/2;i++){
			if(n % i ==0){
				ans.add(i);
				ans.addAll(factorzation(n/i));
				break;
			}
		}

		return ans;
	}

}