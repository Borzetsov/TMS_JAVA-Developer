import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classname    HomeWork16
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         24.04.2026
 */

public class HomeWork16 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №16 | Выдано 22.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);
        String userString;

        System.out.println("Задача предполагает ввод текста.");
        System.out.println("Предложение <Конец текста.> означает конец ввода.");
        System.out.println("Введите текст");

        //Хранит весь текст
        StringBuilder userInputText = new StringBuilder();
        do {
            userString = consoleScanner.nextLine();
            userInputText.append(userString);
            userInputText.append(" ");
        } while (!userString.contains("Конец текста."));

        /*Вычленить слова*/
        //Флаг UNICODE_CHARACTER_CLASS для работы с кириллицей
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher wordMatcher = wordPattern.matcher(userInputText);
        StringBuilder cleanText = new StringBuilder();

        while (wordMatcher.find()) {
            cleanText.append(wordMatcher.group()).append(" ");
        }

        /*Разделить текст на слова*/
        String[] cplitString = cleanText.toString().split(" ");

        /*Записать каждое слово в HashMap*/
        HashMap<String, Boolean> wordsHashMap = new HashMap<>();
        boolean notUnique;
        for (String currentString : cplitString) {
            /*Ключ -- само слово, значение -- признак уникальности*/
            if (wordsHashMap.containsKey(currentString)) {
                //Если такое слово уже встречалось, установить признак
                notUnique = true;
            }
            else {
                //Такое слово встретилось впервые
                notUnique = false;
            }
            wordsHashMap.put(currentString, notUnique);
        }
        System.out.println(wordsHashMap);
        System.out.println();

        System.out.println("Задача 2");
        System.out.println();
        System.out.println("Используется введенный ранее текст.");

        /*Разделить текст на строки*/
        String[] singleStrings = userInputText.toString().split("\\.");

        HashMap<String, String> task2HashMap = new HashMap<>();
        /*Ключ -- первый символ, значение -- последний*/
        for (String currentString : singleStrings) {
            String str = currentString.trim();
            if (!str.isEmpty()) task2HashMap.put(str.substring(0, 1), str.substring(str.length() - 1));
        }
        System.out.println(task2HashMap);
    }
}
