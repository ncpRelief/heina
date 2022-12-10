package com.heina.customer.service;

import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.pojo.User;
import com.heina.customer.pojo.query.UserQuery;

import java.util.List;

public interface UserService {


    JSONObject listUserByName(UserQuery userQuery);


    JSONObject insertUsers(List<User> users);

    JSONObject synUsers();

}
