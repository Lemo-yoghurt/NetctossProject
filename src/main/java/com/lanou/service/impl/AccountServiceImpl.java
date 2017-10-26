package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    //分页信息
    public PageInfo<Account> queryAccountByPage(Integer pageNo, Integer pageSize) {
        //判断参数合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部账号
        List<Account> accounts = accountMapper.findAll();


        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accounts);

        return pageInfo;
    }

    //分页模糊查询
    public PageInfo<Account> queryAccountByCondition(Integer pageNo, Integer pageSize,Account account){
        //判断参数合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部账号
        List<Account> accounts = accountMapper.findByCondition(account);
        System.out.println(accounts);


        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accounts);

        return pageInfo;
    }

    public void addAccount(Account account) {
        accountMapper.insertSelective(account);
    }

    //通过用户名查询信息
    public boolean findByLoginName(String name) {
        Account account = accountMapper.selectByLoginName(name);
        if (account == null){
            return true;
        }
        return false;
    }

    //根据id查询信息
    public Account findByAccountId(Integer aid) {
        Account account = accountMapper.selectByPrimaryKey(aid);
        return account;
    }

    //修改账户信息
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    //保存修改的信息
    public void modiAccount(Account account){
        accountMapper.updateByPrimaryKeySelective(account);
    }


    //通过身份号查找账务账户信息
    public Account findAccountByIdCardNo(String idcardNo) {
        Account account = accountMapper.findAccountByIdCardNo(idcardNo);
        return account;
    }


}
