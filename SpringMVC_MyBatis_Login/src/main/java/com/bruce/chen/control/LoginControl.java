package com.bruce.chen.control;

import com.bruce.chen.entity.ResponseData;
import com.bruce.chen.entity.SpringmvcUser;
import com.bruce.chen.entity.User;
import com.bruce.chen.utils.LoginDataHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class LoginControl {

    /**
     * 设置 请求方式 POST对应PostMapping；GET对应GetMapping
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/loginAjax", produces = {"application/json;charset=UTF-8"})
    public ResponseData login(@RequestBody User user, HttpServletResponse response) {
        System.out.println(user.toString());
        String resCode = LoginDataHelper.checkUserName(user);
        System.out.println(resCode);
        String resDesc = "";
        switch (resCode) {
            case "00":
                resDesc = "登录成功";
                break;
            case "01":
                resDesc = "密码错误";
                break;
            case "02":
                resDesc = "无此账号，请确认账号填写";
                break;
            default:
                break;
        }
        return new ResponseData(resCode, resDesc, "");
    }

    @PostMapping(value = "/loginForm")
    @ResponseBody
    public String select(User user) {
        System.out.println(user.toString());
        String resCode = LoginDataHelper.checkUserName(user);
        System.out.println(resCode);
        String resDesc = "";
        switch (resCode) {
            case "00":
                resDesc = "登录成功";
                break;
            case "01":
                resDesc = "密码错误";
                break;
            case "02":
                resDesc = "无此账号，请确认账号填写";
                break;
            default:
                break;
        }
        return "登录成功,跳转首页";
    }



    @GetMapping("/view")
    public ModelAndView showView() {
        ModelAndView modelAndView = new ModelAndView("/index.html");
        return modelAndView;
    }
}
