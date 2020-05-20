package com.forum.modules.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.modules.follow.entity.Follow;
import com.forum.modules.follow.dao.FollowDao;
import com.forum.modules.follow.service.FollowService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注表 服务实现类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowDao, Follow> implements FollowService {

}
