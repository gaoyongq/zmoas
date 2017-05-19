package com.zm.mall.web.system;

import com.zm.mall.client.result.system.PrivilegeResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.PrivilegeService;
import com.zm.mall.client.vo.system.DepartmentVo;
import com.zm.mall.client.vo.system.PrivilegeVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
    @Resource
    private PrivilegeService privilegeService;
    @RequestMapping(value = "/show.action")
    public ModelAndView getAllPrivilege(HttpServletRequest request) throws Exception {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            PrivilegeVo privilegeVo = new PrivilegeVo();
            privilegeVo.setPluteformid(user.getPluteformid());

            Map<String,Object> map=new HashMap<String, Object>();
            List<PrivilegeResult> privilegeResults=privilegeService.select(privilegeVo);
            map.put("privilegeResults",privilegeResults);
            return new ModelAndView("system/privileges", map);
        }catch (Exception e){
            throw e;
        }
    }
    @RequestMapping(value = "toAddPage.action")
    public ModelAndView toAddPage(HttpServletRequest request) throws Exception {
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        PrivilegeVo privilegeVo = new PrivilegeVo();
        privilegeVo.setPluteformid(user.getPluteformid());

        Map<String,Object> map=new HashMap<String, Object>();
        List<PrivilegeResult> privilegeResults=privilegeService.select(privilegeVo);
        map.put("privilegeResults",privilegeResults);
        return new ModelAndView("system/addPrivilege", map);
    }

    @RequestMapping(value = "add.action")
    public ModelAndView addPrivilege(HttpServletRequest request,PrivilegeVo privilegeVo) throws Exception {
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        privilegeVo.setPluteformid(user.getPluteformid());

        privilegeService.add(privilegeVo);
        return getAllPrivilege(request);
    }

    @RequestMapping(value = "/{id}/getById.action")
    public ModelAndView getPrivilege(HttpServletRequest request,@PathVariable("id")Long id) throws Exception {
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        PrivilegeVo privilegeVo = new PrivilegeVo();
        privilegeVo.setPluteformid(user.getPluteformid());
        privilegeVo.setId(id);
        Map<String,Object> map=new HashMap<String, Object>();
        PrivilegeResult privilegeResult=privilegeService.selectOne(privilegeVo);
        List<PrivilegeResult> privilegeResults=privilegeService.select(privilegeVo);
        map.put("privilegeResult",privilegeResult);
        map.put("privilegeResults",privilegeResults);
        return new ModelAndView("system/privilege", map);
    }

    @RequestMapping(value = "/update.action")
    public ModelAndView update(HttpServletRequest request,PrivilegeVo privilegeVo) throws Exception {
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        privilegeVo.setPluteformid(user.getPluteformid());

        privilegeService.update(privilegeVo);
        return getAllPrivilege(request);
    }
    @RequestMapping(value = "/{id}/delete.action")
    public ModelAndView delete(HttpServletRequest request,@PathVariable("id")Long id) throws Exception {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            PrivilegeVo privilegeVo = new PrivilegeVo();
            privilegeVo.setPluteformid(user.getPluteformid());
            privilegeVo.setId(id);

            privilegeService.delete(privilegeVo);
            return getAllPrivilege(request);
        }catch (Exception e){
            throw e;
        }
    }
}

