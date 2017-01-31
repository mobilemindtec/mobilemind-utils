package br.com.mobilemind.api.utils;

/*
 * #%L
 * Mobile Mind - Utils
 * %%
 * Copyright (C) 2012 Mobile Mind Empresa de Tecnologia
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class utilitaria para formatação
 * 
 * @author Ricardo Bocchi
 */
public class MobileMindUtil {

    private static NumberFormat formar = NumberFormat.getInstance(Locale.US);

    public static boolean isNullOrEmpty(String text) {

        if (text == null || "".equals(text.trim())) {
            return true;
        }
        return false;
    }

    public static String trim(String text) {
        if (text == null) {
            return "";
        }

        return text.trim();
    }

    public static String filterNumber(String str) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static double decimalFormat(double value) {
        formar.setMinimumFractionDigits(4);
        formar.setMaximumFractionDigits(4);
        formar.setMaximumIntegerDigits(10);
        formar.setGroupingUsed(false);
        return Double.parseDouble(formar.format(value));

    }

    public static double formatMoney(double value) {
        formar.setMinimumFractionDigits(2);
        formar.setMaximumFractionDigits(2);
        formar.setMaximumIntegerDigits(10);
        formar.setGroupingUsed(false);
        try {
            return formar.parse(formar.format(value)).doubleValue();
        } catch (ParseException ex) {
            return 0;
        }
    }

    public static String formatMoneyStr(double value) {
        formar.setMinimumFractionDigits(2);
        formar.setMaximumFractionDigits(2);
        formar.setMaximumIntegerDigits(10);
        formar.setGroupingUsed(false);
        return formar.format(value);
    }

    public static double parseMoney(String value) {
        formar.setMinimumFractionDigits(2);
        formar.setMaximumFractionDigits(2);
        formar.setMaximumIntegerDigits(10);
        formar.setGroupingUsed(false);
        try {
            return formatMoney(formar.parse(value.replace(",", ".")).doubleValue());
        } catch (ParseException ex) {
            return 0;
        }
    }
}
