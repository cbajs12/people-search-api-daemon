package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigNew {
    private static  String daemonProps = null;
    private static Logger logger = LoggerFactory.getLogger(ConfigNew.class);
    private static String propertiesName = "";

    static {
        if(daemonProps == null) new ConfigNew();
    }

    public ConfigNew() {
        if(daemonProps == null) init();
    }

    public static String getProperties(String propertyFile, String key) {
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream(propertyFile);
            props.load(in);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = props.getProperty(key);

        return result;
    }

    private void init() {
        try {

            daemonProps =  propertiesName;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int getTimeout() {
        return CommonUtils.nvl(getProperties(daemonProps, "daemon.client.timeout"),500);
    }

    public static int getBatchExecute() {
        return CommonUtils.nvl(getProperties(daemonProps, "daemon.batch.execute"),300);
    }

    public static int getBatchInterval() {
        return CommonUtils.nvl(getProperties(daemonProps, "daemon.batch.interval"),60);
    }

    public static int getBatchThread() {
        return CommonUtils.nvl(getProperties(daemonProps, "daemon.db.thread"),1000);
    }

}
