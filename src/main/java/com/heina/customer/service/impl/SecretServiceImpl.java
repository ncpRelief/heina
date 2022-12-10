package com.heina.customer.service.impl;

import com.heina.customer.dao.SecretDao;
import com.heina.customer.pojo.Secret;
import com.heina.customer.pojo.query.SecretQuery;
import com.heina.customer.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretDao secretDao;


    public String getSecret(String key) {
        SecretQuery secretQuery = new SecretQuery(key, 1, 0);
        List<Secret> secrets = secretDao.listSecretByKey(secretQuery);
        if (null == secrets || secrets.size() == 0) {
            throw new NullPointerException( key + " -secret 不能为null。");
        }
        return secrets.get(0).getValue();
    }

}
