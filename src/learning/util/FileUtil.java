package learning.util;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by edmundas on 23/01/2015.
 */
public class FileUtil {

    public static List<String> readLines(File file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = new LinkedList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch(Exception e) {
                // ignore
            }
        }
        return lines;
    }
}