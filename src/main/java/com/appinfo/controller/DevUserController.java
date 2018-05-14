package com.appinfo.controller;

import com.appinfo.entity.DevUser;
import com.appinfo.service.DevUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/user")
public class DevUserController {

    private Logger logger = Logger.getLogger(DevUserController.class);

    @Resource(name="devUserService")
    private DevUserService devUserService;

    public DevUserService getDevUserService() {
        return devUserService;
    }

    public void setDevUserService(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

    @RequestMapping(value = "/login.html")
    public String login(){
        return "devuserlogin";
    }


    @RequestMapping(value = "/dologin.html")
    public String dologin(@RequestParam String devCode,
                          @RequestParam String devPassword,
                          HttpSession session,
                          HttpServletRequest request)throws Exception{
           DevUser devUser =devUserService.findUserbydevCode(devCode);
         if(devUser!=null && devUser.getDevPassword().equals(devPassword)){
            //登录成功
            //用户对象存session
            session.setAttribute("devUser", devUser);
            logger.debug("登录成功...."+devCode+"\t"+devPassword);
            return "devmain";
        }else{
            //登录失败
            logger.debug("登录失败...."+devCode+"\t"+devPassword);
            //model.addAttribute("msg", "用户名或密码不正确！");
            request.setAttribute("msg", "用户名或密码不正确！");
            return "devuserlogin";
        }

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session,
                          HttpServletRequest request)throws Exception{
       session.removeAttribute("devUser");
        return "devuserlogin";
    }

}