package com.zm.mall.service;


import com.zm.mall.client.PageVo;
import com.zm.mall.client.result.UserInfoResult;
import com.zm.mall.client.result.business.accounts.*;
import com.zm.mall.client.result.business.orders.OrderInfoResult;
import com.zm.mall.client.result.business.orders.OrderItemsResult;
import com.zm.mall.client.result.business.product.*;
import com.zm.mall.client.result.business.purchase.PurchaseInfoResult;
import com.zm.mall.client.result.business.shopUser.ShopUserResult;
import com.zm.mall.client.result.system.*;
import com.zm.mall.client.result.system.ItemSystemCodeResult;
import com.zm.mall.client.result.system.SystemCodeResult;
import com.zm.mall.client.vo.business.accounts.*;
import com.zm.mall.client.vo.business.orders.OrderInfoVo;
import com.zm.mall.client.vo.business.product.*;
import com.zm.mall.client.vo.business.purchase.PurchaseInfoVo;
import com.zm.mall.client.vo.business.shopUser.ShopUserVo;
import com.zm.mall.client.vo.system.*;
import com.zm.mall.client.vo.system.ItemSystemCodeVo;
import com.zm.mall.client.vo.system.SystemCodeVo;
import com.zm.mall.client.vo.user.UserInfoVo;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.shopUser.ShopUser;
import com.zm.mall.domain.system.*;
import com.zm.mall.domain.system.ItemSystemCode;
import com.zm.mall.domain.system.SystemCode;
import com.zm.mall.domain.user.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by liyanshuai on 2016/7/11.
 */
public class SpaceBeanCopy {



    public static UserInfo userInfoVoToUserInfo(UserInfoVo userInfoVo) {
        UserInfo userInfo = new UserInfo();
        if(userInfoVo == null ){
            return null;
        }
        userInfo.setSystemType(userInfoVo.getSystemType());
        userInfo.setBusinessId(userInfoVo.getBusinessId());
        userInfo.setPlatefromId(userInfoVo.getPlatefromId());
        userInfo.setId(userInfoVo.getId());
        return userInfo;
    }

    /**
     * 查询出来的用户的信息转化成用户结果信息
     * @param userInfo
     * @return
     */
    public static UserInfoResult userInfoToResult(UserInfo userInfo) {
        UserInfoResult userInfoResult = new UserInfoResult();
        if(userInfo == null){
            return null;
        }
        userInfoResult.setId(userInfo.getId());
        userInfoResult.setSystemType(userInfo.getSystemType());
        userInfoResult.setBusinessId(userInfo.getBusinessId());
        userInfoResult.setPlatefromId(userInfo.getPlatefromId());
        return userInfoResult;
    }

    /**
     * 报销单信息的转化 vo2实体类
     * @param
     * @return
     */
    public static MoneyManager moneyManagerVo2MoneyManager(MoneyManagerVo moneyManagerVo){
        MoneyManager moneyManager = new MoneyManager();
        if(moneyManagerVo==null){
            return null;
        }
        moneyManager.setId(moneyManagerVo.getId());
        //name
        moneyManager.setName(moneyManagerVo.getName());
        moneyManager.setCode(moneyManagerVo.getCode());
        //departmentName
        moneyManager.setDepartmentName(moneyManagerVo.getDepartmentName());
        //departmentCode
        moneyManager.setDepartmentCode(moneyManagerVo.getDepartmentCode());
        //userName
        moneyManager.setUserName(moneyManagerVo.getUserName());
        //DeparmentUserName
        moneyManager.setDeparmentUserName(moneyManagerVo.getDeparmentUserName());
        //DeparmentUserCode
        moneyManager.setDeparmentUserCode(moneyManagerVo.getDeparmentUserCode());
        //DeparmentStatusName
        moneyManager.setDeparmentStatusName(moneyManagerVo.getDeparmentStatusName());
        //DeparmentStatusCode
        moneyManager.setDeparmentStatusCode(moneyManagerVo.getDeparmentStatusCode());
        //DpartCheckConment
        moneyManager.setDpartCheckConment(moneyManagerVo.getDpartCheckConment());
        //LeaderCode
        moneyManager.setLeaderCode(moneyManagerVo.getLeaderCode());
        //LeaderName
        moneyManager.setLeaderName(moneyManagerVo.getLeaderName());
        //LeaderStatusName
        moneyManager.setLeaderStatusName(moneyManagerVo.getLeaderStatusName());
        //LeaderStatusCode
        moneyManager.setLeaderStatusCode(moneyManagerVo.getLeaderStatusCode());
        //LeaderCheckConment
        moneyManager.setLeaderCheckConment(moneyManagerVo.getLeaderCheckConment());
        //Description
        moneyManager.setDescription(moneyManagerVo.getDescription());
        //Money
        moneyManager.setMoney(moneyManagerVo.getMoney());
        //MoneyType
        moneyManager.setMoneyType(moneyManagerVo.getMoneyType());
        //StatusName
        moneyManager.setStatusName(moneyManagerVo.getStatusName());
        //StatusCode
        moneyManager.setStatusCode(moneyManagerVo.getStatusCode());
        //CreateDate
        moneyManager.setCreateDate(moneyManagerVo.getCreateDate());
        //MoneyItemParamer
        moneyManager.setMoneyItemParamer(moneyManagerVo.getMoneyItemParamer());
        //CheckConment
        moneyManager.setCheckConment(moneyManagerVo.getCheckConment());
 moneyManager.setRealName(moneyManagerVo.getRealName());

        moneyManager.setCurrentPage(moneyManagerVo.getCurrentPage());
        moneyManager.setTotalPages(moneyManagerVo.getTotalPages());
        moneyManager.setPageSize(moneyManagerVo.getPageSize());
        moneyManager.setBeginNumber(moneyManagerVo.getBeginNumber());
        moneyManager.setRoles(moneyManagerVo.getRoles());
        moneyManager.setFileURL(moneyManagerVo.getFileURL());
        //
        Set<MoneyItemVo> moneyItemVos = moneyManagerVo.getItemSet();

        Set<MoneyItem> moneyItems= new HashSet<MoneyItem>();

        for(MoneyItemVo moneyItemVo:moneyItemVos){
            MoneyItem moneyItem = new MoneyItem();
            //绑值
            moneyItem.setId(moneyItemVo.getId());
            moneyItem.setCode(moneyItemVo.getCode());
            moneyItem.setMoneyName(moneyItemVo.getMoneyName());
            moneyItem.setDescription(moneyItemVo.getDescription());
            moneyItem.setMoney(moneyItemVo.getMoney());
            moneyItem.setMoneyManageId(moneyItemVo.getMoneyManageId());
            //放入Set
            moneyItems.add(moneyItem);
        }
        moneyManager.setItemSet(moneyItems);

        return moneyManager;
    }

    /**
     * 报销单的实体转resulet
     * @param
     * @return
     */
    public static List<MoneyManagerResult> moneyManager2MoneyManagerResult (List<MoneyManager> moneyManagers){
        //遍历 绑值
        if(moneyManagers==null){
            return null;
        }
        List<MoneyManagerResult>list = new ArrayList<MoneyManagerResult>();
        for(MoneyManager moneyManager: moneyManagers){
            MoneyManagerResult moneyManagerResult = new MoneyManagerResult();

            moneyManagerResult.setId(moneyManager.getId());
            //name
            moneyManagerResult.setName(moneyManager.getName());
            moneyManagerResult.setCode(moneyManager.getCode());
            //departmentName
            moneyManagerResult.setDepartmentName(moneyManager.getDepartmentName());
            //departmentCode
            moneyManagerResult.setDepartmentCode(moneyManager.getDepartmentCode());
            //userName
            moneyManagerResult.setUserName(moneyManager.getUserName());
            //DeparmentUserName
            moneyManagerResult.setDeparmentUserName(moneyManager.getDeparmentUserName());
            //DeparmentUserCode
            moneyManagerResult.setDeparmentUserCode(moneyManager.getDeparmentUserCode());
            //DeparmentStatusName
            moneyManagerResult.setDeparmentStatusName(moneyManager.getDeparmentStatusName());
            //DeparmentStatusCode
            moneyManagerResult.setDeparmentStatusCode(moneyManager.getDeparmentStatusCode());
            //DpartCheckConment
            moneyManagerResult.setDpartCheckConment(moneyManager.getDpartCheckConment());
            //LeaderCode
            moneyManagerResult.setLeaderCode(moneyManager.getLeaderCode());
            //LeaderName
            moneyManagerResult.setLeaderName(moneyManager.getLeaderName());
            //LeaderStatusName
            moneyManagerResult.setLeaderStatusName(moneyManager.getLeaderStatusName());
            //LeaderStatusCode
            moneyManagerResult.setLeaderStatusCode(moneyManager.getLeaderStatusCode());
            //LeaderCheckConment
            moneyManagerResult.setLeaderCheckConment(moneyManager.getLeaderCheckConment());
            //Description
            moneyManagerResult.setDescription(moneyManager.getDescription());
            //Money
            moneyManagerResult.setMoney(moneyManager.getMoney());
            //MoneyType
            moneyManagerResult.setMoneyType(moneyManager.getMoneyType());
            //StatusName
            moneyManagerResult.setStatusName(moneyManager.getStatusName());
            //StatusCode
            moneyManagerResult.setStatusCode(moneyManager.getStatusCode());
            //CreateDate
            moneyManagerResult.setCreateDate(moneyManager.getCreateDate());
            //MoneyItemParamer
            moneyManagerResult.setMoneyItemParamer(moneyManager.getMoneyItemParamer());
            //CheckConment
            moneyManagerResult.setCheckConment(moneyManager.getCheckConment());
moneyManagerResult.setRealName(moneyManager.getRealName());

            moneyManagerResult.setCurrentPage(moneyManager.getCurrentPage());
            moneyManagerResult.setTotalPages(moneyManager.getTotalPages());
            moneyManagerResult.setPageSize(moneyManager.getPageSize());
            moneyManagerResult.setBeginNumber(moneyManager.getBeginNumber());
            moneyManagerResult.setFileURL(moneyManager.getFileURL());
            //当前用户角色
            moneyManagerResult.setRoles(moneyManager.getRoles());
            //
            Set<MoneyItem> moneyItems = moneyManager.getItemSet();

            Set<MoneyItemResult> moneyItemResults= new HashSet<MoneyItemResult>();

            for(MoneyItem moneyItem:moneyItems){
                MoneyItemResult moneyItemResult = new MoneyItemResult();
                //绑值
                moneyItemResult.setId(moneyItem.getId());
                moneyItemResult.setCode(moneyItem.getCode());
                moneyItemResult.setMoneyName(moneyItem.getMoneyName());
                moneyItemResult.setDescription(moneyItem.getDescription());
                moneyItemResult.setMoney(moneyItem.getMoney());
                moneyItemResult.setMoneyManageId(moneyItem.getMoneyManageId());
                //放入Set
                moneyItemResults.add(moneyItemResult);
            }
            moneyManagerResult.setItemSet(moneyItemResults);
            list.add(moneyManagerResult);
        }
        return list;
    }

    /**
     * moneyItem转 moneyItemResult
     * @param
     * @return
     */

    public static List<MoneyItemResult> moneyItem2moneyItemResult(List<MoneyItem>moneyItems){
        List<MoneyItemResult>moneyItemResults = new ArrayList<MoneyItemResult>();
        for(MoneyItem moneyItem:moneyItems){
            MoneyItemResult moneyItemResult = new MoneyItemResult();
            //绑值
            moneyItemResult.setId(moneyItem.getId());
            moneyItemResult.setCode(moneyItem.getCode());
            moneyItemResult.setMoneyName(moneyItem.getMoneyName());
            moneyItemResult.setDescription(moneyItem.getDescription());
            moneyItemResult.setMoney(moneyItem.getMoney());
            moneyItemResult.setMoneyManageId(moneyItem.getMoneyManageId());
            //放入List
            moneyItemResults.add(moneyItemResult);
        }
        return moneyItemResults;
    }



    //******************************************************************************************************************
    public static SystemCode systemCodeVoToSystemCode(SystemCodeVo systemCodeVo) {
        SystemCode systemcode =  new SystemCode();
        if(systemCodeVo == null){
            return null;
        }
        systemcode.setId(systemCodeVo.getId());
        systemcode.setPid(systemCodeVo.getpId());
        systemcode.setCode(systemCodeVo.getCode());
        systemcode.setPage(systemCodeVo.getPage());
        systemcode.setPluteformid(systemCodeVo.getPluteformid());
        return  systemcode;
    }

    public static SystemCodeResult systemCodeToResult(SystemCode systemCode) {
        SystemCodeResult systemCodeResult = new SystemCodeResult();

        if(systemCode == null){
            return null;
        }
        systemCodeResult.setId(systemCode.getId());
        systemCodeResult.setpId(systemCode.getPid());
        systemCodeResult.setCode(systemCode.getCode());
        systemCodeResult.setPage(systemCode.getPage());
        systemCodeResult.setPluteformid(systemCode.getPluteformid());
        return systemCodeResult;
    }

    public static List<SystemCodeResult> systemCodeToListResult( List<SystemCode> list) {
        SystemCodeResult systemCodeResult = new SystemCodeResult();
        List<SystemCodeResult> systemList=null;
        for (int i=0;i<list.size();i++){
            if(list.get(i).getId()!=null){
                systemCodeResult.setId(list.get(i).getId());
            }
            if(list.get(i).getPid()!=null){
                systemCodeResult.setpId(list.get(i).getPid());
            }
            if (list.get(i).getCode()!=null){
                systemCodeResult.setCode(list.get(i).getCode());
            }
            if (list.get(i).getPage()!=null){
                systemCodeResult.setPage(list.get(i).getPage());
            }
            if (list.get(i).getPage()!=null){
                systemCodeResult.setPluteformid(list.get(i).getPluteformid());
            }
            systemList.set(i,systemCodeResult);
        }

        return systemList;
    }
   //***************************************************************************************************
    public static ItemSystemCode itemSystemCodeVoToSystemCode(ItemSystemCodeVo itemSystemCodeVo) {
        ItemSystemCode itemSystemCode = new ItemSystemCode();
        if (itemSystemCodeVo == null) {
            return null;
        }

        itemSystemCode.setId(itemSystemCodeVo.getId());
        itemSystemCode.setFid(itemSystemCodeVo.getFid());
        itemSystemCode.setBumber(itemSystemCodeVo.getBumber());
        itemSystemCode.setCode(itemSystemCodeVo.getCode());
        itemSystemCode.setDescription(itemSystemCodeVo.getDescription());
        itemSystemCode.setPluteformid(itemSystemCodeVo.getPluteformid());
        return itemSystemCode;
    }

