package com.heina.customer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KFAccount {

    private String kfAccount;

    private String kfName;


    public KFAccount(String kfAccount, String kfName) {
        this.kfAccount = kfAccount;
        this.kfName = kfName;
    }

}
