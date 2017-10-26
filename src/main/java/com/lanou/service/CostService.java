package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService {

    //查找全部的资费
    List<Cost> findAll();

    //添加一条新的资费
    void addCost(Cost cost);

    //删除一条资费信息
    void delCost(Integer costId);

    //根据id查找资费信息
    Cost findCostById(Integer costId);

    //修改资费信息
    void updateCost(Cost cost);

    //分页显示资费信息
    PageInfo<Cost> pageinfo(Integer pageNo, Integer pageSize);

    //升序查询
    PageInfo<Cost> pageInfoSort(Integer pageNo,Integer pageSize,Integer flag);

    //查找所有资费类型
    List<Cost> findAllCostTypes();




}