    public static List<ItemSystemCodeResult> itemSystemCodeToListResult(List<ItemSystemCode> list) {
        List<ItemSystemCodeResult> itemSystemList = new ArrayList<ItemSystemCodeResult>();
        for(ItemSystemCode isc:list){
            //
            ItemSystemCodeResult itemSystemCodeResult= new ItemSystemCodeResult();

            itemSystemCodeResult.setId(isc.getId());
            itemSystemCodeResult.setFid(isc.getFid());
            itemSystemCodeResult.setBumber(isc.getBumber());
            itemSystemCodeResult.setCode(isc.getCode());
            itemSystemCodeResult.setDescription(isc.getDescription());
            itemSystemCodeResult.setPluteformid(isc.getPluteformid());

            itemSystemList.add(itemSystemCodeResult);
        }
        return itemSystemList;
    }
    public static User userVoToUser(UserVo userVo){
        User user=new User();
        if (userVo==null){
            return null;
        }
        user.setId(userVo.getId());
        user.setName(userVo.getName());
        user.setDepartment(userVo.getDepartment());
        user.setDescription(userVo.getDescription());
        user.setEmail(userVo.getEmail());
        user.setState(userVo.getState());
        user.setGender(userVo.getGender());
        user.setNewPassword(userVo.getNewPassword());
        user.setPassword(userVo.getPassword());
        user.setPhone(userVo.getPhone());
        user.setRealName(userVo.getRealName());
        user.setRoles(userVo.getRoles());
        user.setRoleId(userVo.getRoleId());
        user.setDepaedmentId(userVo.getDepaedmentId());
        user.setPluteformid(userVo.getPluteformid());
        user.setShopId(userVo.getShopId());        user.setParentPluteformId(userVo.getParentPluteformId());
        user.setPluteformStatus(userVo.getPluteformStatus());
        user.setLoginState(userVo.getLoginState());
        return user;
    }

    public static UserResult userToUserResult(User user){
        UserResult userResult=new UserResult();
        if (user==null){
            return null;
        }
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setDepartment(user.getDepartment());
        userResult.setDescription(user.getDescription());
        userResult.setEmail(user.getEmail());
        userResult.setState(user.getState());
        userResult.setGender(user.getGender());
        userResult.setNewPassword(user.getNewPassword());
        userResult.setPassword(user.getPassword());
        userResult.setPhone(user.getPhone());
        userResult.setRealName(user.getRealName());
        userResult.setRoles(user.getRoles());
        userResult.setRoleId(user.getRoleId());
        userResult.setDepaedmentId(user.getDepaedmentId());
        userResult.setPluteformid(user.getPluteformid());
        userResult.setShopId(user.getShopId());        userResult.setParentPluteformId(user.getParentPluteformId());
        userResult.setPluteformStatus(user.getPluteformStatus());
        userResult.setLoginState(user.getLoginState());
        return userResult;
    }
    /**
     * 岗位
     *
     */
    public static Role roleVoToRole(RoleVo roleVo){
        Role role=new Role();
        if (roleVo==null){
            return null;
        }
        role.setName(roleVo.getName());
        role.setDepartment(roleVo.getDepartment());
        role.setId(roleVo.getId());
        role.setDescription(roleVo.getDescription());
        role.setUsers(roleVo.getUsers());
        role.setPrivileges(roleVo.getPrivileges());
        role.setPluteformid(roleVo.getPluteformid());
        return role;
    }
    public static RoleResult roleToResult(Role role) {
        RoleResult roleResult = new RoleResult();
        if(role == null){
            return null;
        }
        roleResult.setDescription(role.getDescription());
        roleResult.setId(role.getId());
        roleResult.setName(role.getName());
        roleResult.setDepartment(role.getDepartment());
        roleResult.setPrivileges(role.getPrivileges());
        roleResult.setUsers(role.getUsers());
        roleResult.setPluteformid(role.getPluteformid());
        return roleResult;
    }
    /**
     * 部门岗位
     */
    public static Department departmentVoToDepartment(DepartmentVo departmentVo){
        Department department=new Department();
        if(departmentVo==null){
            return null;
        }
        department.setId(departmentVo.getId());
        department.setRoles(departmentVo.getRoles());
        department.setUsers(departmentVo.getUsers());
        department.setDepartment(departmentVo.getDepartment());
        department.setDepartments(departmentVo.getDepartments());
        department.setName(departmentVo.getName());
        department.setDescription(departmentVo.getDescription());
        department.setPluteformid(departmentVo.getPluteformid());
        return department;
    }
    public static DepartmentResult departmentToDepartmentResult(Department department){
        DepartmentResult departmentResult=new DepartmentResult();
        if(department==null){
            return null;
        }
        departmentResult.setId(department.getId());
        departmentResult.setName(department.getName());
        departmentResult.setDescription(department.getDescription());
        departmentResult.setDepartment(department.getDepartment());
        departmentResult.setDepartments(department.getDepartments());
        departmentResult.setRoles(department.getRoles());
        departmentResult.setUsers(department.getUsers());
        departmentResult.setPluteformid(department.getPluteformid());
        return departmentResult;
    }
    public static Privilege privilegeVoToPrivilege(PrivilegeVo privilegeVo){
        Privilege privilege=new Privilege();
        if(privilegeVo==null){
            return null;
        }
        privilege.setId(privilegeVo.getId());
        privilege.setName(privilegeVo.getName());
        privilege.setRoles(privilegeVo.getRoles());
        privilege.setCion(privilegeVo.getCion());
        privilege.setPrivilege(privilegeVo.getPrivilege());
        privilege.setPrivileges(privilegeVo.getPrivileges());
        privilege.setUrl(privilegeVo.getUrl());
        privilege.setPluteformid(privilegeVo.getPluteformid());
        return privilege;
    }

    public static PrivilegeResult privilegeToPrivilegeResult(Privilege privilege){
        PrivilegeResult privilegeResult=new PrivilegeResult();
        if(privilege==null){
            return null;
        }
        privilegeResult.setId(privilege.getId());
        privilegeResult.setName(privilege.getName());
        privilegeResult.setRoles(privilege.getRoles());
        privilegeResult.setCion(privilege.getCion());
        privilegeResult.setPrivilege(privilege.getPrivilege());
        privilegeResult.setPrivileges(privilege.getPrivileges());
        privilegeResult.setUrl(privilege.getUrl());
        privilegeResult.setPluteformid(privilege.getPluteformid());
        return privilegeResult;
    }
    //属性类分离转换
    public static AttributeInfo attributeInfoVoToattributeInfo(AttributeInfoVo attributeInfoVo){
        AttributeInfo attributeInfo = new AttributeInfo();
        if(attributeInfo==null){
            return null;
        }
        attributeInfo.setAttributeId(attributeInfoVo.getAttributeId());
        attributeInfo.setAttributeName(attributeInfoVo.getAttributeName());
        attributeInfo.setDisplaySequence(attributeInfoVo.getDisplaySequence());
        attributeInfo.setTypeId(attributeInfoVo.getTypeId());
        attributeInfo.setUsageMode(attributeInfoVo.getUsageMode());
        attributeInfo.setUseAttributeImage(attributeInfoVo.isUseAttributeImage());
        attributeInfo.setListAttributeValues(attributeInfoVo.getListAttributeValues());
        attributeInfo.setValueStr(attributeInfoVo.getValueStr());
        attributeInfo.setUserDefinedPic(attributeInfoVo.getUserDefinedPic());
        attributeInfo.setPluteformid(attributeInfoVo.getPluteformid());
        attributeInfo.setStatus(attributeInfoVo.getStatus());
        return attributeInfo;
    }
    public  static AttributeInfoResult attributeInfoToattributeInfoResult(AttributeInfo attributeInfo){
        AttributeInfoResult attributeInfoResult = new AttributeInfoResult();
        if(attributeInfoResult==null){
            return  null;
        }
        attributeInfoResult.setAttributeId(attributeInfo.getAttributeId());
        attributeInfoResult.setAttributeName(attributeInfo.getAttributeName());
        attributeInfoResult.setDisplaySequence(attributeInfo.getDisplaySequence());
        attributeInfoResult.setTypeId(attributeInfo.getTypeId());
        attributeInfoResult.setUsageMode(attributeInfo.getUsageMode());
        attributeInfoResult.setUseAttributeImage(attributeInfo.isUseAttributeImage());
        attributeInfoResult.setListAttributeValues(attributeInfo.getListAttributeValues());
        attributeInfoResult.setValueStr(attributeInfo.getValueStr());
        attributeInfoResult.setUserDefinedPic(attributeInfo.getUserDefinedPic());
        attributeInfoResult.setPluteformid(attributeInfo.getPluteformid());
        return attributeInfoResult;
    }
    //属性值类分离转换
    public static AttributeValue attributeValueVoToattributeValue(AttributeValueVo attributeValueVo){
        AttributeValue attributeValue = new AttributeValue();
        if(attributeValueVo==null){
            return null;
        }
        attributeValue.setValueId(attributeValueVo.getValueId());
        attributeValue.setAttributeId(attributeValueVo.getAttributeId());
        attributeValue.setDisplaySequence(attributeValueVo.getDisplaySequence());
        attributeValue.setValueStr(attributeValueVo.getValueStr());
        attributeValue.setImageUrl(attributeValueVo.getImageUrl());
        attributeValue.setPluteformid(attributeValueVo.getPluteformid());
        return attributeValue;
    }
    public static AttributeValueResult attributeValueToattributeValueResult(AttributeValue attributeValue){
        AttributeValueResult attributeValueResult = new AttributeValueResult();
        if(attributeValue == null){
            return null;
        }
        attributeValueResult.setValueId(attributeValue.getValueId());
        attributeValueResult.setAttributeId(attributeValue.getAttributeId());
        attributeValueResult.setDisplaySequence(attributeValue.getDisplaySequence());
        attributeValueResult.setValueStr(attributeValue.getValueStr());
        attributeValueResult.setImageUrl(attributeValue.getImageUrl());
        attributeValueResult.setPluteformid(attributeValue.getPluteformid());
        return attributeValueResult;
    }
    //品牌类分离
    public static BrandInfo brandInfoVoTobrandInfo(BrandInfoVo brandInfoVo){
        BrandInfo brandInfo = new BrandInfo();
        if(brandInfoVo==null){
            return  null;
        }
        brandInfo.setBrandId(brandInfoVo.getBrandId());
        brandInfo.setBrandName(brandInfoVo.getBrandName());
        brandInfo.setBrandSpell(brandInfoVo.getBrandSpell());
        brandInfo.setMetaDescription(brandInfoVo.getMetaDescription());
        brandInfo.setMetaKeywords(brandInfoVo.getMetaKeywords());
        brandInfo.setLogo(brandInfoVo.getLogo());
        brandInfo.setCompanyUrl(brandInfoVo.getCompanyUrl());
        brandInfo.setDescription(brandInfoVo.getDescription());
        brandInfo.setDisplaySequence(brandInfoVo.getDisplaySequence());
        brandInfo.setTheme(brandInfoVo.getTheme());
        brandInfo.setProductTypeIdOrBrandsId(brandInfoVo.getProductTypeIdOrBrandsId());
        brandInfo.setProductTypes(brandInfoVo.getProductTypes());
        brandInfo.setPluteformid(brandInfoVo.getPluteformid());
        return brandInfo;
    }
    public static BrandInfoResult brandInfoTobrandInfoResult(BrandInfo brandInfo){
        BrandInfoResult brandInfoResult = new BrandInfoResult();
        if(brandInfo ==null){
            return null;
        }
        brandInfoResult.setBrandId(brandInfo.getBrandId());
        brandInfoResult.setBrandName(brandInfo.getBrandName());
        brandInfoResult.setBrandSpell(brandInfo.getBrandSpell());
        brandInfoResult.setMetaDescription(brandInfo.getMetaDescription());
        brandInfoResult.setMetaKeywords(brandInfo.getMetaKeywords());
        brandInfoResult.setLogo(brandInfo.getLogo());
        brandInfoResult.setCompanyUrl(brandInfo.getCompanyUrl());
        brandInfoResult.setDescription(brandInfo.getDescription());
        brandInfoResult.setDisplaySequence(brandInfo.getDisplaySequence());
        brandInfoResult.setTheme(brandInfo.getTheme());
        brandInfoResult.setProductTypeIdOrBrandsId(brandInfo.getProductTypeIdOrBrandsId());
        brandInfoResult.setProductTypes(brandInfo.getProductTypes());
        brandInfoResult.setPluteformid(brandInfo.getPluteformid());
        return brandInfoResult;
    }
    //产品分类类分离
    public static CategoryInfo categoryInfoVoTocategoryInfo(CategoryInfoVo categoryInfoVo){
        CategoryInfo categoryInfo = new CategoryInfo();
        if(categoryInfoVo==null){
            return null;
        }
        categoryInfo.setCategoryId(categoryInfoVo.getCategoryId());
        categoryInfo.setName(categoryInfoVo.getName());
        categoryInfo.setDisplaySequence(categoryInfoVo.getDisplaySequence());
        categoryInfo.setMeta_Title(categoryInfoVo.getMeta_Title());
        categoryInfo.setMeta_Description(categoryInfoVo.getMeta_Description());
        categoryInfo.setMeta_Keywords(categoryInfoVo.getMeta_Keywords());
        categoryInfo.setDescription(categoryInfoVo.getDescription());
        categoryInfo.setParentCategoryId(categoryInfoVo.getParentCategoryId());
        categoryInfo.setDepth(categoryInfoVo.getDepth());
        categoryInfo.setPath(categoryInfoVo.getPath());
        categoryInfo.setRewriteName(categoryInfoVo.getRewriteName());
        categoryInfo.setSkuPrefix(categoryInfoVo.getSkuPrefix());
        categoryInfo.setAssociatedProductType(categoryInfoVo.getAssociatedProductType());
        categoryInfo.setImageUrl(categoryInfoVo.getImageUrl());
        categoryInfo.setNotes1(categoryInfoVo.getNotes1());
        categoryInfo.setNotes2(categoryInfoVo.getNotes2());
        categoryInfo.setNotes3(categoryInfoVo.getNotes3());
        categoryInfo.setNotes4(categoryInfoVo.getNotes4());
        categoryInfo.setNotes5(categoryInfoVo.getNotes5());
        categoryInfo.setTheme(categoryInfoVo.getTheme());
        categoryInfo.setHasChildren(categoryInfoVo.getHasChildren());
        categoryInfo.setSeoUrl(categoryInfoVo.getSeoUrl());
        categoryInfo.setSeoImageAlt(categoryInfoVo.getSeoImageAlt());
        categoryInfo.setSeoImageTitle(categoryInfoVo.getSeoImageTitle());
        categoryInfo.setStatus(categoryInfoVo.getStatus());
        categoryInfo.setNamePath(categoryInfoVo.getNamePath());
        categoryInfo.setPriceChange(categoryInfoVo.getPriceChange());
        categoryInfo.setShopId(categoryInfoVo.getShopId());        categoryInfo.setPluteformid(categoryInfoVo.getPluteformid());
        return categoryInfo;
    }
    public static CategoryInfoResult categoryInfoTocategoryInfoResult(CategoryInfo categoryInfo){
        CategoryInfoResult categoryInfoResult = new CategoryInfoResult();
        if(categoryInfo ==null){
            return null;
        }
        categoryInfoResult.setCategoryId(categoryInfo.getCategoryId());
        categoryInfoResult.setName(categoryInfo.getName());
        categoryInfoResult.setDisplaySequence(categoryInfo.getDisplaySequence());
        categoryInfoResult.setMeta_Title(categoryInfo.getMeta_Title());
        categoryInfoResult.setMeta_Description(categoryInfo.getMeta_Description());
        categoryInfoResult.setMeta_Keywords(categoryInfo.getMeta_Keywords());
        categoryInfoResult.setDescription(categoryInfo.getDescription());
        categoryInfoResult.setParentCategoryId(categoryInfo.getParentCategoryId());
        categoryInfoResult.setDepth(categoryInfo.getDepth());
        categoryInfoResult.setPath(categoryInfo.getPath());
        categoryInfoResult.setRewriteName(categoryInfo.getRewriteName());
        categoryInfoResult.setSkuPrefix(categoryInfo.getSkuPrefix());
        categoryInfoResult.setAssociatedProductType(categoryInfo.getAssociatedProductType());
        categoryInfoResult.setImageUrl(categoryInfo.getImageUrl());
        categoryInfoResult.setNotes1(categoryInfo.getNotes1());
        categoryInfoResult.setNotes2(categoryInfo.getNotes2());
        categoryInfoResult.setNotes3(categoryInfo.getNotes3());
        categoryInfoResult.setNotes4(categoryInfo.getNotes4());
        categoryInfoResult.setNotes5(categoryInfo.getNotes5());
        categoryInfoResult.setTheme(categoryInfo.getTheme());
        categoryInfoResult.setHasChildren(categoryInfo.getHasChildren());
        categoryInfoResult.setSeoUrl(categoryInfo.getSeoUrl());
        categoryInfoResult.setSeoImageAlt(categoryInfo.getSeoImageAlt());
        categoryInfoResult.setSeoImageTitle(categoryInfo.getSeoImageTitle());
        categoryInfoResult.setStatus(categoryInfo.getStatus());
        categoryInfoResult.setNamePath(categoryInfo.getNamePath());
        categoryInfoResult.setPriceChange(categoryInfo.getPriceChange());
        categoryInfoResult.setPluteformid(categoryInfo.getPluteformid());
        return categoryInfoResult;
    }
    //产品属性关联表
    public static ProductAttribute  productAttributeVoToproductAttribute(ProductAttributeVo productAttributeVo){
        ProductAttribute productAttribute = new ProductAttribute();
        if(productAttributeVo==null){
            return null;
        }
        productAttribute.setProductId(productAttributeVo.getProductId());
        productAttribute.setAttributeId(productAttributeVo.getAttributeId());
        productAttribute.setValueId(productAttributeVo.getValueId());
        productAttribute.setPluteformid(productAttributeVo.getPluteformid());
        return productAttribute;
    }
    public  static ProductAttributeResult productAttributeToproductAttributeResult(ProductAttribute productAttribute){
        ProductAttributeResult productAttributeResult = new ProductAttributeResult();
        if(productAttribute==null){
            return null;
        }
        productAttributeResult.setProductId(productAttribute.getProductId());
        productAttributeResult.setAttributeId(productAttribute.getAttributeId());
        productAttributeResult.setValueId(productAttribute.getValueId());
        productAttributeResult.setPluteformid(productAttribute.getPluteformid());
        return productAttributeResult;
    }
    //产品分类关系分离
    public static ProductCategories productCategoriesVoToproductCategories(ProductCategoriesVo productCategoriesVo){
        ProductCategories productCategories = new ProductCategories();
        if(productCategoriesVo==null){
            return null;
        }
        productCategories.setCategoryId(productCategoriesVo.getCategoryId());
        productCategories.setProductId(productCategoriesVo.getProductId());
        productCategories.setCategoryPath(productCategoriesVo.getCategoryPath());
        productCategories.setPluteformid(productCategoriesVo.getPluteformid());
        return productCategories;
    }
    public static ProductCategoriesResult productCategoriesToproductCategoriesResult(ProductCategories productCategories){
        ProductCategoriesResult productCategoriesResult = new ProductCategoriesResult();
        if(productCategories==null){
            return null;
        }
        productCategoriesResult.setCategoryId(productCategories.getCategoryId());
        productCategoriesResult.setProductId(productCategories.getProductId());
        productCategoriesResult.setCategoryPath(productCategories.getCategoryPath());
        productCategoriesResult.setPluteformid(productCategories.getPluteformid());
        return productCategoriesResult;
    }
    //产品图片路径
    public static ProductImage productImageVoToproductImage(ProductImageVo productImageVo){
        ProductImage productImage =new ProductImage();
        if(productImageVo==null){
            return null;
        }
        productImage.setProductImageId(productImageVo.getProductImageId());
        productImage.setProductId(productImageVo.getProductId());
        productImage.setImageUrl(productImageVo.getImageUrl());
        productImage.setThumbnailUrl1(productImageVo.getThumbnailUrl1());
        productImage.setThumbnailUrl2(productImageVo.getThumbnailUrl2());
        productImage.setThumbnailUrl3(productImageVo.getThumbnailUrl3());
        productImage.setThumbnailUrl4(productImageVo.getThumbnailUrl4());
        productImage.setThumbnailUrl5(productImageVo.getThumbnailUrl5());
        productImage.setThumbnailUrl6(productImageVo.getThumbnailUrl6());
        productImage.setThumbnailUrl7(productImageVo.getThumbnailUrl7());
        productImage.setThumbnailUrl8(productImageVo.getThumbnailUrl8());
        productImage.setPluteformid(productImageVo.getPluteformid());
        return productImage;
    }
    public static ProductImageResult productImageToproductImageResult(ProductImage productImage){
        ProductImageResult productImageResult = new ProductImageResult();
        if(productImage==null){
            return null;
        }
        productImageResult.setProductImageId(productImage.getProductImageId());
        productImageResult.setProductId(productImage.getProductId());
        productImageResult.setImageUrl(productImage.getImageUrl());
        productImageResult.setThumbnailUrl1(productImage.getThumbnailUrl1());
        productImageResult.setThumbnailUrl2(productImage.getThumbnailUrl2());
        productImageResult.setThumbnailUrl3(productImage.getThumbnailUrl3());
        productImageResult.setThumbnailUrl4(productImage.getThumbnailUrl4());
        productImageResult.setThumbnailUrl5(productImage.getThumbnailUrl5());
        productImageResult.setThumbnailUrl6(productImage.getThumbnailUrl6());
        productImageResult.setThumbnailUrl7(productImage.getThumbnailUrl7());
        productImageResult.setThumbnailUrl8(productImage.getThumbnailUrl8());
        productImageResult.setPluteformid(productImage.getPluteformid());
        return productImageResult;
    }

