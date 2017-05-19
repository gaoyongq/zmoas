package com.zm.mall.service.business.purchase.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.purchase.PurchaseInfoResult;
import com.zm.mall.client.service.business.purchase.PurchaseService;
import com.zm.mall.client.vo.business.purchase.PurchaseInfoVo;
import com.zm.mall.dao.business.purchase.PurchaseDao;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.business.purchase.PurchasePage;
import com.zm.mall.domain.business.purchase.PurchaseSupplierItems;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService{
	private static Log log = LogFactory.getLog(PurchaseService.class);
	@Autowired
	private PurchaseDao purchaseDao;



	/**
	 * 查询所有采购单
	 * @param purchaseInfoVo
	 * @return
	 */
	public List<PurchaseInfoResult> selectAllPurchase(PurchaseInfoVo purchaseInfoVo){
			PurchaseInfo purchaseInfo= SpaceBeanCopy.purchaseInfoVoToPurchase(purchaseInfoVo);
			List<PurchaseInfo>purchaseInfos= purchaseDao.select(purchaseInfo);
			if(purchaseInfos!=null&purchaseInfos.size()>0){
				List<PurchaseInfoResult> purchaseInfoResults=new ArrayList<PurchaseInfoResult>();
				for(PurchaseInfo pp:purchaseInfos){
					PurchaseInfoResult purchaseInfoResult=SpaceBeanCopy.purchaseToPurchaseInfoResult(pp);
					purchaseInfoResults.add(purchaseInfoResult);
				}
				return purchaseInfoResults;
			}
			return null;
		}

	/**
	 * 增加采购单主单
	 * @param purchaseInfo
	 * @return
	 */
	@Override
	public Integer insertPurchase(PurchaseInfo purchaseInfo) {
		Integer a=purchaseDao.insertPuchaseInfo(purchaseInfo);
//		PurchaseInfo purchaseInfo1=purchaseDao.selectOne(purchaseInfo);
//		purchaseInfo.setPurchaseId(purchaseInfo1.getPurchaseId());
//		purchaseItems.setPurchaseId(purchaseInfo.getPurchaseId());
//		purchaseItems.setPurchaseCode(purchaseInfo.getPurchaseCode());
//	Integer aa=purchaseDao.insertPurchaseItems(purchaseItems);
		int c=a;
		return c;
	}

	/**
	 * 增加采购单产品子单
	 * @param purchaseInfo
	 * @param purchaseItems
	 * @return
	 */
	@Override
	public Integer insertPurchaseItems(PurchaseInfo purchaseInfo,PurchaseItems purchaseItems) {
		purchaseItems.setPurchaseId(purchaseInfo.getPurchaseId());
		purchaseItems.setPurchaseCode(purchaseInfo.getPurchaseCode());
		Integer aa=purchaseDao.insertPurchaseItems(purchaseItems);
		return aa;
	}

	/**
	 *  增加采购单供应商子单
	 * @param purchaseItems
	 * @param purchaseSupplierItems
	 * @return
	 */
	@Override
	public Integer insertPurSupplier(PurchaseItems purchaseItems, PurchaseSupplierItems  purchaseSupplierItems) {
		purchaseSupplierItems.setPurchaseItemId(purchaseItems.getItemId());
		purchaseSupplierItems.setPurchaseItemCode(purchaseItems.getPurchaseCode());
		Integer s=purchaseDao.insertPurchaseSupplier(purchaseSupplierItems);
		return s;
	}

	/**
	 * 采购单审核
	 * @param purchaseId
	 * @return
	 */
	@Override
	public Integer updatePurchaseState(String message,String  purchaseId) {
		Integer an = 0;
		Integer a =0;
		if("yes".equals(message)) {
			 an=purchaseDao.updatePurchaseState(purchaseId);
		}else{
			a=purchaseDao.updateNoPurchaseState(purchaseId);
		}
		return an+a;
	}
	/**
	 * 根据供应商ID查询采购
	 */
	@Override
	public List<PurchaseSupplierItems> selectById(Integer id) {
		return purchaseDao.selectBySupplierId(id);
	}

	/**
	 * 采购单详情
	 * @param id
	 * @return
	 */
	@Override
	public PurchaseInfoResult selectOnePurchase(Long id) {
		PurchaseInfoVo purchaseInfoVo=new PurchaseInfoVo();
		purchaseInfoVo.setPurchaseId(id);
		PurchaseInfo purchaseInfo=SpaceBeanCopy.purchaseInfoVoToPurchase(purchaseInfoVo);
		PurchaseInfo pu= purchaseDao.selectOne(purchaseInfo);
		PurchaseInfoResult purchaseInfoResult=SpaceBeanCopy.purchaseToPurchaseInfoResult(pu);
		return purchaseInfoResult;
	}

	/**
	 * 查询采购单分页
	 * @param purchasePage
	 * @return
	 */
	@Override
	public Integer purchaseCountAll(PurchasePage purchasePage) {
		return purchaseDao.selectPurchaseAllCount(purchasePage);
	}


	@Override
	public List<PurchaseInfo> getAllPurchases(PurchasePage purchasePage) {
		return purchaseDao.selectAllPurchase(purchasePage);
	}


	/**
	 * selectAllIndentFinish分页方法   并完成货物种类查询
	 * @param page
	 * @param purchaseItems
	 * @return
	 */@Override
@Transactional
	public Page selectAllIndentFinish(Page page,PurchaseItems purchaseItems) {

		page.setPurchaseItems(purchaseItems);
		page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
		List<PurchaseItems> list = purchaseDao.selectByLimit(page);

		for(int i=0; i<list.size(); i++){
			String orderCode =list.get(i).getOrderCode();

			Integer orderNumber=0;
			Integer realNumber=0;
			Integer realWeigth=0;
			String  purchaseCode=null;
			String kind=null;
			List<PurchaseItems> PList =purchaseDao.selectOrderCodeAll(orderCode);

			//遍历获取每个订单的 合计、种类		
			for (int j=0;j<PList.size();j++){
				orderNumber += PList.get(j).getNumber();
				realNumber +=PList.get(j).getPurchaseN();
				realWeigth +=PList.get(j).getPurchaseW();
				kind += PList.get(j).getProductStyles()+",";
			}
			purchaseCode = PList.get(0).getPurchaseCode();
			String[] kinds=kind.split(",");
			String zl="";
			for(int k=0;k<kinds.length;k++){
				zl+=""+kinds[k];
			}
			if(zl.contains("白")&zl.contains("艺")){
				//System.out.println(zl);
				list.get(i).setKind("白玻,艺玻");
			}else if(zl.contains("白")){
				//System.out.println(zl);
				list.get(i).setKind("白玻");
			}else {
				//System.out.println(zl);
				list.get(i).setKind("艺玻");
			}


			list.get(i).setOrderNumber(orderNumber);
			list.get(i).setRealNumber(realNumber);
			list.get(i).setRealWeigth(realWeigth);
			list.get(i).setPurchaseCode(purchaseCode);

		}


		Integer pageCount = purchaseDao.selectAllCount(page);
		page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
		if (list !=null && list.size()>0){
			page.setResultPurchaseItems(list);
			return page;
		}
		return null;
	}

	/**
	 * selectOnePurchaseItems 根据订单编码 去重查询 并完成货物种类查询
	 * @param orderCode
	 * @return
	 */	@Override
	@Transactional
	public PurchaseItems selectOnePurchaseItems(String orderCode) {
		PurchaseItems purchaseItems =purchaseDao.selectOnePurchaseItems(orderCode);

		Integer orderNumber=0;
		Integer realNumber=0;
		Integer realWeigth=0;
		String kind=null;
		List<PurchaseItems> PList =purchaseDao.selectOrderCodeAll(orderCode);

		//遍历获取每个订单的 合计、种类
		for (int j=0;j<PList.size();j++){
			orderNumber += PList.get(j).getNumber();
			realNumber +=PList.get(j).getPurchaseN();
			realWeigth +=PList.get(j).getPurchaseW();
			kind += PList.get(j).getProductStyles();
		}
		//String kind = PList.get(0).getProductStyles();
		String[] kinds=kind.split(",");
		String zl="";
		for(int k=0;k<kinds.length;k++){
			zl+=""+kinds[k];
		}
		if(zl.contains("白")&zl.contains("艺")){
			//System.out.println(zl);
			purchaseItems.setKind("白玻,艺玻");
		}else if(zl.contains("白")){
			//System.out.println(zl);
			purchaseItems.setKind("白玻");
		}else {
			//System.out.println(zl);
			purchaseItems.setKind("艺玻");
		}

		purchaseItems.setPurchaseCode(PList.get(0).getPurchaseCode());
		purchaseItems.setShipAddress(PList.get(0).getShipAddress());
		purchaseItems.setShipCellPhone(PList.get(0).getShipCellPhone());
		purchaseItems.setOrderNumber(orderNumber);
		purchaseItems.setRealNumber(realNumber);
		purchaseItems.setRealWeigth(realWeigth);
		return purchaseItems;
	}
		@Override
	public void updateStateByOrderCode(String orderCode) {
		purchaseDao.updateStateByOrderCode(orderCode);
	}



	/**
	 * 采购单详情
	 * @param
	 * @return
	 */
	@Override
	public PurchaseInfo showOnePurchase(Long purchaseId) {
//		PurchaseInfo purchaseInfo=new PurchaseInfo();
//		purchaseInfo.setPurchaseId(purchaseId);
		return purchaseDao.showOnePurchase(purchaseId);
		//return purchaseDao.selectOne(purchaseInfo);
	}

	
	//统计利润的方法
	@Override
	public PurchaseItems selectFinishPurchaseItems(String orderCode) {
		PurchaseItems purchaseItems =purchaseDao.selectOnePurchaseItems(orderCode);

		//PurchaseItems purchaseItems2 = purchaseDao.selectPurchaseCodeByorderCode(orderCode);

		Integer orderNumber=0;
		Integer realNumber=0;
		Integer realWeigth=0;
		Double orderMoney=0.00;
		Double realMoney=0.00;

		//String  purchaseCode=null;
		String kind=null;
		List<PurchaseItems> PList =purchaseDao.selectOrderCodeAll(orderCode);
		//purchaseCode = PList.get(0).getPurchaseCode();

		//遍历获取每个订单的 合计、种类
		for (int j=0;j<PList.size();j++){
			orderNumber += PList.get(j).getNumber();
			realNumber +=PList.get(j).getPurchaseN();
			realWeigth +=PList.get(j).getPurchaseW();
			kind += PList.get(j).getProductStyles();
			/*orderMoney += (PList.get(j).getNumber() * PList.get(j).getPurchasePrice());*/
			int number =PList.get(j).getNumber();
			Double price = PList.get(j).getPurchasePrice();
			orderMoney +=(number*price);
			realMoney  += PList.get(j).getPurchaseN()*PList.get(j).getPurchasePrice();
		}
		//String kind = PList.get(0).getProductStyles();
		String[] kinds=kind.split(",");
		String zl="";
		for(int k=0;k<kinds.length;k++){
			zl+=""+kinds[k];
		}
		if(zl.contains("白")&zl.contains("艺")){
			//System.out.println(zl);
			purchaseItems.setKind("白玻,艺玻");
		}else if(zl.contains("白")){
			//System.out.println(zl);
			purchaseItems.setKind("白玻");
		}else {
			//System.out.println(zl);
			purchaseItems.setKind("艺玻");
		}
		purchaseItems.setOrderNumber(orderNumber);
		purchaseItems.setRealNumber(realNumber);
		purchaseItems.setRealWeigth(realWeigth);
		purchaseItems.setOrderMoney(orderMoney);
		purchaseItems.setRealMoney(realMoney);
		//purchaseItems.setPurchaseCode(purchaseCode);
		return purchaseItems;
	}

	@Override
	public void purchaseUpdateFinishState(String purchaseCode) {
		purchaseDao.purchaseUpdateFinishState(purchaseCode);
	}

	@Override
	public List<PurchaseInfo> selectAllFinishState() {
		return purchaseDao.selectAllFinishState();
	}

	@Override
	public  String selectPurchaseItems(String purchaseCode) {
		List<PurchaseItems> list = purchaseDao.selectPurchaseItems(purchaseCode);
		for (int i=0;i<list.size();i++){
			if (list.get(i).getOrderState()!=2){
				return "0";
			}
		}
		return "1";
	}

	@Override
	public List<PurchaseItems> selectPurchaseItemsByPurchaseCode(String purchaseCode) {
		List<PurchaseItems> list=  purchaseDao.selectPurchaseItemsNoRepetition(purchaseCode);
		List<PurchaseItems> lists=new ArrayList<PurchaseItems>();
		for (int i=0;i<list.size();i++){
			String orderCode =list.get(i).getOrderCode();

			PurchaseItems purchaseItems =purchaseDao.selectOnePurchaseItems(orderCode);

			//PurchaseItems purchaseItems2 = purchaseDao.selectPurchaseCodeByorderCode(orderCode);

			Integer orderNumber=0;
			Integer realNumber=0;
			Integer orderWeigth=0;
			Integer realWeigth=0;

			//价格字段
			Double orderMoney =0.00;
			Double realMoney =0.00;
			//String  purchaseCode=null;
			String kind=null;
			List<PurchaseItems> PList =purchaseDao.selectOrderCodeAll(orderCode);
			//purchaseCode = PList.get(0).getPurchaseCode();

			//遍历获取每个订单的 合计、种类
			for (int j=0;j<PList.size();j++){
				orderNumber += PList.get(j).getNumber();
				realNumber +=PList.get(j).getPurchaseN();
				orderWeigth +=PList.get(j).getPurchaseW();
				realWeigth +=PList.get(j).getPurchaseW();
				kind += PList.get(j).getProductStyles();
				//算价格
				int number =PList.get(j).getPurchaseN();
				Double sell = PList.get(j).getProductSell();
				Double price=PList.get(j).getPurchasePrice();
				orderMoney +=(PList.get(j).getNumber()*sell);
				realMoney  += (price);

			}
			//String kind = PList.get(0).getProductStyles();
			String[] kinds=kind.split(",");
			String zl="";
			for(int k=0;k<kinds.length;k++){
				zl+=""+kinds[k];
			}
			if(zl.contains("白")&zl.contains("艺")){
				//System.out.println(zl);
				purchaseItems.setKind("白玻,艺玻");
			}else if(zl.contains("白")){
				//System.out.println(zl);
				purchaseItems.setKind("白玻");
			}else {
				//System.out.println(zl);
				purchaseItems.setKind("艺玻");
			}
			purchaseItems.setOrderNumber(orderNumber);
			purchaseItems.setRealNumber(realNumber);
			purchaseItems.setOrderWeigth(orderWeigth);
			purchaseItems.setRealWeigth(realWeigth);
			purchaseItems.setOrderMoney(orderMoney);
			purchaseItems.setRealMoney(realMoney);
			//purchaseItems.setPurchaseCode(purchaseCode);
			lists.add(purchaseItems);
		}
		return lists;
	}

	@Override
	public PurchaseInfo selectOnePurchaseInfo(String purchaseCode) {
		return purchaseDao.selectOnePurchaseInfo(purchaseCode);
	}



	/*@Override
	public List<PurchaseItems> selectProductName(String orderCode) {
		List<PurchaseItems> list = purchaseDao.selectProductName(orderCode);
		return list;
	}*/
}
