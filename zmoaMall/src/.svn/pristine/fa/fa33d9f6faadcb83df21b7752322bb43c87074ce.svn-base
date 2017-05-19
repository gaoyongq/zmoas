package com.zm.mall.dao.business.bank.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.bank.BankCardDao;
import com.zm.mall.domain.business.bank.BankCard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class BankCardDaoImpl extends BaseDaoImpl<BankCard> implements BankCardDao{
	private final static String NAMESPACE = "com.zm.mall.dao.business.bank.BankCardDao.";
	@Override
	public String getNameSpace(String statement) {
		return NAMESPACE+statement;
	}

	@Override
	@Transactional
	public Integer insertBankCard(BankCard bankCard) {
		return sqlTemplate.insert(getNameSpace("insertBankCard"),bankCard);
	}

	@Override
	public List<BankCard> selectAllBankCard(BankCard bankCard) {
		return sqlTemplate.selectList(getNameSpace("selectAllBankCard"),bankCard);
	}

	@Override
	@Transactional
	public Integer setDefaultBankCard(BankCard bankCard) {
		return sqlTemplate.update(getNameSpace("setDefaultBankCard"),bankCard);
	}

	@Override
	@Transactional
	public Integer deleteBankCard(BankCard bankCard) {
		return sqlTemplate.delete(getNameSpace("deleteBankCard"),bankCard);
	}

	@Override
	public BankCard selectOneBankCard(BankCard bankCard) {
		return sqlTemplate.selectOne(getNameSpace("selectOneBankCard"),bankCard);
	}

	@Override
	public String selectDefaultPayPassword(BankCard bankCard) {
		return sqlTemplate.selectOne(getNameSpace("selectDefaultPayPassword"),bankCard);
	}
}
