package compoundword;


	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;

	public class assigmnet {

	    static class DigitalTree {
	        DigitalTree[] children = new DigitalTree[26];
	        boolean wordend;

	        void insert(String word) {
	            DigitalTree pCrawl = this;
	            for (char c : word.toLowerCase().toCharArray()) {
	                int index = c - 'a';
	                if (pCrawl.children[index] == null)
	                    pCrawl.children[index] = new DigitalTree();
	                pCrawl = pCrawl.children[index];
	            }
	            pCrawl.wordend = true;
	        }

	        boolean search(String word) {
	            DigitalTree pCrawl = this;
	            for (char c : word.toLowerCase().toCharArray()) {
	                int index = c - 'a';
	                if (pCrawl.children[index] == null)
	                    return false;
	                pCrawl = pCrawl.children[index];
	            }
	            return pCrawl.wordend;
	        }

	        boolean compoundedword(String str) {
	            for (int i = 1; i <= str.length(); i++) {
	                if (search(str.substring(0, i)) && (i == str.length() || compoundedword(str.substring(i)))) {
	                    return true;
	                }
	            }
	            return false;
	        }
	    }

	    public static void main(String[] args) {
	        String filename = "F:\\Eclipse project\\arraypratice\\Input_01.txt";
	        solve(filename);
	    }

	    public static void solve(String filename) {
	        File file = new File(filename);
	        if (!file.exists()) {
	            System.out.println("File not found: " + filename);
	            return;
	        }

	        try {
	            long startTime = System.nanoTime();
	            ArrayList<String> listOfWords = getdata(filename);
	            DigitalTree root = new DigitalTree();
	            for (String s : listOfWords)
	                root.insert(s);

	            String firstAnswer = "";
	            String secondAnswer = "";
	            for (String str : listOfWords) {
	                if (root.compoundedword(str)) {
	                    if (str.length() > firstAnswer.length()) {
	                        secondAnswer = firstAnswer;
	                        firstAnswer = str;
	                    } else if (str.length() > secondAnswer.length()) {
	                        secondAnswer = str;
	                    }
	                }
	            }

	            System.out.println("Longest Compound Word: " + firstAnswer);
	            System.out.println("Second Longest Compound Word: " + secondAnswer);
	            System.out.println("Execution Time: " + (System.nanoTime() - startTime) / 1e6 + "ms");
	        } catch (IOException e) {
	            System.out.println("Error reading file: " + e.getMessage());
	        }
	    }

	    public static ArrayList<String> getdata(String filename) throws IOException {
	        ArrayList<String> array = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
	            String string;
	            while ((string = br.readLine()) != null) {
	                array.add(string.trim().toLowerCase());
	            }
	        }
	        return array;
	    }
	}