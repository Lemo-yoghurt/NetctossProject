package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> findAll();

    Account selectByLoginName(String name);

    void updateAccount(Account account);
    
    //模糊查询
    List<Account> findByCondition(Account account);

    //通过身份证号查询账户账号
    Account findAccountByIdCardNo(String idcardNo);

    //通过推荐人id查找account
    Account findAccountByrecommenderId(Integer recommenderId);
}