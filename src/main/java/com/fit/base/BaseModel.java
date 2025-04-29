package com.fit.base;

import com.fit.util.core.PageUtil;

public class BaseModel {

    private PageUtil pageUtil = new PageUtil();

    public PageUtil getPageUtil() {
        return pageUtil;
    }

    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }
}