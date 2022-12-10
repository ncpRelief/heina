package com.heina.customer.pojo.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretQuery {

    private Integer limit = 10;

    private Integer offset = 0;
    private String key;

    public SecretQuery(String key, int limit, int offset) {
        this.limit = limit <= 0 ? this.limit : limit;
        this.offset = limit <= 0 ? this.offset : offset;
        this.key = key;
    }
}
