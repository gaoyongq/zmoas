package com.zm.mall.client.service.business.purchase;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.purchase.PurchaseInfoResult;
import com.zm.mall.client.vo.business.purchase.PurchaseInfoVo;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.business.purchase.PurchasePage;
import com.zm.mall.domain.business.purchase.PurchaseSupplierItems;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface PurchaseService {
	List<PurchaseInfoResult> selectAllPurchase(PurchaseInfoVo purchaseInfoVo);
	Integer  insertPurchase(PurchaseInfo purchaseInfo);
	Integer insertPurchaseItems(PurchaseInfo purchaseInfo,PurchaseItems purchaseItems);
	Integer insertPurSupplier(PurchaseItems purchaseItems,PurchaseSupplierItems  purchaseSupplierItems);
	Integer updatePurchaseState(String message,String purchaseId);
	List<PurchaseSupplierItems> selectById(Integer  id);
	Integer purchaseCountAll(PurchasePage purchasePage);
	List<PurchaseInfo> getAllPurchases(PurchasePage purchasePage);
	//+++
	Page selectAllIndentFinish( Page page,PurchaseItems purchaseItems);
	PurchaseItems selectOnePurchaseItems(String orderCode);
	void updateStateByOrderCode(String orderCode);
	PurchaseInfoResult selectOnePurchase(Long id);
	PurchaseInfo  showOnePurchase(Long  purchaseId);
	//统计利润的方法	
	PurchaseItems selectFinishPurchaseItems(String orderCode);
	void purchaseUpdateFinishState(String purchaseCode);
	List<PurchaseInfo> selectAllFinishState();
	String selectPurchaseItems(String purchaseCode);
	List<PurchaseItems> selectPurchaseItemsByPurchaseCode(String purchaseCode);
	PurchaseInfo selectOnePurchaseInfo(String purchaseCode);

}

