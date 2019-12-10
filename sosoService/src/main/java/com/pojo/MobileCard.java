package com.pojo;

import com.pojo.cardPackage.NetPackage;
import com.pojo.cardPackage.ServicePackage;
import com.pojo.cardPackage.SuperPackage;
import com.pojo.cardPackage.TalkPackage;

public class MobileCard {
    /*文件名:Service.MobileCard.java
     * 功能：记录移动卡的信息
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
    String cardNumber;//电话号码
    String passWord;//密码
    String userName;//用户名
    ServicePackage serPackage;//使用服务包
    int packageIndex;//约定，使用-1为无包
    //TODO：这块可能调用的时候要麻烦一点了，可能要映射一下，要用012之类的代表一下
    double consumAmount;//一共消费金额
    double money;//余额
    int realTalkTime;//真实聊天时间，这里是本月真实所用，Card里面存储的是一共能有多少在套餐里头
    int realSMSCount;//真实短信数量
    int realFlow;//真实流量

    public MobileCard(){
        /*
        *
        * Default 的卡定义
        * */
        cardNumber="";//卡号
        passWord="";//密码
        userName="" ;//用户名
        serPackage=null;//实际使用包
        packageIndex= -1;//使用的包的index
        consumAmount=0;//实际消费的钱
        money=0;//账户余额//注意，扣钱的时候在进行每次功能结束后进行，也就是说账户余额就是money
        realTalkTime=0;//实际通话时间
        realSMSCount=0;//实际短信数量
        realFlow=0;//实际使用流量
    }
    public MobileCard(String cardNumber,String passWord,String userName,double money,int currPackageIndex){
        /*
        * 实际创建卡对象的时候需要调用的定义
        *
        * */
        this.cardNumber=cardNumber;
        this.passWord=passWord;
        this.userName=userName;
        this.packageIndex = currPackageIndex;
        getAccordPackage();//在这里做一个转换

        realFlow=0;
        realSMSCount=0;
        realFlow=0;
        consumAmount=serPackage.price;//一开始花的钱就是套餐的钱
        this.money=money-consumAmount;//现在扣掉它
    }
    public MobileCard(String cardNumber,String passWord,String userName,double money,int currPackageIndex,double consumAmount,int realTalkTime,int realSMSCount,int realFlow){
        /*
         * 实际创建卡对象的时候需要调用的定义
         *
         * */
        this.cardNumber=cardNumber;
        this.passWord=passWord;
        this.userName=userName;
        this.packageIndex = currPackageIndex;
        getAccordPackage();
        this.realFlow=realFlow;
        this.realSMSCount=realSMSCount;
        this.realFlow=realFlow;
        this.consumAmount=consumAmount;
        this.money=money;//现在扣掉它
    }


    public String showMeg(){
        return("本卡用户名为："+this.userName+"\n，本卡密码为："+this.passWord+"\n  本卡手机号为："+"  "+this.cardNumber)
                + serPackage.showInfo()+ ("\n当前余额为"+money+"元");
    }
    public void getAccordPackage(){
//        ("请选择一个套餐：0：话痨套餐  1：网虫套餐  2：超人套餐");
        if(this.packageIndex==0) this.serPackage = new TalkPackage();
        else if(this.packageIndex==1) this.serPackage = new NetPackage();
        else if(this.packageIndex==2) this.serPackage = new SuperPackage();
        else this.serPackage =null;
    }
    public String getCardNumber(){
        return this.cardNumber;
    }
    public String getPassWord(){
        return this.passWord;
    }
    public String getUserName(){
        return this.userName;
    }
    public int getPackageIndex(){
        return this.packageIndex;
    }
    public int getRealTalkTime(){
        return this.realTalkTime;
    }
    public int getRealSMSCount(){
        return this.realSMSCount;
    }
    public int getRealFlow(){
        return this.realFlow;
    }
    public double getMoney(){
        return this.money;
    }
    public double getConsumAmount(){
        return this.consumAmount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ServicePackage getSerPackage() {
        return serPackage;
    }

    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }

    public void setPackageIndex(int packageIndex) {
        this.packageIndex = packageIndex;
    }

    public void setConsumAmount(double consumAmount) {
        this.consumAmount = consumAmount;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setRealTalkTime(int realTalkTime) {
        this.realTalkTime = realTalkTime;
    }

    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }
}
