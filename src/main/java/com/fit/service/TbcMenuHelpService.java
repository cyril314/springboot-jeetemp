package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbcMenuHelpMapper;
import com.fit.entity.TbcMenuHelpModel;
import org.springframework.stereotype.Service;

@Service("tbcMenuHelpService")
public class TbcMenuHelpService<T> extends BaseService<TbcMenuHelpMapper, TbcMenuHelpModel> {
}