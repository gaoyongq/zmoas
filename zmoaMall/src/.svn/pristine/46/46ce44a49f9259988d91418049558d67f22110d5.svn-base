package com.zm.mall.service.system.impl;
import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.ApplyCarService;

import com.zm.mall.dao.system.ApplyCarDao;
import com.zm.mall.domain.system.ApplyCar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13.
 */

@Service("applyCarService")
public class ApplyCarServiceImpl implements ApplyCarService{
    private static Log log= LogFactory.getLog(ApplyCarService.class);
    @Autowired
    private ApplyCarDao applyCarDao;

    @Override
    public void saveApplyCar(ApplyCar applyCar) {
        applyCarDao.saveApplyCar(applyCar);
    }

    @Override
    public Page selectApplyCarList(Page page,ApplyCar applyCar) {
        page.setApplyCar(applyCar);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<ApplyCar> list = applyCarDao.selectByLimit(page);

        Integer pageCount = applyCarDao.selectAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultApplyCar(list);
            return page;
        }
        return null;
    }

    /**
     * 真正添加车辆
     * @param applyCar
     */
    @Override
    public void applyCarAddCar(ApplyCar applyCar) {
        applyCarDao.applyCarAddCar(applyCar);
    }

    @Override
    public ApplyCar selectOneAppCar(String applyCarCode) {
        return applyCarDao.selectOneAppCar(applyCarCode);
    }

    @Override
    public void updateShowState(String carNumber) {
        applyCarDao.updateShowState(carNumber);
    }
}
