package com.heina.customer.controller;

import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.dao.SecretDao;
import com.heina.customer.pojo.query.UserQuery;
import com.heina.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;


    @GetMapping("/listUsers")
    public JSONObject getUserList(@RequestParam(name = "userName", required = false) String userName,
                                  @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
                                  @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {
        UserQuery userQuery = new UserQuery(userName, limit, offset);
        JSONObject result = userService.listUserByName(userQuery);
        result.put("msg", "查询成功");
        result.put("status", 0);
        return result;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
