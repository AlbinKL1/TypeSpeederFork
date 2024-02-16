package se.ju23.typespeeder.Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Database.DatabaseManager;

import java.util.List;
import java.util.Scanner;

@Component
public class Challenge {

    private final DatabaseManager databaseManager;
    private final Scanner input;

    @Autowired
    public Challenge(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.input = new Scanner(System.in);
    }

    public void startChallenge() {
        String language = selectLanguage();
        List<String> letters = (language.equals("English")) ? databaseManager.getEnglishLetters() : databaseManager.getSwedishLetters();
        List<String> symbols = (language.equals("English")) ? databaseManager.getEnglishSymbols() : databaseManager.getSwedishSymbols();
        List<String> words = (language.equals("English")) ? databaseManager.getEnglishWords() : databaseManager.getSwedishWords();
        List<String> sentences = (language.equals("English")) ? databaseManager.getEnglishSentences() : databaseManager.getSwedishSentences();
        selectLevel(letters,symbols,words,sentences);
    }

    private String selectLanguage() {
        System.out.println("Select Language:");
        System.out.println("1. English");
        System.out.println("2. Svenska");
        int choice = input.nextInt();
        input.nextLine();
        return (choice == 1) ? "English" : "Swedish";
    }

    private void selectLevel(List<String> letters, List<String> symbols, List<String> words, List<String> sentences) {
        
        boolean continueLoop = true;

        do{
            System.out.println("""
                
                Select Level
                
                1. Letters.
                2. Words.
                3. Sentences.
                4. Letters and Symbols.
                5. Words and Symbols.
                6. Sentences and Symbols.
                7. Letters and Words.
                8. Letters and Sentences.
                9. Words and Sentences.
                10. Letters, Words, and Sentences.
                11. Letters, Words, Symbols, and Sentences.
                0. Exit program.
                """);

            System.out.print("Your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> lettersToType(letters);
                case 2 -> wordsToType(words);
                case 3 -> sentencesToType(sentences);
                case 4 -> lettersAndSymbolsToType(letters,symbols);
                case 5 -> wordsAndSymbolsToType(words,symbols);
                case 6 -> sentencesAndSymbolsToType(sentences,symbols);
                case 7 -> lettersAndWordsToType(letters,words);
                case 8 -> lettersAndSentencesToType(letters,sentences);
                case 9 -> wordsAndSentencesToType(words,sentences);
                case 10 ->lettersWordsAndSentencesToType(letters,words,sentences);
                case 11 ->lettersWordsSymbolsAndSentencesToType(letters,words,symbols,sentences);
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }while(continueLoop);
        
    }

    private void lettersToType(List<String> letters) {
        System.out.println("Playing Letters.");
    }

    private void wordsToType(List<String> words) {
        System.out.println("Playing Words.");
    }

    private void sentencesToType(List<String> sentences) {
        System.out.println("Playing Sentences.");
    }

    private void lettersAndSymbolsToType(List<String> letters, List<String> symbols) {
        System.out.println("Playing Letters and Symbols.");
    }

    private void wordsAndSymbolsToType(List<String> words, List<String> symbols) {
        System.out.println("Playing Words and Symbols.");
    }

    private void sentencesAndSymbolsToType(List<String> sentences, List<String> symbols) {
        System.out.println("Playing Sentences and Symbols.");
    }

    private void lettersAndWordsToType(List<String> letters, List<String> words) {
        System.out.println("Playing Letters and Words .");
    }

    private void lettersAndSentencesToType(List<String> letters, List<String> sentences) {
        System.out.println("Playing Letters and Sentences.");
    }

    private void wordsAndSentencesToType(List<String> words, List<String> sentences) {
        System.out.println("Playing Words and Sentences.");
    }

    private void lettersWordsAndSentencesToType(List<String> letters, List<String> words, List<String> sentences) {
        System.out.println("Playing Letters,Words and Sentences.");
    }

    private void lettersWordsSymbolsAndSentencesToType(List<String> letters, List<String> words, List<String> symbols, List<String> sentences) {
        System.out.println("Playing Letters,Words,Symbols and Sentences.");
    }
}
