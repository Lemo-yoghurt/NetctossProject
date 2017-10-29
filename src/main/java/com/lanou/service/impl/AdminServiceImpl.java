package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Admin;
import com.lanou.mapper.AdminMapper;
import com.lanou.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    public PageInfo<Admin> queryAdminByPage(Integer pageNo, Integer pageSize) {
        //判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //查询所有管理员
        List<Admin> admins = adminMapper.findAllAdmins();

        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);

        return pageInfo;
    }

    //根据adminId查找admin
    public Admin findAdminByAdminId(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    //根据adminId删除admin表的内容
    public void deleteAdmin(Integer adminId){
        adminMapper.deleteByPrimaryKey(adminId);
    }

    //向admin表添加信息
    public void insertAdmin(Admin admin){
        adminMapper.insertSelective(admin);
    }

    //根据adminId删除中间表内容
    public void deleteAdminRole(Integer adminId) {
        adminMapper.deleteAdminRole(adminId);
    }

    //给中间表插入数据
    public void insertAdminRole(Integer adminId, Integer roleId) {
        adminMapper.insertAdminRole(adminId,roleId);
    }

    //修改管理员信息
    public void modiAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    //模糊查询
    public PageInfo<Admin> queryAdminByCondition(Integer pageNo,Integer pageSize,Integer moduleId, String roleName) {
        //判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //查询所有管理员
        List<Admin> admins = adminMapper.findAdminByCodition(moduleId, roleName);

        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);

        return pageInfo;
    }

    //根据adminCode查找admin
    public boolean findAdminByAdminCode(String adminCode) {
        Admin admin = adminMapper.findAdminByAdminCode(adminCode);
        if (admin == null){
            return true;
        }
        return false;
    }

}
