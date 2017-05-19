package com.zm.mall.service.system.impl;


import com.zm.mall.client.result.system.MallConfigResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.PrivilegeService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.system.*;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.accountsUsers.UsersExp;
import com.zm.mall.domain.system.*;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Created by Administrator on 2016/11/13.
 */

@Service("userService")
@PropertySource("classpath:urlPrefix.properties")
public class UserServiceImpl implements UserService {
    private static Log log= LogFactory.getLog(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserListDao userListDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private SystemCodeDao systemCodeDao;
    @Autowired
    private MallConfigDao mallConfigDao;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private Environment env;

    @Override
    public UserResult login(UserVo userVo) {
       User user= SpaceBeanCopy.userVoToUser(userVo);
        user=userDao.login(user);
        UserResult userResult=SpaceBeanCopy.userToUserResult(user);
        return userResult;
    }

    @Override
    public UserResult selectOne(UserVo userVo) {
        User user=SpaceBeanCopy.userVoToUser(userVo);
        User u=userDao.selectOne(user);
        UserResult userResult=SpaceBeanCopy.userToUserResult(u);
        return userResult;
    }

    @Override
    public User selectWX(User  user) {
 //      user.setParentPluteformId(user.getPluteformid());
//        user.setOpenId(user.getOpenId());
        User uu=userDao.selectWX(user);
        return uu;
    }


    @Override
    public int updatePass(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        int m=userDao.updatePass(user);
        return m;
    }

    @Override
    public int updateUser(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        int  s=userDao.update(user);
        return s;
    }

    @Override
    public List<User> selectPurchaser(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
       List<User> users= userDao.getPurchaser(user);
        return users;
    }

    @Override
    public List<User> selectAllPhysicalNoEmploy() {
        List<User> list = userDao.selectAllPhysicalNoEmploy();
        if(list != null && list.size()>0){

            return list;
        }
        return null;
    }

    @Override
    public int updateState(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
         return userDao.updateState(user);
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @Override
    public int updateShopState(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        return userDao.updateShopState(user);
    }
    @Override
    public int insertUser(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        user.setLoginState(0L);
        int m=userDao.insert(user);
        User u=new User();
        u.setId(user.getId());
        u.setRoleId(user.getRoleId());
        u.setPluteformid(user.getPluteformid());
        userDao.insertRoleUser(u);

        return m;
    }

    /**
     * 微信端的注册
     * @return
     */
    @Override
    @Transactional
    public Integer insertUserWX(User user)throws  Exception{
        String code="deId";
        SystemCode systemCode=systemCodeDao.getCodeUserId(code.trim());
        ItemSystemCode itemSystemCode=new ItemSystemCode();
        itemSystemCode.setCode("ZM_SH");
        Department department=new Department();
        department.setPluteformid(user.getPluteformid());
        department.setCode(itemSystemCode.getCode());
        Department d=departmentDao.selectWX(department);
        user.setDepaedmentId(d.getId());
        user.setName(user.getNickName());
        user.setGender(user.getSex());
        Integer m=userDao.insertUserWX(user);
        Users users=new Users();
        users.setPluteformid(user.getPluteformid());
        users.setNickName(user.getName());
        users.setOpenId(user.getOpenId());
        users.setFollowWay(user.getFollowWay());
        users.setTrueName(user.getName());
        Integer mm=userListDao.insertUsersWX(users);
        Users userId = userListDao.selectUserWX(users);
        Set<Role> set = d.getRoles();
        Role role =null;
        Iterator<Role> iterator = set.iterator();
        while (iterator.hasNext()){
            role = iterator.next();
        }
        User u=new User();
        u.setId(user.getId());
        u.setRoleId(role.getId());
        u.setPluteformid(user.getPluteformid());
        userDao.insertRoleUser(u);
        //修改登录状态
        userDao.updateShopState(u);
        /**
         * 添加用户Exp表   积分    余额的 信息    待测试
         */
        UsersExp usersExp = new UsersExp();
        usersExp.setUserId(userId.getUserId());
        usersExp.setPluteformid(user.getPluteformid());
        usersExp.setBalance(0.0);
        usersExp.setPoints(0);
        usersExp.setRankScore(0);
        usersExp.setTelPhone(user.getName());
        users.setUsersExp(usersExp);
        Integer integer = userListDao.InsertUserExp(users);
        return m+mm+integer;
    }

    /**
     * 用户手机号校验
     * @param userVo
     * @return
     */
    @Override
    public int checkPhoneNum(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        User resultUser = userDao.checkPhoneNum(user);
        if (resultUser!=null){
            return 0;//手机号存在
        }else{
            return 1;//手机号不存在
        }
    }

    @Override
    @Transactional
    public UserResult openAddShopData(MallConfigVo mallConfigVo) throws Exception {
        MallConfig mallConfig = SpaceBeanCopy.mallConfigVoTomallConfig(mallConfigVo);
        Users u=new Users();
        u.setPluteformid(mallConfig.getParentpId());
        u.setOpenId(mallConfig.getOpenId());
        Users uu=userListDao.selUsersByAppIdopenId(u);
        mallConfig.setUserId(uu.getUserId());
        int i = mallConfigDao.InsertMallConfig(mallConfig);
        User user=null;
        if(i>0){
            user = userDao.selUserBypId(mallConfig);
                if(user!=null){
                user.setPluteformid(mallConfig.getPid());
                user.setRealName(mallConfig.getPhone());
                userDao.update(user);
                //初始化该商户下的商户部门
                Department department=new Department();
                department.setCode("ZM_SH");
                department.setName("商户");
                department.setDescription("负责商城的开户");
                department.setPluteformid(mallConfig.getPid());
                departmentDao.insert(department);
                //初始化该商户下的商户部门的商户岗位
                Role role=new Role();
                role.setPluteformid(null);
                role.setDescription("商户岗位");
                role.setName("商户");
                role.setDepartment(department);
                roleDao.insert(role);
                //根据平台ID添加角色关联关系
//                List<PrivilegeResult> privilegeResults=privilegeService.select(privilegeVo);
//                for (int j = 0;j<publicprivileges.size();j++){
//                    PrivilegeRole privilegeRole = new PrivilegeRole();
//                    privilegeRole.setPrivilegeId(publicprivileges.get(j).getPrivilegeid());
//                    privilegeRole.setRolePrivilege(user.getRoleId());
//                    privilegeRole.setPluteformid(mallConfig.getPid());
//                    roleDao.insertPrivilegeRole(privilegeRole);
//                }
            }
        }
        UserResult userResult = SpaceBeanCopy.userToUserResult(user);
        userResult.setUrl(env.getProperty("url.server") + "/privileges/login.action");
        return userResult;
    }

    /**
     * 根据Appid查询Secretkey
     * @param mallConfigVo
     * @return
     */
    @Override
    public MallConfigResult selSecretkeyByAppId(MallConfigVo mallConfigVo) {
        MallConfig mallConfig = SpaceBeanCopy.mallConfigVoTomallConfig(mallConfigVo);
        MallConfig config = mallConfigDao.selSecretkeyByAppId(mallConfig);
        return SpaceBeanCopy.mallConfigTomallConfigResult(config);
    }

    /**
     * 添加c端用户
     * @param userVo
     * @return
     */
    @Override
    public int addOpenShop(UserVo userVo) {
        User user= SpaceBeanCopy.userVoToUser(userVo);
        user.setLoginState(0L);
        int m=userDao.insert(user);
        User u=new User();
        u.setId(user.getId());
        u.setRoleId(user.getRoleId());
        u.setPluteformid(user.getPluteformid());
        userDao.insertRoleUser(u);
        //根据平台ID添加角色关联关系
        List<Publicprivilege> publicprivileges =  privilegeDao.selPublicprivilegelist();
        for (int i = 0;i<publicprivileges.size();i++){
            PrivilegeRole privilegeRole = new PrivilegeRole();
            privilegeRole.setPrivilegeId(publicprivileges.get(i).getPrivilegeid());
            privilegeRole.setRolePrivilege(user.getRoleId());
            privilegeRole.setPluteformid(user.getPluteformid());
            roleDao.insertPrivilegeRole(privilegeRole);
        }
        return 0;
    }


    @Override
    public List<UserResult> select(UserVo userVo) {
        User user=SpaceBeanCopy.userVoToUser(userVo);
       List<User> users= userDao.select(user);
        if(users!=null&&users.size()>0){
            List<UserResult> userResults=new ArrayList<UserResult>();
            for(User uu:users){
                UserResult userResult=SpaceBeanCopy.userToUserResult(uu);
                userResults.add(userResult);
//                System.out.println(userResult.getRoles().size());
          //    System.out.println(userResult.getDepartment().getName());
            }

            return userResults;
        }
        return null;
    }

    /**
     * 计算总页码
     * @param userPage
     * @return
     */
    @Override
    public Integer userCountAll(userPage userPage) {
        return userDao.selectUserCount(userPage);
    }

    @Override
    public List<User> getUsers(userPage userPage) {
        return userDao.selectByLimit(userPage);
    }
    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @Override
    public int updatePassWord(UserVo userVo) {
        User user=SpaceBeanCopy.userVoToUser(userVo);
        Integer m=userDao.updatePassWord(user);
        return m;
    }

    @Override
    @Transactional
    public Integer addPhone(UserVo userVo) {
        User user=SpaceBeanCopy.userVoToUser(userVo);
        Integer i=userDao.addPhone(user);
        return i;
    }
    //微信注册店铺用户
    @Override
    @Transactional
    public Integer insertUserPartShop(User user)throws  Exception{
        Integer m=userDao.insertUserWX(user);
        Users users=new Users();
        users.setPluteformid(user.getPluteformid());
        users.setNickName(user.getName());
        users.setOpenId(user.getOpenId());
        users.setFollowWay(user.getFollowWay());
        Integer mm=userListDao.insertUsersWX(users);
        return mm+m;
    }

    @Override
    public User selUserBypId(MallConfig mallConfig) {

        return userDao.selUserBypId(mallConfig);
    }
}
