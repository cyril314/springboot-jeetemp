package com.fit.web.front;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fit.base.BaseController;
import com.fit.entity.TbcContentModel;
import com.fit.entity.TbcMenuHelpModel;
import com.fit.entity.TbcMenuModel;
import com.fit.service.TbcContentService;
import com.fit.service.TbcMenuHelpService;
import com.fit.service.TbcMenuService;
import com.fit.util.core.MethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    //工具类
    protected static MethodUtil util = new MethodUtil();
    // 服务类
    @Autowired
    protected TbcMenuService<TbcMenuModel> tbcMenuService;
    @Autowired
    protected TbcMenuHelpService<TbcMenuHelpModel> tbcMenuHelpService;
    @Autowired
    protected TbcContentService<TbcContentModel> tbcContentService;

    /**
     * <br>
     * <b>功能：</b>加载头部通用<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-19 <br>
     *
     * @param modelMap
     */
    private void loadHead(ModelMap modelMap) {
        TbcMenuModel tbcMenuModel = new TbcMenuModel();
        tbcMenuModel.getPageUtil().setAndCondition("parentId IS NULL");
        tbcMenuModel.getPageUtil().setOrderByCondition("sortNumber");
        try {
            modelMap.addAttribute("listTbcMenuModel", tbcMenuService.selectByChild(tbcMenuModel));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <br>
     * <b>功能：</b>加载左边<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-7-21 <br>
     *
     * @param modelMap
     */
    private void loadLeft(ModelMap modelMap) {
        TbcMenuHelpModel tbcMenuHelpModel = new TbcMenuHelpModel();
        tbcMenuHelpModel.getPageUtil().setAndCondition("parentId IS NULL");
        tbcMenuHelpModel.getPageUtil().setOrderByCondition("sortNumber");
        try {
            modelMap.addAttribute("listTbcMenuHelpModel", tbcMenuHelpService.selectByChild(tbcMenuHelpModel));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping({"", "index"})
    public String index(ModelMap modelMap) {
        this.loadHead(modelMap);
        this.loadLeft(modelMap);
        return "index";
    }

    @RequestMapping("download.html")
    public String download() {
        System.out.println("download.html");
        return "download";
    }

    @RequestMapping("{dir}/")
    public String path(@PathVariable String dir, ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", dir + "/");
        System.out.println("{dir}/:" + dir);
        try {
            List<TbcMenuModel> listTbcMenuModel = this.tbcMenuService.selectByMap(map);
            if (listTbcMenuModel == null || listTbcMenuModel.size() == 0) {
                return "404";
            } else {
                map.clear();
                map.put("id", listTbcMenuModel.get(0).getId());
                List<TbcContentModel> listTbcContentModel = tbcContentService.selectByMap(map);
                modelMap.put("listTbcContentModel", listTbcContentModel);
                //通用加载
                this.loadHead(modelMap);
                this.loadLeft(modelMap);
                return "list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "500";
    }
}