package com.fit.base;

import com.fit.util.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-6-18 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
@Slf4j
public class BaseController {

    /**
     * <br>
     * <b>功能：</b>输出JSON<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-6-18 <br>
     *
     * @param t
     * @param response
     */
    public <T> void toJson(T t, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(JsonUtil.toJson(t));
            pw.flush();
            pw.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}