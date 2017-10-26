package com.lanou.mapper;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    //查询全部业务账号
    List<Service> findAllServices();


    //条件查询业务账号
    List<Service> findServiceByCondition(Service service);
}