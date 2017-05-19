package com.zm.mall.web.system;

import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.MoneyManagerService;
import com.zm.mall.client.vo.system.MoneyItemVo;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.Register;
import com.zm.mall.domain.system.Role;
import com.zm.mall.domain.system.User;
import com.zm.mall.web.BaseController;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/system")
public class MoneyManagerController extends BaseController{
    @Resource
    private MoneyManagerService moneyManagerService;
    /**
     * 员工查看自己报销信息，领导查看审批的报销信息
     * @return
     * @throws Exception
     * /moneyManagerAction_list.action
     */

    @RequestMapping(value = "/moneyManagerAction_list.action")

    public ModelAndView itemList(MoneyManagerVo moneyManager,HttpServletRequest request) throws Exception {
        try {


            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Map map = new HashMap();
            moneyManager.setRealName(user.getRealName());
            moneyManager.setRoles(user.getRoles());
            //设置用户所在部门的编号
            moneyManager.setDepartmentName(user.getDepartment().getName());

            Set<Role> role = moneyManager.getRoles();
            for(Role ro:role){
                if (ro.getId() >= 3 && ro.getId() <= 10) {
                    //设置工作流中任务的名字
                    moneyManager.setRealName("部门经理");
                    moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
                    //总条数
                    Integer pageCount = moneyManagerService.selectAllCheckCount(moneyManager);
                    //总页数
                    moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);
                    //查询部门审核
                    List <MoneyManagerResult> list =  moneyManagerService.selectCheckByContion(moneyManager);
                    int currentPage = moneyManager.getCurrentPage();
                    int totalPages = moneyManager.getTotalPages();
                    map.put("currentPage", currentPage);
                    map.put("totalPages", totalPages);
                    map.put("userrole", role);
                    map.put("moneyManager", list);
                    return new ModelAndView("/system/moneyManager",map);
                }else if(ro.getId() >= 1 && ro.getId() <= 2){
                    //设置工作流中任务的名字
                    moneyManager.setRealName("总经理");
                    moneyManager.setDepartmentName("");
                    moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
                    //总条数
                    Integer pageCount = moneyManagerService.selectAllCheckCount(moneyManager);
                    //总页数
                    moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);

                    //查询审核
                    List <MoneyManagerResult> list =  moneyManagerService.selectCheckByContion(moneyManager);
                    int currentPage = moneyManager.getCurrentPage();
                    int totalPages = moneyManager.getTotalPages();
                    map.put("currentPage", currentPage);
                    map.put("totalPages", totalPages);
                    map.put("userrole", role);
                    map.put("moneyManager", list);
                    return new ModelAndView("/system/moneyManager",map);
                }else{
                    moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
                    //总条数
                    Integer pageCount = moneyManagerService.selectAllMoneyCount(moneyManager);
                    //总页数
                    moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);

                    //查询自己的审核单
                    List <MoneyManagerResult> list =  moneyManagerService.selectMoneyByContion(moneyManager);
                    int currentPage = moneyManager.getCurrentPage();
                    int totalPages = moneyManager.getTotalPages();
                    map.put("currentPage", currentPage);
                    map.put("totalPages", totalPages);
                    map.put("userrole", role);
                    map.put("moneyManager", list);
                    return new ModelAndView("/system/moneyManager",map);
                }
            }



