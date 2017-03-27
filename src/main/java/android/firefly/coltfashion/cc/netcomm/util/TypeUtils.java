package android.firefly.coltfashion.cc.netcomm.util;

public class TypeUtils {

    static public final int NUMBER_CEIL_BIG = 1;
    static public final int NUMBER_FLOOR_SMALL = 2;
    static public final int NUMBER_FOURFIVE_MIDDLE = 3;

    static public boolean toBoolean(int value) {
        return value != 0;
    }

    static public boolean toBoolean(long value) {
        return value != 0L;
    }

    static public boolean toBoolean(String value) {
        return Boolean.valueOf(value);

    }

    static public int toInt(boolean value) {
        return value ? 1 : 0;
    }

    static public int toInt(String value) {

        return Integer.decode(value);

    }

    static public int toInt(String value, int defaultValue) {

        try {
            return Integer.decode(value);

        } catch (NumberFormatException ex) {
            return defaultValue;
        }

    }

    static public int toInt2(String value) {

        if (StringUtil.isNullEmpty(value)) {
            throw new NumberFormatException("number format error,value is null or empty");
        }
        String realValue = value;
        if (realValue.charAt(0) == '#') {
            realValue = value.substring(1);
        } else if (realValue.startsWith("0x") || realValue.startsWith("0X")) {
            realValue = value.substring(2);
        } else {
            return Integer.decode(value);
        }

        realValue = realValue.trim();

        if ((realValue.length() <= 0) || (realValue.length() > 8)) {
            throw new NumberFormatException("number format error," + value);
        }

        while (realValue.length() < 8) {
            realValue = "0" + realValue;
        }
        String string1 = realValue.substring(0, 2);
        String string2 = realValue.substring(2, 4);
        String string3 = realValue.substring(4, 6);
        String string4 = realValue.substring(6, 8);

        int intBytes1 = (Integer.parseInt(string1, 16) & 0x000000FF);
        int intBytes2 = (Integer.parseInt(string2, 16) & 0x000000FF);
        int intBytes3 = (Integer.parseInt(string3, 16) & 0x000000FF);
        int intBytes4 = (Integer.parseInt(string4, 16) & 0x000000FF);

        return intBytes1 << 24 | intBytes2 << 16 | intBytes3 << 8 | intBytes4;
    }

    static public int toInt2(String value, int defaultValue) {

        try {

            return toInt2(value);

        } catch (Exception ex) {

            return defaultValue;

        }
    }

    static public int toInt(double value, int mode) {

        double realValue = value;
        if (mode == NUMBER_CEIL_BIG) {
            realValue = Math.ceil(value);
        } else if (mode == NUMBER_FLOOR_SMALL) {
            realValue = Math.floor(value);
        } else if (mode == NUMBER_FOURFIVE_MIDDLE) {
            realValue = Math.rint(value);
        }

        return (int) realValue;
    }

    static public int toInt(double value, int mode, int defaultValue) {

        double realValue = value;
        if (mode == NUMBER_CEIL_BIG) {
            realValue = Math.ceil(value);
        } else if (mode == NUMBER_FLOOR_SMALL) {
            realValue = Math.floor(value);
        } else if (mode == NUMBER_FOURFIVE_MIDDLE) {
            realValue = Math.rint(value);
        }


        if (realValue > Integer.MAX_VALUE) {
            return defaultValue;
        }

        if (realValue < Integer.MIN_VALUE) {
            return defaultValue;
        }
        return (int) realValue;
    }

    static public long toLong(boolean value) {
        return value ? 1L : 0L;
    }

    static public long toLong(String value) {

        return Long.valueOf(value);

    }

    static public long toLong2(String value) {

        if (StringUtil.isNullEmpty(value)) {
            throw new NumberFormatException("number format error,value is null or empty");
        }
        String realValue = value;
        if (realValue.charAt(0) == '#') {
            realValue = value.substring(1);
        } else if (realValue.startsWith("0x") || realValue.startsWith("0X")) {
            realValue = value.substring(2);
        } else {
            return Long.decode(value);
        }

        realValue = realValue.trim();

        if ((realValue.length() <= 0) || (realValue.length() > 16)) {
            throw new NumberFormatException("number format error," + value);
        }

        while (realValue.length() < 16) {
            realValue = "0" + realValue;
        }
        String string1 = realValue.substring(0, 2);
        String string2 = realValue.substring(2, 4);
        String string3 = realValue.substring(4, 6);
        String string4 = realValue.substring(6, 8);
        String string5 = realValue.substring(8, 10);
        String string6 = realValue.substring(10, 12);
        String string7 = realValue.substring(12, 14);
        String string8 = realValue.substring(14, 16);

        long longBytes1 = (Long.parseLong(string1, 16) & 0x00000000000000FF);
        long longBytes2 = (Long.parseLong(string2, 16) & 0x00000000000000FF);
        long longBytes3 = (Long.parseLong(string3, 16) & 0x00000000000000FF);
        long longBytes4 = (Long.parseLong(string4, 16) & 0x00000000000000FF);
        long longBytes5 = (Long.parseLong(string5, 16) & 0x00000000000000FF);
        long longBytes6 = (Long.parseLong(string6, 16) & 0x00000000000000FF);
        long longBytes7 = (Long.parseLong(string7, 16) & 0x00000000000000FF);
        long longBytes8 = (Long.parseLong(string8, 16) & 0x00000000000000FF);

        return longBytes1 << 56 | longBytes2 << 48 | longBytes3 << 40 | longBytes4 << 32 |
                longBytes5 << 24 | longBytes6 << 16 | longBytes7 << 8 | longBytes8;

    }

    static public long toLong2(String value, long defaultValue) {

        try {
            return toLong2(value);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    static public long toLong(String value, long defaultValue) {

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    static public long toLong(double value, int mode) {

        double realValue = value;
        if (mode == NUMBER_CEIL_BIG) {
            realValue = Math.ceil(value);
        } else if (mode == NUMBER_FLOOR_SMALL) {
            realValue = Math.floor(value);
        } else if (mode == NUMBER_FOURFIVE_MIDDLE) {
            realValue = Math.rint(value);
        }


        return (long) realValue;
    }

    static public long toLong(double value, int mode, long defaultValue) {

        double realValue = value;
        if (mode == NUMBER_CEIL_BIG) {
            realValue = Math.ceil(value);
        } else if (mode == NUMBER_FLOOR_SMALL) {
            realValue = Math.floor(value);
        } else if (mode == NUMBER_FOURFIVE_MIDDLE) {
            realValue = Math.rint(value);
        }


        if (realValue > Long.MAX_VALUE) {
            return defaultValue;
        }

        if (realValue < Long.MIN_VALUE) {
            return defaultValue;
        }
        return (long) realValue;
    }

    static public double toDouble(String value) {

        return Double.valueOf(value);
    }

    static public double toDouble(String value, double defaultValue) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

}
