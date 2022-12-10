package com.heina.customer.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.dao.UserDao;
import com.heina.customer.pojo.User;
import com.heina.customer.pojo.query.UserQuery;
import com.heina.customer.service.UserService;
import com.heina.customer.utils.AccessTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject listUserByName(UserQuery userQuery) {
        JSONObject result = new JSONObject();
        JSONArray users = new JSONArray(userDao.listByUserByName(userQuery));
        int count = userDao.countByUserName(userQuery);
        result.put("users", users);
        result.put("count", count);
        result.put("offset", userQuery.getOffset());
        result.put("limit", userQuery.getLimit());
        return result;
    }

    @Override
    public JSONObject insertUsers(List<User> userList) {
        JSONObject jsonObject = new JSONObject();
        userDao.insertUsers(userList);
        jsonObject.put("row", userList.size());
        return jsonObject;
    }

    @Override
    public JSONObject synUsers() {
        JSONObject object = new JSONObject();
        Map<String,String> param = new HashMap<>();
        param.put("access_token", AccessTokenUtils.getAccessToken("usersSecret"));

        int rows = 0;
        object.put("status", 0);
        object.put("size", 0);
        object.put("msg", "同步成功");

        return object;
    }


}
