package readability;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


class Readability {

    private final String userInput;
    private double characters;
    private double sentences;

    private double words;

    private double syllables;
    private double polySyllables;
    private BigDecimal overallAverage = BigDecimal.valueOf(0);

    Readability(String userInput) {
        this.userInput = userInput;
    }

    public void getScores() {
        String[] sentenceArray = userInput.split("[!.?]");
        //total amount of characters
        characters = userInput.replaceAll("[\\s\\n\\t]", "").length();
        //total amount of sentences
        sentences = sentenceArray.length;
        //total amount of words.
        words = userInput.split(" |\\n|\\t").length;
        //total amount of syllables and polysyllables
        String tempString = userInput.replaceAll("e\\b", "#").replaceAll("[aeiouyAEIOUY]{2,}", "*").replaceAll("[.?!]", "");
        String[] tempStringTwo = tempString.split(" ");
        for (String s : tempStringTwo) {
            int replaceAll = s.length() - s.replaceAll("[aeiouyAEIOUY*]", "").length();
            syllables += replaceAll > 0 ? replaceAll : 1;
            polySyllables += replaceAll > 2 ? 1 : 0;
        }

        //calling the functions
        System.out.printf("Words: %s\nSentences: %s\nCharacters: %s\nSyllables: %s\nPolysyllables: %s\n", (int) words, (int) sentences, (int) characters, (int) syllables, (int) polySyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "ARI" -> automatedReadabilityIndex();
            case "FK" -> fleschKincaidIndex();
            case "SMOG" -> smogIndex();
            case "CL" -> colemanIndex();
            default -> {
                automatedReadabilityIndex();
                fleschKincaidIndex();
                smogIndex();
                colemanIndex();
                System.out.printf("This text should be understood in average by %s-year-olds.", overallAverage.divide(BigDecimal.valueOf(4)).setScale(2, RoundingMode.UP));
            }
        }

    }

    private void fleschKincaidIndex() {
        BigDecimal score = BigDecimal.valueOf(0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59);
        printingFunction(score, "Flesch–Kincaid readability tests: ");
    }


    private void automatedReadabilityIndex() {
        //readabilityIndex
        BigDecimal score = BigDecimal.valueOf(4.71 * (characters / words) + 0.5 * (words / sentences) - 21.43);
        printingFunction(score, "Automated Readability Index: ");
    }

    private void smogIndex() {
        BigDecimal score = BigDecimal.valueOf(1.043 * Math.sqrt(polySyllables * (30 / sentences)) + 3.1291);
        printingFunction(score, "Simple Measure of Gobbledygook: ");
    }

    private void colemanIndex() {
        double L = characters / words * 100;
        double S = sentences / words * 100;
        BigDecimal score = BigDecimal.valueOf(0.0588 * L - 0.296 * S - 15.8);
        printingFunction(score, "Coleman–Liau index: ");
    }

    private void printingFunction(BigDecimal score, String testType) {
        System.out.print(testType + score.setScale(2, RoundingMode.DOWN) + " ");
        switch ((score.setScale(0, RoundingMode.UP).intValue())) {
            case 1 -> {
                System.out.println("(about 6-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(6.0));
            }
            case 2 -> {
                System.out.println("(about 7-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(7.0));
            }
            case 3 -> {
                System.out.println("(about 8-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(8.0));
            }
            case 4 -> {
                System.out.println("(about 9-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(9.0));
            }
            case 5 -> {
                System.out.println("(about 10-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(10.0));
            }
            case 6 -> {
                System.out.println("(about 11-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(11.0));
            }
            case 7 -> {
                System.out.println("(about 12-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(12.0));
            }
            case 8 -> {
                System.out.println("(about 13-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(13.0));
            }
            case 9 -> {
                System.out.println("(about 14-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(14.0));
            }
            case 10 -> {
                System.out.println("(about 15-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(15.0));
            }
            case 11 -> {
                System.out.println("(about 16-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(16.0));
            }
            case 12 -> {
                System.out.println("(about 17-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(17.0));
            }
            case 13 -> {
                System.out.println("(about 18-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(18.0));
            }
            default -> {
                System.out.println("(about 22-year-olds).");
                overallAverage = overallAverage.add(BigDecimal.valueOf(22.0));
            }
        }
    }
}