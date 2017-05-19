package com.zm.mall.web.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.product.SupplierInfoService;
import com.zm.mall.client.service.system.MenusService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@SessionAttributes({"userResult"})
@Controller
@RequestMapping("/privileges")
public class MenusController extends BaseController {
    @Resource
    MenusService menusService;
    @Resource
    private UserService userService;
    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private ProductInfoService productInfoService;
    /**
     * 跳转到商品销量排行模块页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toLogin.action")
    public ModelAndView toLogin(HttpServletRequest request){
        return new ModelAndView("system/login");
    }

    @RequestMapping(value = "/toExit.action")
    public ModelAndView toExit(HttpServletRequest request){
        request.getSession().invalidate();
        return new ModelAndView("system/login");
    }
    @RequestMapping(value = "/login.action")
    public ModelAndView login(UserVo userVo,HttpServletRequest request,HttpServletResponse response)throws  Exception{
        try{
            JSONObject jsonObject=new JSONObject();
            Map<String, Object> map = new HashMap<String, Object>();
            //首先判断APP请求还是web请求
            String name= request.getParameter("realName");
            String password=request.getParameter("password");
            if(name==null && password==null) {
                UserResult userResult = userService.login(userVo);
                if (userResult == null) {
                    map.put("error", "用户名输入有误");
                    return new ModelAndView("system/login", map);
                } else {
                    map.put("userResult", userResult);
                    return new ModelAndView("/index", map);
                }
            }else{
                userVo.setRealName(name);
                userVo.setPassword(password);
                UserResult userResult = userService.login(userVo);
                if (userResult == null) {
                    map.put("error", "用户名输入有误");
                    jsonObject.put("state", 0);
                    return new ModelAndView("system/login", map);
                } else {
                    map.put("userResult", userResult);
//            List<Privileges> topPrivilegeList = menusService.getTopPrivilege();
//            List<String> allPrivilegeUrls = menusService.getAllUrls();
//            request.getSession().setAttribute("userResult",userResult);
                    jsonObject.put("state", 1);
                    return new ModelAndView("/index", map);
                }
//                response.getWriter().write(jsonObject.toJSONString());
//                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }
    @RequestMapping(value = "/menu.action")
    public ModelAndView itemList() throws Exception {
        try {
            return new ModelAndView("/index");
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 修改权限跳转页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}/setp.action")
    public ModelAndView getTree(@PathVariable("id")Long id) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",id);
            return new ModelAndView("/system/updateRole",map);
        } catch (Exception e) {
            throw e;
        }
    }

   @RequestMapping(value = "/MenuList.action")
    public void systemMenuList(HttpServletResponse response,HttpServletRequest request) throws Exception {
        // ActionInvocation
       UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);

           /**
            * 权限放入servlet上下文对象
            */
           Privileges privileges =new Privileges();
           Set<Role> set = new HashSet(user.getRoles());
           Long id =null;
           for(Role role:set){
               id =  role.getId();
           }

           JSONArray array = new JSONArray();
           try {
               Role role = new Role();
               role.setId(id);
               role.setPluteformid(user.getPluteformid());
               List<Privileges> list = menusService.selectAllSystemCode(role);
               for (Privileges sc : list) {
                   if(!(sc.getId()!=null&&sc.getId()==53 ||  sc.getPid()!=null&&sc.getPid()==53)){//控制按钮显示
                       JSONObject jsonObject = new JSONObject();
                       jsonObject.put("id", sc.getId());
                       jsonObject.put("name", sc.getName());
                       jsonObject.put("pId", sc.getPid());
                       jsonObject.put("page", sc.getUrl());
                       array.add(jsonObject);
                   }
               }
               response.getWriter().write(array.toString());
           } catch (Exception e) {
               throw e;
           }
       }

    /**
     * 微信端开店铺登录界面
     * @param
     * @return
     */
    @RequestMapping(value = "/shopToLoginOne.action")
    public ModelAndView tLogin(){
        return new ModelAndView("/business/shop/login");
    }
    /**
     * 微信端开店铺预先登录填写店铺信息
     * @param
     * @return
     */
    @RequestMapping(value = "shopLoginOne.action")
    @ResponseBody
    public  ModelAndView shopLoginOne(UserVo userVo,HttpServletRequest request)throws  Exception{
        try{
            UserResult userResult = userService.login(userVo);
            if (userResult!=null){
                Map map=new HashMap();
                RegionInfoVo regionInfoVo = new RegionInfoVo();
                List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(regionInfoVo);
                map.put("regionInfos", regionInfos);
                map.put("brands", supplierInfoService.getAllBrands());
                request.getSession().setAttribute("userResult",userResult);
                return new ModelAndView("/business/shop/register",map);
            }else {
                return  null;
            }
        }catch (Exception e){
            throw e;
        }

    }
   }
