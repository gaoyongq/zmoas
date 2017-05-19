package com.zm.mall.client.service.business.bank;

import com.zm.mall.domain.business.bank.BankCard;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public interface BankCardService {
	//微信端银行卡的增加
	public  Integer insertBankCard(BankCard bankCard);
	//微信端银行卡的查询
	public List<BankCard> selectAllBankCard(BankCard bankCard);
	//微信端银行卡号的设置默认
	public  Integer setDefaultBankCard(BankCard bankCard);
	//微信端银行卡的删除
	public Integer deleteBankCard(BankCard bankCard);
	//微信端单张银行卡查询
	public BankCard selectOneBankCard(BankCard bankCard);
	//查找微信端默认卡号的支付密码
	public String selectDefaultPayPassword(BankCard bankCard);
}
