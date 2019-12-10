package com.service;

import com.pojo.MobileCard;
import com.pojo.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("basicFunctionService")
public interface BasicFunctionService {
    String useSoso(String cardNum);//钱不够就返回false
    String chargeMoney(String cardNum,int money);//没氪够就返回false
    String showInformation(String cardNum);
    Scene randomScene();
    public String searchMonthList(String currCardNumber);
    public String searchRemaining(String currCardNumber);
    public String printConsumeList(String currCardNumber);
    public String changePack(String currCardNumber,int selectNewPac);
    public String deleteUser(String currPhoneNum);
}
