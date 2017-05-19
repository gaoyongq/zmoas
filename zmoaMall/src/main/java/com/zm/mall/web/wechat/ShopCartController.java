package com.zm.mall.web.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.service.business.appointment.AppointmentOrderService;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.wechat.CartService;
import com.zm.mall.client.vo.business.product.ProductInfoVo;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.SkuData;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.wechat.WeChat_cart;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
@Controller
@RequestMapping("/business")
public class ShopCartController extends BaseController {
	@Resource
	private CartService cartService;
	@Resource
	private   OrderInfoService orderInfoService;
	@Resource
	private ProductInfoService productInfoService;
	@Resource
	private AppointmentOrderService appointmentOrderService;
	/**
	 * 查询购物车
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/wechatAction_cart.action")
	@ResponseBody
	public Object cartList(HttpServletRequest request) throws Exception{
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
			String userCode =sb.toString();
			isr.close();
			WeChat_cart ca= JSONObject.parseObject(userCode, WeChat_cart.class);
			String u=ca.getUserCode();
			List<WeChat_cart> cart = cartService.selectAllCart(u);
			JSONObject object=new JSONObject();
			String carts=object.toJSONString(cart);
			return carts;
		}catch (Exception e){
			throw e;
		}
	}

	/**
	 * 根据id查询产品 微信和APP端
	 */
	@RequestMapping(value = "/productId.action")
	@ResponseBody
	public Object productId(HttpServletRequest request) throws Exception{
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
			String cart =sb.toString();
			isr.close();
			JSONObject obj = JSONObject.parseObject(cart);
			ProductInfoVo productInfoVo=new ProductInfoVo();
			String productIds = obj.getString("productId");
			String appid = obj.getString("appid");
			String shopIds=obj.getString("shopId");
			Long productId=Long.parseLong(productIds.trim());
			if(shopIds!=null) {
				Long shopId = Long.parseLong(shopIds.trim());
				productInfoVo.setShopId(shopId);
			}
 			productInfoVo.setProductId(productId);
			productInfoVo.setPluteformid(appid);
			ProductInfo productInfo = productInfoService.productId(productInfoVo);
			//根据产品Id查询出对应规格
			List<AttributeInfo> attributeInfoList = productInfoService.selectAttribute(productInfoVo);
			//根据产品Id查询出对应规格的售价
			List<SkuData> skuData = orderInfoService.checkPrice(productInfoVo);
			//转换JSON
			JSONObject object=new JSONObject();
			/*String skuDatas = JSONObject.toJSONString(skuData);
			String attributeInfoLists = JSONObject.toJSONString(attributeInfoList);
			String productInfos = JSONObject.toJSONString(productInfo);*/
			object.put("productInfo",productInfo);
			object.put("attributeInfoList",attributeInfoList);
			object.put("skuData",skuData);
			return object;
		}catch (Exception e){
			throw e;
		}
	}

	/**
	 * 添加订单-微信 wq
	 *
	 */
	@RequestMapping(value = "/insertOrderByWX.action")
	@ResponseBody
	public Integer insertOderByWechat (HttpServletRequest request) throws Exception{
//        request.setCharacterEncoding("utf-8");
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
			String orderWX =sb.toString();
			isr.close();
//			ObjectMapper om = new ObjectMapper();
//			OrderInfo oderInfoWX= om.readValue(orderWX, OrderInfo.class);
			OrderInfo oderInfoWX= JSONObject.parseObject(orderWX, OrderInfo.class);
			oderInfoWX.setApplyVehicleStatus(0);
			oderInfoWX.setOrderCheckStatus(1);
			//oderInfoWX.setPurchasingStatus(0);
			/**
			 * shipEmail,用shipEmail传 购物车中选中结算的 购物车id  shipperId
			 * shipperId,用 shipperId 作为状态区分是购物车订单还是立即购买订单  0代表立即购买生成的订单，1代表购物车结算生成的订单
			 */
			String cartId=oderInfoWX.getShipEmail();
			Integer state=oderInfoWX.getShipperId();
			oderInfoWX.setShipEmail("");
			oderInfoWX.setShipperId(null);
		    String pluteformid = request.getParameter("pluteformid");
		   oderInfoWX.setPluteformid(pluteformid);
			Integer result = orderInfoService.insertOrder(oderInfoWX);
			if(result>0){
				if(state==1){
					int rr = cartService.updateCartProduct(cartId);
					if(rr>0){
						return rr;
					}else {
						return 0;
					}
				}else {
					return  result;
				}
			}
			return 0;
		}catch (Exception e){
			throw  e;
		}

	}
	/**
	 * 查询购物车数量
	 *
	 */
	@RequestMapping(value = "/wechatAction_cartnum.action")
	@ResponseBody
	public int findCartNum(HttpServletRequest request) throws  Exception{
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
		String userCode =sb.toString();
		isr.close();
		WeChat_cart cart= JSONObject.parseObject(userCode, WeChat_cart.class);
        int cartNum = cartService.selectAllCartNum(cart);
		return cartNum;
	}
	/**
	 * 购物车数量增减
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping( value = "/wechatAction_count.action")
	@ResponseBody
	public boolean count(HttpServletRequest request)throws Exception{
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
		String  ch=sb.toString();
		isr.close();
		WeChat_cart cart= JSONObject.parseObject(ch, WeChat_cart.class);
//		String plateFromId=cart.getUserName();
//		if (plateFromId.equals(-1)) {
			boolean aa = cartService.updateCartCount(cart);
			return aa;
//		}else {
//			String skuAttribute = cart.getSkuAttribute();//白玻:玻;版面:1243*1245
//			String[] skuArr = skuAttribute.split(";");
//			Map<String, String> map = new HashMap<String, String>();
//			for (String str : skuArr) {
//				String[] arr = str.split(":");
//				map.put(arr[0], arr[1]);
//			}
//			String value = map.get("版面");
//			List<WeChat_cart> cartList = cartService.getWeChatCart(cart);
//			Map<String, List<String>> mapList = new HashMap<String, List<String>>();
//			for (WeChat_cart cart1 : cartList) {
//				String[] sku = cart1.getSkuAttribute().split(";");
//				for (String str : sku) {
//					String[] arr = str.split(":");
//					if (mapList.get(arr[0]) == null) {
//						List<String> sList = new ArrayList<String>();
//						sList.add(arr[1]);
//						mapList.put(arr[0], sList);
//					} else {
//						mapList.get(arr[0]).add(arr[1]);
//					}
//				}
//			}
//			if (mapList.get("版面") != null && mapList.get("版面").contains(value)) {
//				boolean aa = cartService.updateCartCount(cart);
//				return aa;
//			}
//			return false;
//		}
	}
	/**
	 * 加入购物车
	 */
	@RequestMapping(value = "/wechatAction_add.action")
	@ResponseBody
	@Transactional
	public boolean addCart(HttpServletRequest request) throws Exception{
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
			String  cart=sb.toString();
			isr.close();
			WeChat_cart carts = JSONObject.parseObject(cart,WeChat_cart.class);
			String plateFromId=carts.getUserName();
//			if(plateFromId.equals(-1)) {
				boolean checkResult = cartService.addCartCheck(carts);
				if (checkResult == true) {
					boolean result = cartService.addCart(carts);
					return result;
				} else if (checkResult == false) {
					boolean addNumResult = cartService.addCartNum(carts);
					return addNumResult;
				} else {
					return false;
				}
//			}else {
//				String skuAttribute = carts.getSkuAttribute();//白玻:玻;版面:1243*1245
//				String[] skuArr = skuAttribute.split(";");
//				Map<String, String> map = new HashMap<String, String>();
//				for (String str : skuArr) {
//					String[] arr = str.split(":");
//					map.put(arr[0], arr[1]);
//				}
//				String value = map.get("版面");
//				List<WeChat_cart> cartList = cartService.getWeChatCart(carts);
//				Map<String, List<String>> mapList = new HashMap<String, List<String>>();
//				for (WeChat_cart cart1 : cartList) {
//					String[] sku = cart1.getSkuAttribute().split(";");
//					for (String str : sku) {
//						String[] arr = str.split(":");
//						if (mapList.get(arr[0]) == null) {
//							List<String> sList = new ArrayList<String>();
//							sList.add(arr[1]);
//							mapList.put(arr[0], sList);
//						} else {
//							mapList.get(arr[0]).add(arr[1]);
//						}
//					}
//				}
//				if (mapList.get("版面") != null && mapList.get("版面").contains(value)) {
//					boolean checkResult = cartService.addCartCheck(carts);
//					if(checkResult==false) {
//						boolean addNumResult = cartService.addCartNum(carts);
//						return addNumResult;
//					}else {
//						boolean result = cartService.addCart(carts);
//						return result;
//					}
//				} else {
//					boolean result = cartService.addCart(carts);
//					return result;
//				}
//			}
		}catch (Exception e){
			throw  e;
		}
	}

	/**
	 *删除购物车
	 * @param
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/wechatAction_del.action")
	@ResponseBody
	public int deleteCart(HttpServletRequest request)throws Exception{
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
			String  cartId=sb.toString();
			isr.close();

			//适配IOS
			if (cartId.startsWith("{")) {
				JSONObject jsonObject = JSONObject.parseObject(cartId);
				cartId = jsonObject.getString("cartId");
			}
		 	int result = cartService.updateCartProduct(cartId);
			return result;
		}catch (Exception e){
			throw e;
		}

	}
	/**
	 * 确认订单-购物车结算
	 */
	@RequestMapping(value = "/wechatAction_orderConfirmCart.action")
	@ResponseBody
	public Object orderConfirmCart(HttpServletRequest request) throws Exception{
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
			String  cart=sb.toString();
			isr.close();
			JSONObject obj = JSONObject.parseObject(cart);
			String shopCartStr=obj.getString("shopCartStr");
			String userCode=obj.getString("userCode");
			List<WeChat_cart> cart2Order = cartService.selectBuyCart(shopCartStr,userCode);
			String re=JSONObject.toJSONString(cart2Order);
			return re;
		}catch (Exception e){
			throw e;
		}
	}

	/**
	 * 根据regionId,productId获取运费
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getYunFeiWX.action")
	@ResponseBody
	public Object getYunFei(HttpServletRequest request)throws Exception{
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			is.close();
			String cart = sb.toString();
			isr.close();
//			JSONObject obj = JSONObject.parseObject(cart);
//			String regionid = obj.getString("regionId)");
//			String productid = obj.getString("productId");
//			Integer regionId=Integer.parseInt(regionid.trim());
			//Integer productId=Integer.parseInt(productid.trim());
//			Integer productId=null;
//			RegionExpressPrice regionExpressPrice = new RegionExpressPrice();
//			//判断productId为空时，根据regionId和TypeId为1查
//			if (productId == null | "".equals(productId)) {
//				regionExpressPrice.setRegionId(regionId);
//				regionExpressPrice.setTypId(1);
//			} else {
//				regionExpressPrice.setProductId(productId);
//				regionExpressPrice.setTypId(3);
//			}
			RegionExpressPrice regionExpressPrice = JSONObject.parseObject(cart,RegionExpressPrice.class);
			regionExpressPrice.setTypId(1);
			RegionExpressPrice regionPrice = orderInfoService.getYunFei(regionExpressPrice);
			return JSON.toJSONString(regionPrice);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * 查询微信用户-收货地址-姓名-电话-运费
	 */
	@RequestMapping(value = "/wechatAction_userInfo.action")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request) throws Exception{
		try{
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			is.close();
			String userCode = sb.toString();
			isr.close();

			//判断是否是json格式的数据--苹果只发送json数据
			if(userCode.contains("{")){
				JSONObject object = JSON.parseObject(userCode);
				userCode = object.getString("userCode");
			}

			ShippingAddress addresss = cartService.selectUserAddress(userCode);
			String address = JSONObject.toJSONString(addresss);
			return address;
		}catch (Exception e){
			throw  e;
		}
	}

	/**
	 * 根据微信端订单code去修改已付款状态
	 * orderStatus 0代表未付 1代表已付 2定金部分支付
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePay.action")
	@ResponseBody
	public Object getOrderCode(HttpServletRequest request)throws Exception{
		try{
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			is.close();
			String orderWX = sb.toString();
			isr.close();
			OrderInfo orderInfo=JSONObject.parseObject(orderWX, OrderInfo.class);
			Double orTotal=orderInfo.getOrderTotal();
			String orderCode=orderInfo.getOrderCode();
			OrderInfo ore;
			if(orderCode.endsWith("yy")){
				ore=appointmentOrderService.selectOneAppointment(orderInfo);
			}else {
				 ore = orderInfoService.selOrderByCodeAndBuyName(orderInfo);
			}
			Double orderTotal=ore.getOrderTotal();
			Double orderCostPrice=ore.getOrderCostPrice();
			if(orderInfo.getOrderCode().endsWith("dj")){
				//定金支付 3
				orderInfo.setOrderStatus(3);
				if(orTotal>=orderCostPrice){
					Integer ii=orderInfoService.updateOrderStatusWX(orderInfo);
					return ii;
				}else {
					orderInfo.setOrderStatus(-1);
					orderInfoService.updateOrderStatusWX(orderInfo);
					return -1;
				}
			}
			else  if(orderInfo.getOrderCode().endsWith("yy")){
				//修改预约表
				orderInfo.setOrderStatus(2);
				Integer i=orderInfoService.updateAppointmentStatusWX(orderInfo);
				return  i;
			}else{
				//全额支付 1
				orderInfo.setOrderStatus(1);
				if(orTotal>=orderTotal){
					Integer ii=orderInfoService.updateOrderStatusWX(orderInfo);
					return ii;
				}else {
					orderInfo.setOrderStatus(-1);
					orderInfoService.updateOrderStatusWX(orderInfo);
					return -1;
				}

			}
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * 添加预约-微信 wq
	 *
	 */
	@RequestMapping(value = "/insertAppointmentWX.action")
	@ResponseBody
	public Integer insertAppointmentWX (HttpServletRequest request) throws Exception{
//        request.setCharacterEncoding("utf-8");
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
			String orderWX =sb.toString();
			isr.close();
//			ObjectMapper om = new ObjectMapper();
//			OrderInfo oderInfoWX= om.readValue(orderWX, OrderInfo.class);
			OrderInfo oderInfoWX= JSONObject.parseObject(orderWX, OrderInfo.class);
			oderInfoWX.setApplyVehicleStatus(0);
			oderInfoWX.setOrderCheckStatus(1);
			//oderInfoWX.setPurchasingStatus(0);
			/**
			 * shipEmail,用shipEmail传 购物车中选中结算的 购物车id  shipperId
			 * shipperId,用 shipperId 作为状态区分是购物车订单还是立即购买订单  0代表立即购买生成的订单，1代表购物车结算生成的订单
			 */
			//String cartId=oderInfoWX.getShipEmail();
			//Integer state=oderInfoWX.getShipperId();
			oderInfoWX.setShipEmail("");
			oderInfoWX.setShipperId(null);
			String pluteformid = request.getParameter("pluteformid");
			oderInfoWX.setPluteformid(pluteformid);
			Integer result = orderInfoService.insertOrder(oderInfoWX);
			return result;
		}catch (Exception e){
			throw  e;
		}

	}
}
