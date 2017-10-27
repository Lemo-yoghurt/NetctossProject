package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Role;

/**
 * Created by dllo on 17/10/27.
 */
public interface RoleService {


    //查找全部并分页
    PageInfo<Role>  queryRolesByPgae(Integer pageNo,Integer pageSize);

    //添加一个角色
    void insertRole(Role role);

    //添加信息到中间表
    void insertIntoRoleModule(Integer roleId,Integer moduleId);

    //通过id查找角色
    Role findRoleByRoleId(Integer roleId);

    //通过条件查找角色
    boolean findByName(String name);

    //删除角色
    void delRole(Integer roleId);

    //根据id删除中间表的内容
    void deleteRoleModule(Integer roleId);

    //修改角色信息
    void updateRole(Role role);
}
