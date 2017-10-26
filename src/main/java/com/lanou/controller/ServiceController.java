package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    //进入业务账号列表页面
    @RequestMapping(value = "/service")
    public String intoService(){
        return "service/service_list";
    }

    //进入添加页面
    @RequestMapping(value ="/addService")
    public String addService(){
        return "service/service_add";
    }

    //进入详情页面
    @RequestMapping(value ="/detailService")
    public String detailService(){
        return "service/service_detail";
    }

    //进入修改页面
    @RequestMapping(value ="/updateService")
    public String updateService(){
        return "service/service_modi";
    }

    //显示全部
    @ResponseBody
    @RequestMapping(value ="/getallServices")
    public PageInfo<Service> findAllService(@RequestParam("no") Integer pageNo,
                                            @RequestParam("size") Integer pageSize){

        System.out.println(pageNo);
        System.out.println(pageSize);
        PageInfo<Service> pageInfo = serviceService.queryServiceByPage(pageNo,pageSize);

        System.out.println(pageInfo);
        return pageInfo;
    }

    //将所要修改的业务账号信息存进session
    @ResponseBody
    @RequestMapping(value = "/saveService")
    public AjaxResult intoUpdateService(@RequestParam("serviceId") Integer sid, HttpServletRequest request){

        HttpSession session = request.getSession();

        Service service = serviceService.findServiceById(sid);

        session.setAttribute("service",service);

        return new AjaxResult(service);
    }

    //在修改页面显示所要修改的业务账号信息
    @ResponseBody
    @RequestMapping(value = "/getServiceDetail")
    public AjaxResult getServiceDetail(HttpServletRequest request){
        HttpSession session = request.getSession();

        Service service = (Service) session.getAttribute("service");
        System.out.println(service);
        return new AjaxResult(service);
    }
}
