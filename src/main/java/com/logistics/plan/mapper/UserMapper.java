package com.logistics.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.plan.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2020-12-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
