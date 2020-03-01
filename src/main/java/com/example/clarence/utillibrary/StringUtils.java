package com.example.clarence.utillibrary;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by clarence on 3/27/16.
 */
public class StringUtils {
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isNotEmpty(Object s) {
        return s != null && !isEmpty(s.toString());
    }
    /**
     * 去掉字符串中所有的空格
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String getSuffixName(String str) {
        if (str == null || str.trim().length() == 0) {
            return str;
        }
        int typeIndex = str.lastIndexOf(".");
        if (typeIndex != -1) {
            String type = str.substring(typeIndex + 1).toLowerCase();
            return type;
        }
        return "";
    }

    /**
     * 对浮点型数值进行格式化处理
     * 2.00->2
     * 2.10->2.1
     * 2.12->2.12
     * 2.01->2.01
     *
     * @param value
     * @return
     */
    public static String formatNum(float value) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String st;
        try {
            st = nf.format(value);
        } catch (Exception e) {
            e.printStackTrace();
            st = String.valueOf(value);
        }
        if (st.indexOf(".") != -1) {
            st = st.replaceAll("0+?$", "");//去掉后面无用的零
            st = st.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return st;
    }

    public static String formatColorText(String str, int color) {
        if (str == null || str.trim().length() == 0) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("<font color=");
            sb.append(color);
            sb.append(">");
            sb.append(str);
            sb.append("</font>");
            return sb.toString();
        }
    }

    /**
     * 手机号加密
     *
     * @param mobile
     * @return
     */
    public static String encryptMobile(String mobile) {
        if (TextUtils.isEmpty(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return buildString(mobile.substring(0, 3), "****", mobile.substring(7, mobile.length()));
    }

    /**
     * 姓名加密
     *
     * @param name
     * @return
     */
    public static String encryptName(String name) {
        if (TextUtils.isEmpty(name) || name.length() < 2) {
            return name;
        }
        return buildString(name.substring(0, 1), "*", name.substring(2, name.length()));
    }

    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean isEmpty(String str) {
        return str == null || "null".equals(str) || str.length() == 0;
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String getString(TextView editText) {
        if (editText == null) {
            return "";
        } else {
            return editText.getText().toString().trim();
        }
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String buildString(Object... str) {
        int size = str.length;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            if (str[i] != null) {
                builder.append(String.valueOf(str[i]));
            }
        }

        return builder.toString();
    }

    public static boolean isNull(String str) {
        return str == null || str.length() == 0 || str.equals("null");
    }

    public static String join(String[] array, String sep) {
        if (array == null) {
            return null;
        }

        int arraySize = array.length;
        int sepSize = 0;
        if (sep != null && !sep.equals("")) {
            sepSize = sep.length();
        }

        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].length()) + sepSize) * arraySize);
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(sep);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String jsonJoin(String[] array) {
        int arraySize = array.length;
        int bufSize = arraySize * (array[0].length() + 3);
        StringBuilder buf = new StringBuilder(bufSize);
        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(',');
            }

            buf.append('"');
            buf.append(array[i]);
            buf.append('"');
        }
        return buf.toString();
    }

    public static byte[] utf8Bytes(String data) {
        try {
            return data.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static String strip(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0, length = s.length(); i < length; i++) {
            char c = s.charAt(i);
            if (c > '\u001f' && c < '\u007f') {
                b.append(c);
            }
        }
        return b.toString();
    }


    public static String getHideCardID(String id) {
        if (StringUtils.isEmpty(id)) {
            return "";
        } else if (id.length() >= 14) {
            return id.substring(0, 4) + "**********" + id.substring(14);
        } else {
            return id.substring(0, 2) + "****" + id.substring(6);
        }
    }


    public static String getDoubleFormat(double num) {
        if (num == 0d) {
            return "0.00";
        }
        // 国际标准
        // NumberFormat format=NumberFormat.getNumberInstance();
        // format.setMinimumFractionDigits(2);//最小保留小数位数
        // format.setMaximumFractionDigits(2);//最大保留小数位数

        DecimalFormat format = new DecimalFormat("######0.00");
        return format.format(num);
    }

    public static String getDoubleFormat(String num) {
        if (isEmpty(num)) {
            return "0.00";
        }
        Double d = Double.valueOf(num);
        return String.format("%.2f", d);
    }


    /**
     * 判断str1中包含str2的个数
     *
     * @param str
     * @param key
     * @return counter
     */
    public static int countStr(String str, String key) {

        int index = 0; //定义变量。记录每一次找到的key的位置。
        int count = 0; //定义变量，记录出现的次数。
        //定义循环。只要索引到的位置不是-1，继续查找。
        while ((index = str.indexOf(str, index)) != -1) {
            //每循环一次，就要明确下一次查找的起始位置。
            index = index - 1 + key.length();
            //每查找一次，count自增。
            count++;
        }
        return count;
    }

    //只能输入一位小数点
    public static void inputSinglePoint(EditText editText, CharSequence s) {
        if (!StringUtils.isEmpty(editText.getText().toString())) {
            if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    editText.setText(s.subSequence(0, 1));
                    editText.setSelection(1);
                    return;
                }
            }
            if (isPositiveInteger(s.toString()) || isPositiveDecimal(s.toString())) {
               /* if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }*/
            } else {
                editText.setText(s.toString().substring(0, s.length() - 1));
                editText.setSelection(editText.getText().length());

            }
        } else {

        }
    }

    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    public static boolean isPositiveDecimal(String orginal) {
        return isMatch(
                "^\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*|^[0-9]+([.]{1}[0-9]+){0,1}$",
                orginal);
    }

    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }
}
