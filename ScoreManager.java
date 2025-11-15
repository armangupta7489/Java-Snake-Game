import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";

    public static void saveScore(String user, int score) {
        HashMap<String, Integer> map = loadScores();

        if (!map.containsKey(user) || score > map.get(user)) {
            map.put(user, score);
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (String key : map.keySet()) {
                bw.write(key + ":" + map.get(key));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static HashMap<String, Integer> loadScores() {
        HashMap<String, Integer> map = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                map.put(parts[0], Integer.parseInt(parts[1]));
            }
            br.close();
        } catch (Exception ignored) {}

        return map;
    }
}
