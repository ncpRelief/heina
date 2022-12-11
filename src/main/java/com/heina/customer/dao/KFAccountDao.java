package com.heina.customer.dao;

import com.heina.customer.pojo.KFAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KFAccountDao {

    List<KFAccount> listKFAccount();

    int delAccount();

    int insertKFAccount(List<KFAccount> kfAccounts);
}
