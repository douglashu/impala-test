package com.xlwt.impala.connect.dao.interfaces;

import com.xlwt.impala.connect.form.ImpalaInfo;

import java.util.List;

/**
 * Created with yangjf
 * Date: 2016/11/19
 * Time: 13:52
 * Describle:使用API实现增删改查，分区表--》incr_test_2
 */
public interface ImpalaDAO {
    //插入1条数据
    public void insertOrderDataToTable( )throws Exception;
    //插入多条数据
    public void insertOrderDataListToTable(List<ImpalaInfo> impalaInfoList)throws Exception;

    //查询9条数据


}
