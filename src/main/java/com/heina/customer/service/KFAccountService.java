package com.heina.customer.service;

import com.alibaba.fastjson2.JSONObject;

public interface KFAccountService {

    JSONObject ListKFAccount();

    JSONObject refreshAccount();
}
