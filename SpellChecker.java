
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
		int minimal = levenshtein(word,dictionary[0]);
		int index = 0;
		int lv = 0;
		if (dictionary[0].equals(word))
		{
			return dictionary[0];
		}
		else 
			{
			for ( int i = 1; i < dictionary.length; i++)
		        {
			      lv = levenshtein(word,dictionary[i]);
			      if (lv < minimal) 
			       {
				       minimal = lv;
				       index = i;
			        }
		         }
		         if (minimal > threshold)
		         {
		         	return word;
		         }
		         else 
		         {
		         	return dictionary[index];
		         }

		}
	}
}
