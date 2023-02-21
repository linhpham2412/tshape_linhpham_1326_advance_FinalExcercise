package nt.tshape.automation.selenium;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {
    public static String generateTestEmail() {
        Random emailRandIndex = new Random();
        return emailRandIndex.nextInt() + "@mail.com";
    }

    public static String generateRandomTestCharacters(int noOfCharsToGenerate) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(noOfCharsToGenerate)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateRandomNumberInRange(int minValue, int maxValue) {
        Random random = new Random();
        int value = random.nextInt(maxValue - minValue + 1) + minValue;
        return String.valueOf(value);
    }

    public static Boolean generateRandomTrueOrFalse() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static Boolean checkIfFileExistInLocation(String location, String fileName) {
        try {
            File fileToCheck = new File(location + fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String convertTextToFormattedDate(String textToFortmat, String inputFormat, String outputFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        LocalDate convertDate = LocalDate.parse(textToFortmat, inputFormatter);
        String outputDate = DateTimeFormatter.ofPattern(outputFormat).format(convertDate);
        return (outputDate.charAt(0) == '0') ? outputDate.substring(1, outputDate.length()) : outputDate;
    }

    public static String convertTextToAgodaLinkFormat(String textToConvert){
        return textToConvert
                .toLowerCase()
                .replaceAll(" ","-")
                .trim();
    }
}
