package com.fit.service;

import com.fit.base.BaseService;
import com.fit.entity.TbcMenuModel;
import org.springframework.stereotype.Service;

import com.fit.dao.TbcMenuMapper;

@Service("tbcMenuService")
public class TbcMenuService<T> extends BaseService<TbcMenuMapper, TbcMenuModel> {
}