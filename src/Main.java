import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.length() > 22) { // 22 = 10 characters per string + 2 quotes per string
            throw new RuntimeException("Input string is too long");
        }

        Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*([+-/*])\\s*\"([^\"]+)\"|\"([^\"]+)\"\\s*([+-/*])\\s*(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String str1 = matcher.group(1);
            String operator = matcher.group(2);
            String str2 = matcher.group(3);
            String str1Alt = matcher.group(4);
            String operatorAlt = matcher.group(5);
            String num = matcher.group(6);

            if (str1 == null) {
                str1 = str1Alt;
                operator = operatorAlt;
            }

            if (str2 == null) {
                str2 = num;
            }

            if (str1.length() > 10 || str2.length() > 10) {
                throw new RuntimeException("Input string is too long");
            }

            int numVal;
            try {
                numVal = Integer.parseInt(str2);
            } catch (NumberFormatException e) {
                numVal = 0;
            }

            String result;
            if (operator.equals("+")) {
                result = addStrings(str1, str2);
            } else if (operator.equals("-")) {
                result = subtractString(str1, str2);
            } else if (operator.equals("*")) {
                if (numVal < 1 || numVal > 10) {
                    throw new RuntimeException("Invalid number range");
                }
                result = multiplyString(str1, numVal);
            } else if (operator.equals("/")) {
                if (numVal < 1 || numVal > 10) {
                    throw new RuntimeException("Invalid number range");
                }
                result = divideString(str1, numVal);
            } else {
                throw new RuntimeException("Unsupported operation");
            }

            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }

            System.out.println(result);
        } else {
            throw new RuntimeException("Invalid input format");
        }
    }

    private static String addStrings(String str1, String str2) {
        return str1 + str2;
    }

    private static String subtractString(String str1, String str2) {
        if (str1.contains(str2)) {
            return str1.replace(str2, "");
        } else {
            return str1;
        }
    }

    private static String multiplyString(String str, int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(str);
        }
        return result.toString();
    }

    private static String divideString(String str, int num) {
        int length = str.length() / num;
        return str.substring(0, length);
    }
}