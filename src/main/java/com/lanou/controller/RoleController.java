package com.lanou.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Module;
import com.lanou.bean.Role;
import com.lanou.service.RoleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */

@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

    //进入角色页面
    @RequestMapping(value = "/role")
    public String intoRole() {
        return "role/role_list";
    }

    //添加角色页面
    @RequestMapping(value = "/addrole")
    public String addRole() {
        return "role/role_add";
    }

    //修改角色信息页面
    @RequestMapping(value = "/updaterole")
    public String updateRole() {
        return "role/role_modi";
    }

    //显示全部角色
    @ResponseBody
    @RequestMapping(value = "/getAllRoles")
    public PageInfo<Role> getAllRoles(@RequestParam("no") Integer pageNo,
                                      @RequestParam("size") Integer pageSize) {

        PageInfo<Role> pageInfo = roleService.queryRolesByPgae(pageNo, pageSize);

        return pageInfo;
    }

    //得到全部角色
    @ResponseBody
    @RequestMapping(value = "/getallRoles")
    public AjaxResult getallRoles(){
        List<Role> roles = roleService.findAllRoles();

        return new AjaxResult(roles);
    }

    //添加角色
    @ResponseBody
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public AjaxResult insertRole(@RequestParam("name") String name,
                                 @RequestParam("modules") String mle) {


        if (mle.equals("") || mle == null) {
            return new AjaxResult(0);
        }
        if(name.equals("") || name == null){
            return new AjaxResult(3);
        }
        if (!roleService.findByName(name)) {
            return new AjaxResult(1);
        }
        String[] str = mle.split(",");
        Role role = new Role();
        role.setName(name);

        roleService.insertRole(role);

        for (String s : str) {
            roleService.insertIntoRoleModule(role.getRoleId(), Integer.parseInt(s));
        }

        return new AjaxResult(2);
    }

    //进入修改角色页面
    @ResponseBody
    @RequestMapping(value = "/modirole")
    public AjaxResult modiRole(@RequestParam("roleId") Integer roleId,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();

        Role role = roleService.findRoleByRoleId(roleId);
        System.out.println(role);
        session.setAttribute("role", role);
        return new AjaxResult(role);
    }

    //在修改页面角色信息
    @ResponseBody
    @RequestMapping(value = "/getRoleDetail")
    public AjaxResult getRoleDetail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("role");
        return new AjaxResult(role);
    }

    //保存修改后的角色信息
    @ResponseBody
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public AjaxResult updateRole(@RequestParam("roleId") Integer roleId,
                                 @RequestParam("name") String name,
                                 @RequestParam("modules") String mle) {

        if (mle.equals("") || mle == null) {
            return new AjaxResult(0);
        }
//        if (!roleService.findByName(name)) {
//            return new AjaxResult(1);
//        }
        String[] str = mle.split(",");
        Role role = new Role();
        role.setRoleId(roleId);
        role.setName(name);

        //修改角色表
        roleService.updateRole(role);

        //根据角色删除中间数据
        roleService.deleteRoleModule(roleId);

        //重新插入中间表
        for (String s : str) {
            roleService.insertIntoRoleModule(roleId, Integer.parseInt(s));
        }

        return new AjaxResult(2);
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/delRole")
    public AjaxResult delRole(@RequestParam("roleId") Integer roleId){
      roleService.deleteRoleModule(roleId);
      roleService.delRole(roleId);
      return new AjaxResult(roleId);
    }
}