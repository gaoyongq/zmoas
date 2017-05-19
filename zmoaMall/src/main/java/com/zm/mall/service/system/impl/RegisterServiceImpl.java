package com.zm.mall.service.system.impl;



import com.zm.mall.client.PageOfRegister;
import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.service.system.MoneyManagerService;
import com.zm.mall.client.service.system.RegisterService;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.dao.system.MoneyManagerDao;
import com.zm.mall.dao.system.RegisterDao;
import com.zm.mall.domain.business.product.CategoryInfo;
import com.zm.mall.domain.business.product.ProductCategories;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;
import com.zm.mall.domain.system.Register;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("registerService")
//@Transactional
public class RegisterServiceImpl implements RegisterService {

    private static Log log = LogFactory.getLog(RegisterService.class);
    @Autowired
    private RegisterDao registerDao;


    @Override
    public void insertRegister(Register register) {
        registerDao.insertRegister(register);
    }

    @Override
    public void updateRegister(Register register) {
        registerDao.updateRegister(register);
    }

    @Override
    public Register selectRegister(Register register) {
        Register reg = registerDao.selectRegister(register);
        return reg;
    }

    @Override
    public PageOfRegister selectAllRegisterByPage(PageOfRegister page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        //签到信息
        List<Register> list = registerDao.selectAllRegisterByPage(page);

        //总条数
        Integer pageCount = registerDao.selectAllCount(page);
        //总页数
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if(list != null && list.size()>0){
            page.setRegisters(list);
            return page;
        }
        return page;
    }
    /**
     * 本人查询当前月
     * @param register
     * @return
     */
    @Override
    public List<Register> selectRegisterAll(Register register) {
        List<Register> list = registerDao.selectRegisterAll(register);
        return list;
    }

    /**
     * 查询所有人
     * @param register
     * @return
     */
    @Override
    public List<Register> selectAllRegister(Register register) {
        List<Register> list = registerDao.selectAllRegister(register);
        return list;
    }
}