    //产品类分离
    public static ProductInfo productInfoVoToProduct(ProductInfoVo productInfoVo){
        ProductInfo productInfo =new ProductInfo();
        if(productInfoVo==null){
            return null;
        }
        productInfo.setAttributeInfoList(productInfoVo.getAttributeInfoList());
        productInfo.setFavorites(productInfoVo.getFavorites());
        productInfo.setSkuInfoList(productInfoVo.getSkuInfoList());
        productInfo.setRelatedProductList(productInfoVo.getRelatedProductList());
        productInfo.setProductTypeList(productInfoVo.getProductTypeList());
        productInfo.setRegionInfoList(productInfoVo.getRegionInfoList());
        productInfo.setSupplier(productInfoVo.getSupplier());
        productInfo.setProductType(productInfoVo.getProductType());
        productInfo.setBrand(productInfoVo.getBrand());
        productInfo.setProductCategories(productInfoVo.getProductCategories());
        productInfo.setRelatedProductId(productInfoVo.getRelatedProductId());
        productInfo.setPackageId(productInfoVo.getPackageId());
        productInfo.setSearchProductCategories(productInfoVo.getSearchProductCategories());
        productInfo.setCategoryId(productInfoVo.getCategoryId());
        productInfo.setTypeId(productInfoVo.getTypeId());
        productInfo.setProductId(productInfoVo.getProductId());
        productInfo.setBrandId(productInfoVo.getBrandId());
        productInfo.setProductName(productInfoVo.getProductName());
        productInfo.setProductCode(productInfoVo.getProductCode());
        productInfo.setSupplierId(productInfoVo.getSupplierId());
        productInfo.setRegionId(productInfoVo.getRegionId());
        productInfo.setShortDescription(productInfoVo.getShortDescription());
        productInfo.setUnit(productInfoVo.getUnit());
        productInfo.setDescription(productInfoVo.getDescription());
        productInfo.setMeta_Description(productInfoVo.getMeta_Description());
        productInfo.setMeta_Keywords(productInfoVo.getMeta_Keywords());
        productInfo.setMeta_Title(productInfoVo.getMeta_Title());
        productInfo.setSaleStatus(productInfoVo.getSaleStatus());
        productInfo.setAddedDate(productInfoVo.getAddedDate());
        productInfo.setVistiCounts(productInfoVo.getVistiCounts());
        productInfo.setSaleCounts(productInfoVo.getSaleCounts());
        productInfo.setStock(productInfoVo.getStock());
        productInfo.setDisplaySequence(productInfoVo.getDisplaySequence());
        productInfo.setLineId(productInfoVo.getLineId());
        productInfo.setMarketPrice(productInfoVo.getMarketPrice());
        productInfo.setLowestSalePrice(productInfoVo.getLowestSalePrice());
        productInfo.setPenetrationStatus(productInfoVo.getPenetrationStatus());
        productInfo.setMainCategoryPath(productInfoVo.getMainCategoryPath());
        productInfo.setExtendCategoryPath(productInfoVo.getExtendCategoryPath());
        productInfo.setHasSku(productInfoVo.getHasSku());
        productInfo.setAdjustedPrice(productInfoVo.getAdjustedPrice());
        productInfo.setPoints(productInfoVo.getPoints());
        productInfo.setImageUrl(productInfoVo.getImageUrl());
        productInfo.setThumbnailUrl1(productInfoVo.getThumbnailUrl1());
        productInfo.setThumbnailUrl2(productInfoVo.getThumbnailUrl2());
        productInfo.setThumbnailUrl3(productInfoVo.getThumbnailUrl3());
        productInfo.setThumbnailUrl4(productInfoVo.getThumbnailUrl4());
        productInfo.setThumbnailUrl5(productInfoVo.getThumbnailUrl5());
        productInfo.setThumbnailUrl6(productInfoVo.getThumbnailUrl6());
        productInfo.setThumbnailUrl7(productInfoVo.getThumbnailUrl7());
        productInfo.setThumbnailUrl8(productInfoVo.getThumbnailUrl8());
        productInfo.setImageUrls(productInfoVo.getImageUrls());        productInfo.setMaxQuantity(productInfoVo.getMaxQuantity());
        productInfo.setMinQuantity(productInfoVo.getMinQuantity());
        productInfo.setTags(productInfoVo.getTags());
        productInfo.setSeoUrl(productInfoVo.getSeoUrl());
        productInfo.setSeoImageAlt(productInfoVo.getSeoImageAlt());
        productInfo.setSeoImageTitle(productInfoVo.getSeoImageTitle());
        productInfo.setSalesType(productInfoVo.getSalesType());
        productInfo.setRestrictionCount(productInfoVo.getRestrictionCount());
        productInfo.setDeliveryTip(productInfoVo.getDeliveryTip());
        productInfo.setRemark(productInfoVo.getRemark());
        productInfo.setStaticUrl(productInfoVo.getStaticUrl());
        productInfo.setLimitCount(productInfoVo.getLimitCount());
        productInfo.setProfit(productInfoVo.getProfit());
        productInfo.setSpecStr(productInfoVo.getSpecStr());
        productInfo.setSkusStr(productInfoVo.getSkusStr());
        productInfo.setPluteformid(productInfoVo.getPluteformid());
        productInfo.setShopId(productInfoVo.getShopId());        productInfo.setStation(productInfoVo.getStation());
        productInfo.setStationType(productInfoVo.getStationType());
        return productInfo;
    }

