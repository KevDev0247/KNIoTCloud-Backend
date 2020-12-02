package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.dto.NotificationDto;
import com.jitlantis.backend.API.model.*;
import com.jitlantis.backend.API.service.RepairService;
import com.jitlantis.backend.API.service.SysMessageService;
import com.jitlantis.backend.API.service.SysUserService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for Main Page that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see Repair
 * @see SysUser
 * @see SysMessage
 * created on 2020/12/02
 */
@Api(tags = {"Main Page"})
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private SysMessageService messageService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private JitConverter converter;

    public ResponseEntity<Map<String, Object>> getIndexData() {
        Map<String, Object> map = new HashMap<>();

        EntityWrapper<Repair> exceptionWrapper = new EntityWrapper<>();
        exceptionWrapper.eq("status", 14);
        exceptionWrapper.eq("is_delete", DeletedEnum.N.value());
        Integer exceptionOrderCount = repairService.selectCount(exceptionWrapper);

        EntityWrapper<Repair> todoWrapper = new EntityWrapper<>();
        todoWrapper.eq("status", 1);
        todoWrapper.eq("is_delete", DeletedEnum.N.value());
        Integer todoOrderCount = repairService.selectCount(todoWrapper);

        EntityWrapper<SysMessage> messageWrapper = new EntityWrapper<>();
        messageWrapper.eq("type", 3);
        messageWrapper.eq("is_delete", DeletedEnum.N.value());
        messageWrapper.orderBy("id");
        messageWrapper.last("desc limit 6");
        List<SysMessage> messageList = messageService.selectList(messageWrapper);

        Map<Integer, SysUser> userMap = new HashMap<>();
        if (messageList != null && messageList.size() > 0) {
            List<Long> userIdList = converter.getLongListFromEntityList(messageList, "create_user_id");
            List<SysUser> userList = userService.findUsersByIds(userIdList);

            for (SysUser user: userList) {
                userMap.put(user.getId(), user);
            }
        }

        List<NotificationDto> notificationDtoList = converter.mergeListByAny(NotificationDto.class, messageList, null, null);
        for (NotificationDto notificationDto: notificationDtoList) {
            if (notificationDto.getCreateUserId() != null && userMap.get(notificationDto.getCreateUserId()) != null) {
                SysUser user = userMap.get(notificationDto.getCreateUserId());
                notificationDto.setCompany(user.getCompany());
                notificationDto.setName(user.getName());
            }
        }

        map.put("notifications", notificationDtoList);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}