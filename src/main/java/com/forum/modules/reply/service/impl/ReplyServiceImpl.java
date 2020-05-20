package com.forum.modules.reply.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.modules.reply.entity.Reply;
import com.forum.modules.reply.dao.ReplyDao;
import com.forum.modules.reply.service.ReplyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 回复信息表 服务实现类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, Reply> implements ReplyService {

}
