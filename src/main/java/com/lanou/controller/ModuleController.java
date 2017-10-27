package com.lanou.controller;

import com.lanou.bean.Module;
import com.lanou.service.ModuleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping(value = "/getAllCheckBox")
    public AjaxResult getAllCheckBox(){

        List<Module> modules = moduleService.findAllModules();
        return new AjaxResult(modules);
    }

}
