package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;


/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {

    //显示分页信息
    PageInfo<Account> queryAccountByPage(Integer pageNo, Integer pageSize);

    //添加一条账户信息
    void addAccount(Account account);

    //通过账户名查询账户信息
   boolean findByLoginName(String name);

    //根据id查询信息
    Account findByAccountId(Integer aid);


    //修改信息
    void updateAccount(Account account);

    void modiAccount(Account account);

    //通过身份号查找账务账户信息
    Account findAccountByIdCardNo(String idcardNo);

    //分页模糊查询
    PageInfo<Account> queryAccountByCondition(Integer pageNo, Integer pageSize,Account account);

    //通过推荐人id查找account
    Account findAccountByrecommenderId(Integer recommenderId);
}