    public static ProductInfoResult productInfoToProductInfoResult(ProductInfo productInfo){
        ProductInfoResult productInfoResult = new ProductInfoResult();
        if(productInfo==null){
            return null;
        }
        productInfoResult.setFavorites(productInfo.getFavorites());
        productInfoResult.setAttributeInfoList(productInfo.getAttributeInfoList());
        productInfoResult.setSkuInfoList(productInfo.getSkuInfoList());
        productInfoResult.setRelatedProductList(productInfo.getRelatedProductList());
        productInfoResult.setProductTypeList(productInfo.getProductTypeList());
        productInfoResult.setRegionInfoList(productInfo.getRegionInfoList());
        productInfoResult.setSupplier(productInfo.getSupplier());
        productInfoResult.setProductType(productInfo.getProductType());
        productInfoResult.setBrand(productInfo.getBrand());
        productInfoResult.setProductCategories(productInfo.getProductCategories());
        productInfoResult.setRelatedProductId(productInfo.getRelatedProductId());
        productInfoResult.setPackageId(productInfo.getPackageId());
        productInfoResult.setSearchProductCategories(productInfo.getSearchProductCategories());
        productInfoResult.setCategoryId(productInfo.getCategoryId());
        productInfoResult.setTypeId(productInfo.getTypeId());
        productInfoResult.setProductId(productInfo.getProductId());
        productInfoResult.setBrandId(productInfo.getBrandId());
        productInfoResult.setProductName(productInfo.getProductName());
        productInfoResult.setProductCode(productInfo.getProductCode());
        productInfoResult.setSupplierId(productInfo.getSupplierId());
        productInfoResult.setRegionId(productInfo.getRegionId());
        productInfoResult.setShortDescription(productInfo.getShortDescription());
        productInfoResult.setUnit(productInfo.getUnit());
        productInfoResult.setDescription(productInfo.getDescription());
        productInfoResult.setMeta_Description(productInfo.getMeta_Description());
        productInfoResult.setMeta_Keywords(productInfo.getMeta_Keywords());
        productInfoResult.setMeta_Title(productInfo.getMeta_Title());
        productInfoResult.setSaleStatus(productInfo.getSaleStatus());
        productInfoResult.setAddedDate(productInfo.getAddedDate());
        productInfoResult.setVistiCounts(productInfo.getVistiCounts());
        productInfoResult.setSaleCounts(productInfo.getSaleCounts());
        productInfoResult.setStock(productInfo.getStock());
        productInfoResult.setDisplaySequence(productInfo.getDisplaySequence());
        productInfoResult.setLineId(productInfo.getLineId());
        productInfoResult.setMarketPrice(productInfo.getMarketPrice());
        productInfoResult.setLowestSalePrice(productInfo.getLowestSalePrice());
        productInfoResult.setPenetrationStatus(productInfo.getPenetrationStatus());
        productInfoResult.setMainCategoryPath(productInfo.getMainCategoryPath());
        productInfoResult.setExtendCategoryPath(productInfo.getExtendCategoryPath());
        productInfoResult.setHasSku(productInfo.getHasSku());
        productInfoResult.setPoints(productInfo.getPoints());
        productInfoResult.setImageUrl(productInfo.getImageUrl());
        productInfoResult.setThumbnailUrl1(productInfo.getThumbnailUrl1());
        productInfoResult.setThumbnailUrl2(productInfo.getThumbnailUrl2());
        productInfoResult.setThumbnailUrl3(productInfo.getThumbnailUrl3());
        productInfoResult.setThumbnailUrl4(productInfo.getThumbnailUrl4());
        productInfoResult.setThumbnailUrl5(productInfo.getThumbnailUrl5());
        productInfoResult.setThumbnailUrl6(productInfo.getThumbnailUrl6());
        productInfoResult.setThumbnailUrl7(productInfo.getThumbnailUrl7());
        productInfoResult.setThumbnailUrl8(productInfo.getThumbnailUrl8());
        productInfoResult.setImageUrls(productInfo.getImageUrls());        productInfoResult.setMaxQuantity(productInfo.getMaxQuantity());
        productInfoResult.setMinQuantity(productInfo.getMinQuantity());
        productInfoResult.setTags(productInfo.getTags());
        productInfoResult.setSeoUrl(productInfo.getSeoUrl());
        productInfoResult.setSeoImageAlt(productInfo.getSeoImageAlt());
        productInfoResult.setSeoImageTitle(productInfo.getSeoImageTitle());
        productInfoResult.setSalesType(productInfo.getSalesType());
        productInfoResult.setRestrictionCount(productInfo.getRestrictionCount());
        productInfoResult.setDeliveryTip(productInfo.getDeliveryTip());
        productInfoResult.setRemark(productInfo.getRemark());
        productInfoResult.setStaticUrl(productInfo.getStaticUrl());
        productInfoResult.setLimitCount(productInfo.getLimitCount());
        productInfoResult.setProfit(productInfo.getProfit());
        productInfoResult.setSpecStr(productInfo.getSpecStr());
        productInfoResult.setSkusStr(productInfo.getSkusStr());
        productInfoResult.setBrandInfos(productInfo.getBrandInfos());
        productInfoResult.setSupplierInfoList(productInfo.getSupplierInfoList());
        productInfoResult.setShopId(productInfo.getShopId());        productInfoResult.setPluteformid(productInfo.getPluteformid());
        productInfoResult.setStation(productInfo.getStation());
        productInfoResult.setStationType(productInfo.getStationType());
        return productInfoResult;
    }
    //产品的类型分离
    public static ProductType productTypeVoToproductType(ProductTypeVo productTypeVo){
        ProductType productType = new ProductType();
        if(productTypeVo==null){
            return null;
        }
        productType.setTypeId(productTypeVo.getTypeId());
        productType.setTypeName(productTypeVo.getTypeName());
        productType.setRemark(productTypeVo.getRemark());
        productType.setBrandsTypes(productTypeVo.getBrandsTypes());
        productType.setPluteformid(productTypeVo.getPluteformid());
        return productType;
    }
    public static ProductTypeResult productTypeToproductTypeResult(ProductType productType){
        ProductTypeResult productTypeResult = new ProductTypeResult();
        if(productType==null){
            return null;
        }
        productTypeResult.setTypeId(productType.getTypeId());
        productTypeResult.setTypeName(productType.getTypeName());
        productTypeResult.setRemark(productType.getRemark());
        productTypeResult.setBrandsTypes(productType.getBrandsTypes());
        productTypeResult.setPluteformid(productType.getPluteformid());
        return productTypeResult;
    }
    //产品地址类
    public static RegionInfo regionInfoVoToregionInfo(RegionInfoVo regionInfoVo){
        RegionInfo regionInfo =new RegionInfo();
        if(regionInfoVo==null){
            return null;
        }
        regionInfo.setAreaId(regionInfoVo.getAreaId());
        regionInfo.setRegionId(regionInfoVo.getRegionId());
        regionInfo.setParentId(regionInfoVo.getParentId());
        regionInfo.setRegionName(regionInfoVo.getRegionName());
        regionInfo.setSpell(regionInfoVo.getSpell());
        regionInfo.setSpellShort(regionInfoVo.getSpellShort());
        regionInfo.setDisplaySequence(regionInfoVo.getDisplaySequence());
        regionInfo.setPath(regionInfoVo.getPath());
        regionInfo.setDepth(regionInfoVo.getDepth());
        regionInfo.setPluteformid(regionInfoVo.getPluteformid());
        return regionInfo;
    }
    public static RegionInfoResult regionInfoToregionInfoResult(RegionInfo regionInfo){
        RegionInfoResult regionInfoResult = new RegionInfoResult();
        if(regionInfo==null){
            return null;
        }
        regionInfoResult.setAreaId(regionInfo.getAreaId());
        regionInfoResult.setRegionId(regionInfo.getRegionId());
        regionInfoResult.setParentId(regionInfo.getParentId());
        regionInfoResult.setRegionName(regionInfo.getRegionName());
        regionInfoResult.setSpell(regionInfo.getSpell());
        regionInfoResult.setSpellShort(regionInfo.getSpellShort());
        regionInfoResult.setDisplaySequence(regionInfo.getDisplaySequence());
        regionInfoResult.setPath(regionInfo.getPath());
        regionInfoResult.setDepth(regionInfo.getDepth());
        regionInfoResult.setPluteformid(regionInfo.getPluteformid());
        return regionInfoResult;
    }
    //产品关联
    public static RelatedProduct relatedProductVoTorelatedProduct(RelatedProductVo relatedProductVo){
        RelatedProduct relatedProduct =new RelatedProduct();
        if(relatedProductVo==null){
            return null;
        }
        relatedProduct.setRelatedId(relatedProductVo.getRelatedId());
        relatedProduct.setProductId(relatedProductVo.getProductId());
        relatedProduct.setPluteformid(relatedProductVo.getPluteformid());
        return relatedProduct;
    }
    public static RelatedProductResult relatedProductTorelatedProductResult(RelatedProduct relatedProduct){
        RelatedProductResult relatedProductResult = new RelatedProductResult();
        if(relatedProduct==null){
            return null;
        }
        relatedProductResult.setRelatedId(relatedProduct.getRelatedId());
        relatedProductResult.setProductId(relatedProduct.getProductId());
        relatedProductResult.setPluteformid(relatedProduct.getPluteformid());
        return relatedProductResult;
    }
    //库存类
    public static SKUInfo skuInfoVoToskuInfo(SKUInfoVo skuInfoVo){
        SKUInfo skuInfo = new SKUInfo();
        if(skuInfoVo==null){
            return null;
        }
        skuInfo.setSkuItemList(skuInfoVo.getSkuItemList());
        skuInfo.setAttributeInfos(skuInfoVo.getAttributeInfos());
        skuInfo.setAttributeValues(skuInfoVo.getAttributeValues());
        skuInfo.setSkuId(skuInfoVo.getSkuId());
        skuInfo.setProductId(skuInfoVo.getProductId());
        skuInfo.setWeight(skuInfoVo.getWeight());
        skuInfo.setStock(skuInfoVo.getStock());
        skuInfo.setAlertStock(skuInfoVo.getAlertStock());
        skuInfo.setCostPrice(skuInfoVo.getCostPrice());
        skuInfo.setSalePrice(skuInfoVo.getSalePrice());
        skuInfo.setUpSelling(skuInfoVo.getUpSelling());
        skuInfo.setProfit(skuInfoVo.getProfit());
        skuInfo.setProductName(skuInfoVo.getProductName());
        skuInfo.setProductImageUrl(skuInfoVo.getProductImageUrl());
        skuInfo.setProductThumbnailUrl(skuInfoVo.getProductThumbnailUrl());
        skuInfo.setAttributeId(skuInfoVo.getAttributeId());
        skuInfo.setValuesStr(skuInfoVo.getValuesStr());
        skuInfo.setValueId(skuInfoVo.getValueId());
        skuInfo.setSpecId(skuInfoVo.getSpecId());
        skuInfo.setMarketprice(skuInfoVo.getMarketprice());
        skuInfo.setSupplierId(skuInfoVo.getSupplierId());
        skuInfo.setPluteformid(skuInfoVo.getPluteformid());
        skuInfo.setSinglePrice(skuInfoVo.getSinglePrice());
        return skuInfo;
    }
    public static SKUInfoResult skuInfoToskuInfoResult(SKUInfo skuInfo){
        SKUInfoResult skuInfoResult = new SKUInfoResult();
        if(skuInfo==null){
            return null;
        }
        skuInfoResult.setSkuItemList(skuInfo.getSkuItemList());
        skuInfoResult.setAttributeInfos(skuInfo.getAttributeInfos());
        skuInfoResult.setAttributeValues(skuInfo.getAttributeValues());
        skuInfoResult.setSkuId(skuInfo.getSkuId());
        skuInfoResult.setProductId(skuInfo.getProductId());
        skuInfoResult.setWeight(skuInfo.getWeight());
        skuInfoResult.setStock(skuInfo.getStock());
        skuInfoResult.setAlertStock(skuInfo.getAlertStock());
        skuInfoResult.setCostPrice(skuInfo.getCostPrice());
        skuInfoResult.setSalePrice(skuInfo.getSalePrice());
        skuInfoResult.setUpSelling(skuInfo.getUpSelling());
        skuInfoResult.setProfit(skuInfo.getProfit());
        skuInfoResult.setProductName(skuInfo.getProductName());
        skuInfoResult.setProductImageUrl(skuInfo.getProductImageUrl());
        skuInfoResult.setProductThumbnailUrl(skuInfo.getProductThumbnailUrl());
        skuInfoResult.setAttributeId(skuInfo.getAttributeId());
        skuInfoResult.setValuesStr(skuInfo.getValuesStr());
        skuInfoResult.setValueId(skuInfo.getValueId());
        skuInfoResult.setSpecId(skuInfo.getSpecId());
        skuInfoResult.setMarketprice(skuInfo.getMarketprice());
        skuInfoResult.setSupplierId(skuInfo.getSupplierId());
        skuInfoResult.setPluteformid(skuInfo.getPluteformid());
        skuInfoResult.setSinglePrice(skuInfo.getSinglePrice());
        return skuInfoResult;
    }
    //库存规格
    public static SKUItem skuItemVoToskuItem(SKUItemVo skuItemVo){
        SKUItem skuItem =new SKUItem();
        if(skuItemVo==null){
            return null;
        }
        skuItem.setValueStr(skuItemVo.getValueStr());
        skuItem.setImageUrl(skuItemVo.getImageUrl());
        skuItem.setProductId(skuItemVo.getProductId());
        skuItem.setSpecId(skuItemVo.getSpecId());
        skuItem.setAttributeId(skuItemVo.getAttributeId());
        skuItem.setValueId(skuItemVo.getValueId());
        skuItem.setAbDisplaySequence(skuItemVo.getAbDisplaySequence());
        skuItem.setUsageMode(skuItemVo.getUsageMode());
        skuItem.setUseAttributeImage(skuItemVo.getUseAttributeImage());
        skuItem.setUserDefinedPic(skuItemVo.getUserDefinedPic());
        skuItem.setAV_isPlaySequence(skuItemVo.getAV_isPlaySequence());
        skuItem.setAV_valueStr(skuItemVo.getAV_valueStr());
        skuItem.setAV_imageUrl(skuItemVo.getAV_imageUrl());
        skuItem.setSkuId(skuItemVo.getSkuId());
        skuItem.setAttributeName(skuItemVo.getAttributeName());
        skuItem.setPluteformid(skuItemVo.getPluteformid());
        return skuItem;
    }
    public static SKUItemResult skuItemToskuItemResult(SKUItem skuItem){
        SKUItemResult skuItemResult =new SKUItemResult();
        if(skuItem==null){
            return null;
        }
        skuItemResult.setValueStr(skuItem.getValueStr());
        skuItemResult.setImageUrl(skuItem.getImageUrl());
        skuItemResult.setProductId(skuItem.getProductId());
        skuItemResult.setSpecId(skuItem.getSpecId());
        skuItemResult.setAttributeId(skuItem.getAttributeId());
        skuItemResult.setValueId(skuItem.getValueId());
        skuItemResult.setAbDisplaySequence(skuItem.getAbDisplaySequence());
        skuItemResult.setUsageMode(skuItem.getUsageMode());
        skuItemResult.setUseAttributeImage(skuItem.getUseAttributeImage());
        skuItemResult.setUserDefinedPic(skuItem.getUserDefinedPic());
        skuItemResult.setAV_isPlaySequence(skuItem.getAV_isPlaySequence());
        skuItemResult.setAV_valueStr(skuItem.getAV_valueStr());
        skuItemResult.setAV_imageUrl(skuItem.getAV_imageUrl());
        skuItemResult.setSkuId(skuItem.getSkuId());
        skuItemResult.setAttributeName(skuItem.getAttributeName());
        skuItemResult.setPluteformid(skuItem.getPluteformid());
        return skuItemResult;
    }
    //规格关系类
    public static SKURelation skuRelationVoToskuRelation(SKURelationVo skuRelationVo){
        SKURelation skuRelation = new SKURelation();
        if(skuRelationVo==null){
            return null;
        }
        skuRelation.setSkuId(skuRelationVo.getSkuId());
        skuRelation.setSpecId(skuRelationVo.getSpecId());
        skuRelation.setProductId(skuRelationVo.getProductId());
        skuRelation.setPluteformid(skuRelationVo.getPluteformid());
        return skuRelation;
    }
    public static SKURelationResult skuRelationToskuRelationResult(SKURelation skuRelation){
        SKURelationResult skuRelationResult = new SKURelationResult();
        if(skuRelation==null){
            return null;
        }
        skuRelationResult.setSkuId(skuRelation.getSkuId());
        skuRelationResult.setSpecId(skuRelation.getSpecId());
        skuRelationResult.setProductId(skuRelation.getProductId());
        skuRelationResult.setPluteformid(skuRelation.getPluteformid());
        return skuRelationResult;
    }
    //图片规格
    public static ThumbnailSize thumbnailSizeVoTothumbnailSize(ThumbnailSizeVo thumbnailSizeVo){
        ThumbnailSize thumbnailSize =new ThumbnailSize();
        if(thumbnailSizeVo==null){
            return null;
        }
        thumbnailSize.setTheme(thumbnailSizeVo.getTheme());
        thumbnailSize.setThumWidth(thumbnailSizeVo.getThumWidth());
        thumbnailSize.setThumHeight(thumbnailSizeVo.getThumHeight());
        thumbnailSize.setType(thumbnailSizeVo.getType());
        thumbnailSize.setRemark(thumbnailSizeVo.getRemark());
        thumbnailSize.setCloudSizeName(thumbnailSizeVo.getCloudSizeName());
        thumbnailSize.setCloudType(thumbnailSizeVo.getCloudType());
        thumbnailSize.setThumMode(thumbnailSizeVo.getThumMode());
        thumbnailSize.setIsWatermark(thumbnailSizeVo.getIsWatermark());
        thumbnailSize.setTheme(thumbnailSizeVo.getTheme());
        thumbnailSize.setPluteformid(thumbnailSizeVo.getPluteformid());
        return thumbnailSize;
    }
    public static ThumbnailSizeResult thumbnailSizeTothumbnailSizeResult(ThumbnailSize thumbnailSize){
        ThumbnailSizeResult thumbnailSizeResult =new ThumbnailSizeResult();
        if(thumbnailSize==null){
            return null;
        }
        thumbnailSizeResult.setTheme(thumbnailSize.getTheme());
        thumbnailSizeResult.setThumWidth(thumbnailSize.getThumWidth());
        thumbnailSizeResult.setThumHeight(thumbnailSize.getThumHeight());
        thumbnailSizeResult.setType(thumbnailSize.getType());
        thumbnailSizeResult.setRemark(thumbnailSize.getRemark());
        thumbnailSizeResult.setCloudSizeName(thumbnailSize.getCloudSizeName());
        thumbnailSizeResult.setCloudType(thumbnailSize.getCloudType());
        thumbnailSizeResult.setThumMode(thumbnailSize.getThumMode());
        thumbnailSizeResult.setIsWatermark(thumbnailSize.getIsWatermark());
        thumbnailSizeResult.setTheme(thumbnailSize.getTheme());
        thumbnailSizeResult.setPluteformid(thumbnailSize.getPluteformid());
        return thumbnailSizeResult;
    }
    //收藏类转换
    public static FavoriteInfo favoriteInfoVoTofavoriteInfo(FavoriteInfoVo favoriteInfoVo){
        FavoriteInfo favoriteInfo = new FavoriteInfo();
        if(favoriteInfoVo==null){
            return null;
        }
        favoriteInfo.setFavoriteId(favoriteInfoVo.getFavoriteId());
        favoriteInfo.setType(favoriteInfoVo.getType());
        favoriteInfo.setTargetId(favoriteInfoVo.getTargetId());
        favoriteInfo.setUserId(favoriteInfoVo.getUserId());
        favoriteInfo.setTags(favoriteInfoVo.getTags());
        favoriteInfo.setRemark(favoriteInfoVo.getRemark());
        favoriteInfo.setCreatedDate(favoriteInfoVo.getCreatedDate());
        favoriteInfo.setPluteformid(favoriteInfoVo.getPluteformid());
        favoriteInfo.setFavoritestr(favoriteInfoVo.getFavoritestr());
        return favoriteInfo;
    }
    public static FavoriteInfoResult favoriteInfoToFavoriteInfoResult(FavoriteInfo favoriteInfo){
        FavoriteInfoResult favoriteInfoResult = new FavoriteInfoResult();
        if(favoriteInfo==null){
            return null;
        }
        favoriteInfoResult.setFavoriteId(favoriteInfo.getFavoriteId());
        favoriteInfoResult.setType(favoriteInfo.getType());
        favoriteInfoResult.setTargetId(favoriteInfo.getTargetId());
        favoriteInfoResult.setUserId(favoriteInfo.getUserId());
        favoriteInfoResult.setTags(favoriteInfo.getTags());
        favoriteInfoResult.setRemark(favoriteInfo.getRemark());
        favoriteInfoResult.setCreatedDate(favoriteInfo.getCreatedDate());
        favoriteInfoResult.setPluteformid(favoriteInfo.getPluteformid());
        favoriteInfoResult.setFavoritestr(favoriteInfo.getFavoritestr());
        return favoriteInfoResult;
    }
    //产品属性转换类
    public static ProductStationModes productStationModesVoToProductStationModes(ProductStationModesVo productStationModesVo){
        ProductStationModes productStationModes = new ProductStationModes();
        if(productStationModesVo==null){
            return null;
        }
        productStationModes.setStationId(productStationModesVo.getStationId());
        productStationModes.setProductId(productStationModesVo.getProductId());
        productStationModes.setDisplaySequence(productStationModesVo.getDisplaySequence());
        productStationModes.setType(productStationModesVo.getType());
        productStationModes.setPluteformid(productStationModesVo.getPluteformid());
        return productStationModes;
    }
    public static ProductStationModesResult productStationModesToProductStationModesResult(ProductStationModes productStationModes){
        ProductStationModesResult productStationModesResult = new ProductStationModesResult();
        if(productStationModes==null){
            return null;
        }
        productStationModesResult.setStationId(productStationModes.getStationId());
        productStationModesResult.setProductId(productStationModes.getProductId());
        productStationModesResult.setDisplaySequence(productStationModes.getDisplaySequence());
        productStationModesResult.setType(productStationModes.getType());
        productStationModesResult.setPluteformid(productStationModes.getPluteformid());
        return productStationModesResult;
    }
    public static Page<SupplierInfo> PageVoToPage(PageVo<SupplierInfo> pageVo){
        Page<SupplierInfo> page=new Page();
        if(pageVo==null){
            return null;
        }
        page.setResult(pageVo.getResult());
        page.setT(pageVo.getT());
        page.setTotalCount(pageVo.getTotalCount());
        page.setCurrentPage(pageVo.getCurrentPage());
        page.setPageSize(pageVo.getPageSize());
        page.setTotalPages(pageVo.getTotalPages());
        return page;
    }
    public static PageResult<SupplierInfo> PageToPageResult(Page<SupplierInfo> page){
        PageResult<SupplierInfo> pageResult=new PageResult<SupplierInfo>();
        if(page==null){
            return null;
        }
        pageResult.setResult(page.getResult());
        pageResult.setT(page.getT());
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotalPages(page.getTotalPages());
        return pageResult;
    }
    public static SupplierInfo supplierInfoVoToSupplierInfo(SupplierInfoVo supplierInfoVo){
        SupplierInfo supplierInfo=new SupplierInfo();
        if(supplierInfoVo==null){
            return null;
        }
        supplierInfo.setName(supplierInfoVo.getName());
        supplierInfo.setStatus(supplierInfoVo.getStatus());
        supplierInfo.setCategoryId(supplierInfoVo.getCategoryId());
        supplierInfo.setEstablishedDate(supplierInfoVo.getEstablishedDate());
        supplierInfo.setCreatedDate(supplierInfoVo.getCreatedDate());
        supplierInfo.setArtiPerson(supplierInfoVo.getArtiPerson());
        supplierInfo.setCompanyType(supplierInfoVo.getCompanyType());
        supplierInfo.setSupplierId(supplierInfoVo.getSupplierId());
        supplierInfo.setUserName(supplierInfoVo.getUserName());
        supplierInfo.setRegisteredCapital(supplierInfoVo.getRegisteredCapital());
        supplierInfo.setBalance(supplierInfoVo.getBalance());
        supplierInfo.setTelPhone(supplierInfoVo.getTelPhone());
        supplierInfo.setCellPhone(supplierInfoVo.getCellPhone());
        supplierInfo.setContactMail(supplierInfoVo.getContactMail());
        supplierInfo.setContact(supplierInfoVo.getContact());
        supplierInfo.setAddress(supplierInfoVo.getAddress());
        supplierInfo.setFax(supplierInfoVo.getFax());
        supplierInfo.setPostCode(supplierInfoVo.getPostCode());
        supplierInfo.setHomePage(supplierInfoVo.getHomePage());
        supplierInfo.setRank(supplierInfoVo.getRank());
        supplierInfo.setTaxNumber(supplierInfoVo.getTaxNumber());
        supplierInfo.setAccountBank(supplierInfoVo.getAccountBank());
        supplierInfo.setAccountInfo(supplierInfoVo.getAccountInfo());
        supplierInfo.setMsn(supplierInfoVo.getMsn());
        supplierInfo.setReMark(supplierInfoVo.getReMark());
        supplierInfo.setIntroduction(supplierInfoVo.getIntroduction());
        supplierInfo.setAaregionpriceimport(supplierInfoVo.getAaregionpriceimport());
        supplierInfo.setEstablishedCity(supplierInfoVo.getEstablishedCity());
        supplierInfo.setRegionId(supplierInfoVo.getRegionId());
        supplierInfo.setEstablishedPlace(supplierInfoVo.getEstablishedPlace());
        supplierInfo.setBrandInfos(supplierInfoVo.getBrandInfos());
        return supplierInfo;
    }
    public static SupplierInfoResult supplierInfoToSupplierInfoResult(SupplierInfo supplierInfo){
        SupplierInfoResult supplierInfoResult=new SupplierInfoResult();
        if(supplierInfo==null){
            return null;
        }
        supplierInfoResult.setName(supplierInfo.getName());
        supplierInfoResult.setStatus(supplierInfo.getStatus());
        supplierInfoResult.setCategoryId(supplierInfo.getCategoryId());
        supplierInfoResult.setEstablishedDate(supplierInfo.getEstablishedDate());
        supplierInfoResult.setCreatedDate(supplierInfo.getCreatedDate());
        supplierInfoResult.setArtiPerson(supplierInfo.getArtiPerson());
        supplierInfoResult.setCompanyType(supplierInfo.getCompanyType());
        supplierInfoResult.setSupplierId(supplierInfo.getSupplierId());
        supplierInfoResult.setUserName(supplierInfo.getUserName());
        supplierInfoResult.setRegisteredCapital(supplierInfo.getRegisteredCapital());
        supplierInfoResult.setBalance(supplierInfo.getBalance());
        supplierInfoResult.setTelPhone(supplierInfo.getTelPhone());
        supplierInfoResult.setCellPhone(supplierInfo.getCellPhone());
        supplierInfoResult.setContactMail(supplierInfo.getContactMail());
        supplierInfoResult.setContact(supplierInfo.getContact());
        supplierInfoResult.setAddress(supplierInfo.getAddress());
        supplierInfoResult.setFax(supplierInfo.getFax());
        supplierInfoResult.setPostCode(supplierInfo.getPostCode());
        supplierInfoResult.setHomePage(supplierInfo.getHomePage());
        supplierInfoResult.setRank(supplierInfo.getRank());
        supplierInfoResult.setTaxNumber(supplierInfo.getTaxNumber());
        supplierInfoResult.setAccountBank(supplierInfo.getAccountBank());
        supplierInfoResult.setAccountInfo(supplierInfo.getAccountInfo());
        supplierInfoResult.setMsn(supplierInfo.getMsn());
        supplierInfoResult.setReMark(supplierInfo.getReMark());
        supplierInfoResult.setIntroduction(supplierInfo.getIntroduction());
        supplierInfoResult.setAaregionpriceimport(supplierInfo.getAaregionpriceimport());
        supplierInfoResult.setEstablishedCity(supplierInfo.getEstablishedCity());
        supplierInfoResult.setRegionId(supplierInfo.getRegionId());
        supplierInfoResult.setRegionId(supplierInfo.getRegionId());
        supplierInfoResult.setEstablishedPlace(supplierInfo.getEstablishedPlace());
        supplierInfoResult.setBrandInfos(supplierInfo.getBrandInfos());
        return supplierInfoResult;
    }

