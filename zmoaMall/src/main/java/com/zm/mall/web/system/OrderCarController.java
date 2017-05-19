package com.zm.mall.web.system;


import com.zm.mall.client.Page;
import com.zm.mall.client.service.business.orders.OrderInfoService;

import com.zm.mall.client.service.system.ApplyCarService;
import com.zm.mall.client.service.system.ApplyCarSonService;
import com.zm.mall.client.service.system.TruckManageService;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.system.ApplyCar;
import com.zm.mall.domain.system.ApplyCarSon;
import com.zm.mall.domain.system.TruckManage;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/business")
public class OrderCarController extends BaseController{
    @Resource
    OrderInfoService orderInfoService;
    @Resource
    ApplyCarService applyCarService;
    @Resource
    ApplyCarSonService applyCarSonService;
    @Resource
    TruckManageService truckManageService;
    @RequestMapping(value = "/toSelectNoCarOrder.action")
    @Transactional
    public ModelAndView toSelectNoCarOrder() throws Exception {

        try {
            return new ModelAndView("/business/selectNoCarOrderList");
        } catch (Exception e) {
            throw e;
        }
    }
    //未申请车辆处理
    @RequestMapping(value = "/selectNoCarOrderList.action")
    @ResponseBody
    public Page selectNoCarOrderList(Integer currentPage,String orderCode,Integer weight,String departmentName) throws Exception {
        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            OrderInfo orderInfo =new OrderInfo();
            if (orderCode.trim()!=""){
                orderInfo.setOrderCode(orderCode);
            }
            if (departmentName.trim()!=""){
                orderInfo.setDepartmentName(departmentName);
            }


            Page resultPage = orderInfoService.selectNoCarOrderList(page,orderInfo);

            return resultPage;

        } catch (Exception e) {
            throw e;
        }
    }
    //订单选择车
    @RequestMapping(value = "/carAddOrder.action")
    @Transactional
    public ModelAndView carAddOrder(HttpServletRequest request) throws Exception {
        String orderCode=request.getParameter("orderCode");

        try {
            String[] orderCodes=orderCode.split(",");
            //创建全局对象
            OrderInfo orderInfo = new OrderInfo();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //提前生成 申请车辆主表编码
            String applyCarCode=simpleDateFormat.format(date).toString();
            for (int i =0;i<orderCodes.length;i++){
                if(orderCodes[i]!=""){
                    orderInfo= orderInfoService.selectOneOrder(orderCodes[i]);
                    ApplyCarSon applyCarSon =new ApplyCarSon();
                    applyCarSon.setOrderCode(orderInfo.getOrderCode());
                    applyCarSon.setApplyCarCode(applyCarCode);
                    applyCarSon.setSmallGroup(orderInfo.getDepartmentName());
                    applyCarSon.setOrderWeigth(orderInfo.getWeight());
                    applyCarSon.setTransactor(orderInfo.getRealName());
                    //先判断是否是商城的订单
                    applyCarSonService.saveOrderAddCar(applyCarSon);
                    //修改订单车辆的状态
                    orderInfoService.updateOrderCarState(orderCodes[i]);
                }
            }

            ApplyCar applyCar =new ApplyCar();
            applyCar.setApplyCarCode(applyCarCode);
            applyCar.setSmallgroup(orderInfo.getDepartmentName());
            //设置申请车辆  的状态
            applyCar.setState("1");
            applyCar.setShowState("1");


            applyCarService.saveApplyCar(applyCar);

            return new ModelAndView("/business/selectNoCarOrderList");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 订单已配车 页面跳转
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toSelectCarOrder.action")
    @Transactional
    public ModelAndView toSelectCarOrder() throws Exception {

        try {
            return new ModelAndView("/business/selectCarOrderList");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 订单已配车 分页查询
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCarOrderList.action")
    @ResponseBody
    public Page selectCarOrderList(Integer currentPage,String smallgroup) throws Exception {
        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            ApplyCar applyCar=new ApplyCar();
            if (smallgroup.trim()!=""& smallgroup!=null){
                applyCar.setSmallgroup(smallgroup);
            }

            Page resultPage = applyCarService.selectApplyCarList(page,applyCar);

            return resultPage;

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 订单已配车 珠子列表展示订单
     * @param applyCarCode
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/selectApplyCarSonList.action")
    @ResponseBody
    public Object selectApplyCarSonList(String applyCarCode) throws Exception {

        try {

            return applyCarSonService.selectApplyCarSonByApplyCarCoder(applyCarCode);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 查询等待的车辆
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCar.action")
    @ResponseBody
    public Object selectCar() throws Exception {

        try {
            String state="2";
            List< TruckManage > list=truckManageService.selectCar(state);
            return list;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 查询运行的车辆
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectOperationCar.action")
    @ResponseBody
    public Object selectOperationCar() throws Exception {

        try {
            String state="4";
            List< TruckManage > list=truckManageService.selectCar(state);
            return list;
        } catch (Exception e) {
            throw e;
        }
    }


    @RequestMapping(value = "/applyCarAddCar.action")
    @Transactional
    public ModelAndView applyCarAddCar(HttpServletRequest request) throws Exception {

        try {
            String applyCarCode =request.getParameter("applyCarCode");
            String carCode =request.getParameter("carCode");
            //先根据申请编码去查询，是否有车，如果有车，修改状态为等待；如果没车判断车编码是否为空，不为空修改车辆状态为运行。
            ApplyCar ac =applyCarService.selectOneAppCar(applyCarCode);
            if(!"".equals(ac.getNumber())&& ac.getNumber()!=null){
                String state="2";
                TruckManage truckManage =new TruckManage();
                truckManage.setState(state);
                truckManage.setNumber(ac.getNumber());
                truckManageService.updateTruckState(truckManage);
            }
            ApplyCar applyCar =new ApplyCar();
            applyCar.setApplyCarCode(applyCarCode);
            applyCar.setNumber(carCode);
            applyCarService.applyCarAddCar(applyCar);
            //同时需要修改车辆状态
            if(carCode!="" && carCode!=null){
                String state="4";
                TruckManage truckManage =new TruckManage();
                truckManage.setState(state);
                truckManage.setNumber(carCode);
                truckManageService.updateTruckState(truckManage);

            }
            //同时需要修改订单状态  订单状态：2(标识已经配上车)
            List<ApplyCarSon> list = applyCarSonService.selectApplyCarSonByApplyCarCoder(applyCarCode);
            for (int i=0;i<list.size();i++){
                orderInfoService.updateOrderCarTwoState(list.get(i).getOrderCode());
            }


            return new ModelAndView("/business/selectCarOrderList");
        } catch (Exception e) {
            throw e;
        }
    }
}