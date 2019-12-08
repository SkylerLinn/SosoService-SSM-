package com.pojo.cardPackage;

public class NetPackage extends ServicePackage {
    /*文件名:Service.NetPackage.java
     * 功能：关于上网的功能实现
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
//    int flow;//MB
//    int talkTime=0;
//    int smsCount=0;
    public NetPackage(){

        flow=3*1024;//MB
        price=68;//yuan per month
    }
    //注意，扣钱的时候在进行每次功能结束后进行，也就是说账户余额就是money
    @Override
    public  String showInfo(){
        return ("使用网虫套餐，套餐内上网流量为3GB。");
    }

}
