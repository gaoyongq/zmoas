package com.zm.mall.client.service.business.accountsUsers;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.accounts.*;
import com.zm.mall.client.result.business.product.RegionInfoResult;
import com.zm.mall.client.vo.business.accounts.*;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@WebService

public interface UserListService {

    public List<Users> selectAll(String keyWord, String beforeTime, String afterTime, Integer activity);

    public List<AccountUserResult> getSelectActiv(AccountUserVo usersVo);

    /*public List<Users> updateBalance(String balance);*/

    //根据Id查询 详细 信息
    public AccountUserResult getUserIdSelect(AccountUserVo usersVo);

    //根据关键字查询
    public List<AccountUserResult> getKeyword(AccountUserVo usersVo);

    public AccountUserResult getTopUp(AccountUserVo usersVo);

    public List<AccountUserResult> getTimeSearch(AccountUserVo usersVo);//根据时间搜索

    public int updateRBP(UsersExpVo usersExpVo);

    public List<PointsDetailResult> getScoreDetail(PointsDetailVo pointsDetailVo);

    public List<PointsDetailResult> getTypeSearch(PointsDetailVo pointsDetailVo) throws  Exception;

    public int updateFreeze(AccountUserVo usersVo)throws  Exception;

    public int activationUser(AccountUserVo usersVo) throws Exception;

    public Page getFenYe(Page page,AccountUserVo users) throws Exception;

    public List<ShippingAddressResult> getAddress(ShippingAddressVo shippingAddressVo) throws Exception;

    public List getUserScore(ItemSystemCodeVo itemSystemCodeVo) throws  Exception;

    public Integer updateScore(UserScore userScoreVo) throws Exception;//更该用户的分数

    public Integer insertScore(UserScore userScoreVo) throws Exception;

    public List getUserList(UserScore userScoreVo) throws Exception;

    public MsRegionsResult msRegions( MsRegionsVo msRegionsVo ) throws Exception;

    public SystemCodeResult getCodeUserId(SystemCodeVo systemCodeVo) throws Exception;

    public List getCodeType(ItemSystemCode itemSystemCodeVo) throws Exception;

    public Integer delCodeType(UserScore userScoreVo) throws Exception;

    public Integer getUserAllScore(AccountUserVo usersVo) throws Exception;

    public List<ShippingAddressResult> getProvince(MsRegionsVo msRegionsVo) throws Exception;


    public List<ShippingAddressResult> getCity(MsRegionsVo msRegionsVo) throws Exception;

    public List<ShippingAddressResult> getArea(MsRegionsVo msRegionsVo) throws Exception;

    public Integer insertShippingAddress(ShippingAddressVo shippingAddressVo) throws Exception;

    /**
     * 会员等级管理
     */
    public List getUserLevel(AccountUserVo usersVo) throws Exception;

    public UserRankResult userRank(UserRankVo userRankVo) throws Exception;

    public Integer getUpdateUser(UserRankVo userRankVo) throws Exception;

    public Integer deleteUserLevel(UserRankVo userRankVo) throws Exception;

    public Integer addUserLevel(UserRankVo userRankVo) throws Exception;

    public List getUserCard(String cardCode) throws Exception;

    /**
     * 添加用户或者供应商的评分
     * @return
     */
    public Double AddScoreOnSuppOrUser(String pluteformid,Integer listSize,Integer userId,String percentage,String scoreVal,String codeId,Integer pid_01);

