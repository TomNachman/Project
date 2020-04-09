package IO;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    /** MyDecompressorInputStream Constructor */
    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException {

        List<Integer> myList = new ArrayList<>();
        int content;
        while ((content = in.read()) != -1) {
            myList.add(content);
        }

        byte [] returnBytes = decompress(myList);
        System.arraycopy(returnBytes, 0, b, 0, b.length);
        return 0;
    }

    public byte[] decompress(List<Integer> compressed) {
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
        return String.valueOf(result).getBytes();
    }
}
