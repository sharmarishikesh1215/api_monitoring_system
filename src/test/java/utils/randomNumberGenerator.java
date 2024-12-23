package utils;

import java.util.Random;

public class randomNumberGenerator {

    public static String generateRandomNumber() {
        Random rand = new Random();
        String fiveDigNum = String.valueOf(1000 + rand.nextInt(100000));
        System.out.println("Random 5 digit Number: " + fiveDigNum);
        return fiveDigNum;
    }
}
