package file;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TextCompressionExample {
    public static void main(String[] args) throws IOException {
        String text = "This is a sample text to compress.";

        // Compressing the text
        ByteArrayOutputStream compressedBytes = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutput = new GZIPOutputStream(compressedBytes)) {
            gzipOutput.write(text.getBytes("UTF-8"));
        }

        // Decompressing the text
        ByteArrayInputStream compressedInput = new ByteArrayInputStream(compressedBytes.toByteArray());
        try (GZIPInputStream gzipInput = new GZIPInputStream(compressedInput);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInput, "UTF-8"))) {
            String decompressedText = reader.readLine();
            System.out.println("Decompressed Text: " + decompressedText);
        }
    }
}