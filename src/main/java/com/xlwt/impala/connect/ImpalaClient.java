package com.xlwt.impala.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with yangjf
 * Date: 2016/11/18
 * Time: 18:34
 * Describle :java调用impala  API实现功能
 */
public class ImpalaClient {
    private static Logger logger= LoggerFactory.getLogger(ImpalaClient.class);
    public static void main(String[] args) {
        try{
            /**加载驱动*/
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //链接
            // Impala默认外部端口： 21050
            Connection conn = DriverManager.getConnection("jdbc:hive2://hadoop3:21050/default;auth=noSasl", "", "");
            PreparedStatement pstm = conn.prepareStatement("select * from illegal_web limit 6 ");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }

            rs.close();
            pstm.close();
            conn.close();
        }catch (Exception e){
         e.printStackTrace();
            logger.info("错误:"+e.toString());
        }


    }
}
