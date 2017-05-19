package com.zm.mall.service.system.impl;

import com.zm.mall.client.result.system.SystemCodeResult;
import com.zm.mall.client.service.system.SystemCodeService;
import com.zm.mall.client.vo.system.SystemCodeVo;
import com.zm.mall.dao.system.SystemCodeDao;
import com.zm.mall.domain.system.SystemCode;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("SystemCodeService")
public class SystemCodeServiceImpl implements SystemCodeService {

    private static Log log = LogFactory.getLog(SystemCodeService.class);
    @Autowired
    private SystemCodeDao systemCodeDao;




    /**
     * 根据条件查询用户的信息
     * @return
     */
    @Override
    public SystemCodeResult selectSystemCode(SystemCodeVo systemCodeVo) {
        //对象类型转换
        SystemCode systemCode =  SpaceBeanCopy.systemCodeVoToSystemCode(systemCodeVo);

        //查询数据库是否存在该用户。
        List<SystemCode> list = systemCodeDao.selectByContion(systemCode);
        if(list != null && list.size()>0){
            SystemCodeResult systemCodeResult = SpaceBeanCopy.systemCodeToResult(list.get(0));
            return systemCodeResult;
        }
        return null;
    }

    public List<SystemCode>selectAllSystemCode(SystemCode systemCode) throws Exception{


        //查询数据库是否存在该用户。
        List<SystemCode> list = systemCodeDao.selectAllByContion(systemCode);
        if(list != null && list.size()>0){

            return list;
        }
        return null;
    }

    @Override
    public void deleteMenu(SystemCode systemCode) {

        systemCodeDao.deleteMenu(systemCode);

    }

    @Override
    public void updateMenu(SystemCode systemCode) {
        systemCodeDao.updateMenu(systemCode);
    }


    @Override
    public void addMenu(SystemCode systemCode) {
        systemCodeDao.addMenu(systemCode);
    }
}
