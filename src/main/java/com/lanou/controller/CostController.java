package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {
    @Resource
    private CostService costService;

    //首页
    @RequestMapping(value = "/home")
    public String frontPage() {
        return "index";
    }


    //资费列表页面
    @RequestMapping(value = "/cost")
    public String costPage() {
        return "fee/fee_list";
    }

    //添加资费页面
    @RequestMapping(value = "/add")
    public String addCost() {
        return "fee/fee_add";
    }

    //修改资费页面
    @RequestMapping(value = "/modify")
    public String modify() {
        return "fee/fee_modi";
    }

    //在修改资费页面显示信息
    @ResponseBody
    @RequestMapping(value = "/modifyInfo")
    public AjaxResult modifyInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cost cost = (Cost) session.getAttribute("cost");
        return new AjaxResult(cost);
    }


    //根据id查找资费信息
    @ResponseBody
    @RequestMapping(value = "/modify1")
    public AjaxResult modifyCost(@RequestParam("costId") Integer costId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cost cost = costService.findCostById(costId);
        session.setAttribute("cost", cost);
        return new AjaxResult(cost);
    }

    //保存修改信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveCost(Cost cost) {
        costService.updateCost(cost);
        return "redirect:cost";
    }


    //显示详情
    @RequestMapping(value = "/detail")
    public String detail() {
        return "fee/fee_detail";
    }

    //显示点击的资费信息
    @ResponseBody
    @RequestMapping(value = "/detailInfo")
    public AjaxResult detailInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cost cost = (Cost) session.getAttribute("cost");
        return new AjaxResult(cost);
    }

    //显示资费信息
    @ResponseBody
    @RequestMapping(value = "/getByPage")
    public PageInfo<Cost> getFeeByPage(@RequestParam("no") Integer pageNo,
                                       @RequestParam("size") Integer pageSize) {

        System.out.println(pageNo);
        System.out.println(pageSize);
        PageInfo<Cost> pageInfo = costService.pageinfo(pageNo, pageSize);
        System.out.println(pageInfo);
        return pageInfo;
    }

    //添加一条资费信息
    @RequestMapping(value = "/addCost", method = RequestMethod.POST)
    public String addCost(Cost cost) {

        cost.setStatus("0");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        costService.addCost(cost);
        return "redirect:cost";
    }

    //删除一条资费信息
    @ResponseBody
    @RequestMapping(value = "/delCost")
    public AjaxResult deleteCost(@RequestParam("costId") Integer costId) {
        costService.delCost(costId);
        return new AjaxResult(costId);

    }

    //启用
    @ResponseBody
    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public AjaxResult openCost(@RequestParam("costId") Integer costId) {
        Cost cost = costService.findCostById(costId);
        cost.setStatus("1");
        cost.setStartime(new Timestamp(System.currentTimeMillis()));
        costService.updateCost(cost);
        return new AjaxResult(cost);
    }

    //排序
    @ResponseBody
    @RequestMapping(value = "/sort")
    public PageInfo<Cost> sortByBaseCostAsc(@RequestParam("no") Integer pageNo,
                                            @RequestParam("size") Integer pageSize,
                                            @RequestParam("flag") Integer flag) {

        PageInfo<Cost> pageInfo = costService.pageInfoSort(pageNo,pageSize,flag);
        return pageInfo;
    }

    //查找所有资费类型
    @ResponseBody
    @RequestMapping(value = "/getAllCostType")
    public AjaxResult getAllCostType(){
        List<Cost> allCostTypes = costService.findAllCostTypes();

        return new AjaxResult(allCostTypes);
    }


}
