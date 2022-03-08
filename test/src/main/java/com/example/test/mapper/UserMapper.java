package com.example.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 14:02
 */

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
