public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
				// Your code goes here

		if (str.length() == 1){
			return "";
		}
		return str.substring(1);

	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
	
		//first we will change the word to lower case since the words in the dictionary are in lower case to compare
		String lowCaseWord1 = word1.toLowerCase();
		String lowCaseWord2 = word2.toLowerCase();
	
		int length1 = lowCaseWord1.length();
		int length2 = lowCaseWord2.length();
        //if one of the words is an empty string, the numbr of changes between the words will be the number of characters = string lenth of the other
	if (length1 == 0){
		return length2;
	}
	 
	if (length2 ==0){
		return length1;
	}
	//if the first char in each word is the same, continue checking the tails
	if (lowCaseWord1.charAt(0) == lowCaseWord2.charAt(0)){
		return levenshtein(tail(lowCaseWord1),tail (lowCaseWord2));
	}
	//if the first char is not the same, count this as 1 change and add the minimal number of changes between each comparison:
	//comparison 1: word1 and tail word2
	//comparison 2: tail word1 and word2
	//and then the minimal of the first 2 comparisons and the third comparison=
	//comparison 3: tail word1 and tail word2 
	else{
		return 1 + Math.min(Math.min(levenshtein(tail(lowCaseWord1), lowCaseWord2), levenshtein(lowCaseWord1, tail(lowCaseWord2))), levenshtein(tail(lowCaseWord1), tail(lowCaseWord2)));

	}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < 3000; i++) {                       
			dictionary [i] = in.readString();                  
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here

		int numOfChange = levenshtein(word, dictionary[0]);
		String minChangeWord = dictionary[0];

				for (int i = 1; i < dictionary.length; i++) {
					int editDistance = levenshtein(word, dictionary[i]);

					if (editDistance < numOfChange){
						numOfChange = editDistance;
						minChangeWord = dictionary[i];
					}
				}

					if (numOfChange > threshold)
						return word;
				return minChangeWord;
			
				}
		}
