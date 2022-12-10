package com.heina.customer.pojo.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {


    private Integer limit = 10;

    private Integer offset = 0;

    private String userName;

    public UserQuery(String userName, int limit, int offset) {
        this.limit = limit <= 0 ? this.limit : limit;
        this.offset = limit <= 0 ? this.offset : offset;
        this.userName = userName;
    }
}
