package com.service.impl;

import com.dao.ConsumeInfoDao;
import com.pojo.ConsumeInfo;
import com.service.ConsumeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsumeInfoServiceImpl implements ConsumeInfoService {
    @Autowired
    ConsumeInfoDao consumeInfoDao;
    @Override
    public boolean saveConsumeInfo(ConsumeInfo consumeInfo) {
        return consumeInfoDao.saveConsumeInfo(consumeInfo);
    }

    @Override
    public boolean deleteConsumeInfo(String phoneNumber) {
        return consumeInfoDao.deleteConsumeInfo(phoneNumber);
    }

    @Override
    public boolean updateConsumeInfo(ConsumeInfo consumeInfo) {
        return consumeInfoDao.updateConsumeInfo(consumeInfo);
    }

    @Override
    public List<ConsumeInfo> getConsumeInfo(String phoneNumber) {
        return consumeInfoDao.getConsumeInfo(phoneNumber);
    }

    @Override
    public boolean isExistConsumeInfo(String phoneNumber) {
        return consumeInfoDao.getConsumeInfo(phoneNumber).isEmpty();
    }

    @Override
    public List<ConsumeInfo> queryAllConsumeInfo() {
        return consumeInfoDao.queryAllConsumeInfo();
    }

}
