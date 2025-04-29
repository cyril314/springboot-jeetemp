package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbcTempMapper;
import com.fit.entity.TbcTempModel;
import org.springframework.stereotype.Service;


@Service("tbcTempService")
public class TbcTempService<T> extends BaseService<TbcTempMapper, TbcTempModel> {
}