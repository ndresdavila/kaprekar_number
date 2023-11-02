import java.util.*;

public class KaprekarNumber {
	
	public static void kaprekar(String userinput) {
		
		int iterations = 0;
		int num=0;
		
		//Repeat until value becomes 6174
		while(!userinput.equals("6174")) {
			
			//convert input to array of integers (num_array)
			int[] num_array = new int[4];
			System.out.print("Array in original order: ");
			for(int i = 0; i < 4; i++) {
				num_array[i] = userinput.charAt(i) - '0';
				//System.out.print(num_array[i] + " ");
			}
			//System.out.println();

			//Rewrite in descendant order (descendant_num_array)
			Arrays.sort(num_array);
			int temp;
			int descendant_num_array[] = num_array;
			for(int i = 0; i < descendant_num_array.length/2; ++i) {
				temp = descendant_num_array[i];
				descendant_num_array[i] = descendant_num_array[descendant_num_array.length - i - 1];
				descendant_num_array[descendant_num_array.length - i - 1] = temp;
			}
			
			//System.out.print("Array in descendant order: ");
			for(int i = 0; i < 4 ; i++) {
				//System.out.print(descendant_num_array[i]+" ");
			}
			//System.out.println();
			
			//Convert both arrays to integers
			int descendant_num = 0;
			for(int i=0,exp=descendant_num_array.length-1;i<descendant_num_array.length;i++,exp--) {
				descendant_num+=descendant_num_array[i]*Math.pow(10, exp);
			}
			//System.out.println("Descendant order number: " + descendant_num);
			
			
			Arrays.sort(descendant_num_array);
			int ascendant_num=0;
			for(int i=0,exp=descendant_num_array.length-1;i<descendant_num_array.length;i++,exp--) {
				ascendant_num+=descendant_num_array[i]*Math.pow(10, exp);
			}				
			//System.out.println("Ascendant order number: " + ascendant_num);
		
			//subtract both numbers
			System.out.println(descendant_num + " - " + ascendant_num + " = " + (descendant_num-ascendant_num));
			
			//update num value and iterations
			int answer = descendant_num-ascendant_num;
		
			//convert num to String (beware of leftside 0's)
			userinput = String.format("%04d", answer);
			//System.out.println("Value for next iteration: " + userinput);
			iterations++;
		}

		//show how many iterations were needed
		System.out.println("6174 was reached after "+ iterations + " iterations");
	}

	public static void main(String[] args) {
		Boolean validInput = false;
		Scanner sc = new Scanner(System.in);
		String userinput = "";
		while(!validInput) {
			System.out.print("Enter a 4 digit number with at least 2 different digits: ");
			userinput = sc.nextLine();
			
			//check if the number has 4 digits
			if(String.valueOf(userinput).length() != 4){
				System.out.println("Number is not 4 digits, try again.");
				continue;
			}
			
			//check if at least 2 of them are different
			Boolean hasDifferentDigits = false;
			for (int i = 0; i < String.valueOf(userinput).length() - 1; i++) {
                for (int j = i + 1; j < String.valueOf(userinput).length(); j++) {
                    if (String.valueOf(userinput).charAt(i) != String.valueOf(userinput).charAt(j)) {
                    	hasDifferentDigits = true;
                        break;
                    }
                }
                if (hasDifferentDigits) {
                    break;
                }
            }
			if(!hasDifferentDigits){
				System.out.println("Too many digits are similar. Try again.");
				continue;
			}
			else {
				validInput = true;
				kaprekar(userinput);
				sc.close();
			}
		}
	}
}