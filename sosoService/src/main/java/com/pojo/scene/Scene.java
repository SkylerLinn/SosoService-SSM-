package com.pojo.scene;

import com.dao.ConsumeInfoDao;
import com.dao.MobileCardDao;
import com.pojo.exception.IllegalTypeException;
import com.pojo.exception.InsufficientMoneyException;
import com.pojo.ConsumeInfo;
import com.pojo.MobileCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Scene {
    /*文件名:Service.Scene.java
     * 功能：实现使用场景的功能
     * @作者：计科林铸天
     * @时间：2019.9.23
     *@ Log：不确定那个条件判断能不能走进子类里面去，最后调试的时候一定要看一看。这些东西的边界条件需要考虑好了
     * */
    private String type;
    private int data;
    private String description;
    Scene(){
        type=null;//是什么类型的场景，初始为null
        data=0;//其中的数据量是多少，初始为0
        description=null;//对这次场景的描述是什么样的
    }
    Scene(String type,int data,String description){
        this.type=type;
        this.data=data;
        this.description=description;
    }

    public String printInfo(){
        //说一说这次的情况
        return ("想要进行："+this.description+"，也就是"+this.type+"类型的消费"+this.data);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
