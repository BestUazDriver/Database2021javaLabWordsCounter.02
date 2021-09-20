package Counter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src\\main\\Resources\\application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config=new HikariConfig();
        config.setPassword(properties.getProperty("db.password"));
        config.setUsername(properties.getProperty("db.user"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikariPoolSize")));
        DataSource dataSource = new HikariDataSource(config);
        WordsCounterImpl counter = new WordsCounterImpl();
        System.out.println("(),.-?!\"");
        //System.out.println(counter.wordsCount("C:\\Users\\admin\\IdeaProjects\\WOrdsCounter\\src\\main\\java\\Info\\Text"));
    }
}
