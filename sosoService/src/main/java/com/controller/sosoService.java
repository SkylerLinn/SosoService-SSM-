package com.controller;
import com.pojo.*;
import com.pojo.cardPackage.NetPackage;
import com.pojo.cardPackage.SuperPackage;
import com.pojo.cardPackage.TalkPackage;
import com.pojo.exception.IllegalTypeException;
import com.pojo.exception.InsufficientMoneyException;
import com.pojo.scene.Scene;
import com.pojo.scene.SceneList;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;
import com.service.ConsumeInfoService;
import com.service.MobileCardService;
import org.springframework.beans.factory.annotation.Autowired;

public class sosoService {
    /*文件名:Service.sosoService.java
    * 功能：实现运营商的界面,存储主体的信息，作为整个功能的框架主体
    * @author：计科林铸天
    * @时间：2019.9.17
    * @Log: 还差嗖嗖功能没写，和一些功能调试
    * */
    int fee[]={58,68,78};//作为资费数组，方便调用
    @Autowired
    private ConsumeInfoService consumeInfoService;
    @Autowired
    private MobileCardService mobileCardService;
    public void initScene() {
        /*
         * 初始化场景，作为界面，主功能无任何问题
         * */
        System.out.println("**********欢迎来到sousou营业厅**********");
        String customChoice = "";//用户选择
        while (!customChoice.equals("6")) {//单引号还是双引号（与matlab不同）

            System.out.println("1.用户登录 2.用户注册 3.使用嗖嗖 4.话费充值 5.资费说明 6.退出系统");
            System.out.println("请选择：");
            Scanner scanner = new Scanner(System.in);
            customChoice = scanner.next();
            if ("1".equals(customChoice)) {
                signUp();
                continue;
            } else if ("2".equals(customChoice)) {
                signIn();
                continue;
            } else if ("3".equals(customChoice)) {
                useSoso();
                continue;
            } else if ("4".equals(customChoice)) {
                chargeMoney();
                continue;
            } else if ("5".equals(customChoice)) {
                showInformation();
                continue;
            } else if ("6".equals(customChoice)) {
            } else {
                showHint();
                continue;
            }
        }
        
        System.out.println("服务已结束");

    }
    /***********************************一级函数*********************************************/
    public void signUp(){
        /*
        /1 登陆功能
        *需要显示若干下拉菜单，下拉菜单做到二级函数去,瞎输入都能进去？
         */
        /*登陆验证功能，如果可以就能进去，如果错误就反复输入*/
        String currPhoneNum="";
        String currPassWord="";
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请输入手机号：");
            currPhoneNum=scanner.next();
            if (currPhoneNum.equals("0"))
                return;
            System.out.println("请输入密码：");
            currPassWord=scanner.next();
            if(!mobileCardService.isExistMobileCard(currPhoneNum,currPassWord)) {
                System.out.println("手机号或密码错误，请重试！若想退出，手机号处请输入0");
                continue;
            }
            else break;
        }

        /*把信息全部调出来，以后就在这上面更改，因为没有new，所以用的是同一个地址，也就是在同一个地址上更改*/
        MobileCard currCardObject=mobileCardService.getMobileCard(currPhoneNum);//把这个用户的信息全部调出
        List<ConsumeInfo> currConsumInfoList=consumeInfoService.getConsumeInfo(currPhoneNum);//把这个用户的消费信息全部调出来

