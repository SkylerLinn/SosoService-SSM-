package com.service;

import com.pojo.ConsumeInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ConsumeInfoService {
    //增加
    boolean saveConsumeInfo(ConsumeInfo consumeInfo);
    //删除
    boolean deleteConsumeInfo(String phoneNumber);
    //修改
    boolean updateConsumeInfo(ConsumeInfo consumeInfo);

    //查询
    //fixme:唯一不同的地方在这里！！！
    List<ConsumeInfo> getConsumeInfo(String phoneNumber);
    //是否存在
    boolean isExistConsumeInfo(String phoneNumber);
    List<ConsumeInfo> queryAllConsumeInfo();
}
