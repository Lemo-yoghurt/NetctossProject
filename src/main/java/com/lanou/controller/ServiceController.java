package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.activemq.consumer.ConsumerService;
import com.lanou.activemq.producer.ProducerService;
import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @Resource
    private ProducerService producerService;

    @Resource
    private ConsumerService consumerService;

    //进入业务账号列表页面
    @RequestMapping(value = "/service")
    public String intoService() {
        return "service/service_list";
    }

    //进入添加页面
    @RequestMapping(value = "/addService")
    public String addService() {
        return "service/service_add";
    }

    //进入详情页面
    @RequestMapping(value = "/detailService")
    public String detailService() {
        return "service/service_detail";
    }

    //进入修改页面
    @RequestMapping(value = "/updateService")
    public String updateService() {
        return "service/service_modi";
    }

    //显示全部
    @ResponseBody
    @RequestMapping(value = "/getallServices")
    public PageInfo<Service> findAllService(@RequestParam("no") Integer pageNo,
                                            @RequestParam("size") Integer pageSize) {

        PageInfo<Service> pageInfo = serviceService.queryServiceByPage(pageNo, pageSize);

        return pageInfo;
    }

    //将所要修改的业务账号信息存进session
    @ResponseBody
    @RequestMapping(value = "/saveService")
    public AjaxResult intoUpdateService(@RequestParam("serviceId") Integer sid, HttpServletRequest request) {

        HttpSession session = request.getSession();

        Service service = serviceService.findServiceById(sid);

        session.setAttribute("service", service);

        return new AjaxResult(service);
    }

    //在修改页面显示所要修改的业务账号信息
    @ResponseBody
    @RequestMapping(value = "/getServiceDetail")
    public AjaxResult getServiceDetail(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Service service = (Service) session.getAttribute("service");
        System.out.println(service);
        return new AjaxResult(service);
    }

    //删除业务账号信息
    @ResponseBody
    @RequestMapping(value = "/delService")
    public AjaxResult delService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.findServiceById(sid);
        service.setCloseDate(new Date());
        service.setStatus("2");
        serviceService.updateService(service);

        return new AjaxResult(service);
    }

    //保存修改后的信息
    @ResponseBody
    @RequestMapping(value = "/modiService")
    public AjaxResult modiService(Service service){

        serviceService.updateService(service);
        return new AjaxResult(service);

    }

    //开启业务账户
    @ResponseBody
    @RequestMapping(value = "/openService")
    public AjaxResult openService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.findServiceById(sid);

        service.setPauseDate(null);
        service.setStatus("1");

        serviceService.modiService(service);

        return new AjaxResult(service);

    }

    //暂停业务账户
    @ResponseBody
    @RequestMapping(value = "/pauseService")
    public AjaxResult pauseService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.findServiceById(sid);
        service.setPauseDate(new Date());
        service.setStatus("0");

        serviceService.updateService(service);

        return new AjaxResult(service);
    }

    //添加一条业务账户信息
    @ResponseBody
    @RequestMapping(value = "/addservice")
    public AjaxResult addservice(Service service) {
        boolean result = serviceService.findServiceByOsUsername(service.getOsUsername());
        System.out.println(result);
        if (result) {
            service.setCreateDate(new Date());
            service.setStatus("1");
            serviceService.insertService(service);
            return new AjaxResult(1);
        }
        return new AjaxResult(0);
    }

    //模糊查询
    @ResponseBody
    @RequestMapping(value = "/queryService", method = RequestMethod.POST)
    public PageInfo<Service> queryServiceByCondition(@RequestParam("no") Integer pageNo,
                                                     @RequestParam("size") Integer pageSize,
                                                     @RequestParam(value = "idcardNo",required = false) String idcardNo,
                                                     @RequestParam(value = "osUsername",required = false) String osUsername,
                                                     @RequestParam(value = "unixHost",required = false) String unixHost,
                                                     @RequestParam(value = "status",required = false) String status) {

        PageInfo<Service> pageInfo = serviceService.queryServiceByCondition(pageNo, pageSize,idcardNo,osUsername,unixHost,status);

        System.out.println(pageInfo);
        return pageInfo;

    }
}
