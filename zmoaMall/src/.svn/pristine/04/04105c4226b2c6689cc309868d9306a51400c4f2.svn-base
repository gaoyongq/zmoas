package com.zm.mall.service.system.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.ItemSystemCodeResult;
import com.zm.mall.client.service.system.ItemSystemCodeService;
import com.zm.mall.client.vo.system.ItemSystemCodeVo;
import com.zm.mall.dao.system.ItemSystemCodeDao;
import com.zm.mall.domain.system.ItemSystemCode;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("ItemSystemCodeService")
public class ItemSystemCodeServiceImpl implements ItemSystemCodeService {

    private static Log log = LogFactory.getLog(ItemSystemCodeService.class);
    @Autowired
    private ItemSystemCodeDao itemSystemCodeDao;

    /**
     * 根据条件查询用户的信息
     * @return
     */
    @Override
    public List<ItemSystemCodeResult>  selectSystemCode(ItemSystemCodeVo itemSystemCodeVo) {
        //对象类型转换
        ItemSystemCode itemsystemCode =  SpaceBeanCopy.itemSystemCodeVoToSystemCode(itemSystemCodeVo);

        //查询数据库是否存在该用户。
        List<ItemSystemCode> list = itemSystemCodeDao.selectByContion(itemsystemCode);
        if(list != null && list.size()>0){
            List<ItemSystemCodeResult> itemSystemCodeList = SpaceBeanCopy.itemSystemCodeToListResult(list);
            return itemSystemCodeList;
        }
        return null;
    }

   @Override
    public Page selectProductInfo(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<ItemSystemCode> list = itemSystemCodeDao.selectByLimit(page);
        Integer pageCount = itemSystemCodeDao.selectAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        page.setNextCurrentPage(page.getCurrentPage()+1);
       if(page.getCurrentPage()-1>=1){
           page.setFrontCurrentPage(page.getCurrentPage()-1);
       }else{
           page.setFrontCurrentPage(0);
       }
        if (list !=null && list.size()>0){
            page.setResultItemSystemCode(list);
            return page;
        }
        return null;
    }

    @Override
    public void addItemSystemCode(ItemSystemCode itemSystemCode) {
        itemSystemCodeDao.addItemSystemCode(itemSystemCode);
    }
    @Override
    public void updateItemSystemCode(ItemSystemCode itemSystemCode) {
        itemSystemCodeDao.updateItemSystemCode(itemSystemCode);
    }

}
