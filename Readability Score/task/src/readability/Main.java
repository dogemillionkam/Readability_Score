package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File userTextFile = new File(args[0]);
//            File userTextFile = new File("/Users/RajaK/IdeaProjects/Readability Score1/Readability Score/task/src/readability/testText.txt");

            StringBuilder userText = new StringBuilder();
            Scanner scanner = new Scanner(userTextFile);
            while(scanner.hasNextLine()) {
                userText.append(scanner.nextLine());
            }
            String userTextInputted = userText.toString();
            Readability objectOne = new Readability(userTextInputted);
            objectOne.getScores();

        } catch (FileNotFoundException fnfe) {
            System.out.println("This file does not exist. Try again.");
        }
    }
}
