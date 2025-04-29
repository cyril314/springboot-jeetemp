package com.fit.dao;

import com.fit.base.BaseMapper;
import com.fit.entity.TestModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<TestModel> {
}