package com.fit.service;

import com.fit.base.BaseService;
import com.fit.entity.TbcConTempModel;
import org.springframework.stereotype.Service;

import com.fit.dao.TbcConTempMapper;

@Service("tbcConTempService")
public class TbcConTempService extends BaseService<TbcConTempMapper, TbcConTempModel> {
}