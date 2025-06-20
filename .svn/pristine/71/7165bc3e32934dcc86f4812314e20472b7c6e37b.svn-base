package com.bonus.ma.controller;

import com.bonus.ma.dao.MaTotalChangeWarnDao;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.ma.beans.ChangeWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.ma.controller
 * @CreateTime: 2024-12-30  10:39
 * @Description: 物资保有量变化预警控制器
 */
@Controller
@RequestMapping("/backstage/maTotalChangeWarn/")
public class MaTotalChangeWarnController extends BaseController<Object> {

    @Autowired
    private MaTotalChangeWarnDao maTotalChangeWarnDao;

    @RequestMapping("list")
    public String index(Model model) {
        return "/ma/maTotalChangeWarning";
    }

    @RequestMapping(value = "getChangeWarnList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes getChangeWarnList() {
        AjaxRes ar = getAjaxRes();
        // 创建模拟数据
        // List<ChangeWarning> warnings = new ArrayList<>();
        String defaultAvatar = "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png";

        List<ChangeWarning> changeWarnList = maTotalChangeWarnDao.getChangeWarnList(null);
        changeWarnList.forEach(item -> item.setUserAvatar(defaultAvatar));

        // 添加模拟数据
//        warnings.add(createWarning(1L, "变更警告 #1", "DEV-001", "设备参数异常变更",
//                "2024-03-20 10:30", "张三", "库管员", defaultAvatar));
//        warnings.add(createWarning(2L, "变更警告 #2", "DEV-001", "设备参数异常变更FFFFFF",
//                "2024-03-20 11:45", "李四", "维修员", defaultAvatar));
//        warnings.add(createWarning(3L, "变更警告 #3", "DEV-001", "设备参数异常变更xx",
//                "2024-03-19 14:20", "王五", "检验员", defaultAvatar));
//        warnings.add(createWarning(4L, "变更警告 #4", "DEV-004", "设备参数异常变更xx",
//                "2024-03-19 14:20", "孙六", "检验员", defaultAvatar));
//        warnings.add(createWarning(5L, "变更警告 #5", "DEV-005", "设备参数异常变更xx",
//                "2024-03-18 15:20", "刘七", "试验员", defaultAvatar));

        ar.setSucceed(changeWarnList);
        return ar;
    }

    private ChangeWarning createWarning(Long id, String title, String deviceCode,
                                        String warningContent, String createTime, String userName, String userRole, String userAvatar) {
        ChangeWarning warning = new ChangeWarning();
        warning.setId(id);
        warning.setTitle(title);
        warning.setDeviceCode(deviceCode);
        warning.setWarningContent(warningContent);
        warning.setCreateTime(createTime);
        warning.setUserName(userName);
        warning.setUserRole(userRole);
        warning.setUserAvatar(userAvatar);
        return warning;
    }

    private Date parseDate(String dateStr) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }

}
