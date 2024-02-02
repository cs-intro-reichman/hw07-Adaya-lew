public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
			for (int i = 0; i < 3000; i++) {                       //go over the 3000 words string
				dictionary [i] = in.readString();                  //make dictionary an array that is all the string (readString read all the doc of words)
    		}
		return dictionary;                                        //returns the dictionary in array form
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		// Your code here

		for (int i = 0; i < dictionary.length; i++) {                 //go over the words in the dictionary (the dictionary contains words (doent read per letter))
			if (dictionary[i].equals(word)){                                   //if the word in index i is the same as the word we got return true, then goes to the next word i++
			return true;
			}
		}
	return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }

        int N = hashtag.length();
		String lowCaseHash = hashtag.toLowerCase();

        for (int i = 1; i <= N; i++) {
		
			if (existInDictionary(lowCaseHash.substring(0, i), dictionary)){               //calls the previos function to check if the letters between 0 and i are a word in the dictionary
				System.out.println(lowCaseHash.substring(0, i));                           //if it is a word, print it, if not continues to i++

				breakHashTag(lowCaseHash.substring(i, N), dictionary);                                //if it was a word make the new variables we recieve in the current function to be form the index finishing
				                                                                                    //the word to the end of the string, and the dictionary and make the check again
				return;
       		 }
   		 }

	}
}