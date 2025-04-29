package com.fit.dao;

import com.fit.base.BaseMapper;
import com.fit.entity.TbsMenuModel;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Mapper
public interface TbsMenuMapper extends BaseMapper<TbsMenuModel> {
    public List<Map<String, Object>> selectByMenuIsMenu(Map<String, Object> map) throws SQLException;

    public List<Map<String, Object>> selectByMenuOther(Map<String, Object> map) throws SQLException;

    public List<TbsMenuModel> selectByButtons(Map<String, Object> map) throws SQLException;
}