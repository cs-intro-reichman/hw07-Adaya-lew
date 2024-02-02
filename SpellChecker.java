
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1)
			return "";
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int l1 = word1.length();
		int l2 = word2.length();
		if (l2 == 0)
		{
            return l1;
        }
		else if (l1 == 0)
		{
			return l2;
		} 
		else if (word1.charAt(0) == word2.charAt(0))
		{
			return levenshtein(tail(word1),tail(word2));
		}
		else
		{
			return 1+minimal(levenshtein(tail(word1),word2),levenshtein(word1,tail(word2)),levenshtein(tail(word1),tail(word2)));
		}
		
	}




	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
				dictionary[i] = in.readString();
			}
		return dictionary;
	}


	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = word.length();
		String minimal = word;
		for(int i = 0; i < dictionary.length; i++){
			int distance = levenshtein(word, dictionary[i]);
			if(distance <= threshold){
			 	if(distance < min){
				min = distance;
				minimal = dictionary[i];
				}
			}
		}
		return minimal;
	}
	

}
