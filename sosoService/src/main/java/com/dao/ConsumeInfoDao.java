package com.dao;

import com.pojo.*;

import java.util.List;

public interface ConsumeInfoDao {
    //增加
    boolean saveConsumeInfo(ConsumeInfo consumeInfo);
    //删除
    boolean deleteConsumeInfo(String phoneNumber);
    //修改
    boolean updateConsumeInfo(ConsumeInfo consumeInfo);

    //查询
    //fixme:唯一不同的地方在这里！！！
    List<ConsumeInfo> getConsumeInfo(String phoneNumber);

    List<ConsumeInfo> queryAllConsumeInfo();
}

