package com.service.impl;

import com.dao.MobileCardDao;
import com.pojo.MobileCard;
import com.service.MobileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MobileCardServiceImpl implements MobileCardService {
    @Autowired//完成自动装配 使得类中getter/setter能被自动填充
    private MobileCardDao mobileCardDao;
    @Override

    public boolean saveMobileCard(MobileCard mobileCard) {
        return mobileCardDao.saveMobileCard(mobileCard);
    }

    @Override
    public boolean deleteMobileCard(String phoneNumber) {
        return mobileCardDao.deleteMobileCard(phoneNumber);
    }

    @Override
    public boolean isExistMobileCard(String phoneNumber) {
        return this.getMobileCard(phoneNumber) != null;
    }

    @Override
    public boolean isExistMobileCard(String phoneNumber, String passWord) {
        if(isExistMobileCard(phoneNumber))
            return getMobileCard(phoneNumber).getPassWord().equals(passWord);
        else
            return false;
    }

    @Override
    public boolean updateMobileCard(MobileCard mobileCard) {
        return mobileCardDao.updateMobileCard(mobileCard);
    }

    @Override
    public MobileCard getMobileCard(String phoneNumber) {
        MobileCard tmp = mobileCardDao.getMobileCard(phoneNumber);
        tmp.getAccordPackage();
        return tmp;
    }

    @Override
    public List<MobileCard> queryAllMobileCard() {
        List<MobileCard> tmp =  mobileCardDao.queryAllMobileCard();
        for(MobileCard card:tmp)
            card.getAccordPackage();
        return tmp;
    }

}
