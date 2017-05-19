package com.zm.mall.service.business.bank.impl;

import com.zm.mall.client.service.business.bank.BankCardService;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.business.bank.BankCardDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.bank.BankCard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService{
    @Resource
    private BankCardDao bankCardDao;
	@Resource
	private UserListDao userListDao;
	@Override
	public Integer insertBankCard(BankCard bankCard) {
		BankCard bankCardList=bankCardDao.selectOneBankCard(bankCard);
		if(bankCardList==null){
			bankCard.setRequestStatus(1);
		}else {
			bankCard.setRequestStatus(0);
		}
		Integer i=bankCardDao.insertBankCard(bankCard);
		return i;
	}

	@Override
	public List<BankCard> selectAllBankCard(BankCard bankCard) {
		List<BankCard> bankCardList= bankCardDao.selectAllBankCard(bankCard);
		return bankCardList;
	}

	@Override
	public Integer setDefaultBankCard(BankCard bankCard) {
		bankCard.setRequestStatus(1);
		Integer ii=bankCardDao.setDefaultBankCard(bankCard);
		return ii;
	}

	@Override
	public Integer deleteBankCard(BankCard bankCard) {
		Integer iii=bankCardDao.deleteBankCard(bankCard);
		return iii;
	}

	@Override
	public BankCard selectOneBankCard(BankCard bankCard) {
		BankCard bankCardOne=bankCardDao.selectOneBankCard(bankCard);
		return bankCardOne;
	}

	@Override
	public String selectDefaultPayPassword(BankCard bankCard) {
		Users users=new Users();
		users.setPluteformid(bankCard.getPluteformid());
		users.setOpenId(bankCard.getOpenId());
		Users u=userListDao.selUsersByAppIdopenId(users);
		bankCard.setUserId(u.getUserId());
		String password=bankCardDao.selectDefaultPayPassword(bankCard);
		return password;
	}
}
