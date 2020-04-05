package com.bruce.chen.control;

import com.bruce.chen.entity.ResponseData;
import com.bruce.chen.entity.User;
import com.bruce.chen.utils.LoginDataHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterControl {

    @PostMapping(value = "/fromRegister")
    @ResponseBody
    public String fromRegisterBackString(User user) {
        boolean a =LoginDataHelper.registerUser(user);
        if(a){
            return user.getUname()+"注册成功";
        }
        return user.getUname()+"注册失败";
    }


    @PostMapping(value = "/ajaxRegister", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseData registerAjaxBackJson(@RequestBody User user) {
        boolean a =LoginDataHelper.registerUser(user);
        if(a){
            return new ResponseData("00", "注册成功", "");
        }
        return new ResponseData("er", "注册失败", "");
    }

}