    public static SupplierProduct supplierProductVoToSupplierProduct(SupplierProductVo supplierProductVo){
        if(supplierProductVo==null){
            return null;
        }
        SupplierProduct supplierProduct=new SupplierProduct();
        supplierProduct.setSupplierInfo(supplierProductVo.getSupplierInfo());
        supplierProduct.setSkuInfo(supplierProductVo.getSkuInfo());
        supplierProduct.setPrize(supplierProductVo.getPrize());
        supplierProduct.setCount(supplierProductVo.getCount());
        supplierProduct.setProductName(supplierProductVo.getProductName());
        supplierProduct.setScore(supplierProductVo.getScore());
        return supplierProduct;
    }

    public static SupplierProductResult supplierProductToSupplierProductResult(SupplierProduct supplierProduct){
        if(supplierProduct==null){
            return null;
        }
        SupplierProductResult supplierProductResult=new SupplierProductResult();
        supplierProductResult.setSupplierInfo(supplierProduct.getSupplierInfo());
        supplierProductResult.setSkuInfo(supplierProduct.getSkuInfo());
        supplierProductResult.setPrize(supplierProduct.getPrize());
        supplierProductResult.setCount(supplierProduct.getCount());
        supplierProductResult.setProductName(supplierProduct.getProductName());
        supplierProductResult.setScore(supplierProduct.getScore());
        return supplierProductResult;
    }

    //产品类型分离
    public static ProductType productTypeVoToProductType(ProductTypeVo productTypeVo){
        ProductType productType = new ProductType();
        if(productType==null){
            return  null;
        }
        productType.setTypeId(productTypeVo.getTypeId());
        productType.setBrandsTypes(productTypeVo.getBrandsTypes());
        productType.setRemark(productTypeVo.getRemark());
        productType.setTypeName(productTypeVo.getTypeName());
        return productType;
    }

