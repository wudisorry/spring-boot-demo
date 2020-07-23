package com.arh.springbootdemo.web.controller;

import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.entity.vo.UserVO;
import com.arh.springbootdemo.service.IUserService;
import com.arh.springbootdemo.util.ServerConfigInfo;
import com.arh.springbootdemo.web.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/23
 **/
@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Resource(name = "userServiceImpl")
    private IUserService userService;

    @Autowired
    private ServerConfigInfo serverConfigInfo;

    @Autowired
    private WebSocketServer webSocketServer;


    @RequestMapping("/show")
    public String showHtml(Model model, @RequestParam(name = "userId") Integer userId) {
        User user = userService.queryUserById(userId);
        UserVO userVO = new UserVO(user);
        model.addAttribute("uuid", UUID.randomUUID());
        model.addAttribute("user", userVO);
        model.addAttribute("serverConfigInfo", serverConfigInfo);
        return "webSocket";
    }

    @RequestMapping("/sendMsg/{userId}/{msg}")
    @ResponseBody
    public void sendMsg(@PathVariable(name = "userId") Integer userId, @PathVariable(name = "msg") String msg) {
        logger.debug("userId:" + userId + ",msg:" + msg);
        try {
            webSocketServer.sendMsg(userId, msg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
