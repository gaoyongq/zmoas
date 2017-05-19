package com.zm.mall.web.system;




import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.TruckManageService;
import com.zm.mall.domain.system.TruckManage;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/system")
public class TruckManageController extends BaseController{
    @Resource
    TruckManageService truckManageService;


    /**
     * 去车辆管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toSelectTruckManage.action")
    @Transactional
    public ModelAndView toSelectTruckManage() throws Exception {

        try {
            return new ModelAndView("/system/selectTruckManageList");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 车辆管理 分页查询
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectTruckManageList.action")
    @ResponseBody
    public Page selectTruckManageList(Integer currentPage,String number,String driver,String state) throws Exception {
        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            TruckManage truckManage =new TruckManage();
            if (number.trim()!=""){
                truckManage.setNumber(number);
            }
            if (driver.trim()!="") {
                truckManage.setDriver(driver);
            }
            if (state.trim()!=""){
                truckManage.setState(state);
            }
            Page resultPage = truckManageService.selectAllTruck(page,truckManage);

            return resultPage;

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 添加车辆
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addTruck.action")
    public ModelAndView addTruck(HttpServletRequest request) throws Exception {
        String number= request.getParameter("number");
        String capacity= request.getParameter("capacity");
        String life= request.getParameter("life");
        String driver= request.getParameter("driver");


        TruckManage truckManage =new TruckManage();
        truckManage.setNumber(number);
        truckManage.setCapacity(capacity);
        truckManage.setLife(life);
        truckManage.setDriver(driver);
        truckManage.setState("1");


        truckManageService.addTruck(truckManage);

        return  new ModelAndView("redirect:/system/toSelectTruckManage.action");

    }

    @RequestMapping(value = "/selectOneTruckManage.action")
    @ResponseBody
    public TruckManage selectOneTruckManage(Integer id) throws Exception {
        try {
            TruckManage resultTruckManage = truckManageService.selectOneTruckManage(id);

            return resultTruckManage;

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 更新车辆信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateTruck.action")
    public ModelAndView updateTruck(HttpServletRequest request) throws Exception {


        try {
            String capacity= request.getParameter("capacity");
            String number= request.getParameter("number");
            Integer id = new Integer(request.getParameter("id"));
            String life= request.getParameter("life");
            String driver= request.getParameter("driver");

            TruckManage truckManage =new TruckManage();
            truckManage.setId(id);
            truckManage.setNumber(number);
            truckManage.setCapacity(capacity);
            truckManage.setLife(life);
            truckManage.setDriver(driver);

            truckManageService.updateTruck(truckManage);

            return  new ModelAndView("redirect:/system/toSelectTruckManage.action");

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 删除车辆
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTruck.action")
    public ModelAndView deleteTruck(HttpServletRequest request) throws Exception {

        try {
            Integer id = new Integer(request.getParameter("id"));


            TruckManage truckManage =new TruckManage();
            truckManage.setId(id);
            truckManage.setState("0");


            truckManageService.deleteTruck(truckManage);

            return  new ModelAndView("redirect:/system/toSelectTruckManage.action");
        } catch (Exception e) {
            throw e;
        }


    }

    /**
     * 车辆排序
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/truckSort.action")
    public ModelAndView truckSort(HttpServletRequest request) throws Exception {

        try {
            String number = request.getParameter("number");
            //创建排号时间
            Date date = new Date();
            SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("MMddHHmmss");
            String time=simpleDateFormat.format(date).toString();
            Integer sortTime=new Integer(time);

            TruckManage truckManage =new TruckManage();
            truckManage.setNumber(number);
            truckManage.setState("2");
            truckManage.setSortTime(sortTime);
            truckManage.setRemark("");

            truckManageService.truckSort(truckManage);

            return  new ModelAndView("redirect:/system/toSelectTruckManage.action");
        } catch (Exception e) {
            throw e;
        }
    }
    @RequestMapping(value = "/truckLeave.action")
    public ModelAndView truckLeave(HttpServletRequest request) throws Exception {

        try {
            String number = request.getParameter("number");
            String remark = request.getParameter("remark");

            TruckManage truckManage =new TruckManage();
            truckManage.setNumber(number);
            truckManage.setState("3");
            if (remark.trim()!=""){
                truckManage.setRemark(remark);
            }

            truckManageService.truckLeave(truckManage);

            return  new ModelAndView("redirect:/system/toSelectTruckManage.action");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 跳转到商品销量排行模块页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/truckList.action")
    public ModelAndView itemList(HttpServletRequest request ,HttpServletResponse response) throws Exception {
        Integer currentPage= new Integer(request.getParameter("currentPage"));
        String number =request.getParameter("number");
        String capacity =request.getParameter("capacity");
        String state =request.getParameter("state");
        if (currentPage==null){
            currentPage=1;
        }
        try {
            Map map =new HashMap();
            Page page = new Page();
            page.getTruckManage().setNumber(number);
            page.getTruckManage().setCapacity(capacity);
            page.getTruckManage().setState(state);
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Page resultPage = truckManageService.selectAllSystemCode(page);
            map.put("resultPage",resultPage);
            map.put("currentPage",currentPage);
            map.put("number",number);
            map.put("capacity",capacity);
            map.put("state",state);
            return new ModelAndView("/system/TruckManage",map);
        } catch (Exception e) {
            throw e;
        }
    }


   /* @RequestMapping(value = "/truckList.action")
    public ModelAndView itemList() throws Exception {

        try {
            Map map =new HashMap();
            List<TruckManage> list= truckManageService.selectAllSystemCode();

            map.put("list",list);
            return new ModelAndView("/system/TruckManage",map);
        } catch (Exception e) {
            throw e;
        }
    }*/


    /*@RequestMapping(value = "/addTruck.action")
    public ModelAndView addTruck(HttpServletResponse response ,HttpServletRequest request) throws Exception {
       String number= request.getParameter("number");
       String capacity= request.getParameter("capacity");
       String life= request.getParameter("life");
       String state= request.getParameter("state");

       *//* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date life=sdf.parse(lifes);*//*

        TruckManage truckManage =new TruckManage();
        truckManage.setNumber(number);
        truckManage.setCapacity(capacity);
        truckManage.setLife(life);
        truckManage.setState(state);

        truckManageService.addTruck(truckManage);

        return  new ModelAndView("redirect:/truckManage/truckList.action?currentPage=1");

    }*/

   /* @RequestMapping(value = "/updateTruck.action")
    public ModelAndView updateTruck(HttpServletResponse response ,HttpServletRequest request) throws Exception {

        String capacity= request.getParameter("capacity");
        String number= request.getParameter("number");
        Integer id = new Integer(request.getParameter("id"));
        String life= request.getParameter("life");
        String state= request.getParameter("state");

        TruckManage truckManage =new TruckManage();
        truckManage.setId(id);
        truckManage.setNumber(number);
        truckManage.setCapacity(capacity);
        truckManage.setLife(life);
        truckManage.setState(state);

        truckManageService.updateTruck(truckManage);

        return  new ModelAndView("redirect:/truckManage/truckList.action?currentPage=1");

    }*/
   /* @RequestMapping(value = "/deleteTruck.action")
    public ModelAndView deleteTruck(HttpServletResponse response ,HttpServletRequest request) throws Exception {


        Integer id = new Integer(request.getParameter("id"));


        TruckManage truckManage =new TruckManage();
        truckManage.setId(id);


        truckManageService.deleteTruck(truckManage);

        return  new ModelAndView("redirect:/truckManage/truckList.action?currentPage=1");

    }*/


   }