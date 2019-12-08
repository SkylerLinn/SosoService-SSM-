package com.exception;

public class InsufficientMoneyException extends Exception {
    /*文件名:Service.InsufficientMoneyException.java
     * 功能：钱不够时候抛出的异常
     * @作者：计科林铸天
     * @时间：2019.10.2
     *@ Log：学习有关于异常的书写
     * */
    String type=null;
    int data=0;
    //String description=null;
    double needCost;
    double remainMoney;
    double needMoney;
    public InsufficientMoneyException(String type,int data,double needCost,double remainMoney) {
        this.type=type;
        this.data=data;
        this.needCost=needCost;
        this.remainMoney=remainMoney;
        needMoney=needCost-remainMoney;


    }
    public void showException(){
        System.out.println("将要进行数值大小为"+type+"，数据类型为"+type+"类型的消费");
        System.out.println("由于余额不足，将要消费"+needCost+"元，只剩下"+remainMoney+"元，还需要"+needMoney+"元才能支付，请移步充值功能。");
    }
}
