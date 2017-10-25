package tp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adilbelhaji on 10/21/17.
 */
public class FileUtils {
    public static List<Pair<Integer, String>> getPairListForKeyAge(String filename) {
        List<Pair<Integer,String>> list = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("all.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));
            String line = null;
            while ((line = br.readLine()) != null){
                String[] row = line.trim().split("\\|");
                list.add(new Pair<>(Integer.parseInt(row[1]),row[0]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
