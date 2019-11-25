package com.spec.analysis.utils.formatter;

import java.text.DecimalFormat;

public class NumbersFormatter {

    public static Double formatDouble(Double number) {

        return formatDouble(number, 2);
    }

    public static Double formatDouble(Double number, int digits) {

        StringBuilder pattern = new StringBuilder("#.");
        for (int i = 0; i < digits; i++) {
            pattern.append("#");
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        String dx = decimalFormat.format(number);

        return Double.valueOf(dx);
    }
}
