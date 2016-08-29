package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public CommonUtils() {
        super();
    }

    public static String nvl(String str) {
        if (str == null || str.equals("null") || str.equals(" ")) {
            return "";
        } else {
            return str.trim();
        }
    }

    public static String nvl(Object obj) {
        if (obj == null || "null".equals(obj)) {
            return "";
        } else {
            return nvl(obj, "");
        }
    }

    public static String nvl(Object obj, String val) {
        if (obj == null || obj.equals("null") || obj.equals("")) {
            return val;
        } else {
            return nvl((String) obj, val);
        }
    }

    public static String nvl(String str, String val) {
        if (str == null || str.equals("null") || str.equals("")) {
            return val;
        } else {
            return str.trim();
        }
    }

    public static int nvl(String str, int val) {
        if (str == null || str.equals("null") || str.equals("")) {
            return val;
        } else {
            return Integer.parseInt(str);
        }
    }

    public static int nvl(Object obj, int val) {
        if (obj == null || obj.equals("null") || obj.equals("")) {
            return val;
        } else {
            return nvl((String) obj, val);
        }
    }
}