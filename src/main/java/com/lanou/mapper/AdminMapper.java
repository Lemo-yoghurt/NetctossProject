package com.lanou.mapper;

import com.lanou.bean.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> findAllAdmins();


    Admin findAdminByAdminCode(String adminCode);

    //根据adminId删除中间表的内容
    int deleteAdminRole(Integer adminId);

    //给中间表插入数据
    int insertAdminRole(@Param("adminId") Integer adminId,@Param("roleId") Integer roleId);

    //高级查询
    List<Admin> findAdminByCodition(@Param("moduleId") Integer moduleId,@Param("roleName") String roleName);

}