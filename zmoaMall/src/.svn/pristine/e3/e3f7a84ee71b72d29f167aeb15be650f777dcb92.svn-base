package com.zm.mall.dao.business.shop.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.shop.ShopInfoDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bochao on 2017/4/13.
 */
public class ShopInfoDaoImpl extends BaseDaoImpl implements ShopInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.shop.ShopInfoDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public WeChatIdentity getWeChatIdentityByUserId(Long userId) {
        return sqlTemplate.selectOne(getNameSpace("getWeChatIdentityByUserId"), userId);
    }

    @Override
    public Users getUserByWeChatIdentity(WeChatIdentity weChatIdentity) {
        return sqlTemplate.selectOne(getNameSpace("getUserByWeChatIdentity"), weChatIdentity);
    }

    @Override
    public int addShopInfo(SupplierInfo supplierInfo) {
        return sqlTemplate.insert(getNameSpace("addShopInfo"), supplierInfo);
    }

    @Override
    public List<SupplierInfo> getShopInfoList(PageableSupplierInfo supplierInfo) {
        return sqlTemplate.selectList(getNameSpace("getShopInfoList"), supplierInfo);
    }

    @Override
    public Long getShopInfoCount(PageableSupplierInfo supplierInfo) {
        return sqlTemplate.selectOne(getNameSpace("getShopInfoCount"), supplierInfo);
    }

    @Override
    public List<SupplierInfo> getShopInfoListByAccountUser(PageableSupplierInfo supplierInfo, Users user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopInfo", supplierInfo);
        map.put("user", user);
        return sqlTemplate.selectList(getNameSpace("getShopInfoListByAccountUser"), map);
    }

    @Override
    public Long getShopInfoCountByAccountUser(PageableSupplierInfo supplierInfo, Users user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopInfo", supplierInfo);
        map.put("user", user);
        return sqlTemplate.selectOne(getNameSpace("getShopInfoCountByAccountUser"), map);
    }

    @Override
    public SupplierInfo getShopInfo(SupplierInfo supplierInfo) {
        return sqlTemplate.selectOne(getNameSpace("getShopInfo"), supplierInfo);
    }

    @Override
    public int checkShop(SupplierInfo supplierInfo) {
        return sqlTemplate.update(getNameSpace("checkShop"), supplierInfo);
    }

    @Override
    public int recommendShop(SupplierInfo supplierInfo) {
        return sqlTemplate.update(getNameSpace("recommendShop"), supplierInfo);
    }

    @Override
    public Department getDepartmentByName(String deptName, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deptName", deptName);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getDepartmentByName"), map);
    }

    @Override
    public Role getRoleByNameAndDeptId(String roleName, Long deptId, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", roleName);
        map.put("deptId", deptId);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getRoleByNameAndDeptId"), map);
    }

    @Override
    public int addUserRole(Integer userId, Long roleId, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("platformId", platformId);
        return sqlTemplate.insert(getNameSpace("addUserRole"), map);
    }

    @Override
    public int addDepartment(Department department) {
        return sqlTemplate.insert(getNameSpace("addDepartment"), department);
    }

    @Override
    public int addRole(Role role) {
        return sqlTemplate.insert(getNameSpace("addRole"), role);
    }

    @Override
    public Integer getUserRoleCount(Integer userId, Long roleId, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getUserRoleCount"), map);
    }

    @Override
    public WeChatIdentity getWeChatIdentityByAccountsUserId(Integer userId) {
        return sqlTemplate.selectOne(getNameSpace("getWeChatIdentityByAccountsUserId"), userId);
    }

    @Override
    public int updateUserDept(Long userId, Long deptId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("shopId", shopId);
        return sqlTemplate.update(getNameSpace("updateUserDept"), map);
    }

    @Override
    public int deleteUserRole(Long userId, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("platformId", platformId);
        return sqlTemplate.delete(getNameSpace("deleteUserRole"), map);
    }

    @Override
    public SupplierInfo getShopInfoByUserId(SupplierInfo supplierInfo) {
        return sqlTemplate.selectOne(getNameSpace("getShopInfoByUserId"),supplierInfo);
    }

    @Override
    public List<SupplierInfo> selAllShop(SupplierInfo supplierInfo) {
        return sqlTemplate.selectList(getNameSpace("getAllShop"),supplierInfo);
    }

    @Override
    public SupplierInfo selOneShop(SupplierInfo supplierInfo) {
        return sqlTemplate.selectOne(getNameSpace("getOneShop"),supplierInfo);
    }
}
