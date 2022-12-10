package com.heina.customer.dao;

import com.heina.customer.pojo.User;
import com.heina.customer.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    int countUser(UserQuery userQuery);

    List<User> listUser(UserQuery userQuery);

    List<User> listByUserByName(UserQuery userQuery);

    int countByUserName(UserQuery userQuery);


    void insertUsers(List<User> users);
}
