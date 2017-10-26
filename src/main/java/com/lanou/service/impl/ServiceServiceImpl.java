package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;


    //条件查询
    public PageInfo<Service> queryServiceByCondition(Integer pageNo, Integer pageSize, Service service) {

        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Service> serviceList = serviceMapper.findServiceByCondition(service);

        PageInfo<Service> pageInfo = new PageInfo<Service>(serviceList);

        return pageInfo;
    }

    //通过id查找service
    public Service findServiceById(Integer sid) {
        Service service = serviceMapper.selectByPrimaryKey(sid);
        return service;
    }

    //修改业务账户信息
    public void updateService(Service service) {
        serviceMapper.updateByPrimaryKeySelective(service);
    }

    //查询全部
    public  PageInfo<Service> queryServiceByPage(Integer pageNo,Integer pageSize){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Service> serviceList = serviceMapper.findAllServices();

        PageInfo<Service> pageInfo = new PageInfo<Service>(serviceList);

        return pageInfo;
    }
}
