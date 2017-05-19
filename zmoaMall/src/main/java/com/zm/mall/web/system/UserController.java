package com.zm.mall.web.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.system.DepartmentResult;
import com.zm.mall.client.result.system.MallConfigResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.DepartmentService;
import com.zm.mall.client.service.system.MallConfigService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.client.vo.system.DepartmentVo;
import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.business.shop.PageableMallInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.userPage;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/13.
 */
@Controller
@RequestMapping("/system")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MallConfigService mallConfigService;
    /**
     *展示所有的用户
     * @return
     */
    @RequestMapping(value = "/show.action")
    public ModelAndView selectAllU(HttpServletRequest request, UserVo userVo){
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        userVo.setPluteformid(user.getPluteformid());
        if(userVo.getDepartment()==null){
            userVo.setDepartment(new Department());
        }
        Map<String,Object> map=new HashMap<String, Object>();
        List<UserResult> userResults=userService.select(userVo);
        List<DepartmentResult> departmentResults=departmentService.select(null);
        map.put("departmentResults",departmentResults);
        map.put("userList", userResults);
        return new ModelAndView("/system/userShow",map);
    }

    /**
     * 查询单个用户
     * @param ids
     * @return
     */
    @RequestMapping(value = "/getById.action")
    public ModelAndView getRoleById(HttpServletRequest request,String ids)throws  Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UserVo userVo = new UserVo();
            userVo.setPluteformid(user.getPluteformid());
            long i=Long.valueOf(ids.trim());
            userVo.setId(i);
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setPluteformid(user.getPluteformid());

            Map<String,Object> map=new HashMap<String, Object>();
            List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
            map.put("departmentResults",departmentResults);
            UserResult userResult=userService.selectOne(userVo);
            map.put("userOne", userResult);
            return new ModelAndView("/system/userOne",map);
        }catch (Exception e){
            throw e;
        }



    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/update.action")
    public ModelAndView updateUser(HttpServletRequest request,UserVo userVo)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userVo.setPluteformid(user.getPluteformid());
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setPluteformid(user.getPluteformid());
            Map<String,Object> map=new HashMap<String, Object>();
            List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
            userService.updateUser(userVo);
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @RequestMapping(value = "/updatePass.action")
    public ModelAndView updateP(HttpServletRequest request,String ids)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UserVo userVo = new UserVo();
            userVo.setPluteformid(user.getPluteformid());
            String []idArr = ids.split(",");
            if(idArr!=null){
                for(String id:idArr){
                    long i=Long.valueOf(id.trim());
                    userVo.setId(i);
                    userService.updatePass(userVo);
                }
            }
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 增加用户之前查询所有的部门
     * @param
     * @return
     */
    @RequestMapping(value = "/showDe.action")
    public ModelAndView showDe(HttpServletRequest request){
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        DepartmentVo departmentVo = new DepartmentVo();
        departmentVo.setPluteformid(user.getPluteformid());
        Map<String,Object> map=new HashMap<String, Object>();
        List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
        map.put("departmentResults",departmentResults);
        return new ModelAndView("system/addUser",map);
    }
//    /**
//     * 开店铺之前查询所有的部门
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "/openShop.action")
//    public ModelAndView openShop(HttpServletRequest request){
//        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
//        DepartmentVo departmentVo = new DepartmentVo();
//        departmentVo.setPluteformid(user.getPluteformid());
//        Map<String,Object> map=new HashMap<String, Object>();
//        List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
//        map.put("departmentResults",departmentResults);
//        return new ModelAndView("system/openShop",map);
//    }
//    /**
//     * 增加c端用户
//     */
//    @RequestMapping(value = "/addopenShop.action")
//    public ModelAndView addopenShop(HttpServletRequest request,UserVo userVo)throws Exception{
//        try{
//            userService.addOpenShop(userVo);
//            Map<String,Object> map=new HashMap<String, Object>();
//            if(userVo.getDepartment()==null){
//                userVo.setDepartment(new Department());
//            }
//            return selectAllUser(request,null,null,null);
//        }catch (Exception e){
//            throw e;
//        }
//    }
    /**
     * 增加用户
     */
    @RequestMapping(value = "/addUser.action")
    public ModelAndView insertUser(HttpServletRequest request,UserVo userVo)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userVo.setPluteformid("-2");
            userVo.setParentPluteformId(user.getPluteformid());
            userService.insertUser(userVo);
