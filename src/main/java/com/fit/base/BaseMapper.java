package com.fit.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T extends BaseModel> {

    /*****************CRUD操作********************/
    public T selectByPrimaryKey(Object key) throws Exception;

    public Integer updateByPrimaryKey(T t) throws Exception;

    public Integer deleteByPrimaryKey(Object key) throws Exception;

    public Integer insert(T t) throws Exception;

    public Integer deleteByEntity(T entity) throws Exception;

    //public Integer deleteByMap()
    public List<T> selectBySql(@Param(value = "sql") String sql) throws Exception;

    public Integer updateBySql(@Param(value = "sql") String sql) throws Exception;

    public Integer deleteBySql(@Param(value = "sql") String sql) throws Exception;

    public Integer insertBySql(@Param(value = "sql") String sql) throws Exception;

    /***********************分页查询操作************************/
    public Integer selectByModelCount(T model) throws Exception;

    public List<T> selectByModel(T model) throws Exception;

    public Integer selectByMapPagingCount(Map<?, ?> map) throws SQLException;

    public List<T> selectByMapPaging(Map<?, ?> map) throws SQLException;

    /***********************查询不分页*************************/
    public List<T> selectByEntity(T entity) throws Exception;

    public Integer selectByMapCount(Map<?, ?> map) throws Exception;

    public List<T> selectByMap(Map<?, ?> map) throws Exception;

    /************************** 图表 **************************/
    public List<Map<?, ?>> charts(Map<?, ?> map) throws SQLException;

    /**************************递归查询***********************/
    public List<T> selectByChild(T model) throws SQLException;
}