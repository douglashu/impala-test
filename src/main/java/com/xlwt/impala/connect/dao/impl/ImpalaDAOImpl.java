package com.xlwt.impala.connect.dao.impl;

import com.xlwt.impala.connect.dao.interfaces.ImpalaDAO;
import com.xlwt.impala.connect.form.ImpalaInfo;
import com.xlwt.impala.connect.util.ImpalaConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created with yangjf
 * Date: 2016/11/21
 * Time: 11:22
 * Describle:接口实现类
 */
public class ImpalaDAOImpl implements ImpalaDAO{
    private static Logger logger= LoggerFactory.getLogger(ImpalaDAOImpl.class);
    @Override
    public void insertOrderDataToTable() throws Exception {
        Connection conn=null;
        PreparedStatement pstm=null;
      try{
          logger.info("开始插入一条数据到分区表incr_test_2中");
          conn= ImpalaConnectionUtil.getConn();
          pstm=conn.prepareStatement("insert into census partition (year='2012') values ('Smith'),('Jones'),('Lee'),('Singh')");
          pstm.executeUpdate();
          logger.info("成功插入一条数据到分区表incr_test_2中");

      }catch (Exception e){
          logger.error("失败插入一条数据到分区表incr_test_2中："+e.toString());
        throw new RuntimeException(e);
      }finally {
          ImpalaConnectionUtil.releaseConn(conn,pstm,null);
      }
    }
    @Override
    public void insertOrderDataListToTable(List<ImpalaInfo> impalaInfoList) throws Exception {
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            logger.info("开始插入多条数据到分区表incr_test_2中");
            conn= ImpalaConnectionUtil.getConn();
            pstm=conn.prepareStatement("insert into census partition (year='2012') values (?)");
            int count=0;
            for(ImpalaInfo info:impalaInfoList){
               pstm.setString(1,info.getOrder_id());
                pstm.addBatch();
                if(count%5==0){
                    pstm.executeBatch();
                    pstm.clearBatch();
                }
            }
            pstm.executeBatch();
            logger.info("成功插入多条数据到分区表incr_test_2中");

        }catch (Exception e){
            logger.error("失败插入多条数据到分区表incr_test_2中："+e.toString());
            throw new RuntimeException(e);
        }finally {
            ImpalaConnectionUtil.releaseConn(conn,pstm,null);
        }
    }
}
