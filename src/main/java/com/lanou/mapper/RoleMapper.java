package com.lanou.mapper;

import com.lanou.bean.Module;
import com.lanou.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findRolesByModuleId(Integer moduleId);

    //查找所有角色
    List<Role> findAllRoles();

    //往中间表添加数据
    void insertIntoRoleModule(@Param("roleId") Integer roleId,
                              @Param("moduleId") Integer moduleId);
}