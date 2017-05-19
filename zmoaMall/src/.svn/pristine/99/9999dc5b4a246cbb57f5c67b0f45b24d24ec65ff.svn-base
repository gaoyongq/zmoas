package com.zm.mall.dao.business.purchase.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.purchase.PurchaseDao;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.business.purchase.PurchasePage;
import com.zm.mall.domain.business.purchase.PurchaseSupplierItems;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class PurchaseDaoImpl extends BaseDaoImpl<PurchaseInfo> implements PurchaseDao{
	private final static String NAMESPACE = "com.zm.mall.dao.business.purchase.PurchaseDao.";
	@Override
	public String getNameSpace(String statement) {
		return NAMESPACE+statement;
	}
	@Override
	public List<PurchaseInfo> select(PurchaseInfo purchaseInfo) {
		return sqlTemplate.selectList(getNameSpace("select"), purchaseInfo);
	}

	/**
	 * 插入主采购单
	 * @param purchaseInfo
	 * @return
	 */

	@Override
	public Integer insertPuchaseInfo(PurchaseInfo purchaseInfo) {
		return sqlTemplate.insert(getNameSpace("insertPuchaseInfo"),purchaseInfo);
	}

	/**
	 * 查询主采购单ID
	 * @param purchaseInfo
	 * @return
	 */
	@Override
	public PurchaseInfo selectPurchaseInfoId(PurchaseInfo purchaseInfo) {
		return sqlTemplate.selectOne(getNameSpace("selectPurchaseInfoId"),purchaseInfo);
	}

	/**
	 * 插入采购单子单
	 * @param purchaseItems
	 * @return
	 */
	@Override
	public Integer insertPurchaseItems(PurchaseItems purchaseItems) {
		return sqlTemplate.insert(getNameSpace("insertPurchaseItems"),purchaseItems);
	}
	/**
	 * 插入采购单供应商子单
	 * @param purchaseSupplierItems
	 * @return
	 */
	@Override
	public Integer insertPurchaseSupplier(PurchaseSupplierItems purchaseSupplierItems) {
		return sqlTemplate.insert(getNameSpace("insertPurchaseSupplier"),purchaseSupplierItems);
	}

	/**
	 * 审核通过功能
	 */
	@Override
	public Integer updatePurchaseState(String ids) {
		Integer oo=0;
		String []idArr = ids.split(",");
		for(String id:idArr) {
			long	i = Long.valueOf(id.trim());
			oo=sqlTemplate.update(getNameSpace("updatePurchaseState"), i);
		}
		return oo;
}
	/**
	 * 审核不通过功能
	 */
	@Override
	public Integer updateNoPurchaseState(String ids) {
		Integer oo=0;
		String []idArr = ids.split(",");
		for(String id:idArr) {
			long	i = Long.valueOf(id.trim());
			oo=sqlTemplate.update(getNameSpace("updateNoPurchaseState"), i);
		}
		return oo;
	}

	/**
	 * 根据供应商ID查询采购
	 *
	 */
	@Override
	public List<PurchaseSupplierItems> selectBySupplierId(Integer id) {
		List<PurchaseSupplierItems> list=sqlTemplate.selectList(getNameSpace("selectBySupplierId"), id);
		return list;
	}
	/**
	 * 采购单分页处理
	 * @param purchasePage
	 * @return
	 */
	@Override
	public Integer selectPurchaseAllCount(PurchasePage purchasePage) {
		Integer a=sqlTemplate.selectOne(getNameSpace("selectPurchaseCount"),purchasePage);
		return a;
	}

	@Override
	public List<PurchaseInfo> selectAllPurchase(PurchasePage purchasePage) {
		return sqlTemplate.selectList(getNameSpace("select"),purchasePage);
	}
	/**
	 * 采购单详情
	 * @param
	 * @return
	 */
	@Override
	public PurchaseInfo showOnePurchase(Long id) {
		return sqlTemplate.selectOne(getNameSpace("selectOne"), id);
	}

	@Override
	public PurchaseInfo selectOne(PurchaseInfo purchaseInfo) {
		return sqlTemplate.selectOne(getNameSpace("selectOne"), purchaseInfo);
	}

	@Override
	public List<PurchaseInfo> selectAll() {
		return sqlTemplate.selectList(getNameSpace("selectAll"));
	}

	@Override
	public List<PurchaseItems> selectByLimit(Page page) {
		return sqlTemplate.selectList(getNameSpace("selectByLimit"), page);
	}

	@Override
	public Integer selectAllCount(Page page) {
		return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
	}

	@Override
	public PurchaseItems selectOnePurchaseItems(String orderCode) {
		return sqlTemplate.selectOne(getNameSpace("selectOnePurchaseItems"),orderCode);
	}

	@Override
	public List<PurchaseItems> selectOrderCodeAll(String orderCode) {
		return sqlTemplate.selectList(getNameSpace("selectOrderCodeAll"), orderCode);
	}
	@Override
	public PurchaseItems selectPurchaseCodeByorderCode(String orderCode) {
		return sqlTemplate.selectOne(getNameSpace("selectPurchaseCodeByorderCode"),orderCode);
	}

	@Override
	public void updateStateByOrderCode(String OrderCode) {
		sqlTemplate.update(getNameSpace("updateStateByOrderCode"), OrderCode);
	}
	@Override
	public void purchaseUpdateFinishState(String purchaseCode) {
		sqlTemplate.update(getNameSpace("purchaseUpdateFinishState"), purchaseCode);
	}

	@Override
	public List<PurchaseInfo> selectAllFinishState() {
		return sqlTemplate.selectList(getNameSpace("selectAllFinishState"));
	}

	@Override
	public List<PurchaseItems> selectPurchaseItems(String purchaseCode) {
		return sqlTemplate.selectList(getNameSpace("selectPurchaseItems"),purchaseCode);
	}
	@Override
	public List<PurchaseItems> selectPurchaseItemsNoRepetition(String purchaseCode) {
		return sqlTemplate.selectList(getNameSpace("selectPurchaseItemsNoRepetition"),purchaseCode);
	}

	@Override
	public PurchaseInfo selectOnePurchaseInfo(String purchaseCode) {
		return sqlTemplate.selectOne(getNameSpace("selectOnePurchaseInfo"),purchaseCode);
	}
	/*@Override
	public void updateStateByPurchaseCode(String purchaseCode) {
		sqlTemplate.update(getNameSpace("updateStateByPurchaseCode"), purchaseCode);
	}*/



	/*@Override
	public List<PurchaseItems> selectProductName(String orderCode) {
		return sqlTemplate.selectList(getNameSpace("selectProductName"), orderCode);
	}*/




}