//            List <MoneyManagerResult> list =  moneyManagerService.selectMoneyManager(moneyManager);
//            int currentPage = moneyManager.getCurrentPage();
//            map.put("currentPage", currentPage);
//            map.put("userrole", role);
//            map.put("moneyManager", list);
//            return new ModelAndView("/system/moneyManager",map);
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 条件查询
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/moneyManagerAction_lists.action")
    public ModelAndView itemListSelect(MoneyManagerVo moneyManager,HttpServletRequest request) throws Exception {
        try {


            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Map map = new HashMap();

            moneyManager.setRoles(user.getRoles());
            Set<Role> role = moneyManager.getRoles();
            for(Role ro:role) {
                if (ro.getId() > 10) {
                    moneyManager.setRealName(user.getRealName());//用户根据姓名查
                }else if(ro.getId()>=1 && ro.getId()<=2 ){
//                    moneyManager.setDepartmentName("");//总经理查所有
                }else if(ro.getId()>=3 && ro.getId()<=10 ){
                    moneyManager.setDepartmentName(user.getDepartment().getName());//部门经理根据部门名字查
                }
            }
            moneyManager.setBeginNumber((moneyManager.getCurrentPage() - 1) * moneyManager.getPageSize());
            //总条数
            Integer pageCount = moneyManagerService.selectAllMoneyCount(moneyManager);
            //总页数
            moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize() + 1);

            //查询自己的审核单
            List<MoneyManagerResult> list = moneyManagerService.selectMoneyByContion(moneyManager);
            int currentPage = moneyManager.getCurrentPage();
            int totalPages = moneyManager.getTotalPages();

            map.put("curName", moneyManager.getName());
            map.put("curdepartmentName", moneyManager.getDepartmentName());
            map.put("curmoneyType", moneyManager.getMoneyType());
            map.put("curstatusCode", moneyManager.getStatusCode());

            map.put("currentPage", currentPage);
            map.put("totalPages", totalPages);
            map.put("userrole", role);
            map.put("moneyManager", list);
            return new ModelAndView("/system/moneySelect", map);


//            List <MoneyManagerResult> list =  moneyManagerService.selectMoneyManager(moneyManager);
//            int currentPage = moneyManager.getCurrentPage();
//            map.put("currentPage", currentPage);
//            map.put("userrole", role);
//            map.put("moneyManager", list);
//            return new ModelAndView("/system/moneyManager",map);
        } catch (Exception e) {
            throw e;
        }
    }
    @RequestMapping(value = "/moneyManagerAction_show.action")
    public ModelAndView moneyShow(Long id) throws Exception {
        try {

            Map map = new HashMap();
            List<MoneyItemResult>moneyManagerResults = moneyManagerService.selectMoneyItem(id);
            map.put("moneyItem",moneyManagerResults);
//            private Long id ;
//            private String moneyName;
//            private String code;
//            private String description;
//            private  Long moneyManageId;
//            private Long money;

//            MoneyManagerVo moneyManagerVo = new MoneyManagerVo();
//            moneyManagerVo.setName("河北兆美");
//            List <MoneyManagerResult> list =  moneyManagerService.selectMoneyManager(moneyManagerVo);
//            map.put("moneyManager",list);
            return new ModelAndView("/system/moneyShow",map);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 逻辑删除报销单
     * @param ids
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/moneyManager_delete.action")
    public ModelAndView moneyManagerDelete(String ids,HttpServletRequest request) throws Exception {
        try {
            String [] moneyids = ids.split(",");
            for(int i=0;i<moneyids.length;i++){
                moneyManagerService.deleteMoneyManager(moneyids[i]);
            }
            MoneyManagerVo moneyManagerVo = new MoneyManagerVo();
            return this.itemList(moneyManagerVo,request);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 点击修改报销信息，根据id查询数据
     * @param ids
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/moneyManager_update.action")
    public ModelAndView moneyManagerUpdate(String ids) throws Exception {
        try {
            Map map = new HashMap();
            Long id = Long.parseLong(ids);
            MoneyManagerVo moneyManagerVo = new MoneyManagerVo();
            moneyManagerVo.setId(id);
            List <MoneyManagerResult> list =  moneyManagerService.selectMoneyManagerById(moneyManagerVo);
            map.put("oneMoney", list);
            return new ModelAndView("/system/moneyShow",map);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 审查报销信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/moneyManager_check.action")
    public ModelAndView moneyManagerCheck(String ids) throws Exception {
        try {
            Map map = new HashMap();
            Long id = Long.parseLong(ids);

            MoneyManagerVo moneyManagerVo = new MoneyManagerVo();
            moneyManagerVo.setId(id);
            List<MoneyManagerResult> list = moneyManagerService.selectMoneyManagerById(moneyManagerVo);
            map.put("oneCheckMoney", list);
            return new ModelAndView("/system/moneyCheck", map);

        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 审核意见
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/moneyManager_checked.action")
    public ModelAndView moneyManagerChecked(MoneyManagerVo moneyManagerVo,HttpServletRequest request) throws Exception {
        try {
            //获取session

            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            Set<Role> role = user.getRoles();
            Map map = new HashMap();
            //编码格转换
            request.setCharacterEncoding("utf-8");
            String leaderCheckConment=moneyManagerVo.getLeaderCheckConment() ;
            String leaderStatusCode=moneyManagerVo.getLeaderStatusCode();

            System.out.println(moneyManagerVo.getId());
//            System.out.println(moneyManagerVo.getStatusName());

            //循环Roles
            for(Role ro:role){
                if(ro.getId() >= 1 && ro.getId() <= 2) {
                    moneyManagerVo.setLeaderCheckConment(leaderCheckConment);
                    moneyManagerVo.setLeaderStatusCode("经理" + leaderStatusCode);
                    moneyManagerVo.setStatusCode(moneyManagerVo.getLeaderStatusCode());
                    System.out.println(moneyManagerVo.getStatusCode());
                    moneyManagerService.updateMoneyLeaCheck(moneyManagerVo);
                }else if(ro.getId() >= 3 && ro.getId() <= 10){
                    moneyManagerVo.setDpartCheckConment(leaderCheckConment);
                    moneyManagerVo.setDeparmentStatusCode("部门"+leaderStatusCode);
                    moneyManagerVo.setStatusCode(moneyManagerVo.getDeparmentStatusCode());

                    moneyManagerVo.setLeaderCheckConment("");
                    moneyManagerVo.setLeaderStatusCode("");
                    moneyManagerService.updateMoneyDepCheck(moneyManagerVo);
                }
            }

            //审核保存，查询所有
            MoneyManagerVo moneyManagerVos = new MoneyManagerVo();
            return this.itemList(moneyManagerVos,request);

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     *报销单修改
     */
    @RequestMapping(value = "/moneyManager_updates.action")
    public ModelAndView moneyManagerUpdates(MoneyManagerVo moneyManagerVo,HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        try {
            Map map = new HashMap();
            request.setCharacterEncoding("utf-8");
            System.out.println(moneyManagerVo.getDescription());

            String itemMoneyName[]=request.getParameterValues("itemMoneyName");
            String itemDescription[]=request.getParameterValues("itemDescription");
            String itemMoney[]=request.getParameterValues("itemMoney");

            //数组合并
            String[] both = (String[]) ArrayUtils.addAll(itemMoneyName, itemDescription);
            String[] third = (String[]) ArrayUtils.addAll(both, itemMoney);
            Set<MoneyItemVo>items = new HashSet<MoneyItemVo>();
            //循环给item对象绑值
            for(int i=0;i<third.length/3;i++){
                MoneyItemVo moneyItemVo = new MoneyItemVo();
                moneyItemVo.setMoneyManageId(moneyManagerVo.getId());
                moneyItemVo.setMoneyName(third[i]);
                moneyItemVo.setDescription(third[i+(third.length/3)]);

                String str1 = (third[i + (third.length - third.length / 3)]);
                BigDecimal moneys=new BigDecimal(str1);
                moneyItemVo.setMoney(moneys);

                items.add(moneyItemVo);
            }
            moneyManagerVo.setItemSet(items);

            //获取session
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            //文件上传
            Calendar cal = Calendar.getInstance();
            String time =  "Upload"+File.separator+"Money"+File.separator+cal.get(Calendar.YEAR)+File.separator+(cal.get(Calendar.MONTH) + 1)+"月"+File.separator+user.getRealName()+File.separator+cal.get(Calendar.HOUR_OF_DAY)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND);
            String path = request.getSession().getServletContext().getRealPath(time);
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path, fileName);

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            if(fileName==""){
                moneyManagerVo.setFileURL("");
            }else{
                moneyManagerVo.setFileURL(time+File.separator+fileName);
            }
            int flag = moneyManagerService.updateMoneyManager(moneyManagerVo);
            MoneyManagerVo moneyManagerVos = new MoneyManagerVo();
            return itemList(moneyManagerVos, request);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 点击新增,跳转页面用
     */
    @RequestMapping(value = "/moneyManager_null.action")
    public ModelAndView moneyManagerInsertNull(HttpServletRequest request) throws Exception {
        try {
            Map map = new HashMap();
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            String username =  user.getRealName();
            String departmentName = user.getDepartment().getName();
            map.put("departmentName",departmentName);
            map.put("username",username);
            return new ModelAndView("/system/insertMoney",map);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 新增报销单
     */
    @RequestMapping(value = "/moneyManager_insert.action")
    public ModelAndView moneyManagerInsert(MoneyManagerVo moneyManagerVo,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) throws Exception {
        try {
            Map map = new HashMap();
            request.setCharacterEncoding("utf-8");
            String itemMoneyName[]=request.getParameterValues("itemMoneyName");
            String itemDescription[]=request.getParameterValues("itemDescription");
            String itemMoney[]=request.getParameterValues("itemMoney");

            //数组合并
            String[] both = (String[]) ArrayUtils.addAll(itemMoneyName, itemDescription);
            String[] third = (String[]) ArrayUtils.addAll(both, itemMoney);
            Set<MoneyItemVo>items = new HashSet<MoneyItemVo>();
            //循环给item对象绑值
            for(int i=0;i<third.length/3;i++){
                MoneyItemVo moneyItemVo = new MoneyItemVo();
                moneyItemVo.setMoneyManageId(moneyManagerVo.getId());
                moneyItemVo.setMoneyName(third[i]);
                moneyItemVo.setDescription(third[i+(third.length/3)]);

                String str1 = (third[i + (third.length - third.length / 3)]);
                BigDecimal moneys=new BigDecimal(str1);
                moneyItemVo.setMoney(moneys);

                items.add(moneyItemVo);
            }
            moneyManagerVo.setItemSet(items);

            //获取session
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            //文件上传
            Calendar cal = Calendar.getInstance();
            String time =  "Upload"+File.separator+"Money"+File.separator+cal.get(Calendar.YEAR)+File.separator+(cal.get(Calendar.MONTH) + 1)+"月"+File.separator+user.getRealName()+File.separator+cal.get(Calendar.HOUR_OF_DAY)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND);
            String path = request.getSession().getServletContext().getRealPath(time);
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path, fileName);

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            //保存路径
            if(fileName==""){
                moneyManagerVo.setFileURL("");
            }else{
                moneyManagerVo.setFileURL(time+File.separator+fileName);
            }
            int flag = moneyManagerService.insertMoneyManager(moneyManagerVo);
            MoneyManagerVo moneyManagerVos = new MoneyManagerVo();
            return itemList(moneyManagerVos, request);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/moneyManager_download.action")
    public ModelAndView moneyManagerImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String fileName = request.getParameter("fileName");
            String name = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
            String path = request.getSession().getServletContext().getRealPath(fileName);
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(name, "UTF-8") + "\"");
            InputStream is = new FileInputStream(path);
            byte[] buff = new byte[is.available()];
            is.read(buff);
            OutputStream os = response.getOutputStream();
            os.write(buff);
            os.flush();
            os.close();
            is.close();
            return  null;
        } catch (Exception e) {
            throw e;
        }
    }

}