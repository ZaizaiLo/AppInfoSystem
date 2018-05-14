package com.appinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(value = "/login.html")
    public String login(){
        return "userlogin";
    }
}