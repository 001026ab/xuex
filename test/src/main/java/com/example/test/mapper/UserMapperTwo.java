package com.example.test.mapper;


import com.example.common.crud.Mapper;
import com.example.test.entity.UserDO;


/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 14:02
 */

@org.apache.ibatis.annotations.Mapper
public interface UserMapperTwo extends Mapper<UserDO,Integer> {
}