        /*写子列表，子列表的功能全部在二级函数中实现*/
        while(true){
            System.out.println("******嗖嗖移动用户菜单*******");
            System.out.println("1.本月账单查询");
            System.out.println("2.套餐余量查询");
            System.out.println("3.打印消费详单");
            System.out.println("4. 套餐变更");
            System.out.println("5. 办理退网");
            System.out.println("6.退出子菜单");
            System.out.println("请选择（输入1~5选择功能，其他键返回上一级）");
            String selectChoice=scanner.next();
            if ("1".equals(selectChoice)) {
                searchMonthList(currPhoneNum);
            } else if ("2".equals(selectChoice)) {
                searchRemaining(currCardObject);
            } else if ("3".equals(selectChoice)) {
                printConsumList(currCardObject);
            } else if ("4".equals(selectChoice)) {
                changePack(currCardObject);
            } else if ("5".equals(selectChoice)) {
                deleteUser(currPhoneNum);
            } else if ("6".equals(selectChoice)) {
                System.out.println("退回上一级");
                return;
            } else {
                System.out.println("输入无效");
                continue;
            }
        }

    }
    public void signIn(){
        /*
        * 2 注册功能
        * Log:
        * (1）还有一个bug，就是循环回来再写一次2功能就不正常了,因为随机数的生成存在问题，现在修复了
        * (2) 其他功能均调通
        *
        * */
        System.out.println("******以下是可选号码******");
        int cardNum=9;//生成卡号数量
        String[]altCardNumbers=getNewNumbers(cardNum);

        for (int i=0;i<cardNum;i++){
            if((i+1)%3==0)
                System.out.println(i+":"+altCardNumbers[i]);
            else
                System.out.print(i+":"+altCardNumbers[i]+"    ");
        }//完成了随机号码的输出
       // boolean isLegal=true;
        Scanner scanner=new Scanner(System.in);

        System.out.print("请选择一个卡号：");

        int selectCard=0;
        selectCard = scanner.nextInt();
        while(true){
            if(!(selectCard>=0&&selectCard<=8)) {
                System.out.println("选择不合法，请重新选择！");
                selectCard = scanner.nextInt();
            }
            else
                break;
        }
        //下面写从数组的第 select元素中提取并且塞入新卡号完成注册
        System.out.println("请选择一个套餐：0：话痨套餐  1：网虫套餐  2：超人套餐");

        /*这里选套餐*/
        String selectPacString=scanner.next();
        int currPackageIndex;
        while (true){/*存在问题，为什么选不了对象，原因是没写break，已修复*/
            if(selectPacString.equals("0")||selectPacString.equals("1")||selectPacString.equals("2")) {//这里不能写==，==代表的是地址一致
                currPackageIndex=Integer.parseInt(selectPacString);//这些卡的初始化没有写,后来补上了
                break;
            }
            else{
                System.out.println("选择出错，请重新选择！输入应当在0与2之间！");
                selectPacString=scanner.next();
                continue;
            }

        }

        /*这里选用户名密码*/
        System.out.println("选择成功，请输入用户名和密码");
        System.out.print("用户名：");
        String currPhoneNum=scanner.next();
        System.out.print("密码：");
        String currPassWord=scanner.next();

        /*这里填预存话费*/
        System.out.print("输入成功，请选择预存话费数量:");
        double currMoney=scanner.nextDouble();
        while(true){
            if(currMoney>=fee[Integer.parseInt(selectPacString)]) break;
            else
                System.out.println("预存话费不足以支付当前套餐的首月收费，请重新充值！");

                currMoney=scanner.nextDouble();
        }

        /*生成映射对应对象，包括卡对象和消费清单对象*/
        MobileCard currCard=new MobileCard(altCardNumbers[selectCard],currPassWord,currPhoneNum,currMoney,currPackageIndex);

        /*添加进去数据库*/
        if(mobileCardService.isExistMobileCard(altCardNumbers[selectCard]))
            mobileCardService.updateMobileCard(currCard);
        else
            mobileCardService.saveMobileCard(currCard);

        //这里就不用再创建这样的数据库了，因为所有手机号公用一个数据库，往里面加就成

        /*结束语句*/
        System.out.println("注册成功，以下是本卡信息：");
        currCard.showMeg();

    }
    public void useSoso(){
        /*
        * 3 使用嗖嗖功能，这个比较难写，主要是每个包里面的功能都不一样
        * 主要是变更消费记录加上在各种实际使用的钱数和消费内容的改变
        * */
        //可以调用的函数为Scene的cost printCostInfo isLeagal

        System.out.println("请输入消费卡号：");
        Scanner scanner=new Scanner(System.in);
        String currCardNum=scanner.next();
        if (!mobileCardService.isExistMobileCard(currCardNum)){
            System.out.println("该卡不存在！");
            return;
        }

        MobileCard currCard= mobileCardService.getMobileCard(currCardNum);

        Scene currScene=randomScene();
        currScene.printInfo();
//        try{
////            currScene.cost(currCard);//cost更改完不再是按址操作了，要上传回去，写进cost里头了
//            System.out.println("消费成功，当前账单为：");
//            searchMonthList(currCardNum);
//        }catch(IllegalTypeException i){
//            i.showException();
//        }catch(InsufficientMoneyException e){
//            e.showException();
//        }
//        //一层层向上抛出的时候要用throws打包，否则上面是识别不出来的，如果这一层不处理，要想要主函数知道，必须要逐层向上抛出

    }

    public void chargeMoney(){
        /*
        * 4 氪金功能，全写完了，边界条件未写
        * */
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入需要充值的卡号");
        String currPhoneNum;
        while(true){

            currPhoneNum=scanner.next();
            if(!this.mobileCardService.isExistMobileCard(currPhoneNum)){
                System.out.println("该卡号不存在，请重新输入：");
                return;
            }
            else break;
        }


        System.out.println("请输入充值金额，每次充值必须大于50元");
        double currCharge;
        while(true){
            currCharge=scanner.nextDouble();
            if(currCharge>=50)break;
            else {
                System.out.println("金额不足50元，请重新充值！");
                continue;
            }
        }
        MobileCard tmpMobileCard = mobileCardService.getMobileCard(currPhoneNum);//这个是按址更改，所以不需要再往回推了
        tmpMobileCard.setMoney(tmpMobileCard.getMoney()+currCharge);
        mobileCardService.updateMobileCard(tmpMobileCard);
        tmpMobileCard.showMeg();


    }
    public void showInformation(){
        /*
        * 5 展示套餐信息功能,保存路径为根目录下的information文件，全写完了，边界条件没写
        * */
        try{

            PrintStream mytxt=new PrintStream("./Information.txt");
            PrintStream out=System.out;
            System.setOut(mytxt);
            //下面写内容就行了，

            System.out.println("****资费说明****");
            //因为把流定向到外面了，只要在把资费输出重新输出出来就行了
            printLine();

            System.out.println("使用话痨套餐，套餐内通话时间为500分钟，短信数量为30条。");
            printLine();
            System.out.println("使用超人套餐，套餐内通话时间为200分钟，使用流量为1GB，短信数量为50条。");
            printLine();
            System.out.println("使用网虫套餐，套餐内上网流量为3GB。");
            printLine();
            System.out.println("套餐外：通话资费为0.2元/分钟，短信条数为0.1元/分钟，上网流量为0.1元/MB");

            System.setOut(out);//重定向回来
            System.out.println("文件保存完毕！");
        }catch(FileNotFoundException e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
            e.printStackTrace();
        }


    }
    public void showHint() {
        /*
        /输入无效提示
         */
        System.out.println("输入无效，请重新输入！");
    }

    public void printLine(){
        System.out.println("-------------------------------------------------");
    }

    public Scene randomScene(){
        //找一个随机的场景出来
        SceneList myList=new SceneList();//调出随机myList
        Random random = new Random();//指定种子数字
        int randomChoice=random.nextInt(6);

        return myList.getSceneList()[randomChoice];//pick出一个场景出来
    }
    /*****************************二级函数***********************************/
    public String getCardNum(){
        /*注意：seed若是一样的，产生的序列也是一样的
        * 获得一个卡号
        * */
        Random random = new Random();//指定种子数字
        String tmpNum="139";
        for(int j=0;j<8;j++){//逐个产生电话号码
            int tmp = random.nextInt(10);
            tmpNum+=tmp;

        }
        return tmpNum;
    }
    public String[] getNewNumbers(int cardNum){
        /*
        * 获得一个合法定长卡号数组
        * */
        String[] altCardNumbers = new String[cardNum];//一定要规定长度吗
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
    /*************************************************************************************************/
    /*二级菜单功能*/
    public void searchMonthList(String currCardNumber){
        /*
        * 子功能1：本月账单查询
        *
        * */

        if(!this.mobileCardService.isExistMobileCard(currCardNumber)){
            System.out.println("用户不存在，请重试");
            return;
        }
        MobileCard currCardObject = this.mobileCardService.getMobileCard(currCardNumber);
        System.out.println("*****本月账单查询*****");
        System.out.println("您的卡号为："+currCardObject.getCardNumber());
        System.out.println("当月账单");
        System.out.println("套餐资费："+currCardObject.getSerPackage().price);
        System.out.println("本月消费:"+currCardObject.getConsumAmount());
        System.out.println("账户余额："+currCardObject.getMoney());//注意，扣钱的时候在进行每次功能结束后进行，也就是说账户余额就是money！！！！注意变成只有一位小数的东西
    }

    public void searchRemaining(MobileCard currCardObject){
        /*
        * 子功能2：套餐余量查询
        *(bepositive就是让它变成符合要求的输出）
        *
        * */
        if(!this.mobileCardService.isExistMobileCard(currCardObject.getCardNumber())){
            System.out.println("用户不存在，请重试");
            return;
        }
        currCardObject.getSerPackage().showInfo();
        //这个地方想必是bePositive的问题
        System.out.println("当前流量余额为："+(bePositive(currCardObject.getSerPackage().getFlow()-currCardObject.getRealFlow())));
        System.out.println("当前通话时长余额为："+(bePositive(currCardObject.getSerPackage().getTalkTime()-currCardObject.getRealTalkTime())));
        System.out.println("当前短信余额为："+(bePositive(currCardObject.getSerPackage().getSmsCount()-currCardObject.getRealSMSCount())));
    }
    public void printConsumList(MobileCard currCardObject){
        /*
        * 子功能3：打印消费详单
        *
        * */
        if(!this.mobileCardService.isExistMobileCard(currCardObject.getCardNumber())){
            System.out.println("用户不存在，请重试");
            return;
        }
        System.out.println("***输入/打印消费详单功能***");
        System.out.println("1. 输入消费信息");
        System.out.println("2. 打印消费详单,其他按键退出该功能");
        Scanner scanner=new Scanner(System.in);
        String choice=scanner.next();
        if(choice.equals("1")) {
            try{
                //inputConsumDetail();
                System.out.println("请输入消费类型：");
                String currType=scanner.next();
                System.out.println("请输入消费数量：");
                int currData=scanner.nextInt();
                ConsumeInfo currInfo=new ConsumeInfo(currCardObject.getCardNumber(),currType,currData);
                consumeInfoService.saveConsumeInfo(currInfo);
                System.out.println("添加成功");
            }catch(Exception e){
                System.out.println("输入错误请重试");
            }

        }
        else if(choice.equals("2")){
            try{

                PrintStream mytxt=new PrintStream("./Consume.txt");
                PrintStream out=System.out;
                System.setOut(mytxt);
                //下面写内容就行了，

                System.out.println("****消费详单****");
                //因为把流定向到外面了，只要在把资费输出重新输出出来就行了
                System.out.println("对应手机号为"+currCardObject.getCardNumber());
                List<ConsumeInfo>currConsumInfoList = consumeInfoService.getConsumeInfo(currCardObject.getCardNumber());
                for(int i=0;i<currConsumInfoList.size();i++){
                    currConsumInfoList.get(i).printInfo();
                }
                System.setOut(out);//重定向回来
                System.out.println("文件保存完毕！");
            }catch(FileNotFoundException e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
                e.printStackTrace();
            }


        }


    }
    public void changePack(MobileCard currCardObject){
        /*
        * 子功能4：套餐变更
        *
        * */
        if(!this.mobileCardService.isExistMobileCard(currCardObject.getCardNumber())){
            System.out.println("用户不存在，请重试");
            return;
        }
        System.out.println("请输入需要更换的套餐：");
        Scanner scanner=new Scanner(System.in);

        //更改套餐
        System.out.println("请选择一个套餐：0：话痨套餐  1：网虫套餐  2：超人套餐");

        /*这里选套餐*/
        int selectNewPac=scanner.nextInt();

        if(currCardObject.getSerPackage() instanceof TalkPackage &&selectNewPac==0||currCardObject.getSerPackage() instanceof NetPackage &&selectNewPac==1||currCardObject.getSerPackage() instanceof SuperPackage &&selectNewPac==2){
            System.out.println("您选中的与当前套餐相同，请重新操作！");
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
                System.out.println("输入非法");
                return;

        }
        System.out.println("当前套餐为：");//这里读取不出当前套餐
        this.mobileCardService.getMobileCard(currCardObject.getCardNumber()).getSerPackage().showInfo();

        /*为什么这里出来的是父类的showInfo*/
//        currCardObject.serPackage.showInfo();

    }
    public void deleteUser(String currPhoneNum){
        /*
        *子功能5：办理退网
        *
        **/
        if(!this.mobileCardService.isExistMobileCard(currPhoneNum)){
            System.out.println("用户不存在，请重试");
            return;
        }else{
            mobileCardService.deleteMobileCard(currPhoneNum);
            consumeInfoService.deleteConsumeInfo(currPhoneNum);
        }
    }

}
