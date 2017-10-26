package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServiceService {

    //分页显示全部
    PageInfo<Service> queryServiceByPage(Integer pageNo,Integer pageSize);

    PageInfo<Service> queryServiceByCondition(Integer pageNo,Integer pageSize,Service service);

    //通过id查找service
    Service findServiceById(Integer sid);

    //修改业务账号信息
    void updateService(Service service);

    //删除暂停时间
    void modiService(Service service);
}
