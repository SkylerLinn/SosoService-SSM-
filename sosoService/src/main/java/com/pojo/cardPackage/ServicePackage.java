package com.pojo.cardPackage;

import com.pojo.exception.InsufficientMoneyException;
import com.pojo.MobileCard;

public class ServicePackage {
    /*文件名:Service.ServicePackage.java
     * 功能：作为各类服务包的父类
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
    public double price=0;
    protected int talkTime=0;
    protected int smsCount=0;
    protected int flow=0;
    public String showInfo(){
        return ("套餐外：通话资费为0.2元/分钟，短信条数为0.1元/分钟，上网流量为0.1元/MB\n");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    /*这些功能改在servicepackage里面更好，这样更方面从上到下调用*/
    public void call(int minCount, MobileCard card)throws InsufficientMoneyException {//这里默认可以扣掉，如果钱超出去了，那就在场景里实现
        double cost=0;
        if(minCount+card.getRealTalkTime()>card.getSerPackage().talkTime){
            //cost=0.2*(minCount+card.realTalkTime-card.serPackage.talkTime);//这里写的不对，相当于每次都得算一遍上一次的通话时间了
            if(card.getRealTalkTime()<card.getSerPackage().talkTime){
                cost=0.2*(minCount+card.getRealTalkTime()-card.getSerPackage().talkTime);
            }
            else{
                cost=0.2*minCount;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("通话",minCount,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setConsumAmount(card.getConsumAmount()+cost);
            card.setRealTalkTime(card.getRealTalkTime()+minCount);
        }

    }
    public void send(int count,MobileCard card)throws InsufficientMoneyException{
        double cost=0;
        if(count+card.getRealSMSCount()>card.getSerPackage().smsCount){
            if(card.getRealSMSCount()<card.getSerPackage().smsCount){
                cost=0.1*(count+card.getRealSMSCount()-card.getSerPackage().smsCount);
            }
            else{
                cost=0.1*count;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("短信",count,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setRealSMSCount(card.getRealSMSCount()+count);
            card.setConsumAmount(card.getConsumAmount()+cost);
        }

    }
    public void netPlay(int flow, MobileCard card)throws InsufficientMoneyException{
        double cost=0;
        if(flow+card.getRealFlow()>card.getSerPackage().flow){
            if(card.getRealFlow()<card.getSerPackage().flow){
                cost=0.1*(flow+card.getRealFlow()-card.getSerPackage().flow);
            }
            else{
                cost=0.1*flow;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("数据",flow,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setRealFlow(card.getRealFlow()+flow);
            card.setConsumAmount(card.getConsumAmount()+cost);
        }
    }
}
