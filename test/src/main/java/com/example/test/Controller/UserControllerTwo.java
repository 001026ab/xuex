package com.example.test.Controller;


import com.example.common.crud.BaseController;
import com.example.common.crud.BaseService;
import com.example.test.entity.UserDO;
import com.example.test.service.UserServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 14:47
 */

@RestController
@RequestMapping(value = "/user2", produces = {"application/json;charset=UTF-8"})
public class UserControllerTwo extends BaseController<UserDO, Integer> {

    @Autowired
    private BaseService<UserDO, Integer> baseService;

    @PostMapping("test2")
    public void test() {
        UserDO userDO = new UserDO();
        userDO.setUserName("sds");
       // baseService.insert(userDO);  //出错，common中的crud有问题
        System.out.println("dsfsdfsdfdsfsd");
    }
}
