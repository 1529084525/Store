package util;

import java.text.DecimalFormat;

/**
 * 格式化double
 */
public class FormattingDoubleUtil {

    public static double parseDouble(double money) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(money));
    }

}