    public  static ProductTypeResult productTypeToProductTypeResult(ProductType productType) {
        ProductTypeResult productTypeResult = new ProductTypeResult();
        if (productTypeResult==null){
            return null;
        }
        productTypeResult.setTypeId(productType.getTypeId());
        productTypeResult.setBrandsTypes(productType.getBrandsTypes());
        productTypeResult.setTypeName(productType.getTypeName());
        productTypeResult.setRemark(productType.getRemark());
        return productTypeResult;
    }

    /**
     * 采购部
     * @param purchaseInfo
     * @return
     */
    public static PurchaseInfoResult purchaseToPurchaseInfoResult(PurchaseInfo purchaseInfo){
        PurchaseInfoResult purchaseInfoResult=new PurchaseInfoResult();
        if (purchaseInfo==null){
            return null;
        }
        purchaseInfoResult.setProcurementTime(purchaseInfo.getProcurementTime());
        purchaseInfoResult.setPurchaseCode(purchaseInfo.getPurchaseCode());
        purchaseInfoResult.setPurchaseId(purchaseInfo.getPurchaseId());
        purchaseInfoResult.setPurchaseItemses(purchaseInfo.getPurchaseItemses());
        purchaseInfoResult.setState(purchaseInfo.getState());
        purchaseInfoResult.setTotalmoney(purchaseInfo.getTotalmoney());
        purchaseInfoResult.setTotalnumber(purchaseInfo.getTotalnumber());
        purchaseInfoResult.setTotalweights(purchaseInfo.getTotalweights());
        purchaseInfoResult.setUpdateTime(purchaseInfo.getUpdateTime());
        purchaseInfoResult.setUser(purchaseInfo.getUser());
        return  purchaseInfoResult;
    }
    public  static PurchaseInfo purchaseInfoVoToPurchase(PurchaseInfoVo purchaseInfoVo){
        PurchaseInfo purchaseInfo=new PurchaseInfo();
        if(purchaseInfoVo==null){
            return null;
        }
        purchaseInfo.setUser(purchaseInfoVo.getUser());
        purchaseInfo.setUpdateTime(purchaseInfoVo.getUpdateTime());
        purchaseInfo.setTotalweights(purchaseInfoVo.getTotalweights());
        purchaseInfo.setTotalnumber(purchaseInfoVo.getTotalnumber());
        purchaseInfo.setProcurementTime(purchaseInfoVo.getProcurementTime());
        purchaseInfo.setPurchaseCode(purchaseInfoVo.getPurchaseCode());
        purchaseInfo.setPurchaseId(purchaseInfoVo.getPurchaseId());
        purchaseInfo.setPurchaseItemses(purchaseInfoVo.getPurchaseItemses());
        purchaseInfo.setState(purchaseInfoVo.getState());
        purchaseInfo.setTotalmoney(purchaseInfoVo.getTotalmoney());
        return purchaseInfo;
    }
    /**
     * 订单
     */
    public static OrderInfoResult orderInfoToOrderInfoResult(OrderInfo orderInfo){
        OrderInfoResult orderInfoResult=new OrderInfoResult();
        if(orderInfo==null){
            return null;
        }
        orderInfoResult.setOrderCode(orderInfo.getOrderCode());
        orderInfoResult.setOrderId(orderInfo.getOrderId());
        orderInfoResult.setOrderItems(orderInfo.getOrderItems());
        orderInfoResult.setOriginalId(orderInfo.getOriginalId());
        orderInfoResult.setRegionId(orderInfo.getRegionId());
        orderInfoResult.setShipAddress(orderInfo.getShipAddress());
        orderInfoResult.setShipName(orderInfo.getShipName());
        orderInfoResult.setSubOrders(orderInfo.getSubOrders());
        orderInfoResult.setShipCellPhone(orderInfo.getShipCellPhone());
        orderInfoResult.setShipRegion(orderInfo.getShipRegion());
        orderInfoResult.setShipTelPhone(orderInfo.getShipTelPhone());
        orderInfoResult.setParentOrderId(orderInfo.getParentOrderId());
        orderInfoResult.setOrderCheckStatus(orderInfo.getOrderCheckStatus());
        return orderInfoResult;
    }
    public  static OrderInfo orderInfoVoToOrderInfo(OrderInfoVo orderInfoVo){
        OrderInfo orderInfo=new OrderInfo();
        if(orderInfoVo==null){
            return null;
        }
        orderInfo.setParentOrderId(orderInfoVo.getParentOrderId());
        orderInfo.setShipTelPhone(orderInfoVo.getShipTelPhone());
        orderInfo.setShipCellPhone(orderInfoVo.getShipCellPhone());
        orderInfo.setShipAddress(orderInfoVo.getShipAddress());
        orderInfo.setShipRegion(orderInfoVo.getShipRegion());
        orderInfo.setSubOrders(orderInfoVo.getSubOrders());
        orderInfo.setShipName(orderInfoVo.getShipName());
        orderInfo.setOrderCode(orderInfoVo.getOrderCode());
        orderInfo.setOrderId(orderInfoVo.getOrderId());
        orderInfo.setOrderItems(orderInfoVo.getOrderItems());
        orderInfo.setOriginalId(orderInfoVo.getOriginalId());
        return orderInfo;
    }

    public static OrderItemsResult orderItemToOrderItemResult(OrderInfo oo, OrderItems orderItems)
    {
        return null;
    }

    /**
     * 客户表的前后转换
     * @param users
     * @return
     */
    public static AccountUserResult  AccountToUserResult(Users users){
        AccountUserResult accountUserResult=new AccountUserResult();
        if(users==null){
            return  null;
        }
		accountUserResult.setOpenId(users.getOpenId());        accountUserResult.setPluteformid(users.getPluteformid());
        accountUserResult.setActivity(users.getActivity());
        accountUserResult.setAfterTime(users.getAfterTime());
        accountUserResult.setBeforeTime(users.getBeforeTime());
        accountUserResult.setCurrentPage(users.getCurrentPage());
        accountUserResult.setDepartmentID(users.getDepartmentID());
        accountUserResult.setEmail(users.getEmail());
        accountUserResult.setEmployeeID(users.getEmployeeID());
        accountUserResult.setKey(users.getKey());
        accountUserResult.setNickName(users.getNickName());
        accountUserResult.setPageSize(users.getPageSize());
        accountUserResult.setPassWord(users.getPassWord());
        accountUserResult.setPhone(users.getPhone());
        accountUserResult.setPointsDetail(users.getPointsDetail());
        accountUserResult.setRankDetail(users.getRankDetail());
        accountUserResult.setSex(users.getSex());
        accountUserResult.setShippingAddress(users.getShippingAddress());
        accountUserResult.setStyle(users.getStyle());
        accountUserResult.setTrueName(users.getTrueName());
        accountUserResult.setUserCLang(users.getUserCLang());
        accountUserResult.setUserDateApprove(users.getUserDateApprove());
        accountUserResult.setUserIApproveState(users.getUserIApproveState());
        accountUserResult.setUserDateCreate(users.getUserDateCreate());
        accountUserResult.setUserDateExpire(users.getUserDateExpire());
        accountUserResult.setUserICreator(users.getUserICreator());
        accountUserResult.setUserId(users.getUserId());
        accountUserResult.setUserName(users.getUserName());
        accountUserResult.setUsersExp(users.getUsersExp());
        accountUserResult.setUserScore(users.getUserScore());
        accountUserResult.setUserType(users.getUserType());
        accountUserResult.setUserDateValid(users.getUserDateValid());
        accountUserResult.setPluteformid(users.getPluteformid());
        accountUserResult.setOpenId(users.getOpenId());
        accountUserResult.setFollowWay(users.getFollowWay());
        return accountUserResult;
    }
    public static Users accounUserVoToAccountUser(AccountUserVo accountUserVo){
        Users users=new Users();
        if(accountUserVo==null){
            return null;
        }
        users.setShippingAddress(accountUserVo.getShippingAddress());
        users.setBeforeTime(accountUserVo.getBeforeTime());
        users.setAfterTime(accountUserVo.getAfterTime());
        users.setKey(accountUserVo.getKey());
        users.setPointsDetail(accountUserVo.getPointsDetail());
        users.setUsersExp(accountUserVo.getUsersExp());
        users.setUserScore(accountUserVo.getUserScore());
        users.setRankDetail(accountUserVo.getRankDetail());
        users.setPageSize(accountUserVo.getPageSize());
        users.setCurrentPage(accountUserVo.getCurrentPage());
        users.setUserId(accountUserVo.getUserId());
        users.setUserName(accountUserVo.getUserName());
        users.setPassWord(accountUserVo.getPassWord());
        users.setTrueName(accountUserVo.getTrueName());
        users.setNickName(accountUserVo.getNickName());
        users.setSex(accountUserVo.getSex());
        users.setPhone(accountUserVo.getPhone());
        users.setEmail(accountUserVo.getEmail());
        users.setDepartmentID(accountUserVo.getDepartmentID());
        users.setEmployeeID(accountUserVo.getEmployeeID());
        users.setUserType(accountUserVo.getUserType());
        users.setActivity(accountUserVo.getActivity());
        users.setStyle(accountUserVo.getStyle());
        users.setUserDateCreate(accountUserVo.getUserDateCreate());
        users.setUserICreator(accountUserVo.getUserICreator());
        users.setUserDateExpire(accountUserVo.getUserDateExpire());
        users.setUserDateApprove(accountUserVo.getUserDateApprove());
        users.setUserDateValid(accountUserVo.getUserDateValid());
        users.setUserIApprover(accountUserVo.getUserIApprover());
        users.setUserIApproveState(accountUserVo.getUserIApproveState());
        users.setPluteformid(accountUserVo.getPluteformid());
        users.setUserCLang(accountUserVo.getUserCLang());
        users.setPluteformid(accountUserVo.getPluteformid());
        users.setOpenId(accountUserVo.getOpenId());
        users.setFollowWay(accountUserVo.getFollowWay());
        return users;
    }

    /**
     * 交易记录表
     * @param tradingRecordVo
     * @return
     */
    public static TradingRecord tradingRecordVoToTradingRecord(TradingRecordVo tradingRecordVo){
        TradingRecord tradingRecord = new TradingRecord();
        if(tradingRecordVo==null){
            return null;
        }
        tradingRecord.setId(tradingRecordVo.getId());
        tradingRecord.setUserId(tradingRecordVo.getUserId());
        tradingRecord.setPluteformid(tradingRecordVo.getPluteformid());
        tradingRecord.setBalance(tradingRecordVo.getBalance());
        tradingRecord.setOrderCode(tradingRecordVo.getOrderCode());
        tradingRecord.setRemark(tradingRecordVo.getRemark());
        tradingRecord.setCreateDate(tradingRecordVo.getCreateDate());
        tradingRecord.setOpenId(tradingRecordVo.getOpenId());
        tradingRecord.setSuccessState(tradingRecordVo.getSuccessState());
        tradingRecord.setPaymentTypeId(tradingRecordVo.getPaymentTypeId());
        return tradingRecord;
    }
    public static TradingRecordResult tradingRecordToTradingRecordResult(TradingRecord tradingRecord){
        TradingRecordResult tradingRecordResult = new TradingRecordResult();
        if(tradingRecord==null){
            return null;
        }
        tradingRecordResult.setId(tradingRecord.getId());
        tradingRecordResult.setUserId(tradingRecord.getUserId());
        tradingRecordResult.setPluteformid(tradingRecord.getPluteformid());
        tradingRecordResult.setBalance(tradingRecord.getBalance());
        tradingRecordResult.setOrderCode(tradingRecord.getOrderCode());
        tradingRecordResult.setRemark(tradingRecord.getRemark());
        tradingRecordResult.setCreateDate(tradingRecord.getCreateDate());
        tradingRecordResult.setOpenId(tradingRecord.getOpenId());
        tradingRecordResult.setSuccessState(tradingRecord.getSuccessState());
        tradingRecordResult.setPaymentTypeId(tradingRecord.getPaymentTypeId());
        return tradingRecordResult;
    }

    public  static BalanceDetails balanceDetailsVoToBalanceDetails(BalanceDetailsVo balanceDetailsVo){
        BalanceDetails balanceDetails = new BalanceDetails();
        if(balanceDetailsVo==null){
            return null;
        }
        balanceDetails.setJournalNumber(balanceDetailsVo.getJournalNumber());
        balanceDetails.setUserId(balanceDetailsVo.getUserId());
        balanceDetails.setTradeDate(balanceDetailsVo.getTradeDate());
        balanceDetails.setTradeType(balanceDetailsVo.getTradeType());
        balanceDetails.setIncome(balanceDetailsVo.getIncome());
        balanceDetails.setExpenses(balanceDetailsVo.getExpenses());
        balanceDetails.setBalance(balanceDetailsVo.getBalance());
        balanceDetails.setPayer(balanceDetailsVo.getPayer());
        balanceDetails.setPayee(balanceDetailsVo.getPayee());
        balanceDetails.setRemark(balanceDetailsVo.getRemark());
        balanceDetails.setPluteformid(balanceDetailsVo.getPluteformid());
        return balanceDetails;
    }
    public static BalanceDetailsResult balanceDetailsToBalanceDetailsResult(BalanceDetails balanceDetails){
        BalanceDetailsResult balanceDetailsResult = new BalanceDetailsResult();
        if(balanceDetails==null){
            return null;
        }
        balanceDetailsResult.setJournalNumber(balanceDetails.getJournalNumber());
        balanceDetailsResult.setUserId(balanceDetails.getUserId());
        balanceDetailsResult.setTradeDate(balanceDetails.getTradeDate());
        balanceDetailsResult.setTradeType(balanceDetails.getTradeType());
        balanceDetailsResult.setIncome(balanceDetails.getIncome());
        balanceDetailsResult.setExpenses(balanceDetails.getExpenses());
        balanceDetailsResult.setBalance(balanceDetails.getBalance());
        balanceDetailsResult.setPayer(balanceDetails.getPayer());
        balanceDetailsResult.setPayee(balanceDetails.getPayee());
        balanceDetailsResult.setRemark(balanceDetails.getRemark());
        balanceDetailsResult.setPluteformid(balanceDetails.getPluteformid());
        return balanceDetailsResult;
    }

