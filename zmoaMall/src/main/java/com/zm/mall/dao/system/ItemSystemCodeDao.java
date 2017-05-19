package com.zm.mall.dao.system;



import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.ItemSystemCode;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface ItemSystemCodeDao extends BaseDao<ItemSystemCode> {

    /**
     * 根据条件查询用户的信息
     * @return
     */
    List<ItemSystemCode> selectByContion(ItemSystemCode itemSystemCode);
    List<ItemSystemCode> selectByLimit(Page page);
    Integer selectAllCount(Page page);
    void addItemSystemCode(ItemSystemCode itemSystemCode);
    void updateItemSystemCode(ItemSystemCode itemSystemCode);
   /* List<SystemCode> selectAllByContion(SystemCode systemCode);
    void deleteMenu(SystemCode systemCode);
    void updateMenu(SystemCode systemCode);
    void addMenu(SystemCode systemCode);*/

}
