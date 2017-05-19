package com.zm.mall.web.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.system.SystemCodeResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.SystemCodeService;
import com.zm.mall.client.vo.system.SystemCodeVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.system.SystemCode;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/systemCode")
public class SystemCodeController extends BaseController {
    @Resource
    SystemCodeService systemCodeService;



    /**
     * 跳转到商品销量排行模块页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menu.action")
    public ModelAndView itemList(HttpServletRequest request) throws Exception {

        try {
            Map map = new HashMap();
            SystemCodeVo systemCodeVo = new SystemCodeVo();
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            systemCodeVo.setPluteformid(user.getPluteformid());
            systemCodeVo.setId(1);

            SystemCodeResult systemCodeResult =systemCodeService.selectSystemCode(systemCodeVo);
            map.put("system",systemCodeResult);
            //return new ModelAndView("/common/right", map);
            return new ModelAndView("/system/menu", map);
        } catch (Exception e) {
            throw e;
        }
    }

   @RequestMapping(value = "/MenuList.action")
    public void systemMenuList(HttpServletRequest request,HttpServletResponse response) throws Exception {

           SystemCode systemCode = new SystemCode();
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
           systemCode.setPluteformid(user.getPluteformid());
           JSONArray array = new JSONArray();
           try {
               List<SystemCode> list = systemCodeService.selectAllSystemCode(systemCode);
               for (SystemCode sc : list) {
                   JSONObject jsonObject = new JSONObject();
                   jsonObject.put("id", sc.getId());
                   jsonObject.put("name", sc.getName());
                   jsonObject.put("pId", sc.getPid());
                   jsonObject.put("code",sc.getCode());
                   jsonObject.put("url",sc.getUrl());
                   jsonObject.put("description",sc.getDescription());
                   jsonObject.put("page", sc.getPage());
                   jsonObject.put("open", "true");
                   array.add(jsonObject);
               }
               response.getWriter().write(array.toString());

           } catch (Exception e) {
               throw e;
           }


       }

    @RequestMapping(value = "/DeleteMenu.action")
    public void DeleteMenu(HttpServletResponse response ,HttpServletRequest request) throws Exception {
        SystemCode systemCode = new SystemCode();
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        systemCode.setPluteformid(user.getPluteformid());

        String ids =request.getParameter("id");
        int id = new Integer(ids);
        systemCode.setId(id);
        try {
            systemCodeService.deleteMenu(systemCode);

        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/UpdateMenu.action")
    public void UpdateMenu(HttpServletResponse response ,HttpServletRequest request) throws Exception {
        SystemCode systemCode = new SystemCode();
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        systemCode.setPluteformid(user.getPluteformid());

        String ids =request.getParameter("id");
        int id = new Integer(ids);
        String name =request.getParameter("name");
        name = URLDecoder.decode(name, "utf-8");
        String code =request.getParameter("code");
        code = URLDecoder.decode(code, "utf-8");
        systemCode.setId(id);
        systemCode.setCode(code);
        systemCode.setName(name);
        try {
            systemCodeService.updateMenu(systemCode);

        } catch (Exception e) {
            throw e;
        }
    }


    @RequestMapping(value = "/AddMenu.action")
    public void AddMenu(HttpServletResponse response ,HttpServletRequest request) throws Exception {
        SystemCode systemCode = new SystemCode();
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        systemCode.setPluteformid(user.getPluteformid());
        
        String ids =request.getParameter("pId");
        int pid = new Integer(ids);
        String name =request.getParameter("name");
        name = URLDecoder.decode(name, "utf-8");
        String code =request.getParameter("code");
        code = URLDecoder.decode(code, "utf-8");

        systemCode.setPid(pid);
        systemCode.setName(name);
        systemCode.setCode(code);
        try {
            systemCodeService.addMenu(systemCode);

        } catch (Exception e) {
            throw e;
        }


    }


   }