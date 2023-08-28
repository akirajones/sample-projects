package utility;

import java.util.Random;

public class NumberHelper {

    public NumberHelper() {	}

    public static int generateRandomInteger(int upperLimit) {
        Random random = new Random();

        return random.nextInt(upperLimit);
    }

    public static int generateRandomInteger(int lowerLimit, int upperLimit) {
        int[] intArray = new Random().ints(1, lowerLimit, upperLimit).toArray();
        return intArray[0];
    }

    public static String generateRandomIntegerString(int lowerLimit, int upperLimit, int requiredLength) {
        String strValue = String.valueOf(generateRandomInteger(lowerLimit, upperLimit));
        while (strValue.length() < requiredLength) {
            strValue = "0" + strValue;
        }
        return strValue;
    }

}
