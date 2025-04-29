package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbsAMapper;
import com.fit.entity.TbsAModel;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
@Service("tbsAService")
public class TbsAService<T> extends BaseService<TbsAMapper, TbsAModel> {
}