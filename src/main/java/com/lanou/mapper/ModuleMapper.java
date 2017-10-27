package com.lanou.mapper;

import com.lanou.bean.Module;
import com.lanou.bean.Role;

import java.util.List;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer moduleId);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module> findModulesByRoleId(Integer roleId);

    //查找全部
    List<Module> findAllModules();
}