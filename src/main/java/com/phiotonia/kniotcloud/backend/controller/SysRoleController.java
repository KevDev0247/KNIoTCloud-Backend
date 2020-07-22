package com.phiotonia.kniotcloud.backend.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.phiotonia.kniotcloud.backend.model.SysMenu;
import com.phiotonia.kniotcloud.backend.model.SysRole;
import com.phiotonia.kniotcloud.backend.model.SysRoleMenu;
import com.phiotonia.kniotcloud.backend.service.SysMenuService;
import com.phiotonia.kniotcloud.backend.service.SysRoleMenuService;
import com.phiotonia.kniotcloud.backend.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"role"})
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @ApiOperation("create new role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysRole", value = "SysRole entity", required = true, dataType = "SysRole")
    })
    @RequestMapping("/create")
    public ResponseEntity<Map<String, Object>> createRole(@RequestBody SysRole sysRole) {
        Map<String, Object> map = new HashMap<>();
        boolean response = sysRoleService.insert(sysRole);
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("delete a role")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "roleId", value = "role id")
    })
    @RequestMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteRole(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysRole sysRoleRetrieved = sysRoleService.selectById(roleId);
        if (sysRoleRetrieved == null) {
            response = false;
        } else {
            response = sysRoleService.deleteById(roleId);
        }

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "get roles list")
    @RequestMapping("/getRolesList")
    public ResponseEntity<Map<String, Object>> getAllRoles() {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id", false);
        map.put("list", sysRoleService.selectList(wrapper));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "bind menu to roles")
    @RequestMapping(value = "/bindMenu", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> bindMenu(Integer roleId, Integer menuId) {
        Map<String, Object> map = new HashMap<>();
        String message;
        boolean response;

        EntityWrapper<SysRoleMenu> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("menu_id", menuId);
        wrapper1.eq("role_id", roleId);
        SysRoleMenu roleMenuRetrieved = sysRoleMenuService.selectOne(wrapper1);

        EntityWrapper<SysRoleMenu> wrapper2 = new EntityWrapper<>();
        SysMenu menu = sysMenuService.selectById(menuId);
        wrapper2.eq("menu_id", menu.getParentId());
        wrapper2.eq("role_id", roleId);
        SysRoleMenu roleParentMenuRetrieved = sysRoleMenuService.selectOne(wrapper2);

        if (roleMenuRetrieved != null) {
            response = false;
            message = "Menu already bind to a role";
        } else if (menu.getParentId() == 0) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            response = sysRoleMenuService.insertOrUpdate(sysRoleMenu);
            if (response) {
                message = "binding successful";
            } else {
                message = "binding failed";
            }
        } else {
            if (roleParentMenuRetrieved == null) {
                response = false;
                message = "please bind the parent menu first";
            } else {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(roleId);
                response = sysRoleMenuService.insertOrUpdate(sysRoleMenu);
                if (response) {
                    message = "binding successful";
                } else {
                    message = "binding failed";
                }
            }
        }

        map.put("data", response);
        map.put("message", message);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "unbind menu to roles")
    @RequestMapping(value = "/unbindMenu", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> unbindMenu(Integer roleId, Integer menuId) {
        Map<String, Object> map = new HashMap<>();
        String message;
        boolean response;

        EntityWrapper<SysMenu> childMenuWrapper = new EntityWrapper<>();
        childMenuWrapper.eq("parent_id", menuId);
        List<SysMenu> childMenuList = sysMenuService.selectList(childMenuWrapper);
        if (childMenuList.size() > 0) {
            for (SysMenu sysMenu : childMenuList) {
                EntityWrapper<SysRoleMenu> menuItemWrapper = new EntityWrapper<>();
                menuItemWrapper.eq("menu_id", menuId);
                menuItemWrapper.eq("role_id", roleId);
                sysRoleMenuService.delete(menuItemWrapper);
            }
        }

        EntityWrapper<SysRoleMenu> parentMenuWrapper = new EntityWrapper<>();
        parentMenuWrapper.eq("role_id", roleId);
        parentMenuWrapper.eq("menu_id", menuId);
        response = sysRoleMenuService.delete(parentMenuWrapper);

        if (response) {
            message = "unbind menu successful";
        } else {
            message = "unbind menu failed ";
        }
        map.put("data", response);
        map.put("message", message);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
