package IO;
import java.io.*;
import java.util.*;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream outBefore;
    private OutputStream outAfter;


    /** MyCompressorOutputStream Constructor */
    public MyCompressorOutputStream(OutputStream outputStream) throws FileNotFoundException {
        this.outBefore = outputStream;
        this.outAfter = new FileOutputStream("savedMazeAfter.maze");
    }

    @Override
    public void write(int b) throws IOException {
    }

    @Override
    public void write(byte[] b) throws IOException {
        // Before Compression File
        try{ outBefore.write(Arrays.toString(b).getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }

        // After Compression File
        List<Integer> result = compress(Arrays.toString(b));
        try{ outAfter.write(result.toString().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Integer> compress(String uncompressed) {
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
