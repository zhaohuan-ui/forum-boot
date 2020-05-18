package com.forum.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.modules.user.dao.UserDao;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDO getUser(String username) {
        UserDO user = this.getOne(new QueryWrapper<UserDO>().eq("username", username));
        return user;
    }

    @Override
    public void updateUser(UserDO user) {
        this.updateById(user);
    }

}
