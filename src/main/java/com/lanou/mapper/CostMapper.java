package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    List<Cost> findAll();

    //升序查询
    List<Cost> findByBaseCostAsc();
    List<Cost> findByByBaseDurationAsc();

    //降序查询
    List<Cost> findByBaseCostDesc();
    List<Cost> findByByBaseDurationDesc();
}