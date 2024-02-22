package se.ju23.typespeeder.DatabaseAndUtility;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UtilityService {
    //Scannner and current player
    public  static String loggedInUsername;

    public static Scanner input = new Scanner(System.in);

    public static int getIntInput() {
        int choice = 0;
        if (input.hasNextInt()) {
            choice = input.nextInt();
        }
        return choice;
    }
    public static void setLoggedInUsername(String loggedInUsername) {
        UtilityService.loggedInUsername = loggedInUsername;
    }
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
}
