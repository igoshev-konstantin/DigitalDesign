import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {

    final static String regex1 = "[\\d]\\[[a-zA-Z]*(\\])";
    final static String regex2 = "[^\\D]*";
    final static String regex3 = "[a-zA-Z]*";

    public static void main(String[] args) throws IOException {

        //Игошев Константин. Тестовое задание Digital design.
        //Почтовый ящик: leader_trident@mail.ru
        //Тел.: 8-910-157-28-38
        //Skype: alex.swon.2

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String checkString = bufferedReader.readLine();

        boolean stringIsValidated = checkValidate(checkString);
        if (!stringIsValidated) {
            System.out.println("Entered string: " + checkString + " is not validated! Please try enter a string again.");
            return;
        }

        System.out.println("Entered string: " + checkString + " is validated.");

        String returnedString = stringParsing(checkString);
        System.out.println("Result: " + returnedString);
    }

    public static boolean checkValidate(String inputString) {
        return stringRecursion(inputString, true) == "#";
    }

    public static String stringParsing(String inputString) {
        return stringRecursion(inputString, false);
    }

    public static String stringRecursion(String inputString, boolean checkValidate) {
        boolean end = true;
        Pattern p = Pattern.compile(regex1);
        Matcher matcher = p.matcher(inputString);

        while (matcher.find()) {
            if (checkValidate) {
                return "#";
            }

            end = false;
            String s = matcher.group();
            String digit = "";
            String str1 = "";
            Matcher matcher2 = Pattern.compile(regex2).matcher(s);
            while (matcher2.find()) {
                digit = matcher2.group();
                if (!digit.isEmpty()) {
                    break;
                }
            }

            Matcher matcher3 = Pattern.compile(regex3).matcher(s);
            while (matcher3.find()) {
                str1 = matcher3.group();
                if (!str1.isEmpty()) {
                    break;
                }
            }

            String appendString = str1.repeat(Integer.valueOf(digit));
            inputString = inputString.substring(0, matcher.start())
                    .concat(appendString)
                    .concat(inputString.substring(matcher.end(), inputString.length()));
            break;
        }

        if (!end) {
            inputString = stringRecursion(inputString, false);
        }
        return inputString;
    }

}
