package com.zm.mall.dao.business.accountsUsers;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.product.SupplierInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
//@Repository("UserListDao")
public interface UserListDao extends BaseDao<Users> {

    public List<Users> selectAll(String keyWord, String beforeTime, String afterTime, Integer activity);//查询全部用户信息/

    public List getSelectActiv(Users users);//根据activity 的值获取对应的数据

   /* public List<Users> updateBalance(String balance);*/

    public  Users getUserIdSelect(Users users);//根据Id查询 详细 信息

    public List getKeyword(Page page) ;//关键字，日期，是否激活查询

    public Users getTopUp(Users users); //充值某项

    public List getTimeSearch(Users users);//根据时间搜索

    //更改成长值 余额 美豆  微信充值
    public int updateRBP(UsersExp usersExp);

    //查询美豆明细
    public List getScoreDetail(PointsDetail pointsDetail);

    //美豆明细上面的搜索 真对Type进行操作
    public List getTypeSearch(PointsDetail pointsDetail) throws Exception;


    public int updateFreeze(Users users)throws  Exception;//冻结用户

    public int activationUser(Users users) throws Exception;//激活用户

    //计算总数
    public Integer getCount(Page page) throws Exception;

     //分页
     public List<Users> getFenYe(Page page) throws Exception;


     public List getAddress(ShippingAddress shippingAddress) throws  Exception;

     public List getUserScore(ItemSystemCode itemSystemCode) throws  Exception;//判断此用户是否存在 查询子节点的 item_system_code表

     public Integer updateScore(UserScore userScore)throws  Exception;//更该用户分数

    public Integer insertScore(UserScore userScore)throws  Exception;//增加用户分数

    public List getUserList(UserScore userScore) throws Exception;//userScore表

    public MsRegions msRegions(MsRegions msRegions)throws Exception;//根据type值取到地址

    //根据特定的code取得所对应的Id
    public SystemCode getCodeUserId(SystemCode systemCode) throws Exception;

    public List getCodeType(ItemSystemCode itemSystemCode) throws Exception;//判断类型是否增加 和删除

    public Integer delCodeType(UserScore userScore)throws Exception;


  /**
   * 获取所有的省
   * @param msRegions
   * @return
   * @throws Exception
   */
    public List getProvince(MsRegions msRegions)throws Exception;

  /**
   * 获取省份下面的所有市
   * @param msRegions
   * @return
   * @throws Exception
   */
    public List getCity(MsRegions msRegions)throws Exception;

    /**
     * 根据市获取下面的所有区域
     * @param msRegions
     * @return
     * @throws Exception
     */
    public List getArea(MsRegions msRegions)throws Exception;

    /**
     * 添加用户的收货地址
     * @param shippingAddress
     * @return
     * @throws Exception
     */
    public Integer insertShippingAddress(ShippingAddress shippingAddress)throws Exception;


     /**
      * 会员等级管理
      */
    public List getUserLevel(Users user) throws Exception;

    public UserRank userRank(UserRank userRank) throws Exception;

    public Integer getUpdateUser(UserRank userRank) throws Exception;

    public  Integer deleteUserLevel(UserRank userRank)throws Exception;

    public Integer addUserLevel(UserRank userRank) throws Exception;

    public Integer getUserAllScore(Users users)throws Exception;//用户的总分数

    /**
     * 会员卡管理
     */
    public List getUserCard(String cardCode)throws  Exception;


 //--------------------------------------------接口调用方法
    /**
     * 接口调用
     */
    public Integer insertUsers(Users users) throws Exception;

 /**
  * 账户充值 和记录添加
  * @param usersExp
  * @param balanceDetails
  * @return
  * @throws Exception
  */
    public Integer insertBalanceDetails(UsersExp usersExp,BalanceDetails balanceDetails)throws Exception;

 /**
  * 美豆充值 和记录添加
  * @param usersExp
  * @param pointsDetail
  * @return
  * @throws Exception
  */
    public Integer insertPoints(UsersExp usersExp,PointsDetail pointsDetail) throws Exception;

 /**
  * 成长值 和记录添加
  * @param usersExp
  * @param rankDetail
  * @return
  * @throws Exception
  */
 public Integer insertRankScore(UsersExp usersExp,RankDetail rankDetail) throws Exception;

 //用户等级接口添加
 public Integer insertUserLevel(UserLog userLog,UserRank userRank)throws Exception;


 /**
  * 删除用户的等级
  * @param userRank
  * @return
  * @throws Exception
  */
 public  Integer web_deleteUserLevel(UserRank userRank,UserLog userLog)throws Exception;

 /**
  * 保存修改过后的用户等级
  * @param userRank
  * @param userLog
  * @return
  * @throws Exception
  */
 public Integer web_getUpdateUserLevel(UserRank userRank,UserLog userLog) throws Exception;


 /**
  * 用户冻结和激活
  * @param users
  * @return
  * @throws Exception
  */
 public Integer web_UserFrozenOrActivation (Users users) throws Exception;

            /*前台接口对接*/
 /**
  * 修改前台用户资料
  * @param users
  * @return
  * @throws Exception
  */
 public Integer web_UpdateUserOrExp(Users users) throws Exception;

 /**
  * 修改用户密码
  * @param users
  * @return
  * @throws Exception
  */
 public Integer web_UpdateUserPwd(Users users) throws Exception;

