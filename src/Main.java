import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;
    static boolean numberIsArabic;
    static String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) {
        System.out.println("Input an expression like [romanian + romanian] or [arabic + arabic]");
        String userInput = scanner.nextLine();
        System.out.println(userInput);
        System.out.println(calc(userInput));
    }

    public static String calc(String input) {
        //check input data
        try {
            String[] expression = input.split(" ");
            //check string length
            if (expression.length > 3) throw new Exception("input is not correct");
            //check for num type
            boolean firstNumIsRoman = Arrays.stream(roman).toList().contains(expression[0]);
            boolean secNumIsRoman = Arrays.stream(roman).toList().contains(expression[2]);
            if (firstNumIsRoman && secNumIsRoman) {
                number1 = romanToArabic(expression[0]);
                number2 = romanToArabic(expression[2]);
                operation = expression[1].charAt(0);
                numberIsArabic = false;
            } else {
                number1 = Integer.parseInt(expression[0]);
                number2 = Integer.parseInt(expression[2]);
                operation = expression[1].charAt(0);
                if (number1 > 10 || number1 < 1 || number2 > 10 || number2 < 1)
                    throw new Exception("input number is not in range 1..10");
                numberIsArabic = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //output result
        result = calculate(number1, number2, operation);
        if (numberIsArabic) {
            return ("Result: " + number1 + " " + operation + " " + number2 + " = " + result);
        } else {
            try {
                if (result < 1) throw new Exception("Result is < 1");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String resultRoman = arabicToRoman(result);
            return ("Result: " + arabicToRoman(number1) + " " + operation + " " + arabicToRoman(number2) + " = " + resultRoman);
        }
    }

    static String arabicToRoman(int numArabic) {
        try {
            if (Objects.equals(roman[numArabic], roman[0])) throw new Exception("arabic num can't be 0");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roman[numArabic];
    }

    static int romanToArabic(String roman) {
        try {
            switch (roman) {
                case "I" -> {
                    return 1;
                }
                case "II" -> {
                    return 2;
                }
                case "III" -> {
                    return 3;
                }
                case "IV" -> {
                    return 4;
                }
                case "V" -> {
                    return 5;
                }
                case "VI" -> {
                    return 6;
                }
                case "VII" -> {
                    return 7;
                }
                case "VIII" -> {
                    return 8;
                }
                case "IX" -> {
                    return 9;
                }
                case "X" -> {
                    return 10;
                }
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("input number is not in range 1..10");
        }
        return -1;
    }

    static int calculate(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                }
            }
            default -> throw new IllegalArgumentException("Check operator character");
        }
        return result;
    }
}