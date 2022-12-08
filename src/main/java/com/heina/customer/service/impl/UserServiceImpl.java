package com.heina.customer.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public JSONArray listUser() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid", "zhangsan");
        jsonObject.put("name", "张三");
        for (int i = 0; i < 10; i++) {
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
