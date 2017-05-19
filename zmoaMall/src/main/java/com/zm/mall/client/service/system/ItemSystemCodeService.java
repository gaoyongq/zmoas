package com.zm.mall.client.service.system;






import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.ItemSystemCodeResult;
import com.zm.mall.client.vo.system.ItemSystemCodeVo;
import com.zm.mall.domain.system.ItemSystemCode;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
public interface ItemSystemCodeService {



    /**
     * 根据用户的条件查询用户的信息
     * @return
     */
    List<ItemSystemCodeResult>  selectSystemCode(ItemSystemCodeVo itemsystemCodeVo);
    Page selectProductInfo(Page page);
    void addItemSystemCode(ItemSystemCode itemSystemCode);
    void updateItemSystemCode(ItemSystemCode itemSystemCode);
   /* List<SystemCode> selectAllSystemCode(SystemCode systemCode)throws Exception;
    void deleteMenu(SystemCode systemCode);
    void updateMenu(SystemCode systemCode);
    void addMenu(SystemCode systemCode);*/


}
