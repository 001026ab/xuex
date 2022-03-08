package com.example.test.service.Impl;

import com.example.test.entity.UserDO;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 10:03
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public <T> Integer insertData(T t) {
        userMapper.insert((UserDO) t);
        return null;
    }
}
