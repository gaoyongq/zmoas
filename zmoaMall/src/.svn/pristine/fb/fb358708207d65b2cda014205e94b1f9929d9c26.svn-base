package com.zm.mall.web.business.shopUser;

import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.service.business.shopUser.ShopUserService;
import com.zm.mall.client.vo.business.shopUser.ShopUserVo;
import com.zm.mall.domain.business.shopUser.ShopUser;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
@Controller
@RequestMapping("business")
public class ShopUserCotroller extends BaseController {
	@Resource
	private ShopUserService shopUserService;

	/**
	 * 根据userId和shopId查询用户店铺关联表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByUserIdhShopId.action")
	@ResponseBody
	public  Object selectByUserIdhShopId(HttpServletRequest request)throws  Exception{
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
			String shopUser =sb.toString();
			isr.close();
			ShopUserVo shopUserVo=JSONObject.parseObject(shopUser, ShopUserVo.class);
			List<ShopUser> shopUserList=shopUserService.selectShopUser(shopUserVo);
			JSONObject object=new JSONObject();
			return object.toJSONString(shopUserList);
		}catch (Exception e){
			throw e;
		}
	}

	/**
	 * 增加用户和店铺的关联表的数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertShopUser.action")
	@ResponseBody
	public Object insertShopUser(HttpServletRequest request)throws  Exception{
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
			String shopUser =sb.toString();
			isr.close();
			ShopUserVo shopUserVo= JSONObject.parseObject(shopUser, ShopUserVo.class);
			Integer i=shopUserService.insertShopUser(shopUserVo);
			return i;
		}catch (Exception e){
			throw e;
		}
	}

}
