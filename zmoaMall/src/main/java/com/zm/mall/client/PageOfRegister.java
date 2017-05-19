package com.zm.mall.client;



import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.Register;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * @author liyanshuai
 * @since 2016-02-01
 * @param <T> 需要分页的实体
 * 
 * @modify liyanshuai(zhaobaoxin)
 * 
 */
public class PageOfRegister<T> implements Serializable{

	private static final long serialVersionUID = -8895742541737948908L;
	/**
	 * 总数
	 */
	private int totalCount;
	/**
	 * 页大小
	 */
	private int pageSize = 31;
	/**
	 * 当前页
	 */
	private int currentPage =1;
	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 内容对象
	 */

	//签到
	private List<Register> registers;
	private  List<MoneyManagerVo> moneyManagerVo;


	private List<ProductInfo> resultProductInfo;
	private Integer beginNumber;
	private ProductInfo productInfo = new ProductInfo();

	public PageOfRegister() {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<ProductInfo> getResultProductInfo() {
		return resultProductInfo;
	}

	public void setResultProductInfo(List<ProductInfo> resultProductInfo) {
		this.resultProductInfo = resultProductInfo;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Integer getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}

	public List<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(List<Register> registers) {
		this.registers = registers;
	}

	public List<MoneyManagerVo> getMoneyManagerVo() {
		return moneyManagerVo;
	}

	public void setMoneyManagerVo(List<MoneyManagerVo> moneyManagerVo) {
		this.moneyManagerVo = moneyManagerVo;
	}

	@Override
	public String toString() {
		return "Page{" +
				"totalCount=" + totalCount +
				", pageSize=" + pageSize +
				", currentPage=" + currentPage +
				", totalPages=" + totalPages +
				", resultProductInfo=" + resultProductInfo +
				", beginNumber=" + beginNumber +
				", productInfo=" + productInfo +
				'}';
	}
}