package com.fit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fit.base.BaseService;
import com.fit.entity.TbsMenuModel;
import org.springframework.stereotype.Service;

import com.fit.dao.TbsMenuMapper;

@Service("tbsMenuService")
public class TbsMenuService extends BaseService<TbsMenuMapper, TbsMenuModel> {

    public List<Map<String,Object>> selectByMenuIsMenu(Map<String,Object> map) throws SQLException{
        return dao.selectByMenuIsMenu(map);
    }

    public List<Map<String,Object>> selectByMenuOther(Map<String,Object> map) throws SQLException{
        return dao.selectByMenuOther(map);
    }

    public List<TbsMenuModel> selectByButtons(Map<String, Object> map) throws SQLException {
        return dao.selectByButtons(map);
    }
}