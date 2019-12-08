package com.service.impl;

import com.pojo.ConsumeInfo;
import com.pojo.MobileCard;
import com.pojo.cardPackage.NetPackage;
import com.pojo.cardPackage.SuperPackage;
import com.pojo.cardPackage.TalkPackage;
import com.pojo.exception.IllegalTypeException;
import com.pojo.exception.InsufficientMoneyException;
import com.pojo.scene.Scene;
import com.pojo.scene.SceneList;
import com.service.BasicFunctionService;
import com.service.ConsumeInfoService;
import com.service.MobileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class BasicFunctionServiceImpl implements BasicFunctionService {
    //定义
    @Autowired
    private ConsumeInfoService consumeInfoService;
    @Autowired
    private MobileCardService mobileCardService;

    @Override
    public String useSoso(String cardNum) {
        if (!mobileCardService.isExistMobileCard(cardNum)){
            return ("该卡不存在！");

        }

        MobileCard currCard= mobileCardService.getMobileCard(cardNum);

        Scene currScene=randomScene();
        String returnInfo = currScene.printInfo();
        try{
            this.cost(currCard,currScene);//cost更改完不再是按址操作了，要上传回去，写进cost里头了
            return returnInfo+("消费成功，当前账单为：")+ searchMonthList(cardNum);
        }catch(IllegalTypeException i){
            return returnInfo+i.showException();
        }catch(InsufficientMoneyException e){
            return returnInfo+e.showException();
        }
    }

    @Override
    public String chargeMoney(String cardNum, int money) {
        if(!this.mobileCardService.isExistMobileCard(cardNum))
            return "卡号不存在!";

        if(money<50)
            return ("每次充值必须大于50元!");

        MobileCard tmpMobileCard = mobileCardService.getMobileCard(cardNum);//这个是按址更改，所以不需要再往回推了
        tmpMobileCard.setMoney(tmpMobileCard.getMoney()+money);
        mobileCardService.updateMobileCard(tmpMobileCard);
        return "充值成功\n"+tmpMobileCard.showMeg();

    }

    @Override
    public String showInformation(String cardNum) {
        try{
            return
            ("****资费说明****"+"\n")
            +("使用话痨套餐，套餐内通话时间为500分钟，短信数量为30条。\n")
            +("使用超人套餐，套餐内通话时间为200分钟，使用流量为1GB，短信数量为50条。\n")
            +("使用网虫套餐，套餐内上网流量为3GB。\n")
            +("套餐外：通话资费为0.2元/分钟，短信条数为0.1元/分钟，上网流量为0.1元/MB\n");
        }catch(Exception e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
            e.printStackTrace();
            return "暂无信息";
        }
    }

    @Override
    public Scene randomScene() {

    final int sceneSize = 6;
    //找一个随机的场景出来
    SceneList myList = new SceneList();//调出随机myList
    Random random = new Random();//指定种子数字
    int randomChoice = random.nextInt(sceneSize);

    return myList.getSceneList()[randomChoice];//pick出一个场景出来
    }
    @Override
    public String searchMonthList(String currCardNumber) {
        if(!this.mobileCardService.isExistMobileCard(currCardNumber)){
           return("用户不存在，请重试");

        }
        MobileCard currCardObject = this.mobileCardService.getMobileCard(currCardNumber);
        String monthListInformation = "*****本月账单查询*****\n";
        monthListInformation = monthListInformation+("您的卡号为："+currCardObject.getCardNumber()+"\n")
                +("当月账单\n")
                +("套餐资费："+currCardObject.getSerPackage().price+"\n")
                +("本月消费:"+currCardObject.getConsumAmount()+"\n")
                +("账户余额："+currCardObject.getMoney()+"\n");
        return monthListInformation;
    }

    @Override
    public String searchRemaining(String cardNum) {
        if(!this.mobileCardService.isExistMobileCard(cardNum)){
            return "用户不存在，请重试";
        }
        MobileCard currCardObject = mobileCardService.getMobileCard(cardNum);
        //fixme：这个地方要把这些println全部变成string传回去！！！
        //fixme：上面的那些东西也要修修补补
//        currCardObject.getSerPackage().showInfo();
        //这个地方想必是bePositive的问题
        return ("当前流量余额为："+(bePositive(currCardObject.getSerPackage().getFlow()-currCardObject.getRealFlow())))
        + ("\n当前通话时长余额为："+(bePositive(currCardObject.getSerPackage().getTalkTime()-currCardObject.getRealTalkTime())))
        +("\n当前短信余额为："+(bePositive(currCardObject.getSerPackage().getSmsCount()-currCardObject.getRealSMSCount())));
    }

    @Override
    public String printConsumeList(String cardNum) {
        try{
            MobileCard currCardObject = mobileCardService.getMobileCard(cardNum);
            String consumeList = "";

            consumeList = consumeList+("****消费详单****")+"\n";
            //因为把流定向到外面了，只要在把资费输出重新输出出来就行了
            consumeList = consumeList+("对应手机号为"+currCardObject.getCardNumber())+"\n";
            List<ConsumeInfo> currConsumeInfoList = consumeInfoService.getConsumeInfo(currCardObject.getCardNumber());
            for(int i=0;i<currConsumeInfoList.size();i++){
                consumeList = consumeList+ currConsumeInfoList.get(i).printInfo()+"\n";
            }

            return consumeList;
        }catch(Exception e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
            e.printStackTrace();
            return "打印失败！";
        }
    }

    @Override
    public String changePack(String cardNum,int selectNewPac) {
        if(!this.mobileCardService.isExistMobileCard(cardNum)){
            return "该卡不存在！";
        }
        MobileCard currCardObject = mobileCardService.getMobileCard(cardNum);
        if(currCardObject.getSerPackage() instanceof TalkPackage &&selectNewPac==0||currCardObject.getSerPackage() instanceof NetPackage &&selectNewPac==1||currCardObject.getSerPackage() instanceof SuperPackage &&selectNewPac==2){
            return "与原来的卡包相同，请重选！";//凡是之前错误提示的部分都改成return false
        }
        switch(selectNewPac){
            case 0:
            case 1:
            case 2:
                //重新排布这些东西的内容
                currCardObject.setPackageIndex(selectNewPac);
                currCardObject.setRealFlow(0);
                currCardObject.setRealSMSCount(0);
                currCardObject.setRealTalkTime(0);

                this.mobileCardService.updateMobileCard( currCardObject);
                break;
            default:
                return "选项错误";

        }
        return "修改成功！新卡包为"+selectNewPac+"号。\n";
    }

    @Override
    public String deleteUser(String currPhoneNum) {
        //完成迁移
        if(!this.mobileCardService.isExistMobileCard(currPhoneNum)){
            return "删除失败！";
        }else{
            mobileCardService.deleteMobileCard(currPhoneNum);
            consumeInfoService.deleteConsumeInfo(currPhoneNum);
            return "删除成功！";
        }
    }

    //下面的都不是Override了
    public String getCardNum(){
        //这个是内置的，并不是继承的，下同
        Random random = new Random();//指定种子数字
        String tmpNum="139";
        for(int j=0;j<8;j++){//逐个产生电话号码
            int tmp = random.nextInt(10);
            tmpNum+=tmp;

        }
        return tmpNum;
    }
    public String[] getNewNumbers(){
        /*
         * 获得一个合法定长卡号数组
         * */
        final int cardNum = 9;
        String[] altCardNumbers = new String[cardNum];
        for (int i=0;i<cardNum;i++){
            String tmpNum=getCardNum();
            while(this.mobileCardService.isExistMobileCard(tmpNum)){
                tmpNum=getCardNum();
            }
            altCardNumbers[i]=tmpNum;//调试通过

        }
        return altCardNumbers;
    }
    int bePositive(int i){
        if(i>=0) return i;
        else return 0;
    }
    double bePositive(double i){
        if(i>=0) return i;
        else return 0;
    }
    void cost(MobileCard currCard,Scene scene) throws InsufficientMoneyException, IllegalTypeException {
        int data = scene.getData();
        String type =scene.getType();

        if(type.equals("数据"))
            currCard.getSerPackage().netPlay(data,currCard);
        else if(type.equals("通话"))
            currCard.getSerPackage().call(data,currCard);
        else if(type.equals("短信"))
            currCard.getSerPackage().send(data,currCard);

        else
            throw new IllegalTypeException(type);
        //更新数据库
        //之后放开
        ConsumeInfo consumeInfo = new ConsumeInfo(currCard.getCardNumber(),type,data);
        consumeInfoService.saveConsumeInfo(consumeInfo);
        mobileCardService.updateMobileCard(currCard);

    }
}
