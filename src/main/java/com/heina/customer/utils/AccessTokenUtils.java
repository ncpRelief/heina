package com.heina.customer.utils;

import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.ioc.CustomApplicationContext;
import com.heina.customer.service.SecretService;
import com.heina.customer.service.impl.SecretServiceImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenUtils {

    private static final long TOKEN_LIFE = 3600;
    private static Map<String, Object> tokens = new HashMap();

    private static final String CORP_ID_KEY = "corpid";

    private static final String CUSTOMER_SECRET_KEY = "customerServiceSecret";

    private static final String USERS_SECRET_KEY = "usersSecret";

    private static String USERS_SECRET = null;

    private static String CORP_ID = null;

    private static String CUSTOMER_SECRET = null;

    public static String getAccessToken(String type) {
        long now = new Date().getTime() / 1000;
        String token = MapUtils.getString(tokens, type);
        if (StringUtils.isNotEmpty(token) && now <= MapUtils.getLongValue(tokens, type + "expireTime")) {
            return token;
        }
        if (StringUtils.isEmpty(CORP_ID)) {
            setCorpId();

        }
        Map<String, String> param = new HashMap<>();
        param.put("corpid", CORP_ID);
        if ("customerServiceSecret".equals(type)) {
            if (StringUtils.isEmpty(CUSTOMER_SECRET)) {
                setCustomerServiceSecret();
            }
            param.put("corpsecret", CUSTOMER_SECRET);
        }
        if ("usersSecret".equals(type)) {
            if (StringUtils.isEmpty(USERS_SECRET)) {
                setUsersSecret();
            }
            param.put("corpsecret", USERS_SECRET);
        }
        HttpsClient client = new HttpsClient();
        JSONObject jsonObject = client.get(Constants.GET_TOKEN, param);
        token = jsonObject.getString("access_token");
        long expireTime = new Date().getTime() / 1000 + TOKEN_LIFE;
        tokens.put(type, token);
        tokens.put(type + "ExpireTime", expireTime);
        return token;
    }


    private static void setCorpId() {
        SecretService service = CustomApplicationContext.getBean(SecretServiceImpl.class);
        CORP_ID = service.getSecret(CORP_ID_KEY);
    }

    private static void setCustomerServiceSecret() {
        SecretService service = CustomApplicationContext.getBean(SecretServiceImpl.class);
        CUSTOMER_SECRET = service.getSecret(CUSTOMER_SECRET_KEY);
    }

    private static void setUsersSecret() {
        SecretService service = CustomApplicationContext.getBean(SecretServiceImpl.class);
        USERS_SECRET = service.getSecret(USERS_SECRET_KEY);
    }


}
