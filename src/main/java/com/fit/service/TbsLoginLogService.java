package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbsLoginLogMapper;
import com.fit.entity.TbsLoginLogModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Primary
@Service("tbsLoginLogService")
public class TbsLoginLogService<T> extends BaseService<TbsLoginLogMapper, TbsLoginLogModel> {
}