 /**
  * 修改上传图片的路径地址
  * @param usersExp
  * @return
  * @throws Exception
  */
 public Integer web_UpdateUserGravatar(UsersExp usersExp) throws Exception;

    /**
     * 提现金额的操作
     * @return
     * @throws Exception
     */
    public Integer web_BalanceDraw(UsersExp usersExp,BalanceDetails balanceDetails,BalanceDrawRequest balanceDrawRequest)throws Exception;

    /**
     * 美豆兑换卷进行操作
     * @param shopExchangeDetail
     * @param shopCouponInfo
     * @param usersExp
     * @param pointsDetail
     * @return
     * @throws Exception
     */
    public Integer web_PointsChange(ShopExchangeDetail shopExchangeDetail,ShopCouponInfo shopCouponInfo,UsersExp usersExp,PointsDetail pointsDetail)throws Exception;

    /**
     * 前端添加收货地址 同步
     * @param shippingAddress
     * @return
     * @throws Exception
     */
    public Integer web_insertShippingAddress(ShippingAddress shippingAddress)throws Exception;


    /**
     * 更改收货地址
     * @param shippingAddress
     * @return
     * @throws Exception
     */
    public Integer web_updateShippingAddress(ShippingAddress shippingAddress)throws Exception;

    /**
     * 删除收货地址
     * @param shippingAddress
     * @return
     * @throws Exception
     */
    public Integer web_deleteShippingAddress(ShippingAddress shippingAddress)throws Exception;

    /**
     * 添加优惠券
     * @param shopCouponClass
     * @return
     * @throws Exception
     */
    public Integer web_insertCoupon(ShopCouponClass shopCouponClass)throws Exception;

    /**
     * 修改优惠券
     * @param shopCouponClass
     * @return
     * @throws Exception
     */
    public Integer web_updateCoupon(ShopCouponClass shopCouponClass)throws Exception;

    /**
     * 删除优惠券
     * @param shopCouponClass
     * @return
     * @throws Exception
     */
    public Integer web_deleteCoupon(ShopCouponClass shopCouponClass)throws Exception;

    /**
     * 更改优惠券显示顺序 根据classId修改显示顺序
     * @param list
     * @return
     * @throws Exception
     */
    public Integer web_updateCouponSequence(List list)throws Exception;



    public Integer web_deleteCouponRule(ShopCouponRule shopCouponRule)throws Exception;

    public Integer web_deleteCouponInfoHistory(ShopCouponInfo shopCouponInfo,ShopCouponHistory shopCouponHistory) throws Exception;

    public Integer web_updateCouponClassStatus(ShopCouponClass shopCouponClass)throws Exception;

    /**
     * 增发优惠券
     * @param shopCouponRule
     * @param list
     * @return
     * @throws Exception
     */
    public Integer web_insertCouponRuleOrInfo(ShopCouponRule shopCouponRule,List list)throws Exception;

    /**
     * 生成优惠券
     * @param shopCouponInfo
     * @return
     * @throws Exception
     */
    public Integer web_insertCouponInfo(List<ShopCouponInfo> shopCouponInfo,ShopCouponRule shopCouponRule)throws Exception;

    /**
     * 转移过期卷 or删除过去优惠券
     * @param shopCouponHistoryList
     * @return
     * @throws Exception
     */
    public Integer web_insertTransferCoupon(List<ShopCouponHistory> shopCouponHistoryList)throws Exception;


    /**
     * 批量删除优惠券
     * @param couponCode
     * @return
     * @throws Exception
     */
    public Integer web_delCouponInfo(String couponCode)throws Exception;


    public Integer web_updateCouponInfo(String couponCode,Integer status)throws Exception;

    /**
     * 批量输出过期优惠券
     * @param history
     * @return
     * @throws Exception
     */
    public Integer web_deleteExpiredCoupons(String history)throws Exception;

        //----------------供应商

    /**
     * 添加供应商
     * @param users
     * @param supplierInfo
     * @return
     * @throws Exception
     */
    public Integer web_InsertSupplierInfo(Users users,SupplierInfo supplierInfo)throws Exception;

    /**
     * 编辑供应商
     * @param supplierInfo
     * @return
     * @throws Exception
     */
    public Integer web_updateSupplierInfo(SupplierInfo supplierInfo,List list)throws Exception;

    /**
     * 删除供应商
     * @param supplierId
     * @return
     * @throws Exception
     */
    public Integer web_delSupplierInfo(Integer supplierId)throws Exception;
    /**
     * 注册微信端
     *
     */
    public  Integer insertUsersWX(Users users)throws  Exception;
    //查询Id
    Users selectUserWX(Users users);
    //查询微信帐号
    public Users selectUserByWeChat(Users users);
    //根据AppId  openId  查询users
    public Users selUsersByAppIdopenId(Users users);
//    查询没豆  余额
    public UsersExp selectBalanceAndPointsByUserId(UsersExp usersExp);
    //初始美豆积分表
    Integer InsertUserExp(Users users);
    //微信余额消费
    public Integer updateBalanceConsumeWX(UsersExp usersExp);
    //查询单个账户的个人信息-移动端
    public Users getAccountsUserApp(String userId);
    //开启店铺预先修改用户的状态
    public Integer updateUserType(Users users);
    //完善店铺下用户的资料
    public Integer  perfectAccountUser(Users users);
    //查询用户最大userId
    public  Long selectUserIdMix();
    //查询全部用户
    public List<Users> selAllUsers(PageableUsers users);
    public Long selAllUsersCount(PageableUsers users);
}
