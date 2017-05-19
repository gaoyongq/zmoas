package com.zm.mall.service.business.shop;

import com.zm.mall.client.service.business.shop.ShopInfoService;
import com.zm.mall.dao.business.shop.ShopInfoDao;
import com.zm.mall.dao.system.UserDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Role;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bochao on 2017/4/13.
 */
@Service("shopInfoService")
public class ShopInfoServiceImpl implements ShopInfoService {

    @Resource
    private ShopInfoDao shopInfoDao;

    @Resource
    private UserDao userDao;

    @Override
    public WeChatIdentity getWeChatIdentityByUserId(Long userId) {
        return shopInfoDao.getWeChatIdentityByUserId(userId);
    }

    @Override
    public Users getUserByWeChatIdentity(WeChatIdentity weChatIdentity) {
        return shopInfoDao.getUserByWeChatIdentity(weChatIdentity);
    }

    @Override
    public void addShopInfo(SupplierInfo supplierInfo) {
        int i = shopInfoDao.addShopInfo(supplierInfo);
    }

    @Override
    public EasyUIListResult<SupplierInfo> getShopInfoList(PageableSupplierInfo supplierInfo) {
        List<SupplierInfo> supplierInfoList = shopInfoDao.getShopInfoList(supplierInfo);
        Long total = shopInfoDao.getShopInfoCount(supplierInfo);
        return new EasyUIListResult<SupplierInfo>(total, supplierInfoList);
    }

    @Override
    public EasyUIListResult<SupplierInfo> getShopInfoListByAccountUser(PageableSupplierInfo supplierInfo, Users user) {
        List<SupplierInfo> supplierInfoList = shopInfoDao.getShopInfoListByAccountUser(supplierInfo, user);
        Long total = shopInfoDao.getShopInfoCountByAccountUser(supplierInfo, user);
        return new EasyUIListResult<SupplierInfo>(total, supplierInfoList);
    }

    @Override
    public SupplierInfo getShopInfo(SupplierInfo supplierInfo) {
        return shopInfoDao.getShopInfo(supplierInfo);
    }

    @Transactional
    @Override
    public void checkShop(SupplierInfo supplierInfo) {
        shopInfoDao.checkShop(supplierInfo);
        Department department = shopInfoDao.getDepartmentByName("店铺部", supplierInfo.getPluteformid());
        if (department == null) {
            //新增店铺部，返回店铺部门自增id，b2c_department
            department = new Department();
            department.setName("店铺部");
            department.setPluteformid(supplierInfo.getPluteformid());
            department.setDepartment(new Department());
            shopInfoDao.addDepartment(department);
        }
        Role role = shopInfoDao.getRoleByNameAndDeptId("店长", department.getId(), supplierInfo.getPluteformid());
        if (role == null) {
            //新增店长角色，返回店长角色自增id，b2c_role
            role = new Role();
            role.setName("店长");
            role.setDepartment(department);
            role.setPluteformid(supplierInfo.getPluteformid());
            shopInfoDao.addRole(role);
        }
        WeChatIdentity weChatIdentity = shopInfoDao.getWeChatIdentityByAccountsUserId(supplierInfo.getUserId());
        User user = new User();
        user.setParentPluteformId(weChatIdentity.getPlatformId());
        user.setOpenId(weChatIdentity.getOpenId());
        User uu = userDao.selectWX(user);
        shopInfoDao.updateUserDept(uu.getId(), department.getId(), supplierInfo.getSupplierId());
        int count = shopInfoDao.getUserRoleCount(uu.getId().intValue(), role.getId(), supplierInfo.getPluteformid());
        if (count == 0) {
            shopInfoDao.deleteUserRole(uu.getId(), supplierInfo.getPluteformid());
            shopInfoDao.addUserRole(uu.getId().intValue(), role.getId(), supplierInfo.getPluteformid());
        }
    }

    @Override
    public void recommendShop(SupplierInfo supplierInfo) {
        int i = shopInfoDao.recommendShop(supplierInfo);
    }

    @Override
    public SupplierInfo getShopInfoByUserId(SupplierInfo supplierInfo) {
        return shopInfoDao.getShopInfoByUserId(supplierInfo);
    }

    @Override
    public List<SupplierInfo> selAllShop(SupplierInfo supplierInfo) {
        return shopInfoDao.selAllShop(supplierInfo);
    }

    @Override
    public SupplierInfo selOneShop(SupplierInfo supplierInfo) {
        return shopInfoDao.selOneShop(supplierInfo);
    }
}
