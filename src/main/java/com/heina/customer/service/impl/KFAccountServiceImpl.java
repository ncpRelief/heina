package com.heina.customer.service.impl;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.dao.KFAccountDao;
import com.heina.customer.pojo.KFAccount;
import com.heina.customer.service.KFAccountService;
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
public class KFAccountServiceImpl implements KFAccountService {

    @Autowired
    private KFAccountDao kfAccountDao;


    @Override
    public JSONObject ListKFAccount() {
        JSONArray jsonArray = new JSONArray(kfAccountDao.listKFAccount());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        jsonObject.put("msg", "查询客服账号成功");
        jsonObject.put("kfAccount", jsonArray);
        return jsonObject;
    }

    @Override
    public JSONObject refreshAccount() {
        String token = AccessTokenUtils.getAccessToken("customerServiceSecret");
        Map<String, String> param = new HashMap<>();
        param.put("access_token", token);
        JSONObject body = new JSONObject();
        body.put("offset", 0);
        body.put("limit", 1000);
        JSONObject jsonObject = new HttpsClient().post(Constants.LIST_KF, param, body);
        JSONArray jsonArray = jsonObject.getJSONArray("account_list");
        List<KFAccount> accountList = new ArrayList<>();
        if (!jsonArray.isEmpty()) {
            kfAccountDao.delAccount();
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                KFAccount kfAccount = new KFAccount(object.getString("open_kfid"),
                        object.getString("name"));
                accountList.add(kfAccount);
            }
        }
        int size = kfAccountDao.insertKFAccount(accountList);
        JSONObject result = new JSONObject();
        result.put("status", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("刷新成功,目前共有微信客服").append(size).append("条");
        result.put("msg", sb.toString());
        return result;
    }
}
