package com.xlwt.impala.connect.form;

/**
 * Created with yangjf
 * Date: 2016/11/19
 * Time: 13:55
 * Describle:incr_test_2表对应实体类
 *
 */
public class ImpalaInfo {
    private String order_id; //订单id
    private String order_no;//订单编号

    public ImpalaInfo(){}
    public ImpalaInfo(String order_id, String order_no) {
        this.order_id = order_id;
        this.order_no = order_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "ImpalaInfo{" +
                "order_id='" + order_id + '\'' +
                ", order_no='" + order_no + '\'' +
                '}';
    }
}
