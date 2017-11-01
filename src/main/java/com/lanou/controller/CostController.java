package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

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
    @ResponseBody
    @RequestMapping(value = "/updateCost", method = RequestMethod.POST)
    public AjaxResult saveCost(Cost cost) {

        //资费名称验证
        if (cost.getName().length() == 0 || cost.getName() == null) {
            return new AjaxResult(1);
        } else if (!cost.getName().matches("^[a-zA-Z\\d\\_\\u2E80-\\u9FFF]{0,50}$")) {
            return new AjaxResult(2);
        }
        costService.updateCost(cost);
        return new AjaxResult(cost);
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

        PageInfo<Cost> pageInfo = costService.pageinfo(pageNo, pageSize);
        System.out.println(pageInfo);
        return pageInfo;
    }


    //添加一条资费信息
    @ResponseBody
    @RequestMapping(value = "/addCost", method = RequestMethod.POST)
    public AjaxResult addCost(Cost cost) {
        System.out.println(cost);
        //资费名称验证
        if (cost.getName().length() == 0 || cost.getName() == null) {
            return new AjaxResult(1);
        } else if (!cost.getName().matches("/^[a-zA-Z\\d\\_\\u2E80-\\u9FFF]{0,50}$/")) {
            return new AjaxResult(2);
        }
//
//        //资费类型为套餐
//        if (cost.getCostType().equals("套餐")) {
//            //基本时长验证
//            if (cost.getBaseDuration() == 0 || cost.getBaseDuration() == null) {
//                return new AjaxResult(3);
//            } else if (cost.getBaseDuration().toString().matches("^[1-5]\\d{2}|^[1-9]\\d{0,1}$")) {
//                return new AjaxResult(4);
//            }
//
//            //基本费用
//            if (cost.getBaseCost() == 0 || cost.getBaseCost() == null) {
//                return new AjaxResult(5);
//            } else if (!cost.getBaseCost().toString().matches("^[1-9]\\d{0,4}$")) {
//                return new AjaxResult(6);
//            }
//
//            //单位费用
//            if (cost.getUnitCost() == 0 || cost.getUnitCost() == null) {
//                return new AjaxResult(7);
//            } else if (!cost.getUnitCost().toString().matches("^[1-9]\\d{0,4}$")) {
//                return new AjaxResult(8);
//            }
//            //资费类型为包月
//        }else if (cost.getCostType().equals("包月")){
//            //基本费用
//            if (cost.getBaseCost() == 0 || cost.getBaseCost() == null) {
//                return new AjaxResult(5);
//            } else if (!cost.getBaseCost().toString().matches("^[1-9]\\d{0,4}$")) {
//                return new AjaxResult(6);
//            }
//            //资费类型为计时
//        }else if (cost.getCostType().equals("计时")){
//            //单位费用
//            if (cost.getUnitCost() == 0 || cost.getUnitCost() == null) {
//                return new AjaxResult(7);
//            } else if (!cost.getUnitCost().toString().matches("^[1-9]\\d{0,4}$")) {
//                return new AjaxResult(8);
//            }
//        }
//        //描述验证
//        if (cost.getDescr().length() == 0 || cost.getDescr() == null) {
//            return new AjaxResult(9);
//        } else if (!cost.getDescr().matches("/^[a-zA-Z\\d\\_\\u2E80-\\u9FFF]{0,100}$/")) {
//            return new AjaxResult(10);
//        }
        cost.setStatus("0");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        costService.addCost(cost);
        return new AjaxResult(cost);
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

        PageInfo<Cost> pageInfo = costService.pageInfoSort(pageNo, pageSize, flag);
        return pageInfo;
    }

    //查找所有资费类型
    @ResponseBody
    @RequestMapping(value = "/getAllCostType")
    public AjaxResult getAllCostType() {

        List<Cost> allCost = costService.findAll();

        return new AjaxResult(allCost);
    }

    //验证套餐名称
    @ResponseBody
    @RequestMapping(value = "/judgeName", method = RequestMethod.POST)
    public AjaxResult judgeName(@RequestParam("name") String name) {

        if (name.length() == 0 || name == null) {
            return new AjaxResult(0);
        } else if (!name.matches("^[a-zA-Z\\d\\_\\u2E80-\\u9FFF]{0,50}$")) {
            return new AjaxResult(1);
        }
        return new AjaxResult(2);
    }

    //验证基本时长
    @ResponseBody
    @RequestMapping(value = "/judgeBaseDuration", method = RequestMethod.POST)
    public AjaxResult judgeBaseDuration(@RequestParam("baseDuration") Integer baseDuration) {
        System.out.println(baseDuration);
        if (baseDuration == null || baseDuration == 0) {
            return new AjaxResult(0);
        } else if (!baseDuration.toString().matches("^[1-5]\\d{2}|^[1-9]\\d{0,1}$")) {
            return new AjaxResult(1);
        }
        return new AjaxResult(2);
    }

    //验证基本费用
    @ResponseBody
    @RequestMapping(value = "/judgeBaseCost", method = RequestMethod.POST)
    public AjaxResult judgeBaseCost(@RequestParam("baseCost") Integer baseCost) {
        System.out.println(baseCost);
        if (baseCost == null || baseCost == 0) {
            return new AjaxResult(0);
        } else if (!baseCost.toString().matches("^[1-9]\\d{0,4}$")) {
            return new AjaxResult(1);
        }
        return new AjaxResult(2);

    }

    //验证单位费用
    @ResponseBody
    @RequestMapping(value = "/judgeUnitCost", method = RequestMethod.POST)
    public AjaxResult judgeUnitCost(@RequestParam("unitCost") Integer unitCost) {
        System.out.println(unitCost);
        if (unitCost == null || unitCost == 0) {
            return new AjaxResult(0);
        } else if (!unitCost.toString().matches("^[1-9]\\d{0,4}$")) {
            return new AjaxResult(1);
        }
        return new AjaxResult(2);

    }

    //验证描述
    @ResponseBody
    @RequestMapping(value = "/judgeDescr", method = RequestMethod.POST)
    public AjaxResult judgeDescr(@RequestParam("descr") String descr) {
        System.out.println(descr);
        if (descr.equals("") || descr == null) {
            return new AjaxResult(0);
        } else if (!descr.matches("^[a-zA-Z\\d\\_\\u2E80-\\u9FFF]{0,100}$")) {
            return new AjaxResult(1);
        }
        return new AjaxResult(2);
    }
}
