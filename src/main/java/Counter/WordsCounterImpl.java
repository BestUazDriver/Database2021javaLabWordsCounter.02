package Counter;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsCounterImpl {
    private JdbcTemplate jdbcTemplate;

    public WordsCounterImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_Save = "insert into words (word, count) values (?,?)";

    public Map<String, Integer> wordsCount(String path) {
        HashMap<String, Integer> wordsCount = new HashMap<>();
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[(),.-?!\"]", "");
                System.out.println(line);
                String[] atributes = line.split(" ");
                words.addAll(Arrays.asList(atributes));
            }
            for (int i = 0; i < words.size(); i++) {
                wordsCount.put(words.get(i), Collections.frequency(words, words.get(i)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsCount;
    }

    private void dataBase(Map<String, Integer> wordsCount) {
        for (String word : wordsCount.keySet()) {
            jdbcTemplate.update(SQL_Save, word, wordsCount.get(word));
        }
    }
}
