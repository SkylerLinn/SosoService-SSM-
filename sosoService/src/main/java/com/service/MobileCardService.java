package com.service;
import com.pojo.MobileCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("mobileCardService")
public interface MobileCardService {
    //增加

    boolean saveMobileCard(MobileCard mobileCard);
    //删除
    boolean deleteMobileCard(String phoneNumber);
    //修改
    boolean updateMobileCard(MobileCard mobileCard);
    //查询
    MobileCard getMobileCard(String phoneNumber);
    boolean isExistMobileCard(String phoneNumber);
    boolean isExistMobileCard(String phoneNumber,String passWord);
    //爬取所有MobileCard
    List<MobileCard> queryAllMobileCard();
}
