package com.ccbc.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class IdcardValidator {
    private static String[][] codeAndCities = { { "11", "北京" }, { "12", "天津" }, { "13", "河北" }, { "14", "山西" },
            { "15", "内蒙古" }, { "21", "辽宁" }, { "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },
            { "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" }, { "37", "山东" }, { "41", "河南" },
            { "42", "湖北" }, { "43", "湖南" }, { "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },
            { "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" }, { "61", "陕西" }, { "62", "甘肃" },
            { "63", "青海" }, { "64", "宁夏" }, { "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },
            { "91", "国外" } };

    private static HashMap<String, String> provinceMap = new HashMap();
    private int[] power;

    public IdcardValidator() {
        this.power = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    }

    public boolean isValidatedAllIdcard(String idcard) {
        if (idcard.length() == 15) {
            idcard = convertIdcarBy15bit(idcard);
        }
        return isValidate18Idcard(idcard);
    }

    public boolean isValidate18Idcard(String idcard) {
        if (idcard.length() != 18) {
            return false;
        }

        String idcard17 = idcard.substring(0, 17);

        String idcard18Code = idcard.substring(17, 18);
        char[] c = null;
        String checkCode = "";

        if (isDigital(idcard17))
            c = idcard17.toCharArray();
        else {
            return false;
        }

        int year = Integer.parseInt(idcard.substring(6, 10));
        int month = Integer.parseInt(idcard.substring(10, 12));
        int day = Integer.parseInt(idcard.substring(12, 14));
        Calendar idTime = Calendar.getInstance();

        Calendar now = Calendar.getInstance();

        if ((year < 1900) || (month > 12) || (month <= 0) || (day <= 0)) {
            return false;
        }
        idTime.set(year, month - 1, 1);
        if (day > idTime.getActualMaximum(5)) {
            return false;
        }

        idTime.set(year, month, day);
        if (idTime.after(now)) {
            return false;
        }
        if (null != c) {
            int[] bit = converCharToInt(c);

            int sum17 = 0;

            sum17 = getPowerSum(bit);

            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }

            if (!(idcard18Code.equalsIgnoreCase(checkCode))) {
                return false;
            }
        }
        return true;
    }

    public String convertIdcarBy15bit(String idcard) {
        String idcard17 = null;

        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            int y = Integer.parseInt("19" + idcard.substring(6, 8));
            int month = Integer.parseInt(idcard.substring(8, 10));
            int day = Integer.parseInt(idcard.substring(10, 12));

            Calendar cday = Calendar.getInstance();
            cday.set(y, month, day);
            String year = String.valueOf(cday.get(1));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char[] c = idcard17.toCharArray();
            String checkCode = "";

            if (null != c) {
                int[] bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);

                checkCode = getCheckCodeBySum(sum17);

                if (null == checkCode) {
                    return null;
                }

                idcard17 = idcard17 + checkCode;
            }
        } else {
            return null;
        }
        return idcard17;
    }

    public boolean isIdcard(String idcard) {
        return (((idcard == null) || ("".equals(idcard))) ? false
                : Pattern.matches("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard));
    }

    public boolean is15Idcard(String idcard) {
        return (((idcard == null) || ("".equals(idcard))) ? false
                : Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", idcard));
    }

    public boolean is18Idcard(String idcard) {
        return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                idcard);
    }

    public boolean isDigital(String str) {
        return (((str == null) || ("".equals(str))) ? false : str.matches("^[0-9]*$"));
    }

    public int getPowerSum(int[] bit) {
        int sum = 0;

        if (this.power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; ++i) {
            for (int j = 0; j < this.power.length; ++j) {
                if (i == j) {
                    sum += bit[i] * this.power[j];
                }
            }
        }
        return sum;
    }

    public String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
        }

        return checkCode;
    }

    public int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[(k++)] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    public static String getProvince(String id) {
        return ((String) provinceMap.get(id.substring(0, 2)));
    }

    public static Gender getGender(String id) {
        String sex;
        if (id.length() == 15)
            sex = id.substring(14);
        else
            sex = id.substring(16, 17);
        if (Integer.parseInt(sex) % 2 == 0) {
            return Gender.Female;
        }
        return Gender.Male;
    }

    static {
        for (String[] codeAndCity : codeAndCities)
            provinceMap.put(codeAndCity[0], codeAndCity[1]);
    }
}