    /**
     * 用户systemcode
     * @return
     */
    public static com.zm.mall.domain.business.accountsUsers.ItemSystemCode userItemSystemCodeVoToItemSystemCode(com.zm.mall.client.vo.business.accounts.ItemSystemCodeVo itemSystemCodeVo){
        com.zm.mall.domain.business.accountsUsers.ItemSystemCode itemSystemCode = new com.zm.mall.domain.business.accountsUsers.ItemSystemCode();
        if(itemSystemCodeVo==null){
            return null;
        }
        itemSystemCode.setId(itemSystemCodeVo.getId());
        itemSystemCode.setFid(itemSystemCodeVo.getFid());
        itemSystemCode.setCode(itemSystemCodeVo.getCode());
        itemSystemCode.setName(itemSystemCodeVo.getName());
        itemSystemCode.setDescription(itemSystemCodeVo.getDescription());
        itemSystemCode.setUserScore(itemSystemCodeVo.getUserScore());
        itemSystemCode.setUserId(itemSystemCodeVo.getUserId());
        itemSystemCode.setValue(itemSystemCodeVo.getValue());
        itemSystemCode.setPluteformid(itemSystemCodeVo.getPluteformid());
        return itemSystemCode;
    }
    public static com.zm.mall.client.result.business.accounts.ItemSystemCodeResult userItemSystemCodeToItemSystemCodeResult(com.zm.mall.domain.business.accountsUsers.ItemSystemCode itemSystemCode){
        com.zm.mall.client.result.business.accounts.ItemSystemCodeResult itemSystemCodeResult = new com.zm.mall.client.result.business.accounts.ItemSystemCodeResult();
        if(itemSystemCode==null){
            return null;
        }
        itemSystemCodeResult.setId(itemSystemCode.getId());
        itemSystemCodeResult.setFid(itemSystemCode.getFid());
        itemSystemCodeResult.setCode(itemSystemCode.getCode());
        itemSystemCodeResult.setName(itemSystemCode.getName());
        itemSystemCodeResult.setDescription(itemSystemCode.getDescription());
        itemSystemCodeResult.setUserScore(itemSystemCode.getUserScore());
        itemSystemCodeResult.setUserId(itemSystemCode.getUserId());
        itemSystemCodeResult.setValue(itemSystemCode.getValue());
        itemSystemCodeResult.setPluteformid(itemSystemCode.getPluteformid());
        return itemSystemCodeResult;
    }
    /**
     * 用户地址
     * @return
     */
    public static MsRegions msRegionsVoToMsRegions(MsRegionsVo msRegionsVo){
        MsRegions msRegions = new MsRegions();
        if(msRegionsVo==null){
            return null;
        }
        msRegions.setAreaId(msRegionsVo.getAreaId());
        msRegions.setRegionId(msRegionsVo.getRegionId());
        msRegions.setParentId(msRegionsVo.getParentId());
        msRegions.setRegionName(msRegionsVo.getRegionName());
        msRegions.setSpell(msRegionsVo.getSpell());
        msRegions.setSpellShort(msRegionsVo.getSpellShort());
        msRegions.setDisplaySequence(msRegionsVo.getDisplaySequence());
        msRegions.setPath(msRegionsVo.getPath());
        msRegions.setPluteformid(msRegionsVo.getPluteformid());
        msRegions.setDepth(msRegionsVo.getDepth());
        return msRegions;
    }
    public static MsRegionsResult msRegionsToMsRegionsResult(MsRegions msRegions){
        MsRegionsResult msRegionsResult = new MsRegionsResult();
        if(msRegions==null){
            return null;
        }
        msRegionsResult.setAreaId(msRegions.getAreaId());
        msRegionsResult.setRegionId(msRegions.getRegionId());
        msRegionsResult.setParentId(msRegions.getParentId());
        msRegionsResult.setRegionName(msRegions.getRegionName());
        msRegionsResult.setSpell(msRegions.getSpell());
        msRegionsResult.setSpellShort(msRegions.getSpellShort());
        msRegionsResult.setDisplaySequence(msRegions.getDisplaySequence());
        msRegionsResult.setPath(msRegions.getPath());
        msRegionsResult.setPluteformid(msRegions.getPluteformid());
        msRegionsResult.setDepth(msRegions.getDepth());
        return msRegionsResult;
    }
    public static PointsDetail pointsDetailVoToPointsDetail(PointsDetailVo pointsDetailVo){
        PointsDetail pointsDetail = new PointsDetail();
        if(pointsDetailVo==null){
            return null;
        }
        pointsDetail.setPointsRule(pointsDetailVo.getPointsRule());
        pointsDetail.setRuleId(pointsDetailVo.getRuleId());
        pointsDetail.setUserId(pointsDetailVo.getUserId());
        pointsDetail.setDetailId(pointsDetailVo.getDetailId());
        pointsDetail.setScore(pointsDetailVo.getScore());
        pointsDetail.setExtData(pointsDetailVo.getExtData());
        pointsDetail.setCurrentPoints(pointsDetailVo.getCurrentPoints());
        pointsDetail.setDescription(pointsDetailVo.getDescription());
        pointsDetail.setCreatedDate(pointsDetailVo.getCreatedDate());
        pointsDetail.setType(pointsDetailVo.getType());
        pointsDetail.setPluteformid(pointsDetailVo.getPluteformid());
        return pointsDetail;
    }
    public static PointsDetailResult pointsDetailToPointsDetailResult(PointsDetail pointsDetail){
        PointsDetailResult pointsDetailResult = new PointsDetailResult();
        if(pointsDetail==null){
            return null;
        }
        pointsDetailResult.setPointsRule(pointsDetail.getPointsRule());
        pointsDetailResult.setRuleId(pointsDetail.getRuleId());
        pointsDetailResult.setUserId(pointsDetail.getUserId());
        pointsDetailResult.setDetailId(pointsDetail.getDetailId());
        pointsDetailResult.setScore(pointsDetail.getScore());
        pointsDetailResult.setExtData(pointsDetail.getExtData());
        pointsDetailResult.setCurrentPoints(pointsDetail.getCurrentPoints());
        pointsDetailResult.setDescription(pointsDetail.getDescription());
        pointsDetailResult.setCreatedDate(pointsDetail.getCreatedDate());
        pointsDetailResult.setType(pointsDetail.getType());
        pointsDetailResult.setPluteformid(pointsDetail.getPluteformid());
        return pointsDetailResult;
    }

