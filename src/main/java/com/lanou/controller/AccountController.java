package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by dllo on 17/10/23.
 */

@Controller
public class AccountController {
    @Resource
    private AccountService accountService;

    //账务账户列表
    @RequestMapping(value = "/account")
    public String accountList() {
        return "account/account_list";
    }

    //添加账务账户页面
    @RequestMapping(value = "/addAccount")
    public String addAccount() {
        return "account/account_add";
    }

    //账户详情页面
    @RequestMapping(value = "/detailAccount")
    public String detail() {
        return "account/account_detail";
    }

    //修改账户信息页面
    @RequestMapping(value = "/updateAccount")
    public String update() {
        return "account/account_modi";
    }

    //获取全部
    @ResponseBody
    @RequestMapping("/accountPage")
    public PageInfo<Account> getAccount(@RequestParam("no") Integer pageNo,
                                        @RequestParam("size") Integer pageSize) {

        PageInfo<Account> pageInfo = accountService.queryAccountByPage(pageNo, pageSize);
        return pageInfo;
    }

    //添加一条账务账户信息
    @ResponseBody
    @RequestMapping(value = "/addaccount", method = RequestMethod.POST)
    public AjaxResult saveAccount(Account account) throws ParseException {

        // 通过身份证设置出生日期
        String idcardNo = account.getIdcardNo();
        String birthDate = idcardNo.substring(6, 10)+"-"+idcardNo.substring(10, 12)+"-"+idcardNo.substring(12, 14)+" 00:00:00";
        SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = sdf.parse(birthDate);
        account.setBirthdate(date);

        if (accountService.findByLoginName(account.getLoginName())) {
            account.setStatus("1");
            account.setCreateDate(new Date());

            accountService.addAccount(account);

            return new AjaxResult("保存成功");
        }
        return new AjaxResult("账户名已存在");
    }

    //显示账户详情
    //通过id查找账户,并把账户存进session里面
    @ResponseBody
    @RequestMapping(value = "/save")
    public AjaxResult saveAccountToSession(@RequestParam("accountId") Integer aid,
                                           HttpServletRequest request) {

        //得到session
        HttpSession session = request.getSession();

        //根据id查找账户
        Account account = accountService.findByAccountId(aid);

        session.setAttribute("account", account);

        return new AjaxResult(account);

    }

    //进入详情页面,显示账户信息
    @ResponseBody
    @RequestMapping(value = "/getdetail")
    public AjaxResult getAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("account");

        System.out.println(account);

        return new AjaxResult(account);
    }

    //修改账户信息

    @ResponseBody
    @RequestMapping(value = "/modiAccount", method = RequestMethod.POST)
    public AjaxResult modiAccount(Account account) {

        System.out.println(account);
        accountService.modiAccount(account);

        return new AjaxResult(account);
    }


    //删除账务账户信息
    @ResponseBody
    @RequestMapping(value = "/delAccount")
    public AjaxResult delete(@RequestParam("accountId") Integer aid) {
        Account account = accountService.findByAccountId(aid);

        account.setCloseDate(new Date());
        account.setStatus("2");

        accountService.modiAccount(account);
        return new AjaxResult(account);

    }


    //开启账户
    @ResponseBody
    @RequestMapping(value = "/open")
    public AjaxResult open(@RequestParam("accountId") Integer aid) {
        Account account = accountService.findByAccountId(aid);

        account.setPauseDate(null);

        account.setStatus("1");

        System.out.println(account);
        accountService.updateAccount(account);

        return new AjaxResult(account);
    }


    //暂停账户
    @ResponseBody
    @RequestMapping(value = "/pause")
    public AjaxResult pause(@RequestParam("accountId") Integer aid) {
        Account account = accountService.findByAccountId(aid);

        account.setPauseDate(new Date());
        account.setStatus("0");

        accountService.updateAccount(account);
        return new AjaxResult(account);
    }


    //模糊查询
    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public PageInfo<Account> fuzzyquery(@RequestParam("no") Integer pageNo,
                                        @RequestParam("size") Integer pageSize,
                                        @RequestParam("idcardNo") String idCard,
                                        @RequestParam("realname") String name,
                                        @RequestParam("loginName") String loginName,
                                        @RequestParam("status") String status) {
        if (idCard.equals("")) {
            idCard = null;
        }
        if (loginName.equals("")) {
            loginName = null;
        }
        if (name.equals("")) {
            name = null;
        }
        if (status.equals("")){
            status = null;
        }
        Account account = new Account();
        account.setIdcardNo(idCard);
        account.setLoginName(loginName);
        account.setRealName(name);
        account.setStatus(status);
        System.out.println(account);
        PageInfo<Account> pageInfo = accountService.queryAccountByCondition(pageNo, pageSize, account);

        return pageInfo;
    }

    //通过身份号查找账务账户信息
    @ResponseBody
    @RequestMapping(value = "/getAccountByIdCardNo")
    public AjaxResult findAccountByIdCardNo(@RequestParam("idcardNo") String idcardNo){
        Account account = accountService.findAccountByIdCardNo(idcardNo);
      if (account == null){
         return new AjaxResult(0);
      }
      return new AjaxResult(account);
    }

    //查找推荐人身份证号
    @ResponseBody
    @RequestMapping(value = "/findrecommenderIdCardNo")
    public AjaxResult findrecommenderIdCardNo(@RequestParam("recommenderId") Integer recommenderId){
        Account account = accountService.findByAccountId(recommenderId);

        return new AjaxResult(account);


    }
}
