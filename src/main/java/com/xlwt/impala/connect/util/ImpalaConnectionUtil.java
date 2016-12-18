package com.xlwt.impala.connect.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
/**
 * Created with yangjf
 * Date: 2016/11/21
 * Time: 11:23
 * Describle:创建线程安全的链接
 */
public class ImpalaConnectionUtil {
    private static Logger logger= LoggerFactory.getLogger(ImpalaConnectionUtil.class);
    private static Properties prop = new Properties();
    private static final ThreadLocal<Connection> tdl = new ThreadLocal<Connection>();
    static {
        try {
            InputStream is = ImpalaConnectionUtil.class.getResourceAsStream("/db.properties");
            prop.load(is);
            Class.forName(prop.getProperty("xlwl.driverClassName"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConn() {
        Connection conn = null;
        conn = tdl.get();
        logger.info("开始获取Impala链接");
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(prop.getProperty("xlwl.url"),prop.getProperty("xlwl.username"), prop.getProperty("xlwl.password"));
                tdl.set(conn);
            }
        } catch (Exception e) {
            logger.error("获取Impala链接失败："+e.toString());
            throw new RuntimeException(e);
        }
        logger.info("成功获取Impala链接");
        return conn;
    }

    public static void releaseConn(Connection conn, PreparedStatement pstm,
                                   ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                tdl.remove();
                conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getConn());
    }
}
