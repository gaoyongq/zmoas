package com.zm.mall.web.wechat;

import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.service.business.bank.BankCardService;
import com.zm.mall.domain.business.bank.BankCard;
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
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/business/bank")
public class BankCardController extends BaseController {
	@Resource
	private BankCardService bankCardService;

	/**
	 * 微信端银行卡的添加
	 * @param request
	 * @return
	 */
	@RequestMapping("insertBankCard.action")
	@ResponseBody
	public Integer insertBankCard(HttpServletRequest request){
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
			String bankCards =sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			Integer i=bankCardService.insertBankCard(bankCard);
			return  i;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询微信下该用户的所有银行卡
	 * @param request
	 * @return
	 */
	@RequestMapping("selectAllBankCard.action")
	@ResponseBody
	public List<BankCard> selectAllBankCard(HttpServletRequest request){
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
			String bankCards =sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			List<BankCard> bankCardList=bankCardService.selectAllBankCard(bankCard);
			return bankCardList;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 微信端银行卡的默认设置
	 * @param request
	 * @return
	 */
	@RequestMapping("setDefaultBankCard.action")
	@ResponseBody
	public Integer setDefaultBankCard(HttpServletRequest request){
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
			String bankCards =sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			Integer i=bankCardService.setDefaultBankCard(bankCard);
			return  i;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 微信端银行卡的删除
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteBankCard.action")
	@ResponseBody
	public  Integer deleteBankCard(HttpServletRequest request){
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
			String bankCards =sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			Integer ii=bankCardService.deleteBankCard(bankCard);
			return ii;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 微信端单独一张银行卡的详情查询
	 * @param request
	 * @return
	 */
	@RequestMapping("selectOneBankCard.action")
	@ResponseBody
	public  BankCard selectOneBankCard(HttpServletRequest request){
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
			String bankCards =sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			BankCard bankCard1=bankCardService.selectOneBankCard(bankCard);
			return bankCard1;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查找微信端默认卡号的支付密码
	 * @param request
	 * @return
	 */
	@RequestMapping("selectDefaultPayPassword.action")
	@ResponseBody
	public  String selectDefaultPayPassword(HttpServletRequest request){
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
			String bankCards=sb.toString();
			isr.close();
			BankCard bankCard=JSONObject.parseObject(bankCards, BankCard.class);
			String password=bankCardService.selectDefaultPayPassword(bankCard);
			return password;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
