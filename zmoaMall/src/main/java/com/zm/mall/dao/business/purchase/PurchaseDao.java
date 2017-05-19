package com.zm.mall.dao.business.purchase;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.business.purchase.PurchasePage;
import com.zm.mall.domain.business.purchase.PurchaseSupplierItems;

import com.zm.mall.client.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface PurchaseDao extends BaseDao<PurchaseInfo> {
	Integer insertPuchaseInfo(PurchaseInfo purchaseInfo);
	PurchaseInfo selectPurchaseInfoId(PurchaseInfo purchaseInfo);
	Integer insertPurchaseItems(PurchaseItems purchaseItems);
	Integer insertPurchaseSupplier(PurchaseSupplierItems purchaseSupplierItems);
	Integer updatePurchaseState(String  ids);
	Integer updateNoPurchaseState(String ids);
	List<PurchaseSupplierItems> selectBySupplierId(Integer id);
	Integer selectPurchaseAllCount(PurchasePage purchasePage);
	List<PurchaseInfo> selectAllPurchase(PurchasePage purchasePage);
	//+++++++
	List<PurchaseInfo> selectAll();
	List<PurchaseItems> selectByLimit(Page page);
	Integer selectAllCount(Page page);
	PurchaseItems selectOnePurchaseItems(String orderCode);
	/*List<PurchaseItems> selectProductName(String orderCode);*/
	List<PurchaseItems> selectOrderCodeAll(String orderCode);
	PurchaseItems selectPurchaseCodeByorderCode(String orderCode);
	/*void updateStateByPurchaseCode(String purchaseCode);*/
	void updateStateByOrderCode(String OrderCode);
	PurchaseInfo   showOnePurchase(Long id);
	void purchaseUpdateFinishState(String purchaseCode);

	List<PurchaseInfo>selectAllFinishState();
	List<PurchaseItems> selectPurchaseItems(String purchaseCode);
	List<PurchaseItems> selectPurchaseItemsNoRepetition(String purchaseCode);
	//目的查询架子费
	PurchaseInfo selectOnePurchaseInfo(String purchaseCode);

}
