package com.fit.base;

import com.fit.util.core.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public abstract class BaseService<D extends BaseMapper<T>, T extends BaseModel> {

    @Autowired
    protected D dao;

    /**
     * <br>
     * <b>功能：</b>主键查询<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public T selectByPrimaryKey(Object key) throws Exception {
        return dao.selectByPrimaryKey(key);
    }

    /**
     * <br>
     * <b>功能：</b>主键修改<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param t
     * @throws Exception
     */
    public Integer updateByPrimaryKey(T t) throws Exception {
        return dao.updateByPrimaryKey(t);
    }

    /**
     * <br>
     * <b>功能：</b>主键删除<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param keys
     * @throws Exception
     */
    public Integer deleteByPrimaryKeys(Object... keys) throws Exception {
        int i = 0;
        for (Object key : keys) {
            i += dao.deleteByPrimaryKey(key);
        }
        return i;
    }

    /**
     * <br>
     * <b>功能：</b>插入<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param t
     * @throws Exception
     */
    public Integer insert(T t) throws Exception {
        return dao.insert(t);
    }

    /**
     * <br>
     * <b>功能：</b>查询总行数<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param model
     * @return
     * @throws Exception
     */
    public Integer selectByModelCount(T model) throws Exception {
        return dao.selectByModelCount(model);
    }

    /**
     * <br>
     * <b>功能：</b>查询Map总行数<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Integer selectByMapCount(Map<?, ?> map) throws Exception {
        return dao.selectByMapCount(map);
    }

    /**
     * <br>
     * <b>功能：</b>模型分页<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param model
     * @return
     * @throws Exception
     */
    public List<T> selectByModel(T model) throws Exception {
        if (((BaseModel) model).getPageUtil().getPaging()) {
            try {
                ((BaseModel) model).getPageUtil().setRowCount(selectByModelCount(model));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao.selectByModel(model);
    }

    /**
     * <br>
     * <b>功能：</b>Map查询 <br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     *
     * @param map
     * @return
     * @throws Exception
     */
    public List<T> selectByMap(Map<?, ?> map) throws Exception {
        return dao.selectByMap(map);
    }

    /**
     * <br>
     * <b>功能：</b>实体查询<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public List<T> selectByEntity(T entity) throws Exception {
        return dao.selectByEntity(entity);
    }

    /**
     * <br>
     * <b>功能：</b>实体删除<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public Integer deleteByEntity(T entity) throws Exception {
        return dao.deleteByEntity(entity);
    }

    /**
     * <br>
     * <b>功能：</b>SQL查询<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public List<T> selectBySql(String sql) throws Exception {
        return dao.selectBySql(sql);
    }

    /**
     * <br>
     * <b>功能：</b>SQL修改<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public Integer updateBySql(String sql) throws Exception {
        return dao.updateBySql(sql);
    }

    /**
     * <br>
     * <b>功能：</b>SQL删除<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public Integer deleteBySql(String sql) throws Exception {
        return dao.deleteBySql(sql);
    }

    /**
     * <br>
     * <b>功能：</b>SQL增加<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-11 <br>
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public Integer insertBySql(String sql) throws Exception {
        return dao.insertBySql(sql);
    }

    /**
     * <br>
     * <b>功能：</b>查询分页数<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-11 <br>
     *
     * @param map
     * @return
     * @throws SQLException
     */
    public Integer selectByMapPagingCount(Map<?, ?> map) throws SQLException {
        return dao.selectByMapPagingCount(map);
    }

    /**
     * <br>
     * <b>功能：</b>查询分页<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-11 <br>
     *
     * @param map
     * @return
     * @throws SQLException
     */
    public List<T> selectByMapPaging(Map<?, ?> map) throws SQLException {
        int rowCount = dao.selectByMapPagingCount(map);
        PageUtil pageUtil = (PageUtil) map.get("pageUtil");
        if (null == pageUtil) {
            System.out.println("错误!!!  pageUtil 参数为NULL");
            return null;
        }
        pageUtil.setRowCount(rowCount);
        return dao.selectByMapPaging(map);
    }

    /**
     * <br>
     * <b>功能：</b>删除一条记录<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-24 <br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public Integer deleteByPrimaryKey(Object key) throws Exception {
        return dao.deleteByPrimaryKey(key);
    }

    /**
     * <br>
     * <b>功能：</b>图表<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-1 <br>
     *
     * @param map
     * @return
     * @throws SQLException
     */
    public List<Map<?, ?>> charts(Map<?, ?> map) throws SQLException {
        return dao.charts(map);
    }

    /**
     * <br>
     * <b>功能：</b>递归查询<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-19 <br>
     *
     * @param model
     * @return
     * @throws SQLException
     */
    public List<T> selectByChild(T model) throws SQLException {
        if (((BaseModel) model).getPageUtil().getPaging()) {
            try {
                ((BaseModel) model).getPageUtil().setRowCount(selectByModelCount(model));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao.selectByChild(model);
    }
}
