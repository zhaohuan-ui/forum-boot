package com.forum.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.user.DO.UserDO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
public interface UserService extends IService<UserDO> {
    /**
     *  登录验证
     */
    UserDO getUser(String username);

    void updateUser(UserDO user);

}
