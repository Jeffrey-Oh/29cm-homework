package kr.co._29cm.homework.common.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 숫자체크
     */
    public static boolean isNumberValid(String value) {
        String pattern = "^[0-9]*$";
        return Pattern.matches(pattern, value);
    }

    /**
     * 원화 가격 숫자 콤마 OR 콤마 + 단위 추가
     * @param price
     * @return
     */
    public static String convertCommaByPrice(long price, boolean checkAddUnit) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String convertString = decimalFormat.format(price);
        if (checkAddUnit) convertString += "원";
        return convertString;
   }

}
