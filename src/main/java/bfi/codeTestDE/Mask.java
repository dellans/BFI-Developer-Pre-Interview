package bfi.codeTestDE;

public class Mask {

	public String mask(String output) {
		String [] splittedInput = output.split(" "); //split word by space
		StringBuilder groupInput = new StringBuilder(); 
		
		for (int i = 0; i < splittedInput.length; i++) {
				String str = splittedInput[i];
				String maskedInput = str.replaceAll("(?<!^).(?!$)", "*"); //replace String char exclude first & last char
				groupInput.append(maskedInput);
				if(i < splittedInput.length-1) {
				groupInput.append(" ");
				}
			
		}
		System.out.println(groupInput);
		return groupInput.toString();
	}

}
