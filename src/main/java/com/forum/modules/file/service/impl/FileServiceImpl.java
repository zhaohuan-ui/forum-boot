package com.forum.modules.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.modules.file.entity.File;
import com.forum.modules.file.dao.FileDao;
import com.forum.modules.file.service.FileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上传文件表 服务实现类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileDao, File> implements FileService {

}
