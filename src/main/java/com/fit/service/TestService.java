package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TestMapper;
import com.fit.entity.TestModel;
import org.springframework.stereotype.Service;


@Service("testService")
public class TestService<T> extends BaseService<TestMapper, TestModel> {

}