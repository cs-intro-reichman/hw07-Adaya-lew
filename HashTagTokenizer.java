

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = in.readLine();
        }

        return dictionary;
    }

		

	

	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0; i < dictionary.length; i++){
			if(dictionary[i].equals(word)){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		String lowHash = hashtag.toLowerCase();
        if (hashtag.isEmpty()) {
            return;
        }
		
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			if(existInDictionary(lowHash.substring(0,i),dictionary)){// using the f that we craet befor.
				System.out.println(lowHash.substring(0,i));

				breakHashTag(hashtag.substring(i, N), dictionary);// starting from when we stop the next world
				return;
			}
        }
    }

}