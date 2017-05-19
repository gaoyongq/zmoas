package com.zm.mall.web.business.purchase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.business.orders.OrderInfoResult;
import com.zm.mall.client.result.business.product.SupplierProductResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.product.SupplierProductService;
import com.zm.mall.client.service.business.purchase.PurchaseService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.business.purchase.PurchasePage;
import com.zm.mall.domain.business.purchase.PurchaseSupplierItems;
import com.zm.mall.domain.system.User;
import com.zm.mall.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * Created by Administrator on 2016/11/29.
 */
@Controller
@RequestMapping("business")
public class PurchaseController  extends BaseController{
	@Resource
	private PurchaseService purchaseService;
	@Resource
	OrderInfoService orderInfoService;
	@Resource
	private UserService userService;
	@Resource
	private SupplierProductService supplierProductService;
	/**
	 * 查询所有的采购单
	 */
	@RequestMapping(value = "/showPurchase.action")
	public ModelAndView selecAllPurchase(HttpServletRequest request,Integer currentPage,Integer state,String purchaseCode,String beginTime,String endTime,Double numMoney,Double bigMoney)throws Exception{
		//ModelAndView mv = new ModelAndView("/business/onePurchase");
		try{
			UserResult user=(UserResult)request.getSession().getAttribute("userResult");
			String status = request.getParameter("status");
			PurchasePage purchasePage=new PurchasePage();
			if(state!=null || purchaseCode!=null || beginTime!=null || endTime!=null) {
				purchasePage.setState(state);
				purchasePage.setPurchaseCode(purchaseCode.trim());
				purchasePage.setBeginTime(beginTime);
				purchasePage.setEndTime(endTime);
			}if(numMoney!=null & bigMoney!=null){
				purchasePage.setNumMoney(numMoney);
				purchasePage.setBigMoney(bigMoney);
			}if(state==null){
				purchasePage.setState(0);
			}if (status==null){
				purchasePage.setStatus(0);
			}else{
				purchasePage.setStatus(Integer.parseInt(status));
			}
			if (currentPage==null){
				currentPage = 1;
			}
			Integer pageSize =3;
			purchasePage.setPageSize(pageSize);
			purchasePage.setCurrentPage(currentPage);
			purchasePage.setDepartmentId(user.getDepartment().getId());
			Integer count=purchaseService.purchaseCountAll(purchasePage);
			Integer totalPage = 0;
			if (count==0){
				totalPage =1;
			}else {
				totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
			}
			Integer beginNumber = (currentPage-1)*pageSize;
			purchasePage.setBegionNumber(beginNumber);
			purchasePage.setCurrentPage(currentPage);
			purchasePage.setDepartmentId(user.getDepartment().getId());
			List<PurchaseInfo> PurchaseInfo= purchaseService.getAllPurchases(purchasePage);
			if(PurchaseInfo!= null){
				double totalweights;
				for (int i=0;i<PurchaseInfo.size();i++){
					totalweights = (Math.round(PurchaseInfo.get(i).getTotalweights()/10000)/100.0);
					PurchaseInfo.get(i).setTotalweights(totalweights);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PurchaseInfo", PurchaseInfo);
			map.put("currentPage", currentPage);
			map.put("totalPage", totalPage);
			map.put("purchaseCode", purchaseCode);
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
			map.put("numMoney", numMoney);
			map.put("bigMoney", bigMoney);
			map.put("state", state);
			return new ModelAndView("/business/onePurchase",map);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * 查询所有的采购部的人，
	 * 查询所有的订单信息
	 */
	@RequestMapping(value = "/selectOrders.action")
	public ModelAndView startPurchase(HttpServletRequest request){
		UserResult user=(UserResult)request.getSession().getAttribute("userResult");
		Long id=user.getDepartment().getId();
		UserVo userVo = new UserVo();
		userVo.setId(id);
		Map<String,Object> map=new HashMap<String, Object>();
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String purchaseCode=simpleDateFormat.format(date).toString();
		map.put("purchaseCode",purchaseCode);
		List<User> userResults= userService.selectPurchaser(userVo);
		map.put("userPurchaseResults",userResults);
		List<OrderInfoResult> orderInfoResults=orderInfoService.startMakePurchase(null);
		map.put("orderInfoResults",orderInfoResults);
		//return new ModelAndView("/business/addPurchase",map);
		return new ModelAndView("/layout/addPurchase",map);
	}
	/**
	 * 生成采购单
	 */
	@RequestMapping(value = "/makePurchase.action")
	@ResponseBody
	public Object make(String dataList){
		Integer s=null;
		Map<String,Object> m= JSONObject.parseObject(dataList, Map.class);
		PurchaseInfo purchaseInfo=new PurchaseInfo();
		String purchaseCode =(String) m.get("purchaseCode");
		purchaseInfo.setPurchaseCode(purchaseCode);
		String totalNumber=(String)m.get("totalnumbers");
		Long tumber=Long.parseLong(totalNumber.trim());
		String totalMoney=m.get("totalmoneyss").toString();
		Double tmoney=Double.parseDouble(totalMoney.trim());
		String totalWeight=(String)m.get("totalweights");
		Double tweight=Double.parseDouble(totalWeight.trim());
		String userId=(String)m.get("purchaser");
		Integer purchaserId=Integer.parseInt(userId.trim());
		String rack=(String)m.get("rackmoney");
		Long rackmoney=Long.parseLong(rack.trim());
		String d=(String)m.get("departmentId");
		Long departmentId=Long.parseLong(d.trim());
		purchaseInfo.setTotalweights(tweight);
		purchaseInfo.setTotalmoney(tmoney);
		purchaseInfo.setTotalnumber(tumber);
		purchaseInfo.setPurchaser(purchaserId);
		purchaseInfo.setRackMoney(rackmoney);
		purchaseInfo.setDepartmentId(departmentId);
	    purchaseService.insertPurchase(purchaseInfo);
		List<Map<String,Object>> list=(List<Map<String,Object>>)m.get("list");
		if(list!=null){
			for(Map<String,Object> listMap:list) {
				PurchaseItems purchaseItems=new PurchaseItems();
				String orderId=(String)listMap.get("orderId");
				String orderCode=(String)listMap.get("orderCode");
				String shipName=(String)listMap.get("shipName");
				String shipAddress=(String)listMap.get("shipAddress");
				String shipCellphone=(String)listMap.get("shipCellphone");
				String Quantity=(String)listMap.get("Quantity");
				Integer quantity=Integer.valueOf(Quantity.trim());
				String orderWeight=(String)listMap.get("orderWeight");
				Integer w=Integer.parseInt(orderWeight.trim());
				String productId=(String)listMap.get("productId");
				Long Pid= Long.parseLong(productId.trim());
				String productCode=(String)listMap.get("productCode");
				String productName=(String)listMap.get("productName");
				String productSku=(String)listMap.get("productSku");
				String orderCheckStatus=(String)listMap.get("orderCheckStatus");
				Integer state=Integer.parseInt(orderCheckStatus.trim());
				String goodsType=(String)listMap.get("goodsType");
				String sellPrice=(String)listMap.get("sellPrice");
				Double Price=Double.parseDouble(sellPrice.trim());
				String purchaseM=(String)listMap.get("purchaseMs");
				Double money=Double.parseDouble(purchaseM.trim());
				String purchaseW=(String)listMap.get("purchaseWs");
				Integer weight=Integer.parseInt(purchaseW.trim());
				String purchaseN=(String)listMap.get("purchaseNs");
				Integer number=Integer.parseInt(purchaseN.trim());
				purchaseItems.setProductId(Pid);
				purchaseItems.setProductCode(productCode);
				purchaseItems.setProductName(productName);
				purchaseItems.setSku(productSku);
				purchaseItems.setOrderId(orderId);
				purchaseItems.setOrderCode(orderCode);
				purchaseItems.setShipperName(shipName);
				purchaseItems.setShipCellPhone(shipCellphone);
				purchaseItems.setShipAddress(shipAddress);
				purchaseItems.setNumber(quantity);
				purchaseItems.setWeight(w);
				purchaseItems.setOrderState(state);
				purchaseItems.setProductStyles(goodsType);
				purchaseItems.setProductSell(Price);
				purchaseItems.setPurchaseW(weight);
				purchaseItems.setPurchaseN(number);
				purchaseItems.setPurchasePrice(money);
				purchaseService.insertPurchaseItems(purchaseInfo,purchaseItems);
				Map<String,Object> mm = (Map<String,Object>)listMap.get("object");
				if(mm!=null&&mm.size()>0){
					for(String k:mm.keySet()) {
						PurchaseSupplierItems purchasesupplier = new PurchaseSupplierItems();
						//k:gongyingshang  id
						//you biao de qi ta data
						Map<String, Object> ms = (Map<String, Object>) mm.get(k);
						if (ms!= null&&ms.size()>0) {
							String supId = (String) ms.get("supplierId");
							Integer sid = Integer.parseInt(supId.trim());
							String supName = (String) ms.get("supplierName");
							String count = ms.get("weight").toString();
							Integer c = Integer.parseInt(count.trim());
							String numbers = ms.get("num").toString();
							Long n = Long.parseLong(numbers.trim());
							String price = ms.get("prize").toString();
							Double ps = Double.parseDouble(price.trim());
							String singleCount = ms.get("singleCount").toString();
							Long counts = Long.parseLong(singleCount.trim());
							String singlePrize = ms.get("singlePrize").toString();
							Double pr = Double.parseDouble(singlePrize.trim());
							purchasesupplier.setCostPrice(pr);
							purchasesupplier.setNumbers(n);
							purchasesupplier.setSupplierName(supName);
							purchasesupplier.setSupplierId(sid);
							purchasesupplier.setCount(counts);
							purchasesupplier.setTotalWeight(c);
							purchasesupplier.setPrices(ps);
							purchaseService.insertPurSupplier(purchaseItems, purchasesupplier);
						}
					}
				}
				s=orderInfoService.updatePurchaseOrderCheckStatus(orderId);
			}
		}
//		Map<String,Object> map=new HashMap<String, Object>();
//		List<PurchaseInfoResult> purchaseInfoResults= purchaseService.selectAllPurchase(null);
//		map.put("PurchaseInfo",purchaseInfoResults);
		//return new ModelAndView("/business/onePurchase",map);
		//return selecAllPurchase(currentPage);
		return s;
	}
	/**
	 * 通过产品规格值获得相应的供应商
	 */
	@RequestMapping(value = "/selectSupplier.action")
	@ResponseBody
	public Object selectSupplierBysku(String skuID){
		//Integer id=Integer.parseInt(ItemId.trim());
		//JSONArray jsonArray=new JSONArray();
		List<SupplierProductResult> supplierProductResults=supplierProductService.selectBySku(skuID);
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("itemKey",itemId);
//		jsonObject.put("supplyerKey",supplierProductResults);
//		jsonArray.add(jsonObject);
		//jsonArray.add(supplierProductResults);
//		for(SupplierProductResult ii: supplierProductResults){
//			ii.setItemId(id);
//		}
		return JSON.toJSON(supplierProductResults);
	}
	/**
	 * 一个订单中多个产品
	 */
	@RequestMapping(value = "/selectItems.action")
	@ResponseBody
	public Object selectByOrderId(String orderId){
		Long id=Long.parseLong(orderId);
		OrderInfo orderInfo=orderInfoService.selectItems(id);
		List<OrderItems> list = orderInfo.getOrderItems();
		return JSON.toJSON(orderInfo);
	}
	/**
	 * 采购单的审核
	 */
	@RequestMapping(value = "/updatePurstate.action")
	@ResponseBody
	public Object updateSt(String message,String ids){
//		String []idArr = ids.split(",");
//		Integer a=null;
//		if(idArr!=null){
//			for(String id:idArr){
//				long i=Long.valueOf(id.trim());
//				a= purchaseService.updatePurchaseState(i);
//			}
//		}
		Integer a=purchaseService.updatePurchaseState(message, ids);
		return a;
	}



	private static <T>T parseUtil(Object value,Class<T> clazz){
		if(clazz == null){
			throw new RuntimeException("未知的类型转换");
		}
		if(clazz.equals(Boolean.class) || clazz.equals(boolean.class)){
			if(value==null || StringUtils.isBlank(value.toString())||value.toString().equals("false") || value.toString().equals("0")){
				return (T)Boolean.valueOf(false);
			}else{
				return (T)Boolean.valueOf(true);
			}
		}
		if(value == null || StringUtils.isBlank(value.toString())){
			return null;
		}
		if(value.getClass().equals(clazz)){
			return (T)value;
		}else{
			if(clazz.equals(String.class)){
				return (T)value.toString();
			}
			try{
				if(clazz.equals(Integer.class) || clazz.equals(int.class)){
					return (T)Integer.valueOf(value.toString());
				}else if(clazz.equals(Byte.class) || clazz.equals(byte.class)){
					return (T)Byte.valueOf(value.toString());
				}else if(clazz.equals(Short.class) || clazz.equals(short.class)){
					return (T)Short.valueOf(value.toString());
				}else if(clazz.equals(Float.class) || clazz.equals(float.class)){
					return (T)Float.valueOf(value.toString());
				}else if(clazz.equals(Double.class) || clazz.equals(double.class)){
					return (T)Double.valueOf(value.toString());
				}else if(clazz.equals(Long.class) || clazz.equals(long.class)){
					return (T)Long.valueOf(value.toString());
				}else if(clazz.equals(Character.class) || clazz.equals(char.class)){
					return (T)Character.valueOf(value.toString().charAt(0));
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}
		return null;
	}

	public static void main(String args[]){
		char d = parseUtil("abcd",char.class);
		System.out.println(d);
	}

	/**
	 * 需要采购的订单
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getOrderId.action",method = RequestMethod.POST)
	public Object getOrderId(HttpServletRequest request,String OrderId)throws Exception{
		try {
			UserResult user=(UserResult)request.getSession().getAttribute("userResult");
			Long id=user.getDepartment().getId();
			UserVo userVo = new UserVo();
			userVo.setId(id);
			//对商城的订单处理时加上小组
			String[] ids = OrderId.split(",");
			//判断处理的订单是否是商城的
			for (int i=0;i<ids.length;i++){
				//修改订单为已处理状态
				orderInfoService.updateOrderToHandled(ids[i]);
				if (ids[i].indexOf("zm")==-1){
					OrderInfo orderInfo = new OrderInfo();
					orderInfo.setOrderId(ids[i]);
					orderInfo.setDepartmentId(user.getDepartment().getId());
					orderInfo.setRoleId(user.getId());
					orderInfo.setDepartmentName(user.getDepartment().getName());
					orderInfo.setRealName(user.getRealName());
					orderInfoService.updateGroup(orderInfo);
				}

			}
			Map<String, Object> map = new HashMap<String, Object>();
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String purchaseCode = simpleDateFormat.format(date).toString();
			map.put("purchaseCode", purchaseCode);
			List<User> userResults = userService.selectPurchaser(userVo);
			map.put("userPurchaseResults", userResults);
			List<OrderItems> orderInfoResults = orderInfoService.selectOrdersByChecked(id,ids);
			map.put("orderInfoResults", orderInfoResults);
		   return new ModelAndView("/business/addPurchase", map);
		}catch (Exception e){
				throw e;
			}
		}
	/**
	 * 采购单详情
	 */
	@RequestMapping(value = "/getPurchaseById.action")
	@ResponseBody
	public Object getPurchaseById(String purchaseId) {
		try {
			Long id=Long.parseLong(purchaseId.trim());
			//PurchaseInfoResult purchaseInfoResult=purchaseService.selectOnePurchase(id);
			PurchaseInfo purchaseInfo=purchaseService.showOnePurchase(id);
			return JSON.toJSON(purchaseInfo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
