public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
				dictionary[i] = in.readString();
			}
		return dictionary;
	}

		

	

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {                 
			if (dictionary[i].equals(word)){                                   
			return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		if (hashtag.isEmpty()) {
            return;
        }

        int N = hashtag.length();
		String lowCaseHash = hashtag.toLowerCase();

        for (int i = 1; i <= N; i++) {
		
			if (existInDictionary(lowCaseHash.substring(0, i), dictionary)){               
				System.out.println(lowCaseHash.substring(0, i));                           

				breakHashTag(lowCaseHash.substring(i, N), dictionary);                                
				                                                                                    
				return;
       		 }
   		 }

	}
}