package com.heina.customer.controller;


import com.alibaba.fastjson2.JSONObject;
import com.heina.customer.service.KFAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KFAccountController {

    @Autowired
    private KFAccountService kfAccountService;

    @GetMapping("/kfAccounts")
    public JSONObject listKFAccount(){
        return kfAccountService.ListKFAccount();
    }

    @PostMapping("/refreshAccount")
    public JSONObject refresh(){
        return kfAccountService.refreshAccount();
    }


}
