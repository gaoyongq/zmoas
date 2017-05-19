package com.zm.mall.client.service.system;





import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
public interface MenusService {



    /**
     * 根据用户的条件查询用户的信息
     * @param
     * @return
     */

    List<Privileges> selectAllSystemCode(Role role)throws Exception;
    List<Privileges> getTopPrivilege();
    //查询所有菜单的url
    List<String> getAllUrls();

}
