package android.firefly.coltfashion.cc.netcomm.util;

import android.firefly.coltfashion.cc.netcomm.CommonConstDef;

import java.nio.charset.Charset;

public class StringUtil {

    static public  final String EMPTYSTR = "";

    static private Charset _DefaultCharset;

    static public boolean isNullEmpty(String value) {

        return (value == null) || value.isEmpty();

    }

    static public String getNotNullString(String value) {

        return (value != null) ? value : EMPTYSTR;

    }

    static public String spaceRepeatString(String value) {

        String result = EMPTYSTR;

        if (value.length() > 1) {
            for (int i = 0; i < value.length() - 1; i++) {
                if ((value.charAt(i) == 32) && (value.charAt(i + 1) == 32)) {
                    continue;
                }
                result += value.charAt(i);
            }

            if (value.charAt(value.length() - 1) != 32) {
                result += value.charAt(value.length() - 1);
            }
        } else {
            result = value;
        }

        return result;
    }

    static public boolean equals(String s1, String s2) {

        return (s1 == null) ? (s2 == null) : s1.equals(s2);
    }

    static public boolean equalsIgnoreCase(String s1, String s2) {

        return (s1 == null) ? (s2 == null) : s1.equalsIgnoreCase(s2);
    }

    static public boolean checkCharset(String charsetName) {

        return Charset.availableCharsets().containsKey(charsetName);

    }

    static public Charset defaultCharset() {

        if (_DefaultCharset == null) {
            if (checkCharset(CommonConstDef.UTF_8)) {
                _DefaultCharset = Charset.forName(CommonConstDef.UTF_8);
            } else if (checkCharset(CommonConstDef.GBK)) {
                _DefaultCharset = Charset.forName(CommonConstDef.GBK);
            } else if (checkCharset(CommonConstDef.US_ASCII)) {
                _DefaultCharset = Charset.forName(CommonConstDef.US_ASCII);
            } else {
                _DefaultCharset = Charset.defaultCharset();
            }
        }

        return _DefaultCharset;
    }

//    static public boolean addNoDuplicateStrings(ArrayList<String> stringList,
//                                                String s) {
//
//        if ((stringList != null) && (!stringList.contains(s)) && (s != null)) {
//
//            return stringList.add(s);
//
//        }
//
//        return false;
//    }

//    static public String getCleanedString(String s) {
//
//        String s1 = s.replace('\r', (char) 0);
//        s1 = s1.replace('\n', (char) 0);
//
//        return s1;
//    }

//    static public String getHttpUrl(String url) {
//
//        if (url.toUpperCase().contains("HTTP://")) {
//
//            return url;
//
//        } else {
//
//            return String.format("http://%s", url);
//        }
//
//    }


}
