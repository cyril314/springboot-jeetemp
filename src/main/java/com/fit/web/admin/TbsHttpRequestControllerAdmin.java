package com.fit.web.admin;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fit.base.BaseController;
import com.fit.entity.TbsHttpRequestModel;
import com.fit.entity.TbsMenuModel;
import com.fit.service.TbsHttpRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fit.service.TbsMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.fit.util.spring.MyTimestampPropertyEdit;
import com.fit.util.spring.SessionUtil;
import com.fit.util.core.MethodUtil;
import com.fit.util.core.ExcelUtil;
import com.fit.util.core.PageParams;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import com.alibaba.fastjson.JSON;
import com.fit.util.browser.HCPool;
import com.fit.util.browser.HtmlJsoup;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/admin/TbsHttpRequest/")
public class TbsHttpRequestControllerAdmin extends BaseController {

    private static MethodUtil util = new MethodUtil();

    @Autowired
    private TbsMenuService tbsMenuService;

    // 服务类
    @Autowired(required = false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
    private TbsHttpRequestService<TbsHttpRequestModel> tbsHttpRequestService;


    @InitBinder//必须有一个参数WebDataBinder 日期类型装换
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new MyTimestampPropertyEdit()); //使用自定义属性编辑器
        //  binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }


    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-6-20 <br>
     *
     * @return
     */
    @RequestMapping("index.html")
    public ModelAndView index(String id, ModelMap modelMap, HttpServletRequest request) {
        List<String> buttons = new ArrayList<String>();
        TbsMenuModel tbsMenuModel = new TbsMenuModel();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", id);
        map.put("orderCondition", "sortNumber");
        System.out.println("id:" + id);
        String isAdmin = (String) SessionUtil.getAttr(request, "isAdmin");
        List<TbsMenuModel> list = null;
        try {
            if (null != isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
                list = tbsMenuService.selectByMap(map);
            } else {
                list = tbsMenuService.selectByButtons(map);
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    tbsMenuModel = list.get(i);
                    String button = tbsMenuModel.getButton();
                    if (null != button && "null" != button) {
                        buttons.add(button);//.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("buttons", buttons);
        return new ModelAndView("admin/TbsHttpRequest/index", modelMap);
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-6-20 <br>
     *
     * @return
     */
    @RequestMapping("charts.html")
    public String charts() {
        return "admin/TbsHttpRequest/charts";
    }


    /**
     * <br>
     * <b>功能：</b>图表<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-1 <br>
     *
     * @param response
     * @param startTime
     * @param endTime
     * @param chartsName
     * @param type
     */
    @RequestMapping("chartsJson.html")
    public void chartsJson(HttpServletResponse response, String startTime, String endTime, String chartsName, Integer type) {
        String resultJson = "[]";
        String data = null;
        String categories = null;
        String startTimeFormat = null;
        String endTimeFormat = null;
        String groupTimeFormat = null;
        String year = "%Y";
        String month = "%m";
        String day = "%d";
        String hour = "%H";
        String minute = "%i";
        String second = "%s";
        Map<String, String> paramMap = new HashMap<String, String>();
        if (null == chartsName) {
            chartsName = "line";
        }
        if (null == type) {
            type = 4;
        }
        switch (type) {
            case 1: //年
                groupTimeFormat = year;
                break;
            case 2://月
                startTimeFormat = year + "-00";
                endTimeFormat = year + "-12";
                groupTimeFormat = year + "-" + month;
                break;
            case 3://日
                startTimeFormat = year + "-" + month + "-01";
                endTimeFormat = year + "-" + month + "-31";
                groupTimeFormat = year + "-" + month + "-" + day;
                break;
            case 4://时
                startTimeFormat = year + "-" + month + "-" + day + " 00";
                endTimeFormat = year + "-" + month + "-" + day + " 24";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour;
                break;
            case 5://分
                startTimeFormat = year + "-" + month + "-" + day + " " + hour + ":01";
                endTimeFormat = year + "-" + month + "-" + day + " " + hour + ":59";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                break;
            case 6://秒
                startTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
                endTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":59";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                break;
            default:
                break;
        }
        paramMap.put("startTimeFormat", startTimeFormat);
        paramMap.put("endTimeFormat", endTimeFormat);
        paramMap.put("groupTimeFormat", groupTimeFormat);
        try {
            List<Map<?, ?>> list = tbsHttpRequestService.charts(paramMap);
            if (null != list && list.size() > 0) {
                System.out.println("list:" + list.size());
                data = "\"data\":[";
                categories = "\"categories\":[";
                for (int i = 0; i < list.size(); i++) {
                    Map<?, ?> map = list.get(i);
                    int count = ((Long) map.get("COUNT(*)")).intValue();
                    String time = map.get("createTime").toString();
                    data += count + ",";
                    categories += "\"" + time + "\",";
                    System.out.println("count:" + count + "|time:" + time);
                }
                data = data.substring(0, data.length() - 1);
                data = data + "]";
                categories = categories.substring(0, categories.length() - 1);
                categories = categories + "]";
                System.out.println(data);
                System.out.println(categories);
                resultJson = "{" + data + "," + categories + "}";
                util.toJsonPrint(response, resultJson);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        util.toJsonPrint(response, resultJson);
    }


    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-6-20 <br>
     *
     * @return
     */
    @RequestMapping("baseDlg.html")
    public String baseDlg() {
        return "admin/TbsHttpRequest/baseDlg";
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-6-20 <br>
     *
     * @return
     */
    @RequestMapping("importDlg.html")
    public String importDlg() {
        return "admin/TbsHttpRequest/importDlg";
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-6-20 <br>
     *
     * @return
     */
    @RequestMapping("searchDlg.html")
    public String searchDlg() {
        return "admin/TbsHttpRequest/searchDlg";
    }


    /**
     * <br>
     * <b>功能：</b>方法功能描述<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-31 <br>
     *
     * @param pageParams
     * @param tbsHttpRequestModel
     * @return
     * @throws Exception
     */
    @RequestMapping("data.html")
    @ResponseBody
    public String data(PageParams pageParams, TbsHttpRequestModel tbsHttpRequestModel) throws Exception {
        System.out.println("pageParams:" + pageParams + "|tbsHttpRequestModel:" + tbsHttpRequestModel);
        tbsHttpRequestModel.getPageUtil().setPaging(true);
        String result = "[]";
        if (pageParams.getPage() != null) {
            try {
                tbsHttpRequestModel.getPageUtil().setPageId(Integer.parseInt(pageParams.getPage())); // 当前页
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (pageParams.getRows() != null) {
            try {
                tbsHttpRequestModel.getPageUtil().setPageSize(Integer.parseInt(pageParams.getRows()));// 显示X条
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (pageParams.getSort() != null) {
            try {
                tbsHttpRequestModel.getPageUtil().setOrderByCondition(pageParams.getSort()); // 排序字段名称
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        // tbsHttpRequestModel.getPageUtil().setOrderDirection(true); //true false 表示 正序与倒序
        String str = "";
        String suffix = "}";
        if (pageParams.getGridName() != null) {
            str = "[";
            suffix = "]}";
        }
        List<TbsHttpRequestModel> listTbsHttpRequestModel = null;
        StringBuilder center = new StringBuilder();

        if (pageParams.getSearchType() != null) {
            if (pageParams.getSearchType().equals("1")) { // 模糊搜索
                tbsHttpRequestModel.getPageUtil().setLike(true);
                listTbsHttpRequestModel = tbsHttpRequestService.selectByModel(tbsHttpRequestModel);
                center.append("{\"total\":\"" + tbsHttpRequestModel.getPageUtil().getRowCount() + "\",\"rows\":" + str);
            } else {
                StringBuffer sb = new StringBuffer(); // 高级查询
                String[] searchColumnNameArray = pageParams.getSearchColumnNames().split("\\,");
                String[] searchAndsArray = pageParams.getSearchAnds().split("\\,");
                String[] searchConditionsArray = pageParams.getSearchConditions().split("\\,");
                String[] searchValsArray = pageParams.getSearchVals().split("\\,");
                System.out.println(Arrays.toString(searchColumnNameArray));
                for (int i = 0; i < searchColumnNameArray.length; i++) {
                    if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length() > 0) {
                        if (i == 0) {
                            sb.append(searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
                        } else {
                            sb.append(" " + searchAndsArray[i].trim() + " " + searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
                        }
                    }
                }
                if (sb.length() > 0) {
                    System.out.println("searchCondition:" + sb.toString());
                    tbsHttpRequestModel.getPageUtil().setAndCondition(sb.toString());
                    listTbsHttpRequestModel = tbsHttpRequestService.selectByModel(tbsHttpRequestModel);
                    center.append("{\"total\":\"" + tbsHttpRequestModel.getPageUtil().getRowCount() + "\",\"rows\":" + str);
                }
            }
        } else {
            if (pageParams.getGridName() == null) {
                listTbsHttpRequestModel = tbsHttpRequestService.selectByModel(tbsHttpRequestModel);
                center.append("{\"total\":\"" + tbsHttpRequestModel.getPageUtil().getRowCount() + "\",\"rows\":");
                suffix = "}";
            } else {
            }
        }

        if (pageParams.getGridName() == null) { //datagrid
            center.append(JSON.toJSONString(listTbsHttpRequestModel));
        } else {
        }
        center.append(suffix);
        result = center.toString();
        System.out.println("json:" + result);
        return result;
    }


    /**
     * <br>
     * <b>功能：</b>导出<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-31 <br>
     *
     * @param response
     */
    @RequestMapping("export.html")
    public void tbsHttpRequestExport(HttpServletResponse response, TbsHttpRequestModel tbsHttpRequestModel) {
        List<TbsHttpRequestModel> listTbsHttpRequestModel = null;
        try {
            listTbsHttpRequestModel = tbsHttpRequestService.selectByModel(tbsHttpRequestModel);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        ExcelUtil excelUtil = new ExcelUtil();
        Map<String, List<TbsHttpRequestModel>> exportMap = new HashMap<String, List<TbsHttpRequestModel>>();
        exportMap.put("sheet", listTbsHttpRequestModel);
        Workbook wb = excelUtil.writeExcel2(exportMap, null, 1);
        String filename = util.getDate(1, null) + ".xls";
        OutputStream out = null;
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //response.addHeader("Content-Length", "");
        try {
            out = response.getOutputStream();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <br>
     * <b>功能：</b>导入<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-31 <br>
     *
     * @param request
     * @param response
     */
    @RequestMapping("import.html")
    public void tbsHttpRequestImport(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            ExcelUtil excelUtil = new ExcelUtil();
            String[] columnName = new String[]{"id", "href", "text"};
            try {
                Map<String, List<TbsHttpRequestModel>> ml = excelUtil.readExcel(new ByteArrayInputStream(mf.getBytes()), columnName, 2, TbsHttpRequestModel.class);
                for (Map.Entry<String, List<TbsHttpRequestModel>> map : ml.entrySet()) {
                    List<TbsHttpRequestModel> lt = map.getValue();
                    for (int i = 0; i < lt.size(); i++) {
                        try {
                            tbsHttpRequestService.insert(lt.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                util.toJsonMsg(response, 1, null);
                return;
            }
        }
        util.toJsonMsg(response, 0, null);
    }

    /**
     * <br>
     * <b>功能：</b>增加 TbsHttpRequestModel<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     *
     * @return
     */
    @RequestMapping("add.html")
    public void add(TbsHttpRequestModel tbsHttpRequestModel, HttpServletResponse response) {
        tbsHttpRequestModel.setId(util.getUid());//设置主键
        System.out.println("tbsHttpRequestModel:" + tbsHttpRequestModel.toString());
        try {
            if (tbsHttpRequestService.insert(tbsHttpRequestModel) > 0) {
                util.toJsonMsg(response, 0, null);
                return;
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        util.toJsonMsg(response, 1, null);
    }

    /**
     * <br>
     * <b>功能：</b>保存 TbsHttpRequestModel<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     *
     * @return
     */
    @RequestMapping("save.html")
    public void save(TbsHttpRequestModel tbsHttpRequestModel, HttpServletResponse response) {
        try {
            if (tbsHttpRequestService.updateByPrimaryKey(tbsHttpRequestModel) > 0) {
                util.toJsonMsg(response, 0, null);
                return;
            }
        } catch (Exception e) {
            util.toJsonMsg(response, 1, null);
            e.printStackTrace();
        }
    }

    /**
     * <br>
     * <b>功能：</b>删除 TbsHttpRequestBean<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-25 <br>
     *
     * @param ids
     * @param response
     */
    @RequestMapping("del.html")
    public void del(String[] ids, HttpServletResponse response) {
        System.out.println("del-ids:" + Arrays.toString(ids));
        try {
            if (null != ids && ids.length > 0) {
                if (tbsHttpRequestService.deleteByPrimaryKeys(ids) > 0) {
                    util.toJsonMsg(response, 0, null);
                    return;
                }
            }
        } catch (Exception e) {
            util.toJsonMsg(response, 1, null);
            log.error(e.getMessage());
        }
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////在下面添加自定义的方法///////////////////////////////////
    private SqlSessionFactory sqlSessionFactory = null;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @RequestMapping(value = "httpIndex.html")
    public String http(/*String id,ModelMap modelMap*/) {
		/*try {
			System.out.println("id:"+id);
			modelMap.addAttribute("tbsHttpRequestModel", tbsHttpRequestService.selectById(Long.parseLong(id)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
        return "admin/TbsHttpRequest/httpIndex";
    }

    @RequestMapping(value = "httpDialog.html")
    public String httpDialog() {
        return "admin/TbsHttpRequest/httpDialog";
    }

    @RequestMapping(value = "http.html")
    @ResponseBody
    public String hc(String type, TbsHttpRequestModel tbsHttpRequestModel, HttpServletResponse response) throws Exception {
        System.out.println("type:" + type + "|" + tbsHttpRequestModel.toString() + "|" + tbsHttpRequestModel.getName());
        if (null == type) return "类型错误";
        try {
            tbsHttpRequestService.updateByPrimaryKey(tbsHttpRequestModel);
        } catch (Exception e) {
            e.printStackTrace();
            return "保存规则失败";
        }
        String encoding = tbsHttpRequestModel.getEncoding();
        if (type.equals("15")) {
            return this.getThreadPool(tbsHttpRequestModel);
        }
        if (type.equals("1")) {
            return new HtmlJsoup().getPage(tbsHttpRequestModel.getFirstPage(), null, null, null, encoding, encoding).html();
        }
        String[] rs = tbsHttpRequestModel.getRegexs().split("\\|");
        String[] as = tbsHttpRequestModel.getArrtSplit().split("\\|");
        List<String[][]> list = this.getList(tbsHttpRequestModel.getFirstPage(), null, null, null, encoding, encoding, rs, as);
        if (type.equals("2")) {
            return this.getPrintList(list, "|");
        }
        if (type.equals("3") || type.equals("4")) {
            String[] inserts = tbsHttpRequestModel.getInserts().split("\\|");
            if (type.equals("3")) {
                return this.getInserts(inserts, list, false);
            } else {
                return this.getInserts(inserts, list, true);
            }
        }
        String listUrlRegex = tbsHttpRequestModel.getListUrlRegex();
        if (type.equals("5")) {
            return this.getPrintListData(this.getListData(this.getGroupArray(listUrlRegex), list));
        }
        String result = null;
        List<String[]> data = this.getListData(this.getGroupArray(listUrlRegex), list);
        Integer pageId = tbsHttpRequestModel.getListPageId();
        String splitUrlChar = tbsHttpRequestModel.getListSplitUrlChar();
        String splitUrl = tbsHttpRequestModel.getListSplitUrl();
        Integer testLinne = tbsHttpRequestModel.getListUrlTest();
        System.out.println("pageId:" + pageId + "|splitUrlChar:" + splitUrlChar + "|splitUrl:" + splitUrl + "|testLinne:" + testLinne);
        String testUrl = data.get(0)[testLinne]; // 取数据
        System.out.println("testUrl:" + testUrl);
        try {
            result = this.getUrlSplist(testUrl, splitUrlChar, splitUrl, pageId);
        } catch (Exception e) {
            e.printStackTrace();
            return "参数设置有误" + e.toString();
        }
        if (type.equals("6")) {
            return result;
        }
        if (type.equals("7")) {
            System.out.println("sendUrl:" + result);
            return new HtmlJsoup().getPage(result, null, null, null, encoding, encoding).html();
        }
        rs = tbsHttpRequestModel.getListRegexs().split("\\|");
        as = tbsHttpRequestModel.getListAttrSplit().split("\\|");
        list = this.getList(result, null, null, null, encoding, encoding, rs, as);
        if (type.equals("8")) {
            return this.getPrintList(list, "|");
        }
        if (type.equals("9") || type.equals("18")) {
            String[] inserts = tbsHttpRequestModel.getListInserts().split("\\|");
            if (type.equals("9")) {
                return this.getInserts(inserts, list, false);
            } else {
                return this.getInserts(inserts, list, true);
            }
        }
        String contUrlRegx = tbsHttpRequestModel.getContUrlRegex();
        data = this.getListData(this.getGroupArray(contUrlRegx), list);
        if (type.equals("10")) {
            return this.getPrintListData(data);
        }
        Integer contUrlTest = tbsHttpRequestModel.getContUrlTest();
        testUrl = data.get(0)[contUrlTest];
        if (type.equals("11")) {
            System.out.println("sendUrl:" + testUrl);
            return new HtmlJsoup().getPage(testUrl, null, null, null, encoding, encoding).html();
        }
        rs = tbsHttpRequestModel.getContRegexs().split("\\|");
        as = tbsHttpRequestModel.getContAttrSplit().split("\\|");
        list = this.getList(testUrl, null, null, null, encoding, encoding, rs, as);
        if (type.equals("12")) {
            return this.getPrintList(list, "|");
        }
        if (type.equals("13") || type.equals("14")) {
            String[] inserts = tbsHttpRequestModel.getContInserts().split("\\|");
            if (type.equals("13")) {
                return this.getInserts(inserts, list, false);
            } else {
                return this.getInserts(inserts, list, true);
            }
        }
        return null;
    }

    private String getThreadPool(final TbsHttpRequestModel tbsHttpRequestModel) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        if (threadPool.isTerminated()) {
            return "线程正在运行!";
        }
        threadPool.execute(new Runnable() {
            public void run() {
                HCPool hcp = new HCPool();
                String encoding = tbsHttpRequestModel.getEncoding();
                System.out.println("encoding:" + encoding);
                List<String[][]> list = getList(tbsHttpRequestModel.getFirstPage(), null, null, null, encoding, encoding, tbsHttpRequestModel.getRegexs().split("\\|"), tbsHttpRequestModel.getArrtSplit().split("\\|"));
                String[] urls = getListData(getGroupArray(tbsHttpRequestModel.getListUrlRegex()), list).get(0);
                //List<Future<String>> listFuture = hcp.httpPool(urls, null, null, null, encoding, 0, 0);
                Integer pageId = tbsHttpRequestModel.getListPageId();
                String[] regexs = tbsHttpRequestModel.getListRegexs().split("\\|");
                String[] attrs = tbsHttpRequestModel.getListAttrSplit().split("\\|");
                String[] conRegexs = tbsHttpRequestModel.getContRegexs().split("\\|");
                String[] conAttrs = tbsHttpRequestModel.getContAttrSplit().split("\\|");
                String[] conInserts = tbsHttpRequestModel.getContInserts().split("\\|");
                for (int i = 0; i < urls.length; i++) {
                    System.out.println("urls[i]:" + urls[i]);
                    Integer listPageId = pageId;
                    while (true) {
						/*if (i < 2)
							break;*/
                        System.out.println("-------------listPageId:" + listPageId);
                        String pageUrl = getUrlSplist(urls[i], tbsHttpRequestModel.getListSplitUrlChar(), tbsHttpRequestModel.getListSplitUrl(), listPageId);
                        System.out.println("-------------pageUrl:" + pageUrl);
                        list = getList(pageUrl, null, null, null, null, null, regexs, attrs);
                        String[] contentUrls = getListData(getGroupArray(tbsHttpRequestModel.getContUrlRegex()), list).get(0);
                        if (contentUrls.length < 1) {
                            System.out.println("最后一页:listPageId:" + (listPageId - 1) + "|urls[i]:" + urls[i]);
                            break;
                        }
                        Queue<Future<Content>> qfs = hcp.httpQueue(contentUrls, null, null, null, encoding, 0, 0);
                        for (int j = 0; j < qfs.size(); j++) {
                            String html = null;
                            try {
                                Future<Content> fc;
                                while ((fc = qfs.poll()) != null) {
                                    //System.out.println(fc.get().asString());
                                    //html = IOUtils.toString(fc.get().asString().getBytes(""), encoding);
                                    html = fc.get().asString();
                                    if (html != null) {
                                        List<String[][]> ls = getList(html, conRegexs, conAttrs);
                                        getInserts(conInserts, ls, true);
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                        listPageId++;
                        //listPageId = listPageId + 5;
                    }
                }
            }
        });
        threadPool.shutdown();
        return "启动线程成功请查看数据库!";
    }

    public void getLooped(String url, Integer pageId, String listSplitUrlChar, String listSplitUrl) {
	   /* list = getList(url, null, null, null, null, null, regexs, attrs);
		List<String[]> listContent = getListData(getGroupArray(tbsHttpRequestModel.getContUrlRegex()), list);
		String[][] listContentSort = getListDateSort(listContent);
		for (int a = 0; a < listContentSort.length; a++) {
			for (int b = 0; b < listContentSort[a].length; b++) {
				System.out.println("pageId:" + listContentSort[a][b]);
			}
		}*/
    }


    private String getUrlSplist(String testUrl, String listSplitUrlChar, String listSplitUrl, Integer pageId) {
        StringBuffer sb = new StringBuffer();
        String[] splitStr = testUrl.split("\\+" + listSplitUrlChar);
        String[] splitStr1 = listSplitUrl.split("\\+");
        for (int i = 0; i < splitStr1.length; i++) {
            if (splitStr1[i].equals("/")) {
                sb.append("/");
            } else if (splitStr1[i].equals("(*)")) {
                sb.append(pageId);
            } else if (splitStr1[i].equals("//")) {
                sb.append("//");
            } else {
                sb.append(splitStr[Integer.parseInt(splitStr1[i])]);
            }
        }
        return sb.toString();
    }

    private String getPrintListData(List<String[]> data) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r");
        for (int i = 0; i < data.size(); i++) {
            String[] array = data.get(i);
            for (int j = 0; j < array.length; j++) {
                sb.append(array[j]);
                sb.append("\r");
            }
        }
        return sb.toString();
    }

    private String getPrintList(List<String[][]> list, String splitChar) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r");
        for (int i = 0; i < list.size(); i++) {
            String[][] array = list.get(i);
            for (int j = 0; j < array.length; j++) {
                for (int x = 0; x < array[j].length; x++) {
                    sb.append(array[j][x] + splitChar);
                }
                sb = sb.delete(sb.length() - 1, sb.length());
                sb.append("\r");
            }
        }
        return sb.toString();
    }

    private List<String[][]> getList(String html, String[] regexs, String[] attrs) {
        Elements es = Jsoup.parse(html).getAllElements();
        List<String[][]> list = new ArrayList<String[][]>();
        for (int i = 0; i < regexs.length; i++) {
            System.out.println("regex:" + regexs[i]);
            list.add(HtmlJsoup.getArrtArray(HtmlJsoup.getPage(es, regexs[i]), attrs[i].split("\\,")));
        }
        return list;
    }

    private List<String[][]> getList(String url, String method, Map<?, ?> headerMap, Map<?, ?> paramsMap, String requestEncoding, String resultEncoding, String[] regexs, String[] attrs) {
        Elements es = new HtmlJsoup().getPageAllElements(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
        List<String[][]> list = new ArrayList<String[][]>();
        for (int i = 0; i < regexs.length; i++) {
            System.out.println("regex:" + regexs[i]);
            list.add(HtmlJsoup.getArrtArray(HtmlJsoup.getPage(es, regexs[i]), attrs[i].split("\\,")));
        }
        return list;
    }

    private List<String[]> getListData(List<String[]> groupArr, List<String[][]> list) {
        List<String[]> listData = new ArrayList<String[]>();
        for (int a = 0; a < groupArr.size(); a++) {
            String[] groupData = groupArr.get(a);
            String[][] array = list.get(Integer.parseInt(groupData[0]));
            System.out.println("column:" + Integer.parseInt(groupData[1]) + "|line:" + Integer.parseInt(groupData[0]) + "|list.size:" + list.size() + "|array.length:" + array.length);
            String[] data = new String[array.length];
            for (int j = 0; j < array.length; j++) {
                data[j] = array[j][Integer.parseInt(groupData[1])];
            }
            listData.add(data);
        }
        return listData;
    }

    private String[][] getListDateSort(List<String[]> listData) {
        String[][] reDate = null;
        int listSize = listData.size();
        for (int b = 0; b < listSize; b++) {
            String[] data = listData.get(b);
            if (b < 1) reDate = new String[data.length][listSize];
            for (int c = 0; c < data.length; c++) {
                reDate[c][b] = data[c];
            }
        }
        return reDate;
    }

    private List<String[]> getGroupArray(String customRegex) {
        List<String[]> listArray = new ArrayList<String[]>();
        if (null != customRegex) customRegex = customRegex.trim();
        String centerStrRep = customRegex.replaceAll("\\{|\\}", "");
        String[] centerStrRepSplit = centerStrRep.split("\\,");
        for (int j = 0; j < centerStrRepSplit.length; j++) {
            String[] sp = centerStrRepSplit[j].split("\\-");
            listArray.add(sp);
        }
        return listArray;
    }

    public String getInserts(String[] inserts, List<String[][]> list, boolean flag) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r");
        for (int i = 0; i < inserts.length; i++) {
            int start = inserts[i].indexOf('{');
            int end = inserts[i].lastIndexOf('}') + 1;
            String startStr = inserts[i].substring(0, start); // 开始字符串
            String endStr = inserts[i].substring(end, inserts[i].length());// 结束字符串
            String regexStr = inserts[i].substring(start, end);// 表达式
            System.out.println("startStr:" + startStr + "|endStr:" + endStr + "regexStr" + regexStr);
            List<String[]> groupArr = this.getGroupArray(regexStr); // 分割组
            List<String[]> listData = this.getListData(groupArr, list); // 获取数据
            String[][] reDate = this.getListDateSort(listData); // 排序
            for (int j = 0; j < reDate.length; j++) {
                String sql = "";
                if (startStr.indexOf("[id]") != -1) {
                    sql = startStr.replaceAll("\\[id\\]", util.getUid());
                } else {
                    sql = startStr;
                }
                for (int x = 0; x < reDate[j].length; x++) {
                    sql += "'" + reDate[j][x] + "',";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += endStr;
                if (flag) {
                    //DBFactory.getDBDaoEx().getInsert(this.sqlSessionFactory.openSession().getConnection(), sql);
                    System.out.println("sql:" + sql);
                    //DBFactory.getDBDaoEx().getInsert(this.sqlSessionFactory.openSession().getConnection(), sql);
                    try {
                        tbsHttpRequestService.insertBySql(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 入库操作
                } else {
                    sb.append(sql);
                    sb.append("\r");
                }
            }
        }
        return sb.toString();
    }

    public void test() {
        String url = "http://www.yy606.com/about/siteMap.shtml";
        String encodeing = "UTF-8";
        String[] rs = new String[]{"html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a", "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a"};
        String[] as = new String[]{"target,href", "text"};
        List<String[][]> list = this.getList(url, null, null, null, encodeing, encodeing, rs, as);
        System.out.println("list.size:" + list.size());
        // String[] is=tbsHttpRequestModel.getInserts().split("\\|");
        String inserts = "INSERT INTO TbcTemp(id,href,text) VALUES('[id]',{1-0},{0-1})|INSERT INTO TbcTemp(id,href,text) VALUES({0-0})";
        this.getInserts(inserts.split("\\|"), list, false);
    }

    public void test1() {
        TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
        //admin.test();
        String[] rs = "html>body>div[class=container]>div[class=index_left04 fl]>div:eq(1)>div:eq(0)>h2>strong".split("\\|");
        String[] as = "text".split("\\|");
        String encoding = "UTF-8";
        String url = "http://www.yy606.com/view/2013031419174481704.shtml";
        List<String[][]> list = admin.getList(url, null, null, null, encoding, encoding, rs, as);
        System.out.println(admin.getPrintList(list, "|"));
    }

    public void test2() throws InterruptedException, ExecutionException {
        TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
        String url = "http://www.yy606.com/about/siteMap.shtml";
        String encodeing = "UTF-8";
        String[] rs = new String[]{"html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a", "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a"};
        String[] as = new String[]{"href", "text"};
        List<String[][]> list = admin.getList(url, null, null, null, encodeing, encodeing, rs, as);
        List<String[]> data = admin.getListData(admin.getGroupArray("{0-0}"), list);
        System.out.println(admin.getPrintListData(data));
        HCPool hcPool = new HCPool();
        for (int i = 0; i < data.size(); i++) {
			/*List<Future<String>> callable= hcPool.httpGetPool(data.get(i), null, null, 0, 0);
			for(int j=0;j<callable.size();j++){
				System.out.println(callable.get(j).get());
				System.out.println("j:"+j);
			}*/
            Queue<Future<Content>> queue = hcPool.httpQueue(data.get(i), null, null, null, null, 0, 0);
            for (int j = 0; j < queue.size(); j++) {
                Future<Content> str;
                while ((str = queue.poll()) != null) {
                    System.out.println("result:" + str.get().asString());
                }
            }
        }
        System.out.println("list.size:" + data.size());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
        TbsHttpRequestModel tbsHttpRequestModel = new TbsHttpRequestModel();
        tbsHttpRequestModel.setFirstPage("http://www.yy606.com/about/siteMap.shtml");
        tbsHttpRequestModel.setEncoding("utf-8");
        tbsHttpRequestModel.setRegexs("html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a|html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a");
        tbsHttpRequestModel.setArrtSplit("href,text|target");
        tbsHttpRequestModel.setInserts("INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{1-0});");

        tbsHttpRequestModel.setListUrlRegex("{0-0}");
        tbsHttpRequestModel.setListPageId(1);
        tbsHttpRequestModel.setListUrlTest(0);
        tbsHttpRequestModel.setListSplitUrlChar("/");
        tbsHttpRequestModel.setListSplitUrl("0+/+/+1+2+/+3+/+4+/+(*)");
        tbsHttpRequestModel.setListRegexs("html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a|html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a>span");
        tbsHttpRequestModel.setListAttrSplit("href,text|text");
        tbsHttpRequestModel.setListInserts("INSERT INTO TbcListTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcListTemp(id,text) VALUES([id],{1-0});");

        tbsHttpRequestModel.setContUrlRegex("{0-0}");
        tbsHttpRequestModel.setContUrlTest(0);
        tbsHttpRequestModel.setContRegexs("html>body>div.container>div.index_left04>div.index_left07a>span.fl>a:eq(2)|html>body>div.container>div.index_left04>div.border>div.news_dt06>h2>strong|html>body>div.container>div.index_left04>div.border>div.news_dt06>p:eq(4)|html>body>div.container>div.index_left04>div.border>div.news_dt06>center>div>img");
        tbsHttpRequestModel.setContAttrSplit("text|text|text|src");
        tbsHttpRequestModel.setContInserts("INSERT INTO TbcConTemp(id,TYPE,title,image,TEXT) VALUES([id],{0-0},{1-0},{2-0},{3-0});");

        System.out.println("getThreadPool:" + admin.getThreadPool(tbsHttpRequestModel));
    }
}