//            //判断填写的平台ID是否为空
//            if(pluteformid=="" && "".equals(pluteformid) || pluteformid==null){
//                userVo.setPluteformid(user.getPluteformid());
//                //判断当前用户是c端还是、b端用户
//                if(user.getStatusId()==null){
//                    userService.insertUser(userVo);
//                }else{
//                    userVo.setStatusId(user.getStatusId());
//                    userService.insertUser(userVo);
//                }
//            }else
//            //判断填写的平台ID与当前用户的平台ID是否相同
//            if(pluteformid==user.getPluteformid() && pluteformid.equals(user.getDescription())){
//                //判断当前用户是c端还是、b端用户
//                if(user.getStatusId()==null){
//                    userService.insertUser(userVo);
//                }else{
//                    userVo.setStatusId(user.getStatusId());
//                    userService.insertUser(userVo);
//                }
//            }else
//            //判断填写的平台ID与当前用户的平台ID是否不同
//            if(pluteformid!=user.getPluteformid() && !pluteformid.equals(user.getDescription())){
//                userService.addOpenShop(userVo);
//            }

            Map<String,Object> map=new HashMap<String, Object>();
            if(userVo.getDepartment()==null){
                userVo.setDepartment(new Department());
            }
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw e;
        }
    }

       /**
     * 根据微信端传来的openId判断后台端是否存在
     * @return
     */