    /**
     * 接口方法
     * @param users
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer insertUsers(Users users) throws Exception;

    @Transactional
    public Integer insertBalanceDetails(UsersExp usersExp,BalanceDetails balanceDetails) throws Exception;

    @Transactional
    public Integer insertPoints(UsersExp usersExp,PointsDetail pointsDetail) throws Exception;

    @Transactional
    public Integer insertRankScore(UsersExp usersExp, RankDetail rankDetail) throws Exception;

    @Transactional
    public Integer insertUserLevel(UserLog userLog, UserRank userRank) throws Exception;

    @Transactional
    public Integer web_deleteUserLevel(UserRank userRank,UserLog userLog) throws Exception;

    @Transactional
    public Integer web_getUpdateUserLevel(UserRank userRank,UserLog userLog) throws Exception;

    @Transactional
    public Integer web_UpdateUserOrExp(Users users) throws Exception;

    @Transactional
    public Integer web_UpdateUserPwd(Users users) throws Exception;

    @Transactional
    public Integer web_UpdateUserGravatar(UsersExp usersExp) throws Exception;

    @Transactional
    public Integer web_UserFrozenOrActivation(Users users) throws Exception;

    @Transactional
    public Integer web_BalanceDraw(UsersExp usersExp, BalanceDetails balanceDetails, BalanceDrawRequest balanceDrawRequest) throws Exception;

    @Transactional
    public Integer web_PointsChange(ShopExchangeDetail shopExchangeDetail,ShopCouponInfo shopCouponInfo,UsersExp usersExp,PointsDetail pointsDetail)throws Exception;

    @Transactional
    public Integer web_insertShippingAddress(ShippingAddress shippingAddress)throws Exception;

    @Transactional
    public Integer web_updateShippingAddress(ShippingAddress shippingAddress) throws Exception;

    @Transactional
    public Integer web_deleteShippingAddress(ShippingAddress shippingAddress) throws Exception;

    @Transactional
    public Integer web_insertCoupon(ShopCouponClass shopCouponClass) throws Exception;

    @Transactional
    public Integer web_updateCoupon(ShopCouponClass shopCouponClass) throws Exception;

    @Transactional
    public Integer web_deleteCoupon(ShopCouponClass shopCouponClass) throws Exception;

    @Transactional
    public Integer web_updateCouponSequence(List list)throws Exception;

    @Transactional
    public Integer web_deleteCouponRule(ShopCouponRule shopCouponRule)throws Exception;

    @Transactional
    public Integer web_deleteCouponInfoHistory(ShopCouponInfo shopCouponInfo,ShopCouponHistory shopCouponHistory) throws Exception;

    @Transactional
    public Integer web_updateCouponClassStatus(ShopCouponClass shopCouponClass) throws Exception;

    @Transactional
    public Integer web_insertCouponRuleOrInfo(ShopCouponRule shopCouponRule,List list)throws Exception;

    @Transactional
    public Integer web_insertCouponInfo(List<ShopCouponInfo> infoList,ShopCouponRule shopCouponRule) throws Exception;

    @Transactional
    public Integer web_insertTransferCoupon(List<ShopCouponHistory> shopCouponHistoryList)throws Exception;

    @Transactional
    public Integer web_delCouponInfo(String couponCode) throws Exception;

    @Transactional
    public Integer web_updateCouponInfo(String couponCode, Integer status) throws Exception;

    @Transactional
    public Integer web_deleteExpiredCoupons(String history)throws Exception;

    @Transactional
    public Integer web_InsertSupplierInfo(Users users, SupplierInfo supplierInfo) throws Exception;

    @Transactional
    public Integer web_updateSupplierInfo(SupplierInfo supplierInfo,List list)throws Exception;

    @Transactional
    public Integer web_delSupplierInfo(Integer supplierId) throws Exception;

    //微信
    @Transactional
    public Users selectUserByWeChat(AccountUserVo accountUserVo);
    //微信查询收货地址
    @Transactional
    public List<ShippingAddress> selectShopAddressByUserId(AccountUserVo accountUserVo);
    //微信添加收货地址
    @Transactional
    public int addShopAddress(ShippingAddressVo shippingAddressVo);
    //微信修改地址
    @Transactional
    public int updateShippingAddress(ShippingAddressVo shippingAddressVo);
    //微信删除收货地址
    @Transactional
    public int removeShopAddress(ShippingAddressVo shippingAddressVo);
    //微信设置默认收货地址
    @Transactional
    public int updateShopAddressState(ShippingAddressVo shippingAddressVo);
    //微信取消所有默认收货地址
    @Transactional
    public int updateAllShopAddressState(ShippingAddressVo shippingAddressVo);
    //微信查询默认收货地址
    @Transactional
    public ShippingAddressResult selectAddressState(ShippingAddressVo shippingAddressVo);
    //微信根据id 查询收货地址
    @Transactional
    public ShippingAddressResult selectAddressOne(ShippingAddressVo shippingAddressVo);
    //微信根据县名称查询regionId
    public RegionInfoResult selectRegionById(RegionInfoVo regionInfoVo);
    //根据AppId 、openId 获得用户
    public AccountUserResult selUsersByAppIdAndOpenId(AccountUserVo accountUserVo);
    //交易记录查询  分页
    public List<TradingRecordResult> selectTradingRecordByUserId(Page page);
    //查询没豆  余额
    public UsersExpResult selectBalanceAndPointsByUserId(UsersExpVo usersExpVo);
    //增加余额交易记录
    public Integer insertTrading(TradingRecordVo tradingRecordVo);
    //微信的余额充值
    public Integer updateBalanceRechargeWX(UsersExpVo usersExpVo);
    //微信的余额消费
    public  Integer updateBalanceConsumeWX(UsersExpVo usersExpVo);
    //根据orderCode查询记录表
    public TradingRecord selTradingRecordByOrderCode(TradingRecordVo tradingRecordVo);
    //查询单个账户的个人信息-移动端
    public Users getAccountsUserApp(String userId);
    //开启店铺预先修改用户的状态
    public Integer updateUserType(AccountUserVo accountUserVo);
    //完善店铺下用户的资料
    public Integer  perfectAccountUser(AccountUserVo accountUserVo);
    //银联返回成功修改account_trading_record充值消费记录表successState状态
    public  Integer updateSuccessState(TradingRecordVo tradingRecordVo);
	//查询全部用户
    public EasyUIListResult<Users> selAllUser(PageableUsers users);}
