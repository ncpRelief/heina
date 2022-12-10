package com.heina.customer.dao;


import com.heina.customer.pojo.Secret;
import com.heina.customer.pojo.query.SecretQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SecretDao {

    List<Secret> listSecretByKey(SecretQuery secretQuery);

}
