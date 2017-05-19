package com.zm.mall.web.system;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.ItemSystemCodeService;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.system.ItemSystemCode;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/itemSystemCode")
public class ItemSystemCodeController extends BaseController {
    @Resource
    ItemSystemCodeService itemSystemCodeService;


    /**
     * 跳转到商品销量排行模块页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/itemList.action")
    public ModelAndView itemList() throws Exception {
        return new ModelAndView("/system/aa");
    }
    @RequestMapping(value = "/itemListLimit.action")
    public ModelAndView itemListLimit(HttpServletRequest request ,HttpServletResponse response) throws Exception {
        Integer fid = new Integer(request.getParameter("fid"));
        Integer currentPage= new Integer(request.getParameter("currentPage"));
        String code =request.getParameter("code");
        String name =request.getParameter("name");
        //Integer fida = new Integer(fid);
        //Integer currentPages =new Integer(currentPage);
        if (currentPage==null){
            currentPage=1;
        }
        try {
            Map map = new HashMap();
            Page page = new Page();
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            page.getItemSystemCode().setPluteformid(user.getPluteformid());
            page.getItemSystemCode().setFid(fid);
            page.getItemSystemCode().setCode(code);
            page.getItemSystemCode().setName(name);
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Page resultPage = itemSystemCodeService.selectProductInfo(page);
            //request.setAttribute("fid",fid);
            map.put("resultPage",resultPage);
            map.put("code",code);
            map.put("name",name);
            map.put("fid",fid);
            map.put("currentPage",currentPage);
            return new ModelAndView("/system/ItemSystemCode",map);
        }catch (Exception e){
            throw e;
        }

    }

    @RequestMapping(value = "/addItemSystemCode.action")
    public ModelAndView addItemSystemCode(HttpServletResponse response ,HttpServletRequest request) throws Exception {
        Integer fid = new Integer(request.getParameter("fid"));
        Integer currentPage = new Integer(request.getParameter("currentPage"));
        String code =request.getParameter("code");
        String name =request.getParameter("name");
        String description=request.getParameter("description");
        Double value =new Double( request.getParameter("value"));

        ItemSystemCode itemSystemCode=new ItemSystemCode();
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        itemSystemCode.setPluteformid(user.getPluteformid());

        itemSystemCode.setFid(fid);
        itemSystemCode.setCode(code);
        itemSystemCode.setName(name);
        itemSystemCode.setDescription(description);
        itemSystemCode.setValue(value);
        itemSystemCodeService.addItemSystemCode(itemSystemCode);


        return  new ModelAndView("redirect:/itemSystemCode/itemListLimit.action?fid="+fid+"&currentPage="+currentPage);

    }
    @RequestMapping(value = "/updateItemSystemCode.action")
    public ModelAndView updateItemSystemCode(HttpServletResponse response ,HttpServletRequest request) throws Exception {
        Integer id = new Integer(request.getParameter("id"));
        Integer fid = new Integer(request.getParameter("fid"));
        Integer currentPage = new Integer(request.getParameter("currentPage"));


        String code = request.getParameter("code");
        String name =request.getParameter("name");
        String description=request.getParameter("description");
        Double value =new Double( request.getParameter("value"));

        ItemSystemCode itemSystemCode=new ItemSystemCode();
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        itemSystemCode.setPluteformid(user.getPluteformid());

        itemSystemCode.setId(id);
        itemSystemCode.setCode(code);
        itemSystemCode.setName(name);
        itemSystemCode.setDescription(description);
        itemSystemCode.setValue(value);
        itemSystemCodeService.updateItemSystemCode(itemSystemCode);

        return  new ModelAndView("redirect:/itemSystemCode/itemListLimit.action?fid="+fid+"&currentPage="+currentPage);

    }


   }