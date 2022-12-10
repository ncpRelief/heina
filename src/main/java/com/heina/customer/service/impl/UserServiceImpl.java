package com.heina.customer.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.dao.UserDao;
import com.heina.customer.pojo.User;
import com.heina.customer.pojo.query.UserQuery;
import com.heina.customer.service.UserService;
import com.heina.customer.utils.AccessTokenUtils;
import com.heina.customer.utils.Constants;
import com.heina.customer.utils.HttpsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        // holy shit 企业微信不给查询企业微信昵称了
        return null;
    }


}
