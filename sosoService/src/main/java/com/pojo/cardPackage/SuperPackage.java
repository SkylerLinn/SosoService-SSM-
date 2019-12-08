package com.pojo.cardPackage;

public class SuperPackage extends ServicePackage {
    /*文件名:Service.SuperPackage.java
     * 功能：实现超级包的功能
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
//    public int talkTime;
//    public int smsCount;
//    public int flow;如果声明了，就成了新开辟子类的空间了，就不对了
    public SuperPackage(){
        talkTime=200;//min
        smsCount=50;//piece
        flow=1024;//MB
        price=78;//yuan per month;
    }
    //注意，扣钱的时候在进行每次功能结束后进行，也就是说账户余额就是money，是否超出套餐是在每次使用功能的时候比较，因为这些功能都是继承来的，所以可以在父类中使用多态？？
    @Override
    public String showInfo(){
        return("使用超人套餐，套餐内通话时间为200分钟，使用流量为1GB，短信数量为50条。");
    }


}
