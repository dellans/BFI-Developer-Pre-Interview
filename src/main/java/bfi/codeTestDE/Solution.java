package bfi.codeTestDE;

import java.util.Arrays;
import java.util.Collections;

public class Solution {

	public int solution (Integer[] a) {
		
		//
		for (int i=0; i < a.length; i++) {
			if(a[i] > 0) {
				a[i] = 0;
			} 
		}
		
		//sort array descending
		Arrays.sort(a, Collections.reverseOrder());
		int missingNegative = -1;
		int greatestNegative = 0;
		
		//find the greatest negative number
		for (int j = 0; j < a.length; j++) {
			if (a[j] < 0) {
				if (a[j] != missingNegative) {
					//greatestNegative = missingNegative;
				} else {
					missingNegative--;
				}
				
			}
		}
		System.out.println(missingNegative);
		return missingNegative                               ;
		
	}
	
}
