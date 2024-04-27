package com.Shopping.Ecommerce.App.Validations;

import com.Shopping.Ecommerce.App.Enum.CardType;
import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Validation {

    private static final String NULL = "null";
    private static final String EMPTY_STRING = "";

    public static boolean validateEmail(String email) {
        return email.matches("(.*)@(.*)mail.com");
    }

    public static boolean validateMobile(String mobile) {
        String pattern = "(0|91)?[7-9][0-9]{9}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static boolean validatePanNumber(String panNo) {
        String pattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(panNo);
        return m.matches();
    }

    public static boolean validateCardNumber(String cardNo, CardType cardType) {
        String pattern = "";
        if(cardType == CardType.VISA) {
            pattern = "4[0-9]{12}(?:[0-9]{3})?";
        } else {
            pattern = "5[1-5][0-9]{14}";
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(cardNo);
        return m.matches();
    }

    public static String validateRequest(Object entity) {
        if (entity == null) {
            return NULL;
        }
        return EMPTY_STRING;
    }
}