package com.heina.customer.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;


    @GetMapping("/listUsers")
    public JSONObject getUserList() {
        JSONArray users = userService.listUser();
        JSONObject result = new JSONObject();
        result.put("msg", "查询成功");
        result.put("status", 0);
        result.put("users", users);
        return result;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
