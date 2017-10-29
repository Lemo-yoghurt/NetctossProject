package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Admin;
import org.junit.After;

/**
 * Created by dllo on 17/10/28.
 */
public interface AdminService {

    PageInfo<Admin> queryAdminByPage(Integer pageNo, Integer pageSize);

    //根据adminId查找admin
    Admin findAdminByAdminId(Integer adminId);

    //根据adminCode查找admin
    boolean findAdminByAdminCode(String adminCode);

    //向admin表添加信息
    void insertAdmin(Admin admin);


    //根据adminId删除admin表的内容
    void deleteAdmin(Integer adminId);


    //根据adminId删除中间表内容
    void deleteAdminRole(Integer adminId);

    //给中间表插入数据
    void insertAdminRole(Integer adminId, Integer roleId);

    //修改管理员信息
    void modiAdmin(Admin admin);

    //模糊查询
    PageInfo<Admin> queryAdminByCondition(Integer pageNo, Integer pageSize,Integer moduleId,String roleName);

}
