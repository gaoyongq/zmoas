package com.zm.mall.web.business.orders;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.vo.business.product.ProductInfoVo;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.*;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.ProductType;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.util.ConversionUtil;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@Controller
@RequestMapping("/business")
public class OrderInfoController extends BaseController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Resource
    OrderInfoService orderInfoService;
    /**
     * 跳转到订单显示页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderManagerAction_list.action")
    public ModelAndView orderList(HttpServletRequest request,String orderCode, String shipName,String buyerName,String beginTime,String endTime,String buyerCellPhone,Integer orderCheckStatus,Integer currentPage,Integer pageSize) throws Exception{
        try{
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            ModelAndView mv = new ModelAndView("/business/orderShow");
            OrderByTip tip = new OrderByTip();
            //对输入的查询条件去除首尾空格
            if (orderCode!=null & shipName!=null & buyerName!=null & buyerCellPhone!=null) {
                orderCode = orderCode.trim();
                shipName = shipName.trim();
                buyerName = buyerName.trim();
                buyerCellPhone = buyerCellPhone.trim();
                tip.setBuyerName(buyerName);
                tip.setShipName(shipName);
                tip.setOrderCode(orderCode);
                tip.setBuyerCellPhone(buyerCellPhone);
            }
            tip.setBeginTime(beginTime);
            tip.setEndTime(endTime);
            if (orderCheckStatus==null){
                orderCheckStatus=0;
            }
            if (currentPage==null){
                currentPage=1;
            }
            pageSize = 5;
            tip.setOrderCheckStatus(orderCheckStatus);
            tip.setPageSize(pageSize);
            tip.setCurrentPage(currentPage);
            //tip.setDepartmentId(user.getDepartment().getId());
            Integer count = orderInfoService.getCount(tip);
            Integer totalPage = 0;
            if (count==0){
                totalPage =1;
            }else {
                totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
            }
            Integer begionNumber = (currentPage-1)*pageSize;
            tip.setBegionNumber(begionNumber);
            tip.setOrderCheckStatus(orderCheckStatus);
            List<OrderInfo> orderInfoList = orderInfoService.selectAllOrderInfo(tip);
            mv.addObject("orders", orderInfoList);
            mv.addObject("currentPage",currentPage);
            mv.addObject("totalPage",totalPage);
            mv.addObject("orderCode",orderCode);
            mv.addObject("shipName",shipName);
            mv.addObject("buyerName",buyerName);
            mv.addObject("orderCheckStatus",orderCheckStatus);
            mv.addObject("beginTime",beginTime);
            mv.addObject("endTime",endTime);
            mv.addObject("buyerCellPhone",buyerCellPhone);
            return mv;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 订单具体信息查看
     */
    @RequestMapping(value = "/OrderInformation.action")
    @ResponseBody
    public Object orderInfoInformation(HttpServletRequest request,String orderId)throws Exception{
        try{
            OrderInfo orderInfo = orderInfoService.selectGuesterInfo(orderId);
            return JSON.toJSON(orderInfo);
        }catch (Exception e){
            throw e;
        }

    }
    /**
     * 订单取消  将shop_orders中IsDelete改为1
     */
    @RequestMapping(value = "/DeleteOrder.action")
    public ModelAndView deleteOrder(HttpServletRequest request,String orderCode){
        int an = orderInfoService.deleteOrder(orderCode);
        return new ModelAndView("redirect:/business/orderManagerAction_list.action");
    }
    /**
     * 跳转到订单新增页面
     */
    @RequestMapping(value = "/addOrderInfo.action")
    public ModelAndView addOrder(HttpServletRequest request){
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        ModelAndView mv = new ModelAndView("/business/addOrderInfo");
        //获得订单号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String orderCode = sdf.format(date).toString();
        //地区查询
        List<RegionInfo> list = orderInfoService.getRegionInfo();
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        List<ProductType> types = orderInfoService.getProductType(platformId, userResult.getShopId());
        mv.addObject("orderCode",orderCode);
        mv.addObject("list",list);
        mv.addObject("types",types);
        return mv;
    }

    /**
     * ajax获取用户对应的收货信息
     */
    @RequestMapping(value = "shipInfo.action")
    @ResponseBody
    public Object getshipInfo(HttpServletRequest request,String buyerName){
        Users users = orderInfoService.getShippingAddress(buyerName);

        return JSON.toJSON(users);
    }
    /**
     * 根据regionId获取运费
     */
    @RequestMapping(value = "/getYunFei.action")
    @ResponseBody
    public Object getYunFei(Integer regionId,Integer productId){
        RegionExpressPrice regionExpressPrice = new RegionExpressPrice();
        //判断productId为空时，根据regionId和TypeId为1查
        if (productId == null | "".equals(productId)){
            regionExpressPrice.setRegionId(regionId);
            regionExpressPrice.setTypId(1);
        }else{
            regionExpressPrice.setProductId(productId);
            regionExpressPrice.setTypId(3);
        }
        RegionExpressPrice regionPrice = orderInfoService.getYunFei(regionExpressPrice);
        return JSON.toJSON(regionPrice);
    }
    /**
     * 添加订单
     */
    @RequestMapping(value = "/insertOrder.action")
    @ResponseBody
    public ModelAndView insetOrder(HttpServletRequest request,OrderInfo orderInfo,String data)throws Exception{
        try {
            UserResult user = (UserResult) request.getSession().getAttribute("userResult");
            List<OrderItems> list = new ArrayList<OrderItems>();
            JSONArray jsonArray = JSONArray.parseArray(data);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                OrderItems orderItems = new OrderItems();
                List<OrderItemsAttribute> attributes = orderItems.getOrderItemsAttributes();
                Long productId = jsonObject.getLong("productId");
                String productName = jsonObject.getString("productName");
                Integer weight = jsonObject.getInteger("weight");
                Integer quantity = jsonObject.getInteger("quantity");
                String sku = jsonObject.getString("sku");
                String productCode = jsonObject.getString("productCode");
                Double sellPrice = jsonObject.getDouble("sellPrice");
                Double costPrice = jsonObject.getDouble("costPrice");
                String thumbnailsUrl = jsonObject.getString("thumbnailsUrl");
                String goodsType = jsonObject.getString("goodsType");
                Integer quality = jsonObject.getInteger("quality");
                String orderItemsAttribute = jsonObject.getString("orderItemsAttributes");
                String[] attrs = orderItemsAttribute.split(";");
                for (int a = 0; a < attrs.length; a++) {
                    OrderItemsAttribute orderItemsAttribute1 = new OrderItemsAttribute();
                    String[] names = attrs[a].split(":");
                    orderItemsAttribute1.setName(names[0]);
                    orderItemsAttribute1.setValue(names[1]);
                    attributes.add(orderItemsAttribute1);
                }
                orderItems.setProductId(productId);
                orderItems.setName(productName);
                orderItems.setWeight(weight);
                orderItems.setQuantity(quantity);
                orderItems.setSku(sku);
                orderItems.setProductCode(productCode);
                orderItems.setSellPrice(sellPrice);
                orderItems.setCostPrice(costPrice);
                orderItems.setThumbnailsUrl(thumbnailsUrl);
                orderItems.setOrderItemsAttributes(attributes);
                orderItems.setGoodsType(goodsType);
                orderItems.setQuality(quality);
                list.add(orderItems);
            }
            orderInfo.setOrderItems(list);
            Department department = user.getDepartment();
            orderInfo.setRoleId(user.getId());
            orderInfo.setOrderCheckStatus(0);
            orderInfo.setDepartmentId(department.getId());
            orderInfo.setRealName(user.getRealName());
            orderInfo.setDepartmentName(user.getDepartment().getName());
            orderInfo.setPluteformid(user.getPluteformid());
            //判断是否申请车辆-申请车辆将订单插入车辆表
            Integer applyVehicleStatus = orderInfo.getApplyVehicleStatus();
            if (applyVehicleStatus == 1) {
                Integer a = orderInfoService.insertApplyCar(orderInfo);
            }
            Integer an = orderInfoService.insertOrder(orderInfo);
            return new ModelAndView("redirect:/business/orderManagerAction_list.action");
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 根据typeId类型Id和规格属性名获取AttributeId,ValueId,ValueStr
     */
    @RequestMapping(value = "selectAttributeValue.action")
    @ResponseBody
    public Object selectAttributeValue(Integer typeId,String attributeName){
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.setTypeId(typeId);
        attributeName = attributeName.trim();
        attributeInfo.setAttributeName(attributeName);
        List<AttributeInfo> list = orderInfoService.selectAttributeValue(attributeInfo);
        return JSON.toJSON(list);
    }
    /**
     * 白洋洋   根据产品Id获取产品价格
     */
    @RequestMapping(value = "selectSalePrice.action")
    @ResponseBody
    public List<SkuData> checkPrice(Long productId,String appid){
        ProductInfoVo productInfoVo=new ProductInfoVo();
        productInfoVo.setProductId(productId);
        productInfoVo.setPluteformid(appid);
        List<SkuData> skuData = orderInfoService.checkPrice(productInfoVo);

        return skuData;
    }

    /**
     * 白洋洋     订单处理
     */
    @RequestMapping(value = "/orderManagerAction_listHandle.action")
    @ResponseBody
    public ModelAndView orderhandle(HttpServletRequest request,Integer currentPage){
        ModelAndView mv = new ModelAndView("/business/orderHandle");
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        if (currentPage==null){
            currentPage = 1;
        }
        Integer pageSize = 10;
        OrderByTip orderByTip = new OrderByTip();
        orderByTip.setCurrentPage(currentPage);
        orderByTip.setPageSize(pageSize);
        orderByTip.setPurchasingStatus(0);
        orderByTip.setApplyVehicleStatus(0);
        orderByTip.setDepartmentId(user.getDepartment().getId());
        Integer count = orderInfoService.getOrderCount(orderByTip);
        Integer totalPage = 0;
        if (count==0){
            totalPage =1;
        }else {
            totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
        }
        Integer begionNumber = (currentPage-1)*pageSize;
        orderByTip.setBegionNumber(begionNumber);
        List<OrderInfo> orderInfoList = orderInfoService.getAllOrderInfo(orderByTip);
        mv.addObject("orders", orderInfoList);
        //地区查询
        List<RegionInfo> list = orderInfoService.getRegionInfo();
        mv.addObject("list",list);
        mv.addObject("currentPage",currentPage);
        mv.addObject("totalPage",totalPage);
        return mv;
    }

    /**
     * 白洋洋   订单处理页面订单查询
     */
    @RequestMapping(value = "/getOrdersByTip.action",method = RequestMethod.POST)
    @ResponseBody
    public Object getOrderByTip(HttpServletRequest request,String orderCode,String buyerName,String shipName,String beginTime,String endTime,String shipProvince,String shipCity,String shipCounty,String purchasingStatus,String applyVehicleStatus,Integer currentPage)throws Exception{
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        OrderByTip tip = new OrderByTip();
        tip.setOrderCode(orderCode.trim());
        tip.setBuyerName(buyerName.trim());
        tip.setShipName(shipName.trim());
        tip.setBeginTime(beginTime);
        tip.setEndTime(endTime);
        tip.setShipProvince(shipProvince);
        tip.setShipCity(shipCity);
        tip.setShipCounty(shipCounty);
        Integer pageSize = 10;
        tip.setPageSize(pageSize);
        tip.setCurrentPage(currentPage);
        tip.setPurchasingStatus(Integer.parseInt(purchasingStatus));
        tip.setApplyVehicleStatus(Integer.parseInt(applyVehicleStatus));
        tip.setDepartmentId(user.getDepartment().getId());
        Page page = new Page();
        Integer totalPage = 0;
        Integer count = orderInfoService.getOrderCount(tip);
        if (count==0){
            totalPage =1;
        }else {
            totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
        }
        Integer begionNumber = (currentPage-1)*pageSize;
        tip.setBegionNumber(begionNumber);
        List<OrderInfo>  orderInfoList = orderInfoService.getOrderByTip(tip);
        page.setCurrentPage(currentPage);
        page.setTotalPages(totalPage);
        page.setResultOrderInfo(orderInfoList);
        return JSON.toJSON(page);
    }
    /**
     * 小组长出来商城过来的公共订单
     */
    @RequestMapping(value = "/getOrdersFromM.action")
    @ResponseBody
    public Object getOrderFromM(HttpServletRequest request,String orderCode,String buyerName,String shipName,String beginTime,String endTime,String shipProvince,String shipCity,String shipCounty,String purchasingStatus,String applyVehicleStatus, Integer currentPage)throws Exception{
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        OrderByTip tip = new OrderByTip();
        tip.setOrderCode(orderCode.trim());
        tip.setBuyerName(buyerName.trim());
        tip.setShipName(shipName.trim());
        tip.setBeginTime(beginTime);
        tip.setEndTime(endTime);
        tip.setShipProvince(shipProvince);
        tip.setShipCity(shipCity);
        tip.setShipCounty(shipCounty);
        Integer pageSize = 10;
        tip.setPageSize(pageSize);
        tip.setPurchasingStatus(Integer.parseInt(purchasingStatus));
        tip.setApplyVehicleStatus(Integer.parseInt(applyVehicleStatus));
        tip.setCurrentPage(currentPage);
        tip.setDepartmentId(user.getDepartment().getId());
        tip.setDepartmentName(user.getDepartment().getName());
        tip.setRealName(user.getRealName());
        Page page = new Page();
        Integer totalPage = 0;
        try {
            Integer count = orderInfoService.getOrderCountByM(tip);
            if (count == 0) {
                totalPage = 1;
            } else {
                totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;//一共多少页数
            }
            Integer begionNumber = (currentPage - 1) * pageSize;
            tip.setBegionNumber(begionNumber);
            List<OrderInfo> orderInfoList = orderInfoService.getOrderByTipByM(tip);
            page.setCurrentPage(currentPage);
            page.setTotalPages(totalPage);
            page.setResultOrderInfo(orderInfoList);
        }catch (Exception e){
            throw e;
        }
        return JSON.toJSON(page);
    }
    /**
     * 订单新增页面  可编辑下拉框
     */
    @RequestMapping(value = "autoCompleteOrderInfo.action")
    @ResponseBody
    public Object autoComplete(){
        List<OrderInfo> orderInfos = orderInfoService.autoCompleteOrderInfo();
        JSON.toJSON(orderInfos);
        System.out.print(JSON.toJSON(orderInfos));
        return JSON.toJSON(orderInfos);
    }
    /**
     * 白洋洋    对订单进行审核操作
     */
    @RequestMapping(value = "/orderPass.action",method =RequestMethod.POST)
    @ResponseBody
    public Object passOrder(HttpServletRequest request,String message,String orderIds){
        Integer an = orderInfoService.passOrder(message,orderIds);
        return an;
    }
    /**
     * 订单修改-查询
     */
    @RequestMapping(value = "changeOrderInfo.action")
    public ModelAndView changeOrderInfo(HttpServletRequest request, @RequestParam("orderId")String orderId)throws Exception{
        UserResult userResult =(UserResult)request.getSession().getAttribute("userResult");
        try{
            ModelAndView mv = new ModelAndView("/business/orderChange");
            OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderId);
            mv.addObject("orderInfo",orderInfo);
            List<OrderItems> orderItemsList = orderInfoService.getOrderItems(orderId);
            for (int i=0;i<orderItemsList.size();i++){
                Integer number = orderItemsList.get(i).getShipmentQuantity();
                Integer weight = orderItemsList.get(i).getWeight();
                Double sellPrice = orderItemsList.get(i).getSellPrice();
                Integer xWeight = number*weight;
                Double xPrice = number*sellPrice;
                orderItemsList.get(i).setxWeight(xWeight);
                BigDecimal b = new BigDecimal(xPrice);
                orderItemsList.get(i).setxPrice(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                Long itemId = orderItemsList.get(i).getItemId();
                List<OrderItemsAttribute> orderItemsAttributes = orderInfoService.getOrderAttribute(itemId);
                String attribute = "";
                for (int an=0;an<orderItemsAttributes.size();an++){
                    attribute += orderItemsAttributes.get(an).getName();
                    attribute += ":";
                    attribute +=orderItemsAttributes.get(an).getValue();
                    attribute +=";";
                }
                attribute = attribute.substring(0,attribute.length()-1);
                orderItemsList.get(i).setAttribute(attribute);
                String name = orderItemsList.get(i).getName();
                Integer length = name.length();
                if (length<3){
                    orderItemsList.get(i).setCutProductName(name);
                }else{
                    name = name.substring(0,2);
                    name +="...";
                    orderItemsList.get(i).setCutProductName(name);
                }
            }
            mv.addObject("orderItems",orderItemsList);
            List<RegionInfo> list = orderInfoService.getRegionInfo();
            String platformId = userResult.getPluteformid();
            if (userResult.getShopId() != null) {
                platformId = userResult.getParentPluteformId();
            }
            List<ProductType> types = orderInfoService.getProductType(platformId, userResult.getShopId());
            mv.addObject("list",list);
            mv.addObject("types",types);
            return mv;
        }catch(Exception e){
            throw e;
        }

    }
    /**
     * 订单修改
     */
    @RequestMapping(value = "/updateOrder.action")
    public ModelAndView updateOrder(HttpServletRequest request,OrderInfo orderInfo,String data)throws Exception{
        List<OrderItems> list = new ArrayList<OrderItems>();
        JSONArray jsonArray = JSONArray.parseArray(data);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            OrderItems orderItems = new OrderItems();
            List<OrderItemsAttribute> attributes = orderItems.getOrderItemsAttributes();
            Long productId = jsonObject.getLong("productId");
            String productName = jsonObject.getString("productName");
            Integer weight = jsonObject.getInteger("weight");
            Integer quantity = jsonObject.getInteger("quantity");
            String sku = jsonObject.getString("sku");
            String productCode = jsonObject.getString("productCode");
            Double sellPrice = jsonObject.getDouble("sellPrice");
            Double costPrice = jsonObject.getDouble("costPrice");
            String thumbnailsUrl = jsonObject.getString("thumbnailsUrl");
            String goodsType = jsonObject.getString("goodsType");
            String orderItemsAttribute = jsonObject.getString("orderItemsAttributes");
            String[] attrs = orderItemsAttribute.split(";");
            for (int a=0;a<attrs.length;a++){
                OrderItemsAttribute orderItemsAttribute1 = new OrderItemsAttribute();
                String[] names = attrs[a].split(":");
                orderItemsAttribute1.setName(names[0]);
                orderItemsAttribute1.setValue(names[1]);
                attributes.add(orderItemsAttribute1);
            }
            orderItems.setProductId(productId);
            orderItems.setName(productName);
            orderItems.setWeight(weight);
            orderItems.setQuantity(quantity);
            orderItems.setSku(sku);
            orderItems.setProductCode(productCode);
            orderItems.setSellPrice(sellPrice);
            orderItems.setCostPrice(costPrice);
            orderItems.setThumbnailsUrl(thumbnailsUrl);
            orderItems.setOrderItemsAttributes(attributes);
            orderItems.setGoodsType(goodsType);
            list.add(orderItems);
        }
        orderInfo.setOrderItems(list);
        Integer an = orderInfoService.updateOrderInfo(orderInfo);
        return new ModelAndView("redirect:/business/orderManagerAction_list.action");
    }

    /**
     * 根据版面计算价格
     * @param layout
     * @param price
     * @return
     */
    @RequestMapping(value = "layoutPrice.action")
    @ResponseBody
    public Double salePrice(String layout,String price){
        Double aDouble =  orderInfoService.layoutPrice(layout, price);
        return aDouble;
    }
    /**
     * 获得用户订单
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selOrderInfoByWeCart.action")
    @ResponseBody
    public Object selectAddressState(HttpServletRequest request)throws  Exception{
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
            String str =sb.toString();
            isr.close();
            Page page=JSONObject.parseObject(str, Page.class);
            List<OrderInfo> orderInfo = orderInfoService.selectWXOrder(page);
            return orderInfo;
        }catch (Exception e){
            throw e;
        }
    }

	/**
     * 获得用户待付款订单
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selectWXOrderDaiFuKuan.action")
    @ResponseBody
    public Object selectWXOrderDaiFuKuan(HttpServletRequest request)throws  Exception{
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
            String str =sb.toString();
            isr.close();
            Page page=JSONObject.parseObject(str, Page.class);
            List<OrderInfo> orderInfo = orderInfoService.selectWXOrderDaiFuKuan(page);
            return orderInfo;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 抢购订单
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value="/orderSnagWx.action")
    public ModelAndView orderSnagWx(HttpServletRequest request,String ids){
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderId(ids);
        orderInfo.setRoleId(user.getId());
        Department department=new Department();
        orderInfo.setDepartmentId(department.getId());
        orderInfo.setRealName(user.getRealName());
        orderInfo.setDepartmentName(user.getDepartment().getName());
        Integer a=orderInfoService.orderSnagWx(orderInfo);
        return new ModelAndView("redirect:/business/orderManagerAction_list.action");
    }
    /**
     * 获得用户订单详细
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selOrderItemByWeCart.action")
    @ResponseBody
    public Object selOrderItemByWeCart(HttpServletRequest request)throws  Exception{
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
            String str =sb.toString();
            isr.close();
            OrderInfo orderInfo=JSONObject.parseObject(str, OrderInfo.class);
            List<OrderItems> orderItemses = orderInfoService.selOrderItemByWeCart(orderInfo);
            return orderItemses;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 个人中心的订单展示-移动端
     */
    @RequestMapping(value="/selIndividualOrdersApp.action")
    @ResponseBody
    public Object selIndividualOrdersApp(HttpServletRequest request)throws  Exception{
        try{

            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String user =sb.toString();
            isr.close();

            JSONObject object = JSON.parseObject(user);
            String buyerId = object.getString("userId");

            List<OrderInfo> orderInfos = orderInfoService.selIndividualOrdersApp(buyerId);

            JSONObject obj = new JSONObject();
            obj.put("orderInfos",orderInfos);

            return obj;
        }catch (Exception e){
            return e;
        }
    }

    @RequestMapping("/getYUYUEOrderInfo.action")
    @ResponseBody
    public Object getYUYUEOrderInfo(HttpServletRequest request ){
       try{
           StringBuffer sb = new StringBuffer();
           InputStream is = request.getInputStream();
           InputStreamReader isr = new InputStreamReader(is,"UTF-8");
           BufferedReader br = new BufferedReader(isr);
           String s = "" ;
           while((s=br.readLine())!=null){
               sb.append(s) ;
           }
           br.close();
           is.close();
           String user =sb.toString();
           isr.close();
           JSONObject objOrder=JSONObject.parseObject(user);
           OrderInfo orderInfo=JSONObject.parseObject(objOrder.getString("orderInfo"),OrderInfo.class);
           Page page=JSONObject.parseObject(user,Page.class);
           page.setOrderInfo(orderInfo);
           List<OrderInfo> aa=orderInfoService.getYUYUEOrderInfo(page);
           if(aa==null) {
               return null;
           }
           return  aa;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }


    }
    @RequestMapping(value = "/updateItems.action")
    @ResponseBody
    public Object updateItems(@RequestBody String param,OrderItems items){
        JSONObject obj=new JSONObject();
        try{
            if (param.startsWith("{")){
                items=JSONObject.parseObject(param,OrderItems.class);
            }

            Integer i=orderInfoService.updateItems(items);
            obj.put("result",i);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
            obj.put("result",-1);
            return obj;
        }
    }
    /**
     * 查询产品月销售量排行
     * @param month
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selChampionHip.action")
    public ModelAndView selChampionHip(HttpServletRequest request,String month,Integer currentPage,String startTime,String endTime)throws  Exception{
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        ModelAndView mav = new ModelAndView("/business/ChampionHipShow");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        if(month == null || "0".equals(month)){
            month = "1";
        }
        //计算月份
        if(startTime == null || startTime == "" || endTime == null || endTime ==""){
            startTime = year+"-"+month+"-1";
            String day = null;

            if("2".equals(month)){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if("4".equals(month) || "6".equals(month) || "9".equals(month) || "11".equals(month)){
                day = "30";
            }else {
                day = "31";
            }
            endTime = year+"-"+month+"-"+day ;
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(user.getPluteformid());
        Integer totalPage = 0;
        Integer pageSize = 10;
        Integer count = orderInfoService.selChampionHipCount(startTime, endTime,orderInfo);
        if (count==0){
            totalPage =1;
        }else {
            totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
        }
        if (currentPage==null){
            currentPage=1;
        }
        Integer begionNumber = (currentPage-1)*pageSize;
        orderInfo.setBegionNumber(begionNumber);
        orderInfo.setCurrentPage(currentPage);
        orderInfo.setPageSize(pageSize);
        List<OrderInfo> orderInfos = orderInfoService.selChampionHip(startTime, endTime,orderInfo);
        mav.addObject("orderInfos",orderInfos);
        mav.addObject("month",month);
        mav.addObject("currentPage",currentPage);
        mav.addObject("totalPage",totalPage);
        mav.addObject("type",1);
        return mav;
    }

    /**
     * 月订单量
     * @param type
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selOrderQuantity.action")
    public ModelAndView selOrderQuantity(HttpServletRequest request,String type,String startTime,String endTime)throws  Exception{
        if("1".equals(type)){
            return new ModelAndView("redirect:/business/selChampionHip.action");
        }else if("3".equals(type)){
            return new ModelAndView("redirect:/business/selOrderTotal.action");
        }
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(user.getPluteformid());
        List list = orderInfoService.selOrderQuantity(startTime, endTime,orderInfo);
        ModelAndView mav = new ModelAndView("/business/ChampionHipShow");
        mav.addObject("type", 2);
        mav.addObject("list",list);
        return mav;
    }

    /**
     * 月金额数
     * @param request
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selOrderTotal.action")
    public ModelAndView selOrderTotal(HttpServletRequest request,String startTime,String endTime)throws Exception{
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(user.getPluteformid());

        List money = orderInfoService.selOrderTotal(startTime,endTime,orderInfo);

        ModelAndView mav = new ModelAndView("/business/ChampionHipShow");
        mav.addObject("type",3);
        mav.addObject("money",money);
        return mav;
    }
    @RequestMapping("/yyOrderList")
    @ResponseBody
    public EasyUIListResult wxOrderList(HttpServletRequest request, AppointmentOrder order, String filterRules, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }

        if (filterRules != null) {
            try {
                List<FilterObj> list = MAPPER.readValue(filterRules,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, FilterObj.class));

                for (FilterObj obj : list) {
                    System.out.print(obj.getField());
                    Field field = order.getClass().getDeclaredField(obj.getField());
                    field.setAccessible(true);
                    if(ConversionUtil.isNumeric(obj.getValue()))
                        field.set(order, Integer.valueOf(obj.getValue()));
                    else
                    field.set(order, obj.getValue());

                }
            }  catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        order.setBegionNumber(((page.intValue() - 1) * rows.intValue())); //(page - 1) * rows, rows
        order.setPageSize(rows.intValue());

        order.setPluteformid(userResult.getPluteformid());

        List<AppointmentOrder> orderInfoList = orderInfoService.getAppointmentData(order);
        EasyUIListResult result = new EasyUIListResult<AppointmentOrder>();
        result.setRows(orderInfoList);
        result.setTotal(orderInfoService.getAppointmentDataCount(order).longValue());
        return result;
    }
    @RequestMapping("/jumpyyOrderPage.action")
    public  String jumpyyOrderPage(){
        return "/business/wxOrder/wxyyOrderList";
    }

    @RequestMapping("/updateOrderStatus")
    @ResponseBody
    public Integer updateOrderStatus(HttpServletRequest request, String orderId, String key, Integer value) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        Integer a= orderInfoService.updateAppOrderStatus(userResult.getPluteformid(), orderId, key, value);
        if(a==0)
            return 0;
        return a;
    }
    @RequestMapping("/wxyyOrderDetail")
    @ResponseBody
    public List<OrderItems> wxOrderDetail(String orderId) {
        List<OrderItems> list=orderInfoService.selectGuesterInfo2(orderId).getOrderItems();
        return list;
    }
}
