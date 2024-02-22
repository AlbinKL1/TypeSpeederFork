package se.ju23.typespeeder.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.DatabaseAndUtility.UtilityService;

import java.util.*;

@Service
public class ChallengeService {

    private final EntityManager databaseManager;

    @Autowired
    public ChallengeService(EntityManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    //Method to get all the different letters, words, symbols and sentences.
    public void startChallenge() {
        String language = selectLanguage();
        List<String> letters = (language.equals("English")) ? databaseManager.getEnglishLetters() : databaseManager.getSwedishLetters();
        List<String> symbols = (language.equals("English")) ? databaseManager.getEnglishSymbols() : databaseManager.getSwedishSymbols();
        List<String> words = (language.equals("English")) ? databaseManager.getEnglishWords() : databaseManager.getSwedishWords();
        List<String> sentences = (language.equals("English")) ? databaseManager.getEnglishSentences() : databaseManager.getSwedishSentences();
        selectLevel(letters,symbols,words,sentences);
    }

    //Selecting what type of language to play in.
    public String selectLanguage() {
        System.out.println("\nSelect Language: ");
        System.out.println("1. Play in English.");
        System.out.println("2. Spela i Svenska.");

        System.out.print("Your choice: ");
        int choice = UtilityService.getIntInput();

        return (choice == 1) ? "English" : "Svenska";
    }

    //Level selection.
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
                0. Back.
                """);

            System.out.print("Your choice: ");
            int choice = UtilityService.getIntInput();

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

    //All the game logic.
    public void gameLogic(List<String> inputList){
        long startTime = System.currentTimeMillis();
        List<String> randomizedList = new ArrayList<>(inputList);
        Collections.shuffle(randomizedList);
        StringBuilder promptBuilder = new StringBuilder("Type: ");
        Random random = new Random();
        for (String item : randomizedList) {
            char[] chars = item.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (Character.isLetter(c)) {
                    if (random.nextBoolean()) {
                        chars[i] = Character.toUpperCase(c);
                    } else {
                        chars[i] = Character.toLowerCase(c);
                    }
                }
            }
            promptBuilder.append(chars).append(" ");
        }
        System.out.println(promptBuilder.toString());

        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine().trim();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        int correctCount = 0;
        for (int i = 0; i < userInput.length() && i < randomizedList.size(); i++) {
            if (Character.toLowerCase(userInput.charAt(i)) == Character.toLowerCase(randomizedList.get(i).charAt(0))) {
                correctCount++;
            }
        }

        double accuracy = (double) correctCount / Math.min(userInput.length(), randomizedList.size()) * 100;

        System.out.println("\nTime taken: " + elapsedTime / 1000 + " seconds");
        System.out.println("Correctly typed: " + correctCount + " out of " + Math.min(userInput.length(), randomizedList.size()));
        System.out.println("Accuracy: " + accuracy + "%");
        int points = (int) (correctCount * accuracy / (elapsedTime / 1000));


        int timeLimitSeconds = 60;
        int accuracyThreshold = 50;
        int pointsToDeduct = 0;

        if (accuracy < accuracyThreshold && (elapsedTime / 1000) > timeLimitSeconds) {
            pointsToDeduct = (int) ((accuracyThreshold - accuracy) * (elapsedTime / 1000 - timeLimitSeconds));
            System.out.println("Accuracy below 50% and time exceeded limit. Points deducted: " + pointsToDeduct);
        }

        if (pointsToDeduct > 0) {
            points -= pointsToDeduct;
            if (points < 0) {
                points = 0;
            }
        }
        int experience = (int) (points * 0.1);

        System.out.println("\nPoints earned: " + points);
        System.out.println("Experience earned: " + experience);

        int playerId = databaseManager.getPlayerIdByUsername(UtilityService.getLoggedInUsername());
        databaseManager.insertPointsAndExperience(playerId, points);
    }

    //Game methods to play typespeeder.
    public void lettersToType(List<String> letters) {
        System.out.println("Playing Letters.");
        gameLogic(letters);
    }
    public void wordsToType(List<String> words) {
        System.out.println("Playing Words.");
        gameLogic(words);
    }
    public void sentencesToType(List<String> sentences) {
        System.out.println("Playing Sentences.");
        gameLogic(sentences);
    }
    public void lettersAndSymbolsToType(List<String> letters, List<String> symbols) {
        System.out.println("Playing Letters and Symbols.");
        List<String> lettersAndSymbols = new ArrayList<>();
        lettersAndSymbols.addAll(letters);
        lettersAndSymbols.addAll(symbols);
        gameLogic(lettersAndSymbols);
    }
    public void wordsAndSymbolsToType(List<String> words, List<String> symbols) {
        System.out.println("Playing Words and Symbols.");
        List<String> wordsAndSymbols = new ArrayList<>();
        wordsAndSymbols.addAll(words);
        wordsAndSymbols.addAll(symbols);
        gameLogic(wordsAndSymbols);
    }
    public void sentencesAndSymbolsToType(List<String> sentences, List<String> symbols) {
        System.out.println("Playing Sentences and Symbols.");
        List<String> sentencesAndSymbols = new ArrayList<>();
        sentencesAndSymbols.addAll(sentences);
        sentencesAndSymbols.addAll(symbols);
        gameLogic(sentencesAndSymbols);
    }
    public void lettersAndWordsToType(List<String> letters, List<String> words) {
        System.out.println("Playing Letters and Words .");
        List<String> lettersAndWords = new ArrayList<>();
        lettersAndWords.addAll(letters);
        lettersAndWords.addAll(words);
        gameLogic(lettersAndWords);
    }
    public void lettersAndSentencesToType(List<String> letters, List<String> sentences) {
        System.out.println("Playing Letters and Sentences.");
        List<String> lettersAndSentences = new ArrayList<>();
        lettersAndSentences.addAll(letters);
        lettersAndSentences.addAll(sentences);
        gameLogic(lettersAndSentences);
    }
    public void wordsAndSentencesToType(List<String> words, List<String> sentences) {
        System.out.println("Playing Words and Sentences.");
        List<String> wordsAndSentences = new ArrayList<>();
        wordsAndSentences.addAll(words);
        wordsAndSentences.addAll(sentences);
        gameLogic(wordsAndSentences);
    }
    public void lettersWordsAndSentencesToType(List<String> letters, List<String> words, List<String> sentences) {
        System.out.println("Playing Letters,Words and Sentences.");
        List<String> lettersWordsAndSentences = new ArrayList<>();
        lettersWordsAndSentences.addAll(letters);
        lettersWordsAndSentences.addAll(words);
        lettersWordsAndSentences.addAll(sentences);
        gameLogic(lettersWordsAndSentences);
    }
    public void lettersWordsSymbolsAndSentencesToType(List<String> letters, List<String> words, List<String> symbols, List<String> sentences) {
        System.out.println("Playing Letters,Words,Symbols and Sentences.");
        List<String> lettersWordsSymbolsAndSentences = new ArrayList<>();
        lettersWordsSymbolsAndSentences.addAll(letters);
        lettersWordsSymbolsAndSentences.addAll(words);
        lettersWordsSymbolsAndSentences.addAll(symbols);
        lettersWordsSymbolsAndSentences.addAll(sentences);
        gameLogic(lettersWordsSymbolsAndSentences);
    }
}
