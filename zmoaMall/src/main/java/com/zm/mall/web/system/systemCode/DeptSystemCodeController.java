package com.zm.mall.web.system.systemCode;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.domain.system.systemCode.DeptSystemCode;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.service.system.systemCode.DeptSystemCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * 部门编码
 * Created by Bochao on 2017/2/23.
 */
@Controller
@RequestMapping("/deptSystemCode")
public class DeptSystemCodeController {
    @Resource
    private DeptSystemCodeService deptSystemCodeService;

    /**
     * 部门编码列表页面跳转
     * @param model
     * @param fid
     * @return
     */
    @RequestMapping("/deptCodeListPage")
    public String deptCodeListPage(Model model, Long fid) {
        model.addAttribute("parentId", fid);
        return "system/systemCode/deptSystemCode";
    }

    /**
     * 按给定的部门父id获取部门编码列表json
     * {"id":"1","parentId":"0","state":"closed"}
     * @param request
     * @return
     */
    @RequestMapping("/getDeptCodeListByDeptParentId")
    @ResponseBody
    public List<DeptSystemCode> getDeptCodeListByDeptParentId(
            HttpServletRequest request, @RequestParam(value="id", required=false) Long deptParentId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        List<DeptSystemCode> deptCodeList = deptSystemCodeService.getDeptCodeListByDeptParentId(
                userResult.getPluteformid(), deptParentId);
        return deptCodeList;
    }

    /**
     *
     * @param deptSystemCode
     * @return
     */
    @RequestMapping("/updateDeptCode")
    @ResponseBody
    public EasyUIResult updateDeptCode(DeptSystemCode deptSystemCode) {
        try {
            int i = deptSystemCodeService.updateDeptCode(deptSystemCode);
            if (i == 0) {
                return new EasyUIResult("更新失败");
            }
            return new EasyUIResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult("发生异常，出错信息：" + e.getMessage());
        }
    }


}