//    String Pluteformid,String ParentPluteformId,String Status
    @RequestMapping(value ="/selectUserWX.action")
    @ResponseBody
        public Object selectShopUser(HttpServletRequest request)throws  Exception{
        try{
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        br.close();
        is.close();
       String userWX =sb.toString();
        isr.close();
        //首先判断该客户是否已经存在
        User uu= JSONObject.parseObject(userWX, User.class);
            uu.setParentPluteformId(uu.getPluteformid());
       User u=userService.selectWX(uu);
        if(u==null){
            return 0;
        }else {
            return 1;
        }
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信端客户的注册
     */
    @RequestMapping(value ="/insertUserWX.action")
    @ResponseBody
    public Object addShopUser(HttpServletRequest request)throws  Exception{
        try {
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        br.close();
        is.close();
        String userWX =sb.toString();
        isr.close();
        User u= JSONObject.parseObject(userWX, User.class);
        Integer ii= userService.insertUserWX(u);
        return ii;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     *用户手机号校验
     */
    @RequestMapping(value ="/checkPhoneNum.action")
    @ResponseBody
    public Object checkPhoneNum(HttpServletRequest request)throws  Exception{
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        br.close();
        is.close();
        String str =sb.toString();
        isr.close();
        UserVo userVo= JSONObject.parseObject(str, UserVo.class);
        Integer status= userService.checkPhoneNum(userVo);
        return status;
    }
    /**
     * 微信端商户信息的补全
     */
    @RequestMapping(value ="/openAddShopData.action")
    @ResponseBody
    public Object openAddShopData(HttpServletRequest request)throws  Exception{
        try{
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        br.close();
        is.close();
        String mallconfig =sb.toString();
        isr.close();
        MallConfigVo mallConfigVo= JSONObject.parseObject(mallconfig, MallConfigVo.class);
        UserResult userResult= userService.openAddShopData(mallConfigVo);
        return userResult;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据Appid查询Secretkey
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selSecretkeyByAppId.action")
    @ResponseBody
    public Object selSecretkeyByAppId(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String mallConfig =sb.toString();
            isr.close();
            MallConfigVo mallConfigVo= JSONObject.parseObject(mallConfig, MallConfigVo.class);
            MallConfigResult mallConfigResult = userService.selSecretkeyByAppId(mallConfigVo);
            return mallConfigResult;
        }catch (Exception e){
            throw e;
        }
    }



    /**
     * 用户的有效无效的一种状态设置
     * @param
     * @return
     */
    @RequestMapping(value = "/updateSte.action")
    public ModelAndView updateSte(HttpServletRequest request,String ids)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UserVo userVo = new UserVo();
            userVo.setPluteformid(user.getPluteformid());

            String []idArr = ids.split(",");
            if(idArr!=null){
                for(String id:idArr){
                    long i=Long.valueOf(id.trim());
                    userVo.setId(i);
                    userService.updateState(userVo);
                }
            }
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw e;
        }
    }
    @RequestMapping(value = "/addU.action")
    @ResponseBody
    public Object addRole(HttpServletRequest request,Long roleId){
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        DepartmentVo departmentVo = new DepartmentVo();
        //roleid暂时放这个对象里吧
        departmentVo.setId(roleId);
        departmentVo.setPluteformid(user.getPluteformid());
        List<Department> roleList=departmentService.getRole(departmentVo);
        return JSON.toJSON(roleList);
    }
    /**
     * 查询所有采购部门的员工
     */
    @RequestMapping(value="/selectPurchase.action")
      public    ModelAndView  selectP(HttpServletRequest request){
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        UserVo userVo = new UserVo();
        userVo.setPluteformid(user.getPluteformid());

        Map<String,Object> map=new HashMap<String, Object>();
       List<User> userResults= userService.selectPurchaser(userVo);
        map.put("userPurchaseResults",userResults);
        return new ModelAndView("/business/addPurchase",map);
    }
    /**
     * 分页显示所有的用户
     */
    @RequestMapping(value = "/shows.action")
    public ModelAndView selectAllUser(HttpServletRequest request,Integer currentPage,String realName,Long dId)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userPage userPage=new userPage();
            userPage.setPluteformid(user.getPluteformid());
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setPluteformid(user.getPluteformid());

            Department department=new Department();
            if(realName!=null ){
                userPage.setRealName(realName.trim());
            }if(dId!=null){
                department.setId(dId);
                userPage.setDepartment(department);
                userPage.setdId(dId);
            }
            if (currentPage==null){
                currentPage = 1;
            }
            Integer pageSize = 10;
            userPage.setPageSize(pageSize);
            userPage.setCurrentPage(currentPage);
            Integer count=userService.userCountAll(userPage);
            Integer totalPage = 0;
            if (count==0){
                totalPage =1;
            }else {
                totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
            }
            Integer beginNumber = (currentPage-1)*pageSize;
            userPage.setBeginNumber(beginNumber);
            userPage.setCurrentPage(currentPage);
            List<User> userList=userService.getUsers(userPage);
            List<DepartmentResult> departmentResults=departmentService.select(departmentVo);
            ModelAndView mv = new ModelAndView("/system/userShow");
            mv.addObject("userList",userList);
            mv.addObject("currentPage",currentPage);
            mv.addObject("totalPage",totalPage);
            mv.addObject("realName",realName);
            mv.addObject("dId",dId);
            mv.addObject("departmentResults", departmentResults);
            return mv;
        }catch (Exception e){
            throw e;
        }
    }


    @RequestMapping(value = "/updateShopState.action")
    public ModelAndView updateShopState(HttpServletRequest request,String ids) throws Exception{
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UserVo userVo = new UserVo();
            userVo.setPluteformid(user.getPluteformid());
            long id=Long.valueOf(ids.trim());
            userVo.setId(id);
            userService.updateShopState(userVo);
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 跳转修改密码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePa.action")
    public ModelAndView updatePa(HttpServletRequest request)throws Exception{
        try{
            ModelAndView mv=new ModelAndView("/system/updatePass");
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            String passWord=user.getPassword();
            Long id=user.getId();
            String userName=user.getRealName();
            mv.addObject("passWord",passWord);
            mv.addObject("userId",id);
            mv.addObject("userName",userName);
            return mv;
        }catch (Exception e){
            throw  e;
        }
    }
    /**
     * 修改密码
     * @param userVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/updatePassWord.action")
    public ModelAndView updatePassWord(HttpServletRequest request,UserVo userVo)throws Exception{
        try{
            userService.updatePassWord(userVo);
            return selectAllUser(request,null,null,null);
        }catch (Exception e){
            throw  e;
        }
    }
    @RequestMapping(value = "/getAllMallConfig.action")
    @ResponseBody
    public EasyUIListResult<MallConfigVo> getAllMallConfig(PageableMallInfo mallInfo,HttpSession session,HttpServletRequest request){
        UserResult userResult=(UserResult) session.getAttribute("userResult");
        if (userResult==null){
            return null;
        }
        mallInfo.setPid(userResult.getPluteformid());
        try{
            return mallConfigService.getAllMallInfo(mallInfo);
        }catch (Exception e){
            e.printStackTrace();
            return  new EasyUIListResult<MallConfigVo>();
        }
    }
    @RequestMapping(value ="AllMallConfig.action")
    public String AllMallConfig(HttpSession session){
        UserResult userResult=(UserResult)session.getAttribute("userResult");
        if(userResult==null){
            return null;
        }
        return "business/mallInfoList";
    }
    @RequestMapping(value = "/checkShopDlg")
    public String getMallInfo(Long id,Model model)
    {
        MallConfigVo mallConfigVo=mallConfigService.getMallInfo(id);
        model.addAttribute("mall",mallConfigVo);
        return "business/checkMallDlg";
    }
    @RequestMapping(value = "checkShop")
    @ResponseBody
    public Integer checkShop(MallConfigVo mall){
        return mallConfigService.updateStatus(mall);
    }
}
