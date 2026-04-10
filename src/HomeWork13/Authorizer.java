/**
 * Classname    Authorizer
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.04.2026
 */
 
package HomeWork13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authorizer {

    public static boolean tryAuthorize(String login, String password, String confirmPassword) {
        boolean status = true;
        try {
            checkLogin(login);
            checkPassword(password);
            checkConfirmPassword(password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println("Ошибка: " + e.getMessage());
            status = false;
        }
        return status;
    }

    private static void checkLogin(String login) throws WrongLoginException {
        if ((login.length() < 3) || (login.length() > 19))
            throw new WrongLoginException("Ошибка длины логина");
        Pattern loginPattern = Pattern.compile("\\s+");  //Количество пробельных символов > 0
        Matcher loginMatcher  = loginPattern.matcher(login);
        if (loginMatcher.find()) throw new WrongLoginException("Логин содержит запрещенные символы");
    }

    private static void checkPassword(String password) throws WrongPasswordException {
        if ((password.length() < 3) || (password.length() > 19))
            throw new WrongPasswordException("Ошибка длины пароля");

        //Количество пробельных символов > 0 и нет цифр
        Pattern passwordPatternSpace = Pattern.compile("\\s+");
        Matcher passwordMatcher  = passwordPatternSpace.matcher(password);
        if (passwordMatcher.find()) throw
                new WrongPasswordException("Пароль содержит запрещенные символы");

        Pattern passwordPatternDigit = Pattern.compile(".*\\d.*");
        Matcher passwordMatcherDigit  = passwordPatternDigit.matcher(password);
        if (!passwordMatcherDigit.find()) throw
                new WrongPasswordException("Пароль должен содержать не менее одной цифры");
    }

    private static void checkConfirmPassword(String password, String confirmPassword)
            throws WrongPasswordException {
        if (!password.equals(confirmPassword)) throw
                new WrongPasswordException("Ошибка подтверждения пароля");
    }
}
