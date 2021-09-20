package Counter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        WordsCounterImpl wordsCounter=context.getBean(WordsCounterImpl.class);
        String path="C:\\Users\\Пользователь\\JavaFiles\\untilted\\sabitov_itis_2021_lab\\src\\main\\java\\Info\\Text";
        Map<String, Integer> wordsCount;
        wordsCount=wordsCounter.wordsCount(path);
        System.out.println(wordsCount);
        wordsCounter.dataBase(wordsCount);
    }
}
