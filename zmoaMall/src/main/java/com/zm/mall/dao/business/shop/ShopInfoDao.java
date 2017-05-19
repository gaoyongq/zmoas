package com.zm.mall.dao.business.shop;

import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Role;

import java.util.List;

/**
 * Created by Bochao on 2017/4/13.
 */
public interface ShopInfoDao {
    /**
     * 通过b2c_user表的id查询该用户的父平台id和openId
     * 用于将用户从b2c_user对应到b2c_accounts_users
     * @param userId
     * @return
     */
    WeChatIdentity getWeChatIdentityByUserId(Long userId);

    /**
     * 通过平台id和openId获取accountsUsers信息
     * @param weChatIdentity
     * @return
     */
    Users getUserByWeChatIdentity(WeChatIdentity weChatIdentity);

    /**
     * 添加店铺信息
     * @param supplierInfo
     * @return
     */
    int addShopInfo(SupplierInfo supplierInfo);

    /**
     * 店铺列表分页所需当前页的店铺数据
     * @param supplierInfo
     * @return
     */
    List<SupplierInfo> getShopInfoList(PageableSupplierInfo supplierInfo);

    /**
     * 店铺列表分页所需总店铺记录数
     * @param supplierInfo
     * @return
     */
    Long getShopInfoCount(PageableSupplierInfo supplierInfo);

    /**
     * 用户关注店铺列表分页所需当前页的店铺数据
     * @param supplierInfo
     * @return
     */
    List<SupplierInfo> getShopInfoListByAccountUser(PageableSupplierInfo supplierInfo, Users user);

    /**
     * 用户关注店铺列表分页所需总店铺记录数
     * @param supplierInfo
     * @return
     */
    Long getShopInfoCountByAccountUser(PageableSupplierInfo supplierInfo, Users user);

    /**
     * 查询指定店铺信息
     * @param supplierInfo
     * @return
     */
    SupplierInfo getShopInfo(SupplierInfo supplierInfo);

    /**
     * 审核店铺。修改店铺的是否审核状态
     * @param supplierInfo
     * @return
     */
    int checkShop(SupplierInfo supplierInfo);

    //根据店铺主人查询
    SupplierInfo getShopInfoByUserId(SupplierInfo supplierInfo);

    /**
     * 推荐店铺。修改店铺的是否推荐状态
     * @param supplierInfo
     * @return
     */
    int recommendShop(SupplierInfo supplierInfo);

    /**
     * 得到指定部门名的部门信息。用于判断该平台下是否已有该部门。
     * @param deptName
     * @param platformId
     * @return
     */
    Department getDepartmentByName(String deptName, String platformId);

    /**
     * 得到指定角色名的角色信息。用于判断该平台下是否已有该角色。
     * @param roleName
     * @param deptId
     * @param platformId
     * @return
     */
    Role getRoleByNameAndDeptId(String roleName, Long deptId, String platformId);

    /**
     * 添加用户和角色的关联关系
     * @param userId
     * @param roleId
     * @param platformId
     * @return
     */
    int addUserRole(Integer userId, Long roleId, String platformId);

    /**
     * 初始化店铺部。审核店铺信息时，给开店铺的用户加权限，如果平台下没有店铺部门，初始化店铺部
     * @param department
     * @return
     */
    int addDepartment(Department department);

    /**
     * 初始化角色。审核店铺信息时，给开店铺的用户加权限，如果平台下的店铺部门没有店长角色，初始化角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 查询开店铺用户是否已经有了权限
     * @param userId
     * @param roleId
     * @param platformId
     * @return
     */
    Integer getUserRoleCount(Integer userId, Long roleId, String platformId);

    /**
     * 通过accountsUsers表中的用户id查询该用户的平台id和openId
     * @param userId
     * @return
     */
    WeChatIdentity getWeChatIdentityByAccountsUserId(Integer userId);

    /**
     * 修改开店铺的用户的部门和店铺id。将申请开店铺的用户的部门修改为店铺部
     * @param userId
     * @param deptId
     * @return
     */
    int updateUserDept(Long userId, Long deptId, Long shopId);

    /**
     * 删除用户角色
     * @param userId
     * @param platformId
     * @return
     */
    int deleteUserRole(Long userId, String platformId);

    //查询平台下的全部店铺
    List<SupplierInfo> selAllShop(SupplierInfo supplierInfo);
    //根据shopId查询店铺
    SupplierInfo selOneShop(SupplierInfo supplierInfo);
}
