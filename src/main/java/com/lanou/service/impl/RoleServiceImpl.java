package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Role;
import com.lanou.mapper.RoleMapper;
import com.lanou.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleServiceImpl implements RoleService {
   @Resource
   private RoleMapper roleMapper;


    public PageInfo<Role> queryRolesByPgae(Integer pageNo, Integer pageSize) {

        //判断参数的合法性
        pageNo = pageNo == null ? 1 :pageNo;
        pageSize = pageSize == null?5:pageSize;

        PageHelper.startPage(pageNo,pageSize);

        //查找所有角色
        List<Role> roles = roleMapper.findAllRoles();

        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);

        return pageInfo;
    }

    //添加一个角色
    public void insertRole(Role role) {
        roleMapper.insertSelective(role);
    }

    public void insertIntoRoleModule(Integer roleId, Integer moduleId) {
        roleMapper.insertIntoRoleModule(roleId,moduleId);
    }

    //根据id查找角色
    public Role findRoleByRoleId(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
