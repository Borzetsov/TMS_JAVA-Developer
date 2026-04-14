/**
 * Classname    HomeWork14
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         14.04.2026
 */
 
package HomeWork14;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork14 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №14 | Выдано 13.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();
        /*Чтение файла*/
        File filePath = new File("src/HomeWork14/Task1/");
        File inputFile = new File(filePath, "RnJ_Synopsis.txt");
        try (FileReader inputFileReader = new FileReader(inputFile)) {
            StringBuilder inputText = new StringBuilder();
            /*Составить текст*/
            int currentChar;
            while((currentChar = inputFileReader.read()) != -1) {
                inputText.append((char)currentChar);
            }
            /*Вычленить слова*/
            Pattern wordPattern = Pattern.compile("\\b[\\w]+\\b");  //Любое слово
            Matcher wordMatcher = wordPattern.matcher(inputText);
            StringBuilder cleanText = new StringBuilder();
            while (wordMatcher.find()) {
                cleanText.append(inputText.subSequence(wordMatcher.start(), wordMatcher.end()));
                cleanText.append(" ");  //Разделитель
            }
            /*Разделить текст на слова*/
            String[] cplitString = cleanText.toString().split(" ");
            /*Найти самое длинное слово*/
            String theLongestWord = "";
            for (String currentString : cplitString) {
                if (currentString.length() > theLongestWord.length()) {
                    theLongestWord = currentString;
                }
            }
            File outputFile = new File(filePath, "RnJ_TheLongestWord.txt");
            try (FileWriter outputFileWriter = new FileWriter(outputFile)) {
                outputFileWriter.write("The longest word of the text is: " + theLongestWord);
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл");
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            System.out.println(e.getMessage());
        }
    }
}
