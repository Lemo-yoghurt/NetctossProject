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

    void insertIntoRoleModule(Integer roleId,Integer moduleId);

    //通过id查找角色
    Role findRoleByRoleId(Integer roleId);
}
