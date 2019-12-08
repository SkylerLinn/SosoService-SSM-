package com.dao;

import com.pojo.MobileCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MobileCardDao {
    //增加

    boolean saveMobileCard(MobileCard mobileCard);
    //删除
    boolean deleteMobileCard(String phoneNumber);
    //修改
    boolean updateMobileCard(MobileCard mobileCard);

    //查询
    MobileCard getMobileCard(String phoneNumber);

    List<MobileCard> queryAllMobileCard();
}
