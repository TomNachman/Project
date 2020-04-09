package IO;
import java.io.*;
import java.util.*;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream outBefore;
    private OutputStream outAfter;
    private OutputStream outAfterDec;



    /** MyCompressorOutputStream Constructor */
    public MyCompressorOutputStream(OutputStream outputStream) throws FileNotFoundException {
        this.outBefore = outputStream;
        this.outAfter = new FileOutputStream("savedMazeAfter.maze");
        this.outAfterDec = new FileOutputStream("savedMazeAfterDecompression.maze");
    }

    @Override
    public void write(int b) throws IOException {
        //String s = "100010101010001010101010101101010101010101010000011111111000001100100101";
        //byte[]bytes = s.getBytes();
        //System.out.println(String.format("Before: %d", bytes.length));
        //compress(Arrays.toString(bytes));
        //System.out.println(Arrays.toString(bytes));
        //compress(s);
        //byte[]bytes = new byte[s.length()];
        //for(int i=0;i<s.length();i++)
        //    bytes[i]=s[i];
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

        // After Compression File
        String afterDecompression = decompress(result);
        try{ outAfterDec.write(afterDecompression.getBytes());
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


    public static String decompress(List<Integer> compressed) {
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
        return result.toString();
    }

}
