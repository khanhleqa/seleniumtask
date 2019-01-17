package utilities;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

public class StringUtils {
    public static String[] addStringToArray(String[] arrays, String textToAdd) {
        if(arrays!=null)
            return ArrayUtils.add(arrays, textToAdd);
        return null;
    }

    public static int getIndexOfArray(String[] arrays, String text) {
        if (arrays!=null)
            return Arrays.asList(arrays).indexOf(text);
        return -1;
    }

    public static String getLastValueInSplit(String text, String splitBy) {
        String[] arrays = text.split(splitBy);
        int index = arrays.length - 1;
        return text.split(splitBy)[index];
    }

    public static int randomNumberInRange(Object min, Object max) {
        int minNumber = Integer.parseInt(min.toString());
        int maxNumber = Integer.parseInt(max.toString());
        return Integer.parseInt(RandomStringUtils.randomNumeric(minNumber, maxNumber));
    }
}
