package com.pojo.cardPackage;

public class TalkPackage extends ServicePackage {
    /*文件名:Service.TalkPackage.java
     * 功能：关于通话包的功能实现
     * @作者：计科林铸天
     * @时间：2019.9.17
     *
     * */
//    int talkTime;
//    int smsCount;
//    int flow=0;//MB
    public TalkPackage(){
        talkTime=500;//min
        smsCount=30;//piece
        price=58;//yuan per month
    }
    //注意，扣钱的时候在进行每次功能结束后进行，也就是说账户余额就是money
    @Override
    public String showInfo(){
        return ("使用话痨套餐，套餐内通话时间为500分钟，短信数量为30条。");
}

}
