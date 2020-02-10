import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a converter using MorseCodeTree to translate Morse code to English word.
 * 
 * @author Derek Luong
 *
 */
public class MorseCodeConverter {

	public MorseCodeConverter() {
		
	}
	
	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them. 
	 * Uses the toArrayList method in MorseCodeTree It should return the data in this order:
	 * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	 * Note the extra space between j and b - that is because there is an empty string that is the root, 
	 * and in the LNR traversal, the root would come between the right most child of the left tree (j) 
	 * and the left most child of the right tree (b). This is used for testing purposes to make sure 
	 * the MorseCodeTree has been built properly
	 * 
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree() {
		MorseCodeTree morseCodeTree = new MorseCodeTree();
		ArrayList<String> codeList = morseCodeTree.toArrayList();
		String result = "";
		for (String code : codeList) {
			result += code + " ";
		}
		return result.trim();
	}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * Example:
	 * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	 * string returned = "Hello World" 
	 * 
	 * @param code - the morse code
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		MorseCodeTree morseCodeTree = new MorseCodeTree();
		String[] letterCodes = code.split(" ");
		String decodedText = "";
		if (letterCodes != null && letterCodes.length > 0) {
			for (int i=0; i < letterCodes.length; i++) {
				if ("/".equals(letterCodes[i])) {
					decodedText += " ";
				} else {
					String letter = morseCodeTree.fetch(letterCodes[i]);
					decodedText += letter;
				}
			}
		}
		return decodedText;
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * Example:
	 * a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	 * string returned = "Hello World"
	 * 
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws java.io.FileNotFoundException - when there are any errors with reading, closing codeFile
	 */
	public static String convertToEnglish(File codeFile)
            throws java.io.FileNotFoundException {
        try {
        	// FileReader reads text files in the default encoding.
    		FileReader fileReader = new FileReader(codeFile);
    		// Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        	String inputText = "";
        	String line = null;
			while((line = bufferedReader.readLine()) != null) {
				String decodedLine = convertToEnglish(line);
				if (!"".equals(inputText)) {
					inputText += "\n";
				}
				inputText += decodedLine;
				
			}
			bufferedReader.close();
			fileReader.close();
			
			return inputText;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
}
