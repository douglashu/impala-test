package com.xlwt.impala.connect;

import com.xlwt.impala.connect.dao.impl.ImpalaDAOImpl;
import com.xlwt.impala.connect.dao.interfaces.ImpalaDAO;
import com.xlwt.impala.connect.form.ImpalaInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with yangjf
 * Date: 2016/11/21
 * Time: 11:36
 * Describle:
 */
public class TestDAO {
    private static ImpalaDAO impalaDAO=new ImpalaDAOImpl();
    //一条一条插入
    //测试通过
    @Test
    public void testInsertOne(){
     try{
         for(int i=0;i<2;i++){
             impalaDAO.insertOrderDataToTable();
         }

     }catch (Exception e){
         e.printStackTrace();
     }
    }
    //插入多条数据
    //测试不通过，方法不支持
    @Test
    public void testInsertList(){
        try{
            List<ImpalaInfo> list=new ArrayList<ImpalaInfo>();
            list.add(new ImpalaInfo("hello001","cassie1"));
            list.add(new ImpalaInfo("hello002","cassie2"));
            list.add(new ImpalaInfo("hello003","cassie3"));
            list.add(new ImpalaInfo("hello004","cassie4"));
            list.add(new ImpalaInfo("hello005","cassie5"));
            impalaDAO.insertOrderDataListToTable(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
