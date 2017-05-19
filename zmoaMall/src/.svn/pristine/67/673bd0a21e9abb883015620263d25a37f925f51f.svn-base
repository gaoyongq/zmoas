package com.zm.mall.service.system.impl;




import com.zm.mall.client.service.system.MenusService;
import com.zm.mall.dao.system.MenusDao;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("PrivilegeService")
public class MenusServiceImpl implements MenusService {

    private static Log log = LogFactory.getLog(MenusService.class);
    @Autowired
    private MenusDao menusDao;




    /**
     * 根据条件查询用户的信息
     * @param
     * @return
     */


    public List<Privileges>selectAllSystemCode(Role role) throws Exception{


        //查询数据库是否存在该用户。
        List<Privileges> list = menusDao.selectAllByContion(role);
        if(list != null && list.size()>0){
            return list;
        }
        return null;
    }
    @Override
    public List<Privileges> getTopPrivilege() {
        List<Privileges> list =  menusDao.getTopPrivilege();
        return list;
    }

    @Override
    public List<String> getAllUrls() {
        List list = menusDao.getAllUrls();
        return list;
    }

}
