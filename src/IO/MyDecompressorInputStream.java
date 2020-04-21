package IO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    /** MyDecompressorInputStream Constructor */
    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    /** Interface read */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /** The Main function of the class - read the data from the file and put it in the byte[] array
     * @param b - The array to insert the data
     */
    public int read(byte[] b) throws IOException {

        // Reading the data from the file and converting to List<Integer>
        List<Integer> myList = fileToBytesString();

        // Decompress the List and return String
        String strAfter = decompress(myList);

        // Converting From string to int[]
        int[] arr2 = Arrays.stream(strAfter.substring(1, strAfter.length()-1).split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();

        for(int i=0;i<arr2.length;i++){
            b[i] = (byte)arr2[i];
        }
        return 0;
    }

    /** Reading the data from the file and converting to List<Integer>
     * @return List of Integers
     */
    private List<Integer> fileToBytesString(){
        try{
            System.out.println(String.format("input stream %s", in.toString()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();

            // Reading the data from the file and converting to String
            int[] arr = Arrays.stream(out.substring(1, out.length()-1).split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();

            List<Integer> myList = new ArrayList<>();
            for(Integer i:arr){ myList.add(i);}
            return myList;
        }
        catch (IOException e){e.printStackTrace();}
        return null;
    }

    /** Decompress a list of List<Integer> to a string According to LZW - Algorithm*/
    private String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        //System.out.println(result.toString());
        return result.toString();
    }
}
