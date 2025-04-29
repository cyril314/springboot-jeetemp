package com.fit.service;

import com.fit.base.BaseService;
import com.fit.entity.TbsBrowserNameModel;
import org.springframework.stereotype.Service;

import com.fit.dao.TbsBrowserNameMapper;

@Service("tbsBrowserNameService")
public class TbsBrowserNameService<T> extends BaseService<TbsBrowserNameMapper, TbsBrowserNameModel> {

}