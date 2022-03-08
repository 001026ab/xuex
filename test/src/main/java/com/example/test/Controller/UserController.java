package com.example.test.Controller;

import com.example.test.entity.UserDO;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 10:33
 */

@RestController
@RequestMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("test")
    public void test(){
        UserDO userDO = new UserDO();
        userDO.setUserName("张三");
        userService.insertData(userDO);
        System.out.println("dsfsdfsdfdsfsd");
    }
}
