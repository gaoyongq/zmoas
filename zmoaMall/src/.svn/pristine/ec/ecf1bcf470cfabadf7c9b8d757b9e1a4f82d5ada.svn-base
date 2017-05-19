package com.zm.mall.dao.system;




import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface MenusDao extends BaseDao<Privileges> {

    /**
     * 根据条件查询用户的信息
     * @param
     * @return
     */

    List<Privileges> selectAllByContion(Role role);
    List<Privileges> getTopPrivilege();
    //查询所有菜单的url
    List<String> getAllUrls();
}
