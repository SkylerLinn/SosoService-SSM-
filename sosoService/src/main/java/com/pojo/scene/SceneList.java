package com.pojo.scene;

public  class SceneList {
    final Scene sceneList[]=new Scene[10];

    public Scene[] getSceneList() {
        return sceneList;
    }

    public SceneList(){
        sceneList[0]=new Scene("数据",100,"打游戏，花掉100MB");
        sceneList[1]=new Scene("通话",20,"与家人通话20分钟");
        sceneList[2]=new Scene("短信",50,"通知别人更换号码");
        sceneList[3]=new Scene("数据",1000,"看了一部高清的电影");
        sceneList[4]=new Scene("通话",10,"与老板汇报工作");
        sceneList[5]=new Scene("短信",20,"向同事群发工作信息");
    }
}
