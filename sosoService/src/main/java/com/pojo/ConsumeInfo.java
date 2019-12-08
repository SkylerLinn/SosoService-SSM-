package com.pojo;

public class ConsumeInfo {
    /*文件名:Service.ConsumInfo.java
     * 功能：存储消费信息
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
    private String cardNumber;//电话号码
    private String consumeType;//消费类型
    private int consumeData;//消费数额



    ConsumeInfo(){
        cardNumber=null;
        consumeType=null;
        consumeData=0;
    }
    public ConsumeInfo(String cardNumber, String type, int consumData){
        this.consumeData=consumData;
        this.cardNumber=cardNumber;
        this.consumeType=type;
    }
    public String printInfo(){
        return ("消费类型："+consumeType+",消费金额："+consumeData);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public int getConsumeData() {
        return consumeData;
    }

    public void setConsumeData(int consumeData) {
        this.consumeData = consumeData;
    }
}
