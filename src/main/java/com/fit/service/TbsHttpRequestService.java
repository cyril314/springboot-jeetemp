package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbsHttpRequestMapper;
import com.fit.entity.TbsHttpRequestModel;
import org.springframework.stereotype.Service;

@Service("tbsHttpRequestService")
public class TbsHttpRequestService<T> extends BaseService<TbsHttpRequestMapper, TbsHttpRequestModel> {
}