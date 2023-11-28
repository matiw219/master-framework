package me.masterkaiser.framework.java.string;

import lombok.experimental.UtilityClass;
import me.masterkaiser.framework.java.number.RandomUtil;

@UtilityClass
public class RandomStringUtil {
    public static char[] alfaNumericChars = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'z', 'x', 'c', 'v', 'b', 'n', 'm',
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
            'Z', 'X', 'C', 'V', 'B', 'N', 'M'
    };

    public static String randomAlfaNumericString(int length) {
        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(alfaNumericChars[RandomUtil.randomInt(0, alfaNumericChars.length - 1)]);
        }

        return result.toString();
    }
}
