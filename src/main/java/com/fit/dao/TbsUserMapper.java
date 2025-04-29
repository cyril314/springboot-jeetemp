package com.fit.dao;

import com.fit.base.BaseMapper;
import com.fit.entity.TbsUserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbsUserMapper extends BaseMapper<TbsUserModel> {
    public java.util.List<java.util.Map<String, Object>> selectByRoleUrls(java.util.Map<?, ?> map) throws Exception;
}