    /**
     * 用户systemcode
     * @return
     */
    public static com.zm.mall.domain.business.accountsUsers.SystemCode userSystemCodeVoToSystemCode(com.zm.mall.client.vo.business.accounts.SystemCodeVo systemCodeVo){
        com.zm.mall.domain.business.accountsUsers.SystemCode systemCode = new com.zm.mall.domain.business.accountsUsers.SystemCode();
        if(systemCode==null){
            return null;
        }
        systemCode.setId(systemCodeVo.getId());
        systemCode.setPid(systemCodeVo.getPid());
        systemCode.setCode(systemCodeVo.getCode());
        systemCode.setName(systemCodeVo.getName());
        systemCode.setItemSystemCode(systemCodeVo.getItemSystemCode());
        systemCode.setPluteformid(systemCodeVo.getPluteformid());
        return systemCode;
    }
    public static com.zm.mall.client.result.business.accounts.SystemCodeResult userSystemCodeToSystemCodeResult(com.zm.mall.domain.business.accountsUsers.SystemCode systemCode){
        com.zm.mall.client.result.business.accounts.SystemCodeResult systemCodeResult = new com.zm.mall.client.result.business.accounts.SystemCodeResult();
        if(systemCode==null){
            return null;
        }
        systemCodeResult.setId(systemCode.getId());
        systemCodeResult.setPid(systemCode.getPid());
        systemCodeResult.setCode(systemCode.getCode());
        systemCodeResult.setName(systemCode.getName());
        systemCodeResult.setItemSystemCode(systemCode.getItemSystemCode());
        systemCodeResult.setPluteformid(systemCode.getPluteformid());
        return systemCodeResult;
    }
    public static UserCard userCardVoToUserCard(UserCardVo userCardVo){
        UserCard userCard = new UserCard();
        if(userCardVo==null){
            return null;
        }
        userCard.setUsers(userCardVo.getUsers());
        userCard.setCardCode(userCardVo.getCardCode());
        userCard.setCardPwd(userCardVo.getCardPwd());
        userCard.setCardValue(userCardVo.getCardValue());
        userCard.setUserId(userCardVo.getUserId());
        userCard.setStatus(userCardVo.getStatus());
        userCard.setType(userCardVo.getType());
        userCard.setEndDate(userCardVo.getEndDate());
        userCard.setCreatedDate(userCardVo.getCreatedDate());
        userCard.setRemark(userCardVo.getRemark());
        userCard.setPluteformid(userCardVo.getPluteformid());
        return userCard;
    }
    public static UserCardResult userCardToUserCardResult(UserCard userCard){
        UserCardResult userCardResult = new UserCardResult();
        if(userCard==null){
            return null;
        }
        userCardResult.setUsers(userCard.getUsers());
        userCardResult.setCardCode(userCard.getCardCode());
        userCardResult.setCardPwd(userCard.getCardPwd());
        userCardResult.setCardValue(userCard.getCardValue());
        userCardResult.setUserId(userCard.getUserId());
        userCardResult.setStatus(userCard.getStatus());
        userCardResult.setType(userCard.getType());
        userCardResult.setEndDate(userCard.getEndDate());
        userCardResult.setCreatedDate(userCard.getCreatedDate());
        userCardResult.setRemark(userCard.getRemark());
        userCardResult.setPluteformid(userCard.getPluteformid());
        return userCardResult;
    }
    public static UserRank userRankVoToUserRank(UserRankVo userRankVo){
        UserRank userRank = new UserRank();
        if(userRankVo==null){
            return null;
        }
        userRank.setRankId(userRankVo.getRankId());
        userRank.setName(userRankVo.getName());
        userRank.setRankLevel(userRankVo.getRankLevel());
        userRank.setScoreMax(userRankVo.getScoreMax());
        userRank.setScoreMin(userRankVo.getScoreMin());
        userRank.setIsDefault(userRankVo.getIsDefault());
        userRank.setRankType(userRankVo.getRankType());
        userRank.setDescription(userRankVo.getDescription());
        userRank.setCreatedUserId(userRankVo.getCreatedUserId());
        userRank.setPluteformid(userRankVo.getPluteformid());
        return userRank;
    }
    public static UserRankResult userRankToUserRankResult(UserRank userRank){
        UserRankResult userRankResult = new UserRankResult();
        if(userRank==null){
            return null;
        }
        userRankResult.setRankId(userRank.getRankId());
        userRankResult.setName(userRank.getName());
        userRankResult.setRankLevel(userRank.getRankLevel());
        userRankResult.setScoreMax(userRank.getScoreMax());
        userRankResult.setScoreMin(userRank.getScoreMin());
        userRankResult.setIsDefault(userRank.getIsDefault());
        userRankResult.setRankType(userRank.getRankType());
        userRankResult.setDescription(userRank.getDescription());
        userRankResult.setCreatedUserId(userRank.getCreatedUserId());
        userRankResult.setPluteformid(userRank.getPluteformid());
        return userRankResult;
    }
    public static UserScore userScoreVoToUserScore(UserScoreVo userScoreVo){
        UserScore userScore = new UserScore();
        if(userScoreVo==null){
            return null;
        }
        userScore.setId(userScoreVo.getId());
        userScore.setUserId(userScoreVo.getUserId());
        userScore.setPid(userScoreVo.getPid());
        userScore.setCreateTime(userScoreVo.getCreateTime());
        userScore.setUpdateTime(userScoreVo.getUpdateTime());
        userScore.setScore(userScoreVo.getScore());
        userScore.setStatus(userScoreVo.getStatus());
        userScore.setInputScore(userScoreVo.getInputScore());
        userScore.setPluteformid(userScoreVo.getPluteformid());
        return userScore;
    }
    public static UserScoreResult userScoreToUserScoreResult(UserScore userScore){
        UserScoreResult userScoreResult = new UserScoreResult();
        if(userScore==null){
            return null;
        }
        userScoreResult.setId(userScore.getId());
        userScoreResult.setUserId(userScore.getUserId());
        userScoreResult.setPid(userScore.getPid());
        userScoreResult.setCreateTime(userScore.getCreateTime());
        userScoreResult.setUpdateTime(userScore.getUpdateTime());
        userScoreResult.setScore(userScore.getScore());
        userScoreResult.setStatus(userScore.getStatus());
        userScoreResult.setInputScore(userScore.getInputScore());
        userScoreResult.setPluteformid(userScore.getPluteformid());
        return userScoreResult;
    }
    public static UsersExp usersExpVoToUsersExp(UsersExpVo usersExpVo){
        UsersExp usersExp = new UsersExp();
        if(usersExpVo==null){
            return null;
        }
        usersExp.setUserId(usersExpVo.getUserId());
        usersExp.setGravatar(usersExpVo.getGravatar());
        usersExp.setSingature(usersExpVo.getSingature());
        usersExp.setTelPhone(usersExpVo.getTelPhone());
        usersExp.setQq(usersExpVo.getQq());
        usersExp.setMsn(usersExpVo.getMsn());
        usersExp.setHomePage(usersExpVo.getHomePage());
        usersExp.setBirthday(usersExpVo.getBirthday());
        usersExp.setBirthdayVisible(usersExpVo.getBirthdayVisible());
        usersExp.setBirthdayIndexVisible(usersExpVo.getBirthdayIndexVisible());
        usersExp.setConstellation(usersExpVo.getConstellation());
        usersExp.setConstellationVisible(usersExpVo.getConstellationVisible());
        usersExp.setConstellationIndexVisible(usersExpVo.getConstellationIndexVisible());
        usersExp.setNativePlace(usersExpVo.getNativePlace());
        usersExp.setNativePlaceVisible(usersExpVo.getNativePlaceVisible());
        usersExp.setNativePlaceIndexVisible(usersExpVo.getNativePlaceIndexVisible());
        usersExp.setRegionId(usersExpVo.getRegionId());
        usersExp.setAddress(usersExpVo.getAddress());
        usersExp.setAddressVisible(usersExpVo.getAddressVisible());
        usersExp.setAddressIndexVisible(usersExpVo.getAddressIndexVisible());
        usersExp.setBodilyForm(usersExpVo.getBodilyForm());
        usersExp.setBodilyFormVisible(usersExpVo.getBodilyFormVisible());
        usersExp.setBodilyFormIndexVisible(usersExpVo.getBodilyFormIndexVisible());
        usersExp.setBloodType(usersExpVo.getBloodType());
        usersExp.setBloodTypeVisible(usersExpVo.getBloodTypeVisible());
        usersExp.setBloodTypeIndexVisible(usersExpVo.getBloodTypeIndexVisible());
        usersExp.setMarriaged(usersExpVo.getMarriaged());
        usersExp.setMarriagedVisible(usersExpVo.getMarriagedVisible());
        usersExp.setMarriagedIndexVisible(usersExpVo.getMarriagedIndexVisible());
        usersExp.setPersonalStatus(usersExpVo.getPersonalStatus());
        usersExp.setPersonalStatusVisible(usersExpVo.getPersonalStatusVisible());
        usersExp.setPersonalStatusIndexVisible(usersExpVo.getPersonalStatusIndexVisible());
        usersExp.setGrade(usersExpVo.getGrade());
        usersExp.setBalance(usersExpVo.getBalance());
        usersExp.setPoints(usersExpVo.getPoints());
        usersExp.setTopicCount(usersExpVo.getTopicCount());
        usersExp.setReplyTopicCount(usersExpVo.getReplyTopicCount());
        usersExp.setFavTopicCount(usersExpVo.getFavTopicCount());
        usersExp.setPvCount(usersExpVo.getPvCount());
        usersExp.setFansCount(usersExpVo.getFansCount());
        usersExp.setFellowCount(usersExpVo.getFellowCount());
        usersExp.setAblumsCount(usersExpVo.getAblumsCount());
        usersExp.setFavouritesCount(usersExpVo.getFavouritesCount());
        usersExp.setFavoritedCount(usersExpVo.getFavoritedCount());
        usersExp.setShareCount(usersExpVo.getShareCount());
        usersExp.setProductsCount(usersExpVo.getProductsCount());
        usersExp.setPersonalDomain(usersExpVo.getPersonalDomain());
        usersExp.setLastAccessTime(usersExpVo.getLastAccessTime());
        usersExp.setLastAccessIP(usersExpVo.getLastAccessIP());
        usersExp.setLastPostTime(usersExpVo.getLastPostTime());
        usersExp.setLastLoginTime(usersExpVo.getLastLoginTime());
        usersExp.setRemark(usersExpVo.getRemark());
        usersExp.setIsUserDPI(usersExpVo.getIsUserDPI());
        usersExp.setPayAccount(usersExpVo.getPayAccount());
        usersExp.setUserCardCode(usersExpVo.getUserCardCode());
        usersExp.setUserCardType(usersExpVo.getUserCardType());
        usersExp.setSourceType(usersExpVo.getSourceType());
        usersExp.setSalesId(usersExpVo.getSalesId());
        usersExp.setBalanceCredit(usersExpVo.getBalanceCredit());
        usersExp.setRankScore(usersExpVo.getRankScore());
        usersExp.setPluteformid(usersExpVo.getPluteformid());
        usersExp.setPluteformid(usersExpVo.getPluteformid());
        return usersExp;
    }
    public static UsersExpResult usersExpToUsersExpResult(UsersExp usersExp){
        UsersExpResult usersExpResult =new UsersExpResult();
        if(usersExp==null){
            return null;
        }
        usersExpResult.setUserId(usersExp.getUserId());
        usersExpResult.setGravatar(usersExp.getGravatar());
        usersExpResult.setSingature(usersExp.getSingature());
        usersExpResult.setTelPhone(usersExp.getTelPhone());
        usersExpResult.setQq(usersExp.getQq());
        usersExpResult.setMsn(usersExp.getMsn());
        usersExpResult.setHomePage(usersExp.getHomePage());
        usersExpResult.setBirthday(usersExp.getBirthday());
        usersExpResult.setBirthdayVisible(usersExp.getBirthdayVisible());
        usersExpResult.setBirthdayIndexVisible(usersExp.getBirthdayIndexVisible());
        usersExpResult.setConstellation(usersExp.getConstellation());
        usersExpResult.setConstellationVisible(usersExp.getConstellationVisible());
        usersExpResult.setConstellationIndexVisible(usersExp.getConstellationIndexVisible());
        usersExpResult.setNativePlace(usersExp.getNativePlace());
        usersExpResult.setNativePlaceVisible(usersExp.getNativePlaceVisible());
        usersExpResult.setNativePlaceIndexVisible(usersExp.getNativePlaceIndexVisible());
        usersExpResult.setRegionId(usersExp.getRegionId());
        usersExpResult.setAddress(usersExp.getAddress());
        usersExpResult.setAddressVisible(usersExp.getAddressVisible());
        usersExpResult.setAddressIndexVisible(usersExp.getAddressIndexVisible());
        usersExpResult.setBodilyForm(usersExp.getBodilyForm());
        usersExpResult.setBodilyFormVisible(usersExp.getBodilyFormVisible());
        usersExpResult.setBodilyFormIndexVisible(usersExp.getBodilyFormIndexVisible());
        usersExpResult.setBloodType(usersExp.getBloodType());
        usersExpResult.setBloodTypeVisible(usersExp.getBloodTypeVisible());
        usersExpResult.setBloodTypeIndexVisible(usersExp.getBloodTypeIndexVisible());
        usersExpResult.setMarriaged(usersExp.getMarriaged());
        usersExpResult.setMarriagedVisible(usersExp.getMarriagedVisible());
        usersExpResult.setMarriagedIndexVisible(usersExp.getMarriagedIndexVisible());
        usersExpResult.setPersonalStatus(usersExp.getPersonalStatus());
        usersExpResult.setPersonalStatusVisible(usersExp.getPersonalStatusVisible());
        usersExpResult.setPersonalStatusIndexVisible(usersExp.getPersonalStatusIndexVisible());
        usersExpResult.setGrade(usersExp.getGrade());
        usersExpResult.setBalance(usersExp.getBalance());
        usersExpResult.setPoints(usersExp.getPoints());
        usersExpResult.setTopicCount(usersExp.getTopicCount());
        usersExpResult.setReplyTopicCount(usersExp.getReplyTopicCount());
        usersExpResult.setFavTopicCount(usersExp.getFavTopicCount());
        usersExpResult.setPvCount(usersExp.getPvCount());
        usersExpResult.setFansCount(usersExp.getFansCount());
        usersExpResult.setFellowCount(usersExp.getFellowCount());
        usersExpResult.setAblumsCount(usersExp.getAblumsCount());
        usersExpResult.setFavouritesCount(usersExp.getFavouritesCount());
        usersExpResult.setFavoritedCount(usersExp.getFavoritedCount());
        usersExpResult.setShareCount(usersExp.getShareCount());
        usersExpResult.setProductsCount(usersExp.getProductsCount());
        usersExpResult.setPersonalDomain(usersExp.getPersonalDomain());
        usersExpResult.setLastAccessTime(usersExp.getLastAccessTime());
        usersExpResult.setLastAccessIP(usersExp.getLastAccessIP());
        usersExpResult.setLastPostTime(usersExp.getLastPostTime());
        usersExpResult.setLastLoginTime(usersExp.getLastLoginTime());
        usersExpResult.setRemark(usersExp.getRemark());
        usersExpResult.setIsUserDPI(usersExp.getIsUserDPI());
        usersExpResult.setPayAccount(usersExp.getPayAccount());
        usersExpResult.setUserCardCode(usersExp.getUserCardCode());
        usersExpResult.setUserCardType(usersExp.getUserCardType());
        usersExpResult.setSourceType(usersExp.getSourceType());
        usersExpResult.setSalesId(usersExp.getSalesId());
        usersExpResult.setBalanceCredit(usersExp.getBalanceCredit());
        usersExpResult.setRankScore(usersExp.getRankScore());
        usersExpResult.setPluteformid(usersExp.getPluteformid());
        usersExpResult.setPluteformid(usersExp.getPluteformid());
        return usersExpResult;
    }
    /*
    收货地址转换
     */
    public static ShippingAddress shippingAddressVoToshippingAddress(ShippingAddressVo shippingAddressVo){
        ShippingAddress shippingAddress = new ShippingAddress();
        if(shippingAddressVo==null){
            return null;
        }
        shippingAddress.setMsRegions(shippingAddressVo.getMsRegions());
        shippingAddress.setShippingId(shippingAddressVo.getShippingId());
        shippingAddress.setRegionId(shippingAddressVo.getRegionId());
        shippingAddress.setUserId(shippingAddressVo.getUserId());
        shippingAddress.setShipName(shippingAddressVo.getShipName());
        shippingAddress.setAddress(shippingAddressVo.getAddress());
        shippingAddress.setZipcode(shippingAddressVo.getZipcode());
        shippingAddress.setEmailAddress(shippingAddressVo.getEmailAddress());
        shippingAddress.setTelPhone(shippingAddressVo.getTelPhone());
        shippingAddress.setCelPhone(shippingAddressVo.getCelPhone());
        shippingAddress.setIsDefault(shippingAddressVo.getIsDefault());
        shippingAddress.setAliases(shippingAddressVo.getAliases());
        shippingAddress.setLatitude(shippingAddressVo.getLatitude());
        shippingAddress.setLongitude(shippingAddressVo.getLongitude());
        shippingAddress.setLineId(shippingAddressVo.getLineId());
        shippingAddress.setCircleId(shippingAddressVo.getCircleId());
        shippingAddress.setDepotId(shippingAddressVo.getDepotId());
        shippingAddress.setRemark(shippingAddressVo.getRemark());
        shippingAddress.setPluteformid(shippingAddressVo.getPluteformid());
        shippingAddress.setState(shippingAddressVo.getState());
        return shippingAddress;
    }

    public static ShippingAddressResult shippingAddressToshippingAddressResult(ShippingAddress shippingAddress){
        ShippingAddressResult shippingAddressResult = new ShippingAddressResult();
        if(shippingAddress==null){
            return null;
        }
        shippingAddressResult.setMsRegions(shippingAddress.getMsRegions());
        shippingAddressResult.setShippingId(shippingAddress.getShippingId());
        shippingAddressResult.setRegionId(shippingAddress.getRegionId());
        shippingAddressResult.setUserId(shippingAddress.getUserId());
        shippingAddressResult.setShipName(shippingAddress.getShipName());
        shippingAddressResult.setAddress(shippingAddress.getAddress());
        shippingAddressResult.setZipcode(shippingAddress.getZipcode());
        shippingAddressResult.setEmailAddress(shippingAddress.getEmailAddress());
        shippingAddressResult.setTelPhone(shippingAddress.getTelPhone());
        shippingAddressResult.setCelPhone(shippingAddress.getCelPhone());
        shippingAddressResult.setIsDefault(shippingAddress.getIsDefault());
        shippingAddressResult.setAliases(shippingAddress.getAliases());
        shippingAddressResult.setLatitude(shippingAddress.getLatitude());
        shippingAddressResult.setLongitude(shippingAddress.getLongitude());
        shippingAddressResult.setLineId(shippingAddress.getLineId());
        shippingAddressResult.setCircleId(shippingAddress.getCircleId());
        shippingAddressResult.setDepotId(shippingAddress.getDepotId());
        shippingAddressResult.setRemark(shippingAddress.getRemark());
        shippingAddressResult.setPluteformid(shippingAddress.getPluteformid());
        shippingAddressResult.setState(shippingAddress.getState());
        return shippingAddressResult;
    }
    /**
     * 商户信息表转换
     */
    public static MallConfig mallConfigVoTomallConfig(MallConfigVo mallConfigVo){
        MallConfig mallConfig = new MallConfig();
        if(mallConfigVo==null){
            return null;
        }
        mallConfig.setId(mallConfigVo.getId());
        mallConfig.setPid(mallConfigVo.getPid());
        mallConfig.setParentpId(mallConfigVo.getParentpId());
        mallConfig.setSecretkey(mallConfigVo.getSecretkey());
        mallConfig.setNickname(mallConfigVo.getNickname());
        mallConfig.setLogo(mallConfigVo.getLogo());
        mallConfig.setOpenId(mallConfigVo.getOpenId());
        mallConfig.setPhone(mallConfigVo.getPhone());
        mallConfig.setAddress(mallConfigVo.getAddress());
        mallConfig.setRegionId(mallConfigVo.getRegionId());
        mallConfig.setUserId(mallConfigVo.getUserId());
        mallConfig.setStatus(mallConfigVo.getStatus());
        return mallConfig;
    }
    public  static MallConfigResult mallConfigTomallConfigResult(MallConfig mallConfig){
        MallConfigResult mallConfigResult = new MallConfigResult();
        if(mallConfig==null){
            return null;
        }
        mallConfigResult.setId(mallConfig.getId());
        mallConfigResult.setPid(mallConfig.getPid());
        mallConfigResult.setParentpId(mallConfig.getParentpId());
        mallConfigResult.setSecretkey(mallConfig.getSecretkey());
        mallConfigResult.setNickname(mallConfig.getNickname());
        mallConfigResult.setLogo(mallConfig.getLogo());
        mallConfigResult.setOpenId(mallConfig.getOpenId());
        mallConfigResult.setPhone(mallConfig.getPhone());
        mallConfigResult.setRegionId(mallConfig.getRegionId());
        mallConfigResult.setAddress(mallConfig.getAddress());
        mallConfigResult.setUserId(mallConfig.getUserId());
        mallConfigResult.setStatus(mallConfig.getStatus());
        return mallConfigResult;
    }

    /**
     * 店铺和用户中间关联表
     * @param shopUser
     * @return
     */
    public static ShopUserResult shopUserToshopUserResult(ShopUser shopUser){
        ShopUserResult shopUserResult=new ShopUserResult();
        if(shopUserResult==null){
            return  null;
        }
        shopUserResult.setPluteformid(shopUser.getPluteformid());
        shopUserResult.setCreateTime(shopUser.getCreateTime());
        shopUserResult.setShopInfoId(shopUser.getShopInfoId());
        shopUserResult.setUserId(shopUser.getUserId());
        shopUserResult.setShopUserId(shopUser.getShopUserId());
        shopUserResult.setOpenId(shopUser.getOpenId());
        return shopUserResult;
    }
    public static ShopUser shopUserVoToShopUser(ShopUserVo shopUserVo){
        ShopUser shopUser=new ShopUser();
        if(shopUser==null){
            return  null;
        }
        shopUser.setOpenId(shopUserVo.getOpenId());
        shopUser.setUserId(shopUserVo.getUserId());
        shopUser.setShopInfoId(shopUserVo.getShopInfoId());
        shopUser.setCreateTime(shopUserVo.getCreateTime());
        shopUser.setPluteformid(shopUserVo.getPluteformid());
        shopUser.setShopUserId(shopUserVo.getShopUserId());
        return shopUser;
    }

}
