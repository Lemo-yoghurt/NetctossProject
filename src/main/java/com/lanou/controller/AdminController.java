package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Admin;
import com.lanou.service.AdminService;
import com.lanou.utils.AjaxResult;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.UnicodeEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by dllo on 17/10/28.
 */
@Controller
public class AdminController {

    @Resource
    private AdminService adminService;

    //管理员页面
    @RequestMapping(value = "/admin")
    public String intoAdmin() {
        return "admin/admin_list";
    }

    //添加页面
    @RequestMapping(value = "/addadmin")
    public String addadmin() {
        return "admin/admin_add";
    }

    //修改页面
    @RequestMapping(value = "/updateadmin")
    public String updateadmin() {
        return "admin/admin_modi";
    }

    //显示全部
    @ResponseBody
    @RequestMapping(value = "/getAllAdmins")
    public PageInfo<Admin> getAllAdmin(@RequestParam("no") Integer pageNo,
                                       @RequestParam("size") Integer pageSize) {

        PageInfo<Admin> pageInfo = adminService.queryAdminByPage(pageNo, pageSize);

        return pageInfo;
    }

    //删除管理员
    @ResponseBody
    @RequestMapping(value = "/delAdmin")
    public AjaxResult delAdmin(@RequestParam("adminId") Integer adminId) {

        //根据adminId删除admin表中的内容
        adminService.deleteAdmin(adminId);

        //删除中间表的内容
        adminService.deleteAdminRole(adminId);

        return new AjaxResult(adminId);

    }

    //将要修改的admin存进session
    @ResponseBody
    @RequestMapping(value = "/updateAdmin")
    public AjaxResult updateAdmin(@RequestParam("adminId") Integer adminId,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();

        Admin admin = adminService.findAdminByAdminId(adminId);

        session.setAttribute("admin", admin);

        return new AjaxResult(admin);
    }

    //在修改页面显示要修改的admin对象的信息
    @ResponseBody
    @RequestMapping(value = "/detailAdmin")
    public AjaxResult detailAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("admin");

        return new AjaxResult(admin);
    }

    //保存信息
    @ResponseBody
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public AjaxResult addAdmin(@RequestParam("name") String name,
                               @RequestParam("adminCode") String adminCode,
                               @RequestParam("password") String password,
                               @RequestParam("telephone") String telephone,
                               @RequestParam("email") String email,
                               @RequestParam("roles") Integer[] roles) {

        if (roles.length == 0 || roles == null) {
            return new AjaxResult(0);
        }
        if (!adminService.findAdminByAdminCode(adminCode)) {
            return new AjaxResult(1);
        }
        Admin admin = new Admin();

        admin.setName(name);
        admin.setAdminCode(adminCode);
        admin.setEnrolldate(new Date());
        admin.setPassword(password);
        admin.setEmail(email);
        admin.setTelephone(telephone);

        adminService.insertAdmin(admin);

        for (Integer role : roles) {
            adminService.insertAdminRole(admin.getAdminId(), role);
        }
        return new AjaxResult(2);
    }

    //保存修改后的信息
    @ResponseBody
    @RequestMapping(value = "/modiAdmin")
    public AjaxResult modiAdmin(@RequestParam("adminId") String adminId,
                                @RequestParam("name") String name,
                                @RequestParam("adminCode") String adminCode,
                                @RequestParam("telephone") String telephone,
                                @RequestParam("email") String email,
                                @RequestParam("roles") Integer[] roles) {

        System.out.println(adminId);

        if (roles.length == 0 || roles == null) {
            return new AjaxResult(0);
        }

        Admin admin = new Admin();
        admin.setAdminId(Integer.parseInt(adminId));
        admin.setAdminCode(adminCode);
        admin.setName(name);
        admin.setEmail(email);
        admin.setTelephone(telephone);

        System.out.println(admin);

        adminService.modiAdmin(admin);

        adminService.deleteAdminRole(Integer.parseInt(adminId));

        for (Integer role : roles) {

            adminService.insertAdminRole(Integer.parseInt(adminId), role);
        }

        return new AjaxResult(2);

    }

    //高级查询
    @ResponseBody
    @RequestMapping(value = "/queryAdminByCondition",method = RequestMethod.POST)
    public PageInfo<Admin> queryAdminByCondition(@RequestParam("no") Integer pageNo,
                                                 @RequestParam("size") Integer pageSize,
                                                 @RequestParam("moduleId") Integer moduleId,
                                                 @RequestParam("roleName") String roleName) {
        if (moduleId == 0){
            moduleId = null;
        }

        PageInfo<Admin> pageInfo = adminService.queryAdminByCondition(pageNo, pageSize, moduleId,roleName);

        return pageInfo;
    }

}
