package com.zm.mall.web.system;

import com.sun.deploy.net.HttpResponse;
import com.zm.mall.client.PageOfRegister;
import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.MoneyManagerService;
import com.zm.mall.client.service.system.RegisterService;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.system.Register;
import com.zm.mall.web.BaseController;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/system")
public class RegisterController extends BaseController{
    @Resource
    private RegisterService registerService;
    /**
     * 查询报销信息
     * @return
     * @throws Exception
     * /moneyManagerAction_list.action
     */
    @RequestMapping(value = "/register.action")

    public ModelAndView itemList(HttpServletRequest request) throws Exception {
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Map map = new HashMap();
            Register reg = new Register();
            List<Register> list = null;
            if(user.getRealName().equals("admin")){
                return new ModelAndView("/system/registerPageShow");
            }else{
                reg.setUserid(user.getId());
                //时间转字符串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                //拼的字符串转时间
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String  string = sdf.format(new Date());
                String sb1 = new String(string +" 00:00:01" );
                String sb2 = new String(string +" 23:59:59" );
    //            Date ss =sdf1.parse(sb1);
    //            Date sss =sdf1.parse(sb2);
//                reg.setFlagtime1(sdf1.parse(sb1));
//                reg.setFlagtime2(sdf1.parse(sb2));
                reg.setFlagtime1(sb1);
                reg.setFlagtime2(sb2);
                Register register =  registerService.selectRegister(reg);
                if(register==null ){
                    reg.setUsername(user.getRealName());
                    reg.setUserid(user.getId());
                    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    Date date =  new Date();
                    reg.setCreatetime(date);
                    if(date.getHours()>=8){
                        reg.setMstatus("早：迟到");
                    }else{
                        reg.setMstatus("正常");
                    }
                    registerService.insertRegister(reg);
                }else{
                    reg.setUserid(user.getId());
                    Date date =  new Date();
                    reg.setUpdatetime(date);
                    reg.setCreatetime(register.getCreatetime());
                    if((date.getHours()*100+date.getMinutes())>=1730 ){
                        reg.setEstatus("正常");
                    }else{
                        reg.setEstatus("晚：早退");
                    }
                    registerService.updateRegister(reg);
                }
                list = registerService.selectRegisterAll(reg);
                map.put("registers", list);
            return new ModelAndView("/system/registerShow",map);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    @RequestMapping(value = "/registerpage.action")
    @ResponseBody
    public PageOfRegister registerPage(Integer currentPage) throws Exception {
        try {
            PageOfRegister page = new PageOfRegister();
//            page.getProductInfo().setSaleStatus(saleStatus);
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            PageOfRegister resultPage = registerService.selectAllRegisterByPage(page);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            List<Register>list =resultPage.getRegisters();
            for(Register register:list){
                String create =df.format(register.getCreatetime());
                register.setCreatetimepage(create);
                if(register.getUpdatetime()!=null){
                    String update = df.format(register.getUpdatetime());
                    register.setUpdatetimepage(update);
                }
            }
            return resultPage;
        }catch (Exception e){
            throw e;
        }
    }
}