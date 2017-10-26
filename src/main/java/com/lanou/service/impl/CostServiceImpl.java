package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    //查找全部资费
    public List<Cost> findAll() {
        return costMapper.findAll();
    }

    //添加资费
    public void addCost(Cost cost) {
        costMapper.insertSelective(cost);
    }

    //删除资费
    public void delCost(Integer costId) {
        costMapper.deleteByPrimaryKey(costId);
    }

    //根据id查找资费
    public Cost findCostById(Integer costId) {
        return costMapper.selectByPrimaryKey(costId);
    }

    //修改资费信息
    public void updateCost(Cost cost) {
        costMapper.updateByPrimaryKeySelective(cost);
    }


    public PageInfo<Cost> pageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
    }


    //分页显示资费信息
    public PageInfo<Cost> queryCostByPage(Integer pageNo, Integer pageSize) {


        //判断参数合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部学员
        List<Cost> costs = costMapper.findAll();

        //使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);

        return pageInfo;
    }

    //通过升降序查询
    public PageInfo<Cost> pageInfoSort(Integer pageNo, Integer pageSize,Integer flag) {
        List<Cost> costs = null;
        //判断参数合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        if (flag == 1){
            costs = costMapper.findByBaseCostAsc();
        } else if (flag == 2){
            costs = costMapper.findByBaseCostDesc();
        }else if (flag == 3){
            costs = costMapper.findByByBaseDurationAsc();
        }else {
            costs = costMapper.findByByBaseDurationDesc();
        }

        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);

        return pageInfo;
    }

    public List<Cost> findAllCostTypes() {

        List<Cost> costTypes = costMapper.findAllCostTypes();

        return costTypes;
    }


}
