package com.fit.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fit.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fit.util.browser.HtmlJsoup;

/**
 * <br>
 * <b>功能：</b>采集控制类<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-11-26 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
@Controller
@RequestMapping("admin/SystemHttp")
public class SystemHttpClientController extends BaseController {

    @RequestMapping(value = "/index.html")
    public String httpClient() {
        return "admin/SystemHttp/HttpClientIndex";
    }

    @RequestMapping(value = "/hc.html")
    @ResponseBody
    public String hc(HttpServletRequest request, HttpServletResponse response) {
        HtmlJsoup hj = new HtmlJsoup();
        //hj.getPage(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
        return null;
    }
}

