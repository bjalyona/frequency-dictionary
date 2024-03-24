import java.io.*;
import java.util.*;

public class FreqDict {
    HashMap<Character, Integer> dictionary = new HashMap<>();
    public void getFiles(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the input file:");
        String inputName = scan.nextLine();
        if (readFile(inputName)) {
            System.out.println("Enter the name of the output file:");
            String outputName = scan.nextLine();
            writeDict(outputName);

        }
        scan.close();
    }
    public boolean readFile(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                putIntoDict(line);
            }
            br.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    public void putIntoDict(String s){
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)){
                if (dictionary.containsKey(c)){
                    int tmp = dictionary.get(c);
                    tmp++;
                    dictionary.put(c, tmp);
                }
                else {
                    dictionary.put(c, 1);
                }
            }
        }
    }
    public void writeDict(String fileName){
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            ArrayList<Character> sortedKeys = new ArrayList<>(dictionary.keySet());
            Collections.sort(sortedKeys);
            for (Character x : sortedKeys)
                pw.println(x + "-" + dictionary.get(x));
            pw.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
