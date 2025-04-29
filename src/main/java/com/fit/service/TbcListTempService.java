package com.fit.service;

import com.fit.base.BaseService;
import com.fit.entity.TbcListTempModel;
import org.springframework.stereotype.Service;

import com.fit.dao.TbcListTempMapper;

@Service("tbcListTempService")
public class TbcListTempService<T> extends BaseService<TbcListTempMapper, TbcListTempModel> {
}