/**
 * Classname    HomeWork14
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         14.04.2026
 */
 
package HomeWork14;

import java.io.*;

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
            String theLongestWord = "";
            StringBuilder inputText = new StringBuilder();
            /*Составить текст*/
            int currentChar;
            while((currentChar = inputFileReader.read()) != -1) {
                inputText.append((char)currentChar);
            }
            System.out.println(inputText);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            System.out.println(e.getMessage());
        }
    }
}
