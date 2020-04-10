package IO;
import java.io.*;
import java.util.*;

/**
 *
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /** MyCompressorOutputStream Constructor */
    public MyCompressorOutputStream(OutputStream outputStream) throws FileNotFoundException {
        this.out = outputStream;
    }

    /** Interface write */
    @Override
    public void write(int b) throws IOException {
    }

    /** The Main function of the class - write the data from the byte[] array to the  file (after compressing) */
    @Override
    public void write(byte[] b) throws IOException {
        List<Integer> result = compress(Arrays.toString(b));
        try{ out.write(result.toString().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /** Compress a String to a list of List<Integer> According to LZW - Algorithm*/
    private List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);

        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));

        return result;
